package com.example.myapplication;

import android.os.Bundle;
import com.google.android.material.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Calculator _calculator;
    private TextView _textView;
    private String _text ="";
    private boolean error = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        _textView = findViewById(R.id.textView);
       // System.out.println(_textView);
        _calculator = new Calculator();

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        _textView = findViewById(R.id.textView);
    }

    private void subscribeButtons()
    {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void clear()
    {
        setText("");
    }
    private void addText(String toAdd)
    {
        if(error)
        {
            clear();
            error = false;
        }

        _text += toAdd;
        _textView.setText(_text);
    }

    private void setText(String text)
    {
        _text = text;
        _textView.setText(_text);
    }

    public void sum(View view)
    {
        addText("+");
    }
    public void sub(View view)
    {
        addText("-");
    }
    public void multiply(View view)
    {
        addText("*");
    }
    public void division(View view)
    {
        addText("/");
    }
    public void backSpace(View view)
    {
        if(_text.length()>0)
        {
            setText(_text.substring(0, _text.length() - 1));
        }
    }
    public void rightBracket(View view)
    {
        addText(")");
    }
    public void leftBracket(View view)
    {
        addText("(");
    }
    public void cos(View view)
    {
        addText("cos(");
    }
    public void sin(View view)
    {
        addText("sin(");
    }
    public void mSum(View view)
    {
        double result = _calculator.evaluate(_text);
        if(!Double.isNaN(result))
        {
            _calculator.addToMemory(result);
        }
    }
    public void mSub(View view)
    {
        double result = _calculator.evaluate(_text);
        if(!Double.isNaN(result))
        {
            _calculator.addToMemory(-result);
        }
    }
    public void mR(View view)
    {
        addText(String.valueOf(_calculator.getMemory()));
    }
    public void zero(View view)
    {
        addText("0");
    }
    public void one(View view)
    {
        addText("1");
    }
    public void two(View view)
    {
        addText("2");
    }
    public void three(View view)
    {
        addText("3");
    }
    public void four(View view)
    {
        addText("4");
    }
    public void five(View view)
    {
        addText("5");
    }
    public void six(View view)
    {
        addText("6");
    }
    public void seven(View view)
    {
        addText("7");
    }
    public void eight(View view)
    {
        addText("8");
    }
    public void nine(View view)
    {
        addText("9");
    }
    public void eval(View view)
    {
        double result = _calculator.evaluate(_text);
        if(!Double.isNaN(result))
        {
            setText(String.valueOf(result));
        }
        else
        {
            setText("nope");
            error = true;
        }
    }



}