package com.example.cau2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SoftwareManage.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SOFTWARE = "softwarelist";


    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SOFT_ID = "soft_id";
    private static final String COLUMN_SOFT_NAME = "soft_name";
    private static final String COLUMN_VERSION = "version";
    private static final String COLUMN_PUBLISH = "publish";


    private static final String CREATE_TABLE_SOFTWARE = "CREATE TABLE " + TABLE_SOFTWARE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_SOFT_ID + " TEXT,"
            + COLUMN_SOFT_NAME + " TEXT,"
            + COLUMN_VERSION + " TEXT,"
            + COLUMN_PUBLISH + " TEXT" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SOFTWARE);


        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOFTWARE);
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_SOFT_ID, "SW001");
        values.put(COLUMN_SOFT_NAME, "Microsoft Office");
        values.put(COLUMN_VERSION, "2021");
        values.put(COLUMN_PUBLISH, "Microsoft");
        db.insert(TABLE_SOFTWARE, null, values);

        values.clear();
        values.put(COLUMN_SOFT_ID, "SW002");
        values.put(COLUMN_SOFT_NAME, "Adobe Photoshop");
        values.put(COLUMN_VERSION, "2023");
        values.put(COLUMN_PUBLISH, "Adobe");
        db.insert(TABLE_SOFTWARE, null, values);

        values.clear();
        values.put(COLUMN_SOFT_ID, "SW003");
        values.put(COLUMN_SOFT_NAME, "Visual Studio Code");
        values.put(COLUMN_VERSION, "1.85");
        values.put(COLUMN_PUBLISH, "Microsoft");
        db.insert(TABLE_SOFTWARE, null, values);
    }


    public long addSoftware(Software software) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_SOFT_ID, software.getSoftId());
        values.put(COLUMN_SOFT_NAME, software.getSoftName());
        values.put(COLUMN_VERSION, software.getVersion());
        values.put(COLUMN_PUBLISH, software.getPublish());

        long id = db.insert(TABLE_SOFTWARE, null, values);
        db.close();
        return id;
    }


    public List<Software> getAllSoftware() {
        List<Software> softwareList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SOFTWARE + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Software software = new Software();
                software.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                software.setSoftId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOFT_ID)));
                software.setSoftName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOFT_NAME)));
                software.setVersion(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VERSION)));
                software.setPublish(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISH)));

                softwareList.add(software);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return softwareList;
    }


    public Software getSoftwareBySoftId(String softId) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SOFTWARE, null, COLUMN_SOFT_ID + "=?",
                new String[] { softId }, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Software software = new Software();
            software.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
            software.setSoftId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOFT_ID)));
            software.setSoftName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SOFT_NAME)));
            software.setVersion(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_VERSION)));
            software.setPublish(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PUBLISH)));

            cursor.close();
            db.close();
            return software;
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }


    public int updateSoftware(Software software) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SOFT_ID, software.getSoftId());
        values.put(COLUMN_SOFT_NAME, software.getSoftName());
        values.put(COLUMN_VERSION, software.getVersion());
        values.put(COLUMN_PUBLISH, software.getPublish());

        int result = db.update(TABLE_SOFTWARE, values, COLUMN_SOFT_ID + " = ?",
                new String[] { software.getSoftId() });
        db.close();
        return result;
    }


    public void deleteSoftware(String softId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SOFTWARE, COLUMN_SOFT_ID + " = ?", new String[] { softId });
        db.close();
    }


    public boolean softwareExists(String softId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SOFTWARE, null, COLUMN_SOFT_ID + "=?",
                new String[] { softId }, null, null, null, null);

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }
}