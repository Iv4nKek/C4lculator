package com.example.myapplication;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculator {
    private double memory;

    public double getMemory()
    {
        return memory;
    }
    public void addToMemory(double toAdd)
    {
        memory += toAdd;
    }

    /*public String evaluate(String text)
    {
        String output;
        try
        {
            Expression c = new ExpressionBuilder(text).build();
            double result = c.evaluate();
            output =  String.valueOf(result);
        }
        catch (Exception e)
        {
            output = "kekW";
        }

        return output;
    }*/
    public double evaluate(String text)
    {
        double output;
        try
        {
            Expression c = new ExpressionBuilder(text).build();
            output = c.evaluate();
        }
        catch (Exception e)
        {
            output = Double.NaN;
        }

        return output;
    }




}
