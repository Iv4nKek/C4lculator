package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Auth extends AppCompatActivity {

    private String login = "1";
    private String password = "2";
    private EditText loginEdit;
    private EditText passwordEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        if(findViewById(R.id.editLogin)==null)
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        loginEdit = findViewById(R.id.editLogin);
        passwordEdit = findViewById(R.id.editPassword);
        Intent intent = getIntent();
        String newLogin = intent.getStringExtra("login");
        String newPassword = intent.getStringExtra("password");
        if(newLogin != null && newPassword != null)
        {
            login = newLogin;
            password = newPassword;
        }
        System.out.println(newPassword);
        System.out.println(newLogin);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);


    }


    public void signIn(View view) {
        System.out.println(loginEdit.getText().toString());
        System.out.println(passwordEdit.getText().toString());
        if(loginEdit.getText().toString().equals(login) && passwordEdit.getText().toString().equals(password))
        {
            OpenCalculator();
            System.out.println("kekW");
        }
        else
        {
            System.out.println("kekW2");
        }
    }
    private void OpenCalculator()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void OpenSignUp(View view)
    {
        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
}