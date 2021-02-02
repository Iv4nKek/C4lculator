package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberElement extends Element {


    public NumberElement(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        ArrayList <Class<?>> available  = new ArrayList <>(Arrays.asList(NumberElement.class));
        Element last = source.getLast();
        if(last != null && available.contains(last) )
            return action.add;
        else
            return action.ignore;
    }
}
