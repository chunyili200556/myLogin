package com.example.chunyili.mylogin;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity{
    private EditText register_etAccount,register_etPswd,register_etPswd2;
    private Button OKbtn;
    String account,pswd,pswd2;
    Account registedAccount;
    private MySQLiteOpenHelper sqLiteOpenHelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new MySQLiteOpenHelper(this);
        }
        findView();
        okBtnClick();
    }

    private void okBtnClick() {
        OKbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = register_etAccount.getText().toString();
                pswd = register_etPswd.getText().toString();
                pswd2 = register_etPswd2.getText().toString();
                registedAccount = new Account(account,pswd);
                if(!pswd.equals(pswd2)){
                    Toast.makeText(
                            RegisterActivity.this,
                            "密碼錯誤",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if(sqLiteOpenHelper.checkExist(registedAccount)){
                        Toast.makeText(
                                RegisterActivity.this,
                                "帳號已經存在",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(
                                RegisterActivity.this,
                                "創立帳號成功",
                                Toast.LENGTH_SHORT).show();
                        sqLiteOpenHelper.insert(registedAccount);
                    }
                }
                register_etAccount.setText(null);
                register_etPswd.setText(null);
                register_etPswd2.setText(null);
            }
        });
    }

    private void findView() {
        register_etAccount = findViewById(R.id.register_etAccount);
        register_etPswd = findViewById(R.id.register_etPswd);
        register_etPswd2 = findViewById(R.id.register_etPswd2);
        OKbtn = findViewById(R.id.OKbtn);
    }
}
