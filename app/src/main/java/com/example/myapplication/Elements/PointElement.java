package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class PointElement extends Element {
    ArrayList<Class<?>> available  = new ArrayList <>(Arrays.asList(NumberElement.class));
    ArrayList<Class<?>> change  = new ArrayList <>(Arrays.asList());
    public PointElement(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {

        Element last = source.getLast();
        if(last != null  )
        {
            if(last.getClass() == NumberElement.class)
            {
                NumberElement element = (NumberElement)last;
                if(!source.isDotAvailble())
                {
                    return action.ignore;
                }
                else
                {
                    return action.add;
                }
            }
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
