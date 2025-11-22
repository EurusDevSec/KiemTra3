package com.example.cau1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView tvTitle, tvKetQua;
    private Button btnSoHoanHao, btnUocSoThucSu;
    private EditText edtN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvKetQua = findViewById(R.id.tvKetQua);
        btnSoHoanHao = findViewById(R.id.btnSoHoanHao);
        btnUocSoThucSu = findViewById(R.id.btnUocSoThucSu);
        edtN = findViewById(R.id.edtN);

        btnSoHoanHao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSoHoanHao();
            }
        });

        btnUocSoThucSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleTongUocSo();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void handleSoHoanHao() {
        String input = edtN.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số N", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int n = Integer.parseInt(input);
            if (n <= 0) {
                Toast.makeText(this, "Vui lòng nhập số nguyên dương", Toast.LENGTH_SHORT).show();
                return;
            }

            int sum = sumProperDivisors(n);
            if (sum == n) {
                tvKetQua.setText(n + " là số hoàn hảo");
            } else {
                tvKetQua.setText(n + " không phải là số hoàn hảo");
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleTongUocSo() {
        String input = edtN.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số N", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int n = Integer.parseInt(input);
            if (n <= 0) {
                Toast.makeText(this, "Vui lòng nhập số nguyên dương", Toast.LENGTH_SHORT).show();
                return;
            }

            int sum = sumProperDivisors(n);
            tvKetQua.setText("Tổng các ước số thực sự: " + sum);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    private int sumProperDivisors(int n) {
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }
}