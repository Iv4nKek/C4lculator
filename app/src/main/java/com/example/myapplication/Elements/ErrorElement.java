package com.example.myapplication.Elements;

public class ErrorElement extends Element {

    public ErrorElement(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        return action.add;
    }
}
