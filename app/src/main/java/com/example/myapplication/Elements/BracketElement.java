package com.example.myapplication.Elements;

public abstract class BracketElement extends Element {
    public BracketElement(String text) {
        super(text);
    }


    protected int getBracketSum(ElementsContainer source)
    {
        int sum = 0;
        for (char c:source.toString().toCharArray()) {
            if(c == '(')
                sum++;
            else if(c == ')')
                sum--;
        }
        return sum;
    }

}
