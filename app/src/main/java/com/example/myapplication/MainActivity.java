package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Elements.Element;
import com.example.myapplication.Elements.ElementsContainer;
import com.example.myapplication.Elements.ErrorElement;
import com.example.myapplication.Elements.LeftBracket;
import com.example.myapplication.Elements.NumberElement;
import com.example.myapplication.Elements.OperationElement;
import com.example.myapplication.Elements.PointElement;
import com.example.myapplication.Elements.RightBracket;
import com.example.myapplication.Elements.TextOperationElement;
import com.example.myapplication.Elements.action;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity {

    private Calculator _calculator;
    private TextView _textView;
    private ElementsContainer _container;
    private String _text ="";
    private boolean error = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _container = new ElementsContainer();
        setContentView(R.layout.activity_main);
        _textView = findViewById(R.id.textView);
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
    private action AddElement(Element toAdd)
    {
        if(error)
        {
            clear();
            error = false;
        }
        action act = _container.handleElement(toAdd);

        _textView.setText(_container.toString());
        System.out.println(_container.toString());
        return act;
    }

    private void setText(String text)
    {
        _text = text;
        _textView.setText(_text);
    }

    public void sum(View view)
    {
        AddElement(new OperationElement("+"));
    }
    public void sub(View view)
    {
        AddElement(new OperationElement("-"));
    }
    public void multiply(View view)
    {
        AddElement(new OperationElement("*"));
    }
    public void division(View view)
    {
        AddElement(new OperationElement("/"));
    }
    public void backSpace(View view)
    {
        _container.deleteLat();
        _textView.setText(_container.toString());
    }
    public void rightBracket(View view)
    {
        AddElement(new RightBracket(")"));
    }
    public void leftBracket(View view)
    {
        AddElement(new LeftBracket("("));
    }
    public void cos(View view)
    {
        if(AddElement(new TextOperationElement("cos"))!=action.ignore)
            AddElement(new LeftBracket("("));

    }
    public void sin(View view)
    {
        if(AddElement(new TextOperationElement("sin"))!=action.ignore)
            AddElement(new LeftBracket("("));
    }
    public void mSum(View view)
    {
        double result = _calculator.evaluate(_container.toString());
        if(!Double.isNaN(result))
        {
            _calculator.addToMemory(result);
        }
    }
    public void mSub(View view)
    {
        double result = _calculator.evaluate(_container.toString());
        if(!Double.isNaN(result))
        {
            _calculator.addToMemory(-result);
        }
    }
    public void dot(View view)
    {
        AddElement(new PointElement("."));
    }
    public void mR(View view)
    {
        if(_container.getLast()==null)
        {
            AddElement(new NumberElement(String.valueOf(_calculator.getMemory())));
        }
        else if( _container.getLast().getClass()!= NumberElement.class && _container.getLast().getClass()!= PointElement.class)
            AddElement(new NumberElement(String.valueOf(_calculator.getMemory())));
    }
    public void zero(View view)
    {
        AddElement(new NumberElement("0"));
    }
    public void one(View view)
    {
        AddElement(new NumberElement("1"));
    }
    public void two(View view)
    {
        AddElement(new NumberElement("2"));
    }
    public void three(View view)
    {
        AddElement(new NumberElement("3"));
    }
    public void four(View view)
    {
        AddElement(new NumberElement("4"));
    }
    public void five(View view)
    {
        AddElement(new NumberElement("5"));
    }
    public void six(View view)
    {
        AddElement(new NumberElement("6"));
    }
    public void seven(View view)
    {
        AddElement(new NumberElement("7"));
    }
    public void eight(View view)
    {
        AddElement(new NumberElement("8"));
    }
    public void nine(View view)
    {
        AddElement(new NumberElement("9"));
    }
    public void eval(View view)
    {
        int bracketSum = _container.getBracketSum();
        System.out.println(bracketSum);
        for(;bracketSum<0;bracketSum++)
        {
            AddElement(new RightBracket(")"));
            System.out.println("kek");
        }
        double result = _calculator.evaluate(_container.toString());

        _container.clear();
        if(Double.isInfinite(result))
        {
            AddElement(new ErrorElement("error"));
            error = true;
        }
        if(!Double.isNaN(result))
        {
            String toAdd = String.valueOf(result);
            for(char symbol : toAdd.toCharArray())
            {
                if(symbol == '.')
                {
                    AddElement(new PointElement((String.valueOf(symbol))));
                }
                else
                {
                    AddElement(new NumberElement((String.valueOf(symbol))));
                }

            }

        }
        else
        {
            AddElement(new ErrorElement("error"));
            error = true;
        }
    }
    public void rickAstley()
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab_channel=RickAstleyVEVO"));
        startActivity(browserIntent);
    }



}