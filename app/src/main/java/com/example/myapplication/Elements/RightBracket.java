package com.example.myapplication.Elements;

public class RightBracket extends BracketElement {
    public RightBracket(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        int bracketSum = getBracketSum(source);
        Element last = source.getLast();
        if(last != null && bracketSum<0  &&last.getClass() ==  NumberElement.class && last.getClass() == RightBracket.class )
            return action.add;
        else
            return action.ignore;
    }
}
