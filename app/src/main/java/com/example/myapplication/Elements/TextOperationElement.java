package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class TextOperationElement extends Element {
    ArrayList <Class<?>> available  = new ArrayList <>(Arrays.asList(OperationElement.class,LeftBracket.class));
    ArrayList<Class<?>> change  = new ArrayList <>(Arrays.asList());
    public TextOperationElement(String text) {
        super(text);
    }
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
