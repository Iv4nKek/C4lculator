package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends Fragment implements View.OnClickListener {

    private EditText nameEdit;
    private EditText loginEdit;
    private EditText passwordEdit;
    private EditText confirmEdit;

    private TextView nameReq;
    private TextView loginReq;
    private TextView passwordReq1;
    private TextView passwordReq2;
    private TextView passwordReq3;
    private TextView confirmReq;

    private String green = "#72b35d";
    private String red ="#fc4026";

    private boolean available;
    private boolean nameState;
    private boolean loginState;
    private boolean passwordState;
    private boolean confirmState;
    private String regex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private MainActivity _activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View root = inflater.inflate(R.layout.activity_sign_up, container, false);
        _activity = (MainActivity)getActivity();
        nameEdit = root.findViewById(R.id.name);
        loginEdit = root.findViewById(R.id.login);
        passwordEdit = root.findViewById(R.id.password);
        confirmEdit = root.findViewById(R.id.confirm);

        nameReq  =root.findViewById(R.id.nameReq1);
        loginReq  =root.findViewById(R.id.mailReq1);
        passwordReq1  =root.findViewById(R.id.passReq1);
        passwordReq2  =root.findViewById(R.id.passReq2);
        passwordReq3  =root.findViewById(R.id.passReq3);
        confirmReq  =root.findViewById(R.id.confReq1);
        root.findViewById(R.id.next).setOnClickListener(this);
        nameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>16)
                {
                    nameEdit.setText(s.subSequence(0,s.length()-1));
                }
                if(s.length()>4 && s.length()<17)
                {
                    nameReq.setTextColor(Color.parseColor(green));
                    nameState =true;
                }
                else
                {
                    nameState = false;
                    nameReq.setTextColor(Color.parseColor(red));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        loginEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s);
                if(!matcher.find())
                {
                    loginReq.setVisibility(View.VISIBLE);
                    loginState = false;
                }
                else
                {
                    loginState = true;
                    loginReq.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateTextColor(passwordReq1,s.length()>4 && s.length()<16);

                Pattern pattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])");
                Matcher matcher = pattern.matcher(s);
                updateTextColor(passwordReq2,matcher.find());

                pattern = Pattern.compile("(?=.*\\d)");
                matcher = pattern.matcher(s);
                passwordState = matcher.find();
                updateTextColor(passwordReq3,matcher.find());

                updateTextColor(confirmReq,s.equals(passwordEdit.getText()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(passwordEdit.getText().toString().contentEquals(s))
                {
                    confirmReq.setText("пароли совпадают");
                    confirmReq.setTextColor(Color.parseColor(green));
                    confirmState =true;
                }
                else
                {
                    confirmState  =false;
                    confirmReq.setText("пароли не совпадают");
                    confirmReq.setTextColor(Color.parseColor(red));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmEdit.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_ENTER:
                            SignUp();
                            hideKeyboard(_activity);
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });

        nameEdit.setOnFocusChangeListener((v, hasFocus) ->
        {
            TurnOnName();
        });

        passwordEdit.setOnFocusChangeListener((v, hasFocus) ->
        {
            TurnOnPassword();
        });
        confirmEdit.setOnFocusChangeListener((v, hasFocus) ->
        {
            TurnOnConfirm();
        });

        return root;
    }

  /*  @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.activity_sign_up);



    }*/
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
    public void TurnOnName()
    {
        nameReq.setVisibility(View.VISIBLE);
    }

    public void TurnOnPassword()
    {
        passwordReq1.setVisibility(View.VISIBLE);
        passwordReq2.setVisibility(View.VISIBLE);
        passwordReq3.setVisibility(View.VISIBLE);
    }
    public void TurnOnConfirm()
    {
        confirmReq.setVisibility(View.VISIBLE);
    }
    private void updateTextColor(TextView textView, boolean value)
    {
        if(value)
        {
            textView.setTextColor(Color.parseColor(green));
        }
        else
        {
            textView.setTextColor(Color.parseColor(red));
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                SignUp();
                break;
        }
    }

    public void SignUp() {
        String password = passwordEdit.getText().toString();
        String login = loginEdit.getText().toString();
        System.out.println(nameState);
        System.out.println(loginState);
        System.out.println(passwordState);
        System.out.println(confirmState);
        if(nameState && loginState && passwordState && confirmState)
        {
            confirmSignUp(login, password);
        }
    }
    private void confirmSignUp(String login, String password)
    {
        ((MainActivity)getActivity()).OpenAuth();
        /*Intent intent = new Intent(getActivity(),Auth.class);
        intent.putExtra("name",name);
        intent.putExtra("login",login);
        intent.putExtra("password",password);
        startActivity(intent);*/
    }
}