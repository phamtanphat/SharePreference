package com.ptp.phamtanphat.sharepreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnDangnhap;
    EditText edtTk,edtMk;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDangnhap = findViewById(R.id.buttonDangnhap);
        edtTk = findViewById(R.id.edittextTaikhoan);
        edtMk = findViewById(R.id.edittextMatkhau);
        checkBox = findViewById(R.id.checkboxSave);

        sharedPreferences = getSharedPreferences("thongtinuser",MODE_PRIVATE);
        String tk = sharedPreferences.getString("taikhoan","");
        String mk = sharedPreferences.getString("matkhau","");
        boolean save = sharedPreferences.getBoolean("saved",false);

        edtTk.setText(tk);
        edtMk.setText(mk);
        checkBox.setChecked(save);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taikhoan = edtTk.getText().toString();
                String matkhau = edtMk.getText().toString();

                if (taikhoan.equals("android") && matkhau.equals("123")){
                    editor = sharedPreferences.edit();
                    if (checkBox.isChecked()){

                        editor.putString("taikhoan",taikhoan);
                        editor.putString("matkhau",matkhau);
                        editor.putBoolean("saved",true);
                        editor.commit();

                    }else {
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("saved");
                        editor.commit();
                    }
                    Toast.makeText(MainActivity.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "That bai!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
