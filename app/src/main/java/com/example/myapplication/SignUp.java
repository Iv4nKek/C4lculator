package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;

import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private String redHint = "@android:color/holo_red_light";
    private String greenHint = "@android:color/holo_green_light";
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
                CheckName(s);
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
                CheckEmail(s);
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
                CheckPassword(s);
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
                CheckConfirm(s);

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
            CheckName(nameEdit.getText());
        });
        loginEdit.setOnFocusChangeListener((v, hasFocus) ->
        {
            CheckEmail(loginEdit.getText());
        });
        passwordEdit.setOnFocusChangeListener((v, hasFocus) ->
        {
            CheckPassword(passwordEdit.getText());
        });
        confirmEdit.setOnFocusChangeListener((v, hasFocus) ->
        {
            CheckConfirm(confirmEdit.getText());
        });

        return root;
    }
    private void CheckName(CharSequence s)
    {
        if(s.length()>16)
        {
            nameEdit.setText(s.subSequence(0,s.length()-1));
        }
        if(s.length()>4 && s.length()<17)
        {
            //nameReq.setTextColor(Color.parseColor(green));
            nameEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_green_light));
            nameReq.setVisibility(View.INVISIBLE);
            nameState =true;
        }
        else
        {
            nameEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_red_light));
            nameReq.setVisibility(View.VISIBLE);
            nameState = false;
            nameReq.setTextColor(Color.parseColor(red));
        }
    }
    private void CheckEmail(CharSequence s)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if(!matcher.find() || s.length() == 0)
        {
            loginReq.setVisibility(View.VISIBLE);
            loginState = false;
            loginEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_red_light));
        }
        else
        {
            loginEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_green_light));
            loginState = true;
            loginReq.setVisibility(View.INVISIBLE);
        }
    }
    Toast toast;
    public void showToast() {
        if(toast != null)
        {
            toast.cancel();
        }
        //создаём и отображаем текстовое уведомление
        toast = Toast.makeText(getContext(),
                "Пора покормить кота!",
                Toast.LENGTH_SHORT);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(getContext().LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.signup_toast, null);
        toast.setView(view);
        toast.setGravity(Gravity.TOP, 0, 1350);

        toast.show();
    }
    private void CheckPassword(CharSequence s)
    {
        boolean req1 = s.length()>4 && s.length()<16;


        updateTextColor(passwordReq1,req1);

        Pattern pattern = Pattern.compile("(?=.*[a-z])(?=.*[A-Z])");
        Matcher matcher = pattern.matcher(s);

        boolean req2 = matcher.find();
        updateTextColor(passwordReq2,req2);
        pattern = Pattern.compile("(?=.*\\d)");
        matcher = pattern.matcher(s);
        boolean req3 =matcher.find();
        updateTextColor(passwordReq3,req3);
        System.out.println(req1);
        System.out.println(req2);
        System.out.println(req3);
        updateTextColor(passwordReq3,req3);
        if(req1 && req2 && req3)
        {
            passwordState = true;
            passwordReq1.setVisibility(View.INVISIBLE);
            passwordReq2.setVisibility(View.INVISIBLE);
            passwordReq3.setVisibility(View.INVISIBLE);
            passwordEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_green_light));
        }
        else
        {
            passwordReq1.setVisibility(View.VISIBLE);
            passwordReq2.setVisibility(View.VISIBLE);
            passwordReq3.setVisibility(View.VISIBLE);
            passwordEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_red_light));
        }






        CheckConfirm(confirmEdit.getText());
    }
    private void CheckConfirm(CharSequence s)
    {
        if(passwordEdit.getText().toString().contentEquals(s))
        {
           // confirmReq.setText("пароли совпадают");
          //  confirmReq.setTextColor(Color.parseColor(green));
            confirmEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_green_light));
            confirmReq.setVisibility(View.INVISIBLE);
            confirmState =true;
        }
        else
        {

            confirmReq.setText("пароли не совпадают");
            confirmReq.setVisibility(View.VISIBLE);
            confirmReq.setTextColor(Color.parseColor(red));
            confirmState  =false;
            confirmEdit.setBackgroundTintList(getContext().getResources().getColorStateList(android.R.color.holo_red_light));
        }

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
        if(nameState && loginState && passwordState && confirmState)
        {
            confirmSignUp(login, password);
        }
        else
        {
            showToast();
            TurnOnName();
            TurnOnPassword();
            TurnOnConfirm();
            CheckName(nameEdit.getText());
            CheckEmail(loginEdit.getText());
            CheckPassword(passwordEdit.getText());
            CheckConfirm(confirmEdit.getText());
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