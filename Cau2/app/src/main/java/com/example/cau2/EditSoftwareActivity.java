package com.example.cau2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditSoftwareActivity extends AppCompatActivity {

    private EditText etSearchId, etSoftId, etSoftName, etVersion, etPublish;
    private Button btnSearch, btnUpdate, btnCancel;
    private DatabaseHelper dbHelper;
    private Software currentSoftware;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_software);

        initViews();
        setupDatabase();
        setupClickListeners();
    }

    private void initViews() {
        etSearchId = findViewById(R.id.et_search_id);
        etSoftId = findViewById(R.id.et_soft_id);
        etSoftName = findViewById(R.id.et_soft_name);
        etVersion = findViewById(R.id.et_version);
        etPublish = findViewById(R.id.et_publish);
        btnSearch = findViewById(R.id.btn_search);
        btnUpdate = findViewById(R.id.btn_update);
        btnCancel = findViewById(R.id.btn_cancel);


        setEditFieldsEnabled(false);
    }

    private void setupDatabase() {
        dbHelper = new DatabaseHelper(this);
    }

    private void setupClickListeners() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchSoftware();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSoftware();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void searchSoftware() {
        String searchId = etSearchId.getText().toString().trim();

        if (searchId.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập mã phần mềm cần tìm", Toast.LENGTH_SHORT).show();
            return;
        }

        currentSoftware = dbHelper.getSoftwareBySoftId(searchId);

        if (currentSoftware != null) {

            etSoftId.setText(currentSoftware.getSoftId());
            etSoftName.setText(currentSoftware.getSoftName());
            etVersion.setText(currentSoftware.getVersion());
            etPublish.setText(currentSoftware.getPublish());

            setEditFieldsEnabled(true);
            Toast.makeText(this, "Tìm thấy phần mềm", Toast.LENGTH_SHORT).show();
        } else {
            // Not found
            clearEditFields();
            setEditFieldsEnabled(false);
            Toast.makeText(this, "Không có định danh phần mềm cần sửa trong CSDL", Toast.LENGTH_LONG).show();
        }
    }

    private void updateSoftware() {
        if (currentSoftware == null) {
            Toast.makeText(this, "Vui lòng tìm kiếm phần mềm trước", Toast.LENGTH_SHORT).show();
            return;
        }

        String softId = etSoftId.getText().toString().trim();
        String softName = etSoftName.getText().toString().trim();
        String version = etVersion.getText().toString().trim();
        String publish = etPublish.getText().toString().trim();

        if (softId.isEmpty() || softName.isEmpty() || version.isEmpty() || publish.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }


        if (!softId.equals(currentSoftware.getSoftId()) && dbHelper.softwareExists(softId)) {
            Toast.makeText(this, "Mã phần mềm mới đã tồn tại", Toast.LENGTH_SHORT).show();
            return;
        }


        Software updatedSoftware = new Software(softId, softName, version, publish);
        int result = dbHelper.updateSoftware(updatedSoftware);

        if (result > 0) {
            Toast.makeText(this, "Cập nhật phần mềm thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Lỗi khi cập nhật phần mềm", Toast.LENGTH_SHORT).show();
        }
    }

    private void setEditFieldsEnabled(boolean enabled) {
        etSoftId.setEnabled(enabled);
        etSoftName.setEnabled(enabled);
        etVersion.setEnabled(enabled);
        etPublish.setEnabled(enabled);
        btnUpdate.setEnabled(enabled);
    }

    private void clearEditFields() {
        etSoftId.setText("");
        etSoftName.setText("");
        etVersion.setText("");
        etPublish.setText("");
    }
}