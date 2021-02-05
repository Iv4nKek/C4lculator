package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class NumberElement extends Element {
    ArrayList <Class<?>> available  = new ArrayList <>(Arrays.asList(NumberElement.class, OperationElement.class,LeftBracket.class, PointElement.class));
    ArrayList<Class<?>> change  = new ArrayList <>(Arrays.asList(ErrorElement.class));
    private boolean hasDot;
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
            if(change.contains(last.getClass()))
            {
                return action.change;
            }
            else
                return action.ignore;
        }
        return action.add;
    }

    public boolean isHasDot() {
        return hasDot;
    }

    public void setHasDot(boolean hasDot) {
        this.hasDot = hasDot;
    }
}
