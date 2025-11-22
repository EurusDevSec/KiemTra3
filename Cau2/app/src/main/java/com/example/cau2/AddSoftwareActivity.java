package com.example.cau2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddSoftwareActivity extends AppCompatActivity {

    private EditText etSoftId, etSoftName, etVersion, etPublish;
    private Button btnSave, btnCancel;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_software);

        initViews();
        setupDatabase();
        setupClickListeners();
    }

    private void initViews() {
        etSoftId = findViewById(R.id.et_soft_id);
        etSoftName = findViewById(R.id.et_soft_name);
        etVersion = findViewById(R.id.et_version);
        etPublish = findViewById(R.id.et_publish);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
    }

    private void setupClickListeners() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSoftware();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveSoftware() {
        String softId = etSoftId.getText().toString().trim();
        String softName = etSoftName.getText().toString().trim();
        String version = etVersion.getText().toString().trim();
        String publish = etPublish.getText().toString().trim();

        if (softId.isEmpty() || softName.isEmpty() || version.isEmpty() || publish.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }


        if (dbHelper.softwareExists(softId)) {
            Toast.makeText(this, "Mã phần mềm đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }

        Software software = new Software(softId, softName, version, publish);
        long result = dbHelper.addSoftware(software);

        if (result != -1) {
            Toast.makeText(this, "Thêm phần mềm thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi thêm phần mềm", Toast.LENGTH_SHORT).show();
        }
    }
}