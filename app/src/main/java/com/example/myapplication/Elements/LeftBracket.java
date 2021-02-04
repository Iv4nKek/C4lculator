package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class LeftBracket extends BracketElement {
    ArrayList<Class<?>> available  = new ArrayList <>(Arrays.asList(OperationElement.class, LeftBracket.class, TextOperationElement.class));
    ArrayList<Class<?>> change  = new ArrayList <>(Arrays.asList());
    public LeftBracket(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {

        Element last = source.getLast();
        if(last != null  )
        {
            if(available.contains(last.getClass()))
                return action.add;
            else if (change.contains(last.getClass()))
                return action.change;
            else
            {
                return action.ignore;
            }
        }
        return action.add;
    }
}
