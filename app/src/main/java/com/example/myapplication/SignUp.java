package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;

public class SignUp extends AppCompatActivity {

    private EditText nameEdit;
    private EditText loginEdit;
    private EditText passwordEdit;
    private EditText confirmEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nameEdit = findViewById(R.id.name);
        loginEdit = findViewById(R.id.login);
        passwordEdit = findViewById(R.id.password);
        confirmEdit = findViewById(R.id.confirm);
        System.out.println(nameEdit);

    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        /*nameEdit = findViewById(R.id.name);
        loginEdit = findViewById(R.id.login);
        passwordEdit = findViewById(R.id.password);
        confirmEdit = findViewById(R.id.confirm);
        System.out.println(nameEdit);*/
    }

    public void SignUp(View view) {
        String password = passwordEdit.getText().toString();
        String confirm = confirmEdit.getText().toString();
        String name = nameEdit.getText().toString();
        String login = loginEdit.getText().toString();
        if(validateName(name) && validateLogin(login) && password != "" && confirm.equals(password))
        {
            confirmSignUp(name, login, password);
        }
        else {

        }
    }
    private boolean validateName(String name)
    {
        return name != null && name != "" && name.length()>2 && name.length()<16;
    }
    private boolean validateLogin(String login)
    {
        return login != null && login != "" && login.length()>4 && login.length()<16 && login.contains("@") && login.charAt(login.length()-1)=='y'&& login.charAt(login.length()-2)=='b'&& login.charAt(login.length()-3)=='.';
    }

    private void confirmSignUp(String name, String login, String password)
    {
        Intent intent = new Intent(this,Auth.class);
        intent.putExtra("name",name);
        intent.putExtra("login",login);
        intent.putExtra("password",password);
        startActivity(intent);
    }
}