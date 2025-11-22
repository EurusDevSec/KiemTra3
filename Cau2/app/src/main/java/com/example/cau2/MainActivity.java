package com.example.cau2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvSoftware;
    private SoftwareAdapter adapter;
    private DatabaseHelper dbHelper;
    private List<Software> softwareList;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        initViews();
        setupDatabase();
        loadSoftwareList();
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("software_mng_2224802010279");
        }
    }

    private void initViews() {
        lvSoftware = findViewById(R.id.lv_software);
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
    }

    private void loadSoftwareList() {
        softwareList = dbHelper.getAllSoftware();
        adapter = new SoftwareAdapter(this, softwareList);
        lvSoftware.setAdapter(adapter);

        if (softwareList.isEmpty()) {
            Toast.makeText(this, "Không có phần mềm nào trong danh sách", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_add) {
            Intent intent = new Intent(MainActivity.this, AddSoftwareActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_edit) {
            Intent intent = new Intent(MainActivity.this, EditSoftwareActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSoftwareList();
    }
}