package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class Auth extends Fragment implements View.OnClickListener {

    private String login = "1";
    private String password = "2";
    private EditText loginEdit;
    private EditText passwordEdit;
    private int RC_SIGN_IN;
    GoogleSignInClient mGoogleSignInClient;
    private MainActivity _activity;

    private final static String G_PLUS_SCOPE =
            "oauth2:https://www.googleapis.com/auth/plus.me";
    private final static String USERINFO_SCOPE =
            "https://www.googleapis.com/auth/userinfo.profile";
    private final static String EMAIL_SCOPE =
            "https://www.googleapis.com/auth/userinfo.email";
    private final static String SCOPES = G_PLUS_SCOPE + " " + USERINFO_SCOPE + " " + EMAIL_SCOPE;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
     //   ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        _activity = (MainActivity)getActivity();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        View root = inflater.inflate(R.layout.activity_auth, container, false);
        root.findViewById(R.id.sign_in_button).setOnClickListener(this);
        root.findViewById(R.id.button).setOnClickListener(this);
        root.findViewById(R.id.authButton).setOnClickListener(this);
     //   root.findViewById(R.id.authButton).setOnClickListener(this);
        if(root.findViewById(R.id.editLogin)==null)
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        loginEdit = root.findViewById(R.id.editLogin);
        passwordEdit =  root.findViewById(R.id.editPassword);
        Intent intent =  getActivity().getIntent();
        String newLogin = intent.getStringExtra("login");
        String newPassword = intent.getStringExtra("password");
        if(newLogin != null && newPassword != null)
        {
            login = newLogin;
            password = newPassword;
        }
        return root;
    }
    Toast toast;
    public void showToast() {
        if(toast != null)
        {
            toast.cancel();
        }
        //создаём и отображаем текстовое уведомление
        toast = Toast.makeText(getContext(),
                "Тёмный, мрачный коридор...",
                Toast.LENGTH_SHORT);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.auth_toast, null);
        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 250);

        toast.show();
    }

   public static void hideKeyboard(Activity activity) {
       InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
       //Find the currently focused view, so we can grab the correct window token from it.
       View view = activity.getCurrentFocus();
       //If no view currently has focus, create a new one, just so we can grab a window token from it
       if (view == null) {
           view = new View(activity);
       }
       imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
   }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                googleSignIn();
                break;
            case R.id.button:
                signIn();
                break;
            case R.id.authButton:
                OpenSignUp();
                break;

        }
    }

    private void OpenSignUp()
    {
        ((MainActivity)getActivity()).OpenSignUpActivity();
    }

    public void googleSignIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void signIn() {
       hideKeyboard(_activity);
        System.out.println(loginEdit.getText().toString());
        System.out.println(passwordEdit.getText().toString());
        if(loginEdit.getText().toString().equals(login) && passwordEdit.getText().toString().equals(password))
        {
            Toast toast = Toast.makeText(getContext(),
                    "Авторизация прошла успешно", Toast.LENGTH_SHORT);
            toast.show();
            OpenCalculator();
        }
        else
        {
            showToast();
        }
    }
    private void OpenCalculator()
    {
        _activity.OpenCalculator();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                handleSignInResult(task);
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) throws ApiException {

        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Toast toast = Toast.makeText(getContext(),
                    "Вы вошли как "+account.getDisplayName(), Toast.LENGTH_SHORT);
            toast.show();
            OpenCalculator();
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("fail");
        }
    }
}