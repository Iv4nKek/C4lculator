package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberElement extends Element {
    ArrayList <Class<?>> available  = new ArrayList <>(Arrays.asList(NumberElement.class, OperationElement.class,LeftBracket.class, PointElement.class));

    public NumberElement(String text) {
        super(text);
    }

    public void Add(String add)
    {
        text += add;
    }
    @Override
    protected action GetAction(ElementsContainer source) {

        Element last = source.getLast();

        if(last != null)
        {
            System.out.println(last.getClass());
            System.out.println(available.contains(last));
            if(available.contains(last.getClass()) )
                return action.add;
            else
                return action.ignore;
        }
        return action.add;
    }
}
