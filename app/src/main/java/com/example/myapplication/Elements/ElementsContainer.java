package com.example.myapplication.Elements;

import java.util.ArrayList;

public class ElementsContainer {
    ArrayList<Element> elements = new ArrayList<>();

    @Override
    public String toString() {
        String result = "";
        for (Element element: elements)
        {
            result += element.getText();
        }
        return result;
    }
    public void deleteLat()
    {
        if(elements != null && elements.size()!=0)
        {
            elements.remove(elements.size()-1);
        }
    }
    public Element  getLast()
    {
        if(elements.size()==0)
            return null;
        return elements.get(elements.size() -1);
    }
    public void clear()
    {
        elements.clear();
    }
    public action handleElement(Element element)
    {
        action toDo = element.GetAction(this);
        System.out.println(toDo);
        if(toDo == action.add)
            addElement(element);
        else if(toDo == action.change)
            changeLastElement(element);
        return toDo;


    }
    private void addElement(Element element)
    {
        elements.add(element);
    }

    private void changeLastElement(Element element)
    {
        elements.remove(elements.size()-1);
        addElement(element);
    }

}
