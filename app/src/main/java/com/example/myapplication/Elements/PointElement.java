package com.example.myapplication.Elements;

public class PointElement extends Element {
    public PointElement(String text) {
        super(text);
    }

    @Override
    protected action GetAction(ElementsContainer source) {
        return null;
    }
}
