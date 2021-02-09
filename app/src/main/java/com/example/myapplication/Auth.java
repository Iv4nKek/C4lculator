package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Auth extends AppCompatActivity implements View.OnClickListener {

    private String login = "1";
    private String password = "2";
    private EditText loginEdit;
    private EditText passwordEdit;
    private int RC_SIGN_IN;
    GoogleSignInClient mGoogleSignInClient;
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
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
         mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        findViewById(R.id.sign_in_button).setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                googleSignIn();
                break;
            // ...
        }
    }
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);


    }
    public void googleSignIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        System.out.println("googleSignIn");
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            System.out.println("RC_SIGN_IN");
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            OpenCalculator();
        } catch (ApiException e) {
            System.out.println(e);
            System.out.println("fail");
        }
    }
}