package com.example.myapplication;

import android.util.Log;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Vizner
{
    public static int smesh = (int)'a';//смещение алфавита относительно таблицы юникодов english
    public static int bias = 1072, letters = 33;
    public static String keyWord = "shifr";

    public static String encrypt(String text)
    {
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < text.length();i++)
        {
            char c;
            if (!(text.charAt(i) >= 0 && text.charAt(i) < 'a')&& text.charAt(i) != ' ')
            {
                if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
                {
                    int num = ((text.charAt(i) + keyWord.charAt(i % keyWord.length()) - 2 * smesh) % 26);
                    c = (char) (num + smesh);
                    ans.append(c);
                }
                else
                {
                    c = encryptRU(text.charAt(i), i);
                    ans.append(c);
                }
            }
            else
            {
                int num = text.charAt(i) + keyWord.length();
                c = (char)num;
                ans.append(c);
            }
        }
        return ans.toString();
    }

    public static String decrypt(String text)
    {
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < text.length(); i++)
        {
            char c;
            if (!(text.charAt(i) >= 0 && text.charAt(i) < 'a') && text.charAt(i) != ' ')
            {
                if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z')
                {
                    int num = ((text.charAt(i) - keyWord.charAt(i % keyWord.length()) + 26) % 26);
                    c = (char) (num + smesh);
                    ans.append(c);
                }
                else
                {
                    c = decryptRU(text.charAt(i), i);
                    ans.append(c);
                }
            }
            else
            {
                int num = text.charAt(i) - keyWord.length();
                c = (char)num;
                ans.append(c);
            }
        }
        return ans.toString();
    }

    public static char encryptRU(char bukva, int i)
    {
        char encryptRU;
        final int keyLen = keyWord.length();
        if (Character.isUpperCase(bukva) == false)
        {
            encryptRU = toLowerCase((char) (((bukva + keyWord.charAt(i % keyLen) - 2 * bias) % letters) + bias));
        }
        else
        {
            int num = bukva + keyWord.length();
            encryptRU = (char)num;
        }
        return encryptRU;
    }

    public static char decryptRU(char bukva, int i)
    {
        char decryptRU;
        final int keyLen = keyWord.length();
        if (Character.isUpperCase(bukva) == false)
        {
            bukva = toUpperCase(bukva);
            decryptRU = (char) (((bukva - keyWord.charAt(i % keyLen) + letters) % letters) + bias);
        }
        else
        {
            int num = bukva - keyWord.length();
            decryptRU = (char)num;
        }
        return decryptRU;
    }
}