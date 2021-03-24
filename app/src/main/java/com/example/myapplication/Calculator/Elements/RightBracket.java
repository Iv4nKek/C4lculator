package com.example.myapplication.Calculator.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class RightBracket extends BracketElement {
    ArrayList<Class<?>> available  = new ArrayList <>(Arrays.asList(NumberElement.class, RightBracket.class));
    ArrayList<Class<?>> change  = new ArrayList <>(Arrays.asList());
    public RightBracket(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        int bracketSum =source.getBracketSum();
        Element last = source.getLast();
        if(last != null  )
        {
            if(available.contains(last.getClass()) && bracketSum <0)
                return action.add;
            else if (change.contains(last.getClass()))
                return action.change;
            else
            {
                return action.ignore;
            }
        }
        return action.ignore;
    }
}
