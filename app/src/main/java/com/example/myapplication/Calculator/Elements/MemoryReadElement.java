package com.example.myapplication.Calculator.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class MemoryReadElement extends Element {
    ArrayList<Class<?>> available  = new ArrayList <>(Arrays.asList(OperationElement.class,LeftBracket.class));
    public MemoryReadElement(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        Element last = source.getLast();

        if(last != null)
        {
            if(available.contains(last.getClass()) )
                return action.add;
            else
                return action.ignore;
        }
        return action.add;
    }
}
