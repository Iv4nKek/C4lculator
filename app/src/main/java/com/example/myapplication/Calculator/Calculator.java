package com.example.myapplication.Calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Calculator.Elements.Element;
import com.example.myapplication.Calculator.Elements.ElementsContainer;
import com.example.myapplication.Calculator.Elements.ErrorElement;
import com.example.myapplication.Calculator.Elements.LeftBracket;
import com.example.myapplication.Calculator.Elements.NumberElement;
import com.example.myapplication.Calculator.Elements.OperationElement;
import com.example.myapplication.Calculator.Elements.PointElement;
import com.example.myapplication.Calculator.Elements.RightBracket;
import com.example.myapplication.Calculator.Elements.TextOperationElement;
import com.example.myapplication.Calculator.Elements.action;
import com.example.myapplication.CalculatorHandler;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class Calculator extends Fragment implements View.OnClickListener {
    
    private CalculatorHandler _calculatorHandler;
    private TextView _textView;
    private ElementsContainer _container;
    private String _text ="";
    private boolean error = false;
    View _root;
    MainActivity _activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _root = inflater.inflate(R.layout.fragment_calculator, container, false);
        _activity = (MainActivity)getActivity(); 
        subscribe();
        _container = new ElementsContainer();
        _textView = _root.findViewById(R.id.calculatorView);
        _calculatorHandler = new CalculatorHandler();
        return _root;

    }
    private void subscribe()
    {  
        _root.findViewById(R.id.b0).setOnClickListener(this);
        _root.findViewById(R.id.b1).setOnClickListener(this);
        _root.findViewById(R.id.b2).setOnClickListener(this);
        _root.findViewById(R.id.b3).setOnClickListener(this);
        _root.findViewById(R.id.b4).setOnClickListener(this);
        _root.findViewById(R.id.b5).setOnClickListener(this);
        _root.findViewById(R.id.b6).setOnClickListener(this);
        _root.findViewById(R.id.b7).setOnClickListener(this);
        _root.findViewById(R.id.b8).setOnClickListener(this);
        _root.findViewById(R.id.b9).setOnClickListener(this);
        _root.findViewById(R.id.sum).setOnClickListener(this);
        _root.findViewById(R.id.sub).setOnClickListener(this);
        _root.findViewById(R.id.mul).setOnClickListener(this);
        _root.findViewById(R.id.div).setOnClickListener(this);
        _root.findViewById(R.id.cos).setOnClickListener(this);
        _root.findViewById(R.id.sin).setOnClickListener(this);
        _root.findViewById(R.id.leftBracket).setOnClickListener(this);
        _root.findViewById(R.id.rightBracket).setOnClickListener(this);
        _root.findViewById(R.id.msum).setOnClickListener(this);
        _root.findViewById(R.id.msub).setOnClickListener(this);
        _root.findViewById(R.id.mr).setOnClickListener(this);
        _root.findViewById(R.id.backspace).setOnClickListener(this);
        _root.findViewById(R.id.eval).setOnClickListener(this);
        _root.findViewById(R.id.kekW).setOnClickListener(this);
        
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                one();
                break;
            case R.id.b2:
                two();
                break;
            case R.id.b3:
                three();
                break;
            case R.id.b4:
                four();
                break;
            case R.id.b5:
                five();
                break;
            case R.id.b6:
                six();
                break;
            case R.id.b7:
                seven();
                break;
            case R.id.b8:
                eight();
                break;
            case R.id.b9:
                nine();
                break;
            case R.id.b0:
                zero();
                break;
            case R.id.eval:
                eval();
                break;
            case R.id.kekW:
                dot();
                break;
            case R.id.sum:
                sum();
                break;
            case R.id.sub:
                sub();
                break;
            case R.id.mul:
                multiply();
                break;
            case R.id.div:
                division();
                break;
            case R.id.sin:
                sin();
                break;
            case R.id.cos:
                cos();
                break;
            case R.id.leftBracket:
                leftBracket();
                break;
            case R.id.rightBracket:
                rightBracket();
                break;
            case R.id.backspace:
                backSpace();
                break;



        }
    }
    private void clear()
    {
        setText("");
    }

    private action AddElement(Element toAdd)
    {
        if(error)
        {
            error = false;
        }
        action act = _container.handleElement(toAdd);

        _textView.setText(_container.toString());
        System.out.println(_container.toString());
        System.out.println(_textView);
        return act;
    }

    private void setText(String text)
    {
        _text = text;
        _textView.setText(_text);
    }

    public void sum()
    {
        AddElement(new OperationElement("+"));
    }
    public void sub()
    {
        AddElement(new OperationElement("-"));
    }
    public void multiply()
    {
        AddElement(new OperationElement("*"));
    }
    public void division()
    {
        AddElement(new OperationElement("/"));
    }
    public void backSpace()
    {
        _container.deleteLat();
        _textView.setText(_container.toString());
    }
    public void rightBracket()
    {
        AddElement(new RightBracket(")"));
    }
    public void leftBracket()
    {
        AddElement(new LeftBracket("("));
    }
    public void cos()
    {
        if(AddElement(new TextOperationElement("cos"))!=action.ignore)
            AddElement(new LeftBracket("("));

    }
    public void sin()
    {
        if(AddElement(new TextOperationElement("sin"))!=action.ignore)
            AddElement(new LeftBracket("("));
    }
    public void mSum()
    {
        double result = _calculatorHandler.evaluate(_container.toString());
        if(!Double.isNaN(result))
        {
            _calculatorHandler.addToMemory(result);
        }
    }
    public void mSub()
    {
        double result = _calculatorHandler.evaluate(_container.toString());
        if(!Double.isNaN(result))
        {
            _calculatorHandler.addToMemory(-result);
        }
    }
    public void dot()
    {
        AddElement(new PointElement("."));
    }
    public void mR()
    {
        if(_container.getLast()==null)
        {
            AddElement(new NumberElement(String.valueOf(_calculatorHandler.getMemory())));
        }
        else if( _container.getLast().getClass()!= NumberElement.class && _container.getLast().getClass()!= PointElement.class)
            AddElement(new NumberElement(String.valueOf(_calculatorHandler.getMemory())));
    }
    public void zero()
    {
        AddElement(new NumberElement("0"));
    }
    public void one()
    {
        AddElement(new NumberElement("1"));
    }
    public void two()
    {
        AddElement(new NumberElement("2"));
    }
    public void three()
    {
        AddElement(new NumberElement("3"));
    }
    public void four()
    {
        AddElement(new NumberElement("4"));
    }
    public void five()
    {
        AddElement(new NumberElement("5"));
    }
    public void six()
    {
        AddElement(new NumberElement("6"));
    }
    public void seven()
    {
        AddElement(new NumberElement("7"));
    }
    public void eight()
    {
        AddElement(new NumberElement("8"));
    }
    public void nine()
    {
        AddElement(new NumberElement("9"));
    }
    public void eval()
    {
        int bracketSum = _container.getBracketSum();
        System.out.println(bracketSum);
        for(;bracketSum<0;bracketSum++)
        {
            AddElement(new RightBracket(")"));
            System.out.println("kek");
        }
        double result = _calculatorHandler.evaluate(_container.toString());

        _container.clear();
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
}