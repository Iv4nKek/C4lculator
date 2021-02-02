package com.example.myapplication.Elements;

public class LeftBracket extends BracketElement {
    public LeftBracket(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        int bracketSum = getBracketSum(source);
        Element last = source.getLast();
        if(last != null  &&last.getClass() ==  NumberElement.class && last.getClass() == LeftBracket.class )
            return action.add;
        else
            return action.ignore;

    }
}
