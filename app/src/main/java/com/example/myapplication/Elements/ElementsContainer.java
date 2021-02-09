package com.example.myapplication.Elements;

import java.util.ArrayList;

public class ElementsContainer {
    ArrayList<Element> elements = new ArrayList<>();
    private boolean _dotAvailable = true;
    @Override
    public String toString() {
        String result = "";
        for (Element element: elements)
        {
            result += element.getText();
        }
        return result;
    }
    public boolean isDotAvailble()
    {

        for (int i = elements.size()-1;i>=0;i--)
        {
            Element element = elements.get(i);
            if(element.getClass() == NumberElement.class)
                continue;
            else if(element.getClass() == PointElement.class)
            {
                return false;
            }
            else
            {
                return true;
            }

        }
        return true;
    }
    public void deleteLat()
    {
        if(elements != null && elements.size()!=0)
        {
            elements.remove(elements.size()-1);
            if(elements.size()!=0) {
                Element last = elements.get(elements.size() - 1);
                if (last.getClass() == TextOperationElement.class) {
                    deleteLat();
                }
            }
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
        if(toString().length()<14*5)
        {
            elements.add(element);
        }

    }
    public int getBracketSum()
    {
        int sum = 0;
        for (char c:toString().toCharArray()) {
            if(c == '(')
                sum--;
            else if(c == ')')
                sum++;
        }
        return sum;
    }
    private void changeLastElement(Element element)
    {
        if(elements.size() == 0)
        {
            addElement(element);
        }
        elements.remove(elements.size()-1);
        addElement(element);
    }

    public boolean is_dotAvailable() {
        return _dotAvailable;
    }

    public void set_dotAvailable(boolean _dotAvailable) {
        this._dotAvailable = _dotAvailable;
    }
}
