package com.example.myapplication.Elements;

import java.util.ArrayList;
import java.util.Arrays;

public class OperationElement extends Element {
//done
    ArrayList<Class<?>> available  = new ArrayList <>(Arrays.asList(NumberElement.class, RightBracket.class));
    ArrayList<Class<?>> change  = new ArrayList <>(Arrays.asList(OperationElement.class));
    public OperationElement(String text) {
        super(text);
    }
    protected action GetAction(ElementsContainer source) {

        Element last = source.getLast();
        if(last != null  )
        {
            if(source.getLast().getClass() == OperationElement.class && source.elements.size()==1)
                return action.ignore;
            if(available.contains(last.getClass()))
            {
                source.set_dotAvailable(true);
                return action.add;
            }

            else if (change.contains(last.getClass()))
                return action.change;
            else
            {
                return action.ignore;
            }
        }
        else if( text == "-")
        {
            return action.add;
        }
        return action.ignore;
    }
}
