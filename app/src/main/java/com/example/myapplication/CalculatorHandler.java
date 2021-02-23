package com.example.myapplication;



import org.mariuszgromada.math.mxparser.*;
public class CalculatorHandler {
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
            Expression c = new Expression(text);
            output = c.calculate();
        }
        catch (Exception e)
        {
            output = Double.NaN;
        }

        return output;
    }




}
