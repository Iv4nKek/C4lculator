package com.example.myapplication.Elements;

public abstract class Element {
    private String text;

    public Element(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    protected abstract action GetAction(ElementsContainer source);
    protected void Add(String toAdd)
    {}
}
