package com.example.chunyili.mylogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private Button btnLogin,btnRegister;
    private EditText etAccount,etPswd;
    private TextView textView;
    String account,pswd;
    private Account loginAccount;
    private MySQLiteOpenHelper mySQLiteOpenHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        if (mySQLiteOpenHelper == null) {
            mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        }
        btnLoginClick();
    }


    private void btnLoginClick() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account = etAccount.getText().toString();
                pswd = etPswd.getText().toString();
                loginAccount = new Account(account,pswd);
                Boolean bool = mySQLiteOpenHelper.checkExist(loginAccount);
                if(bool){
                    Toast.makeText(MainActivity.this,"登入成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"帳號密碼錯誤",Toast.LENGTH_SHORT).show();
                }
                etAccount.setText(null);
                etPswd.setText(null);
            }
        });
    }

    private void findView() {
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        etAccount = (EditText)findViewById(R.id.etAccount);
        etPswd = (EditText)findViewById(R.id.etPswd);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void registerClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
