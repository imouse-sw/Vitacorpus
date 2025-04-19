package org.imouse.vitacorpus.util;

import java.util.*;

public class ReadUtil
{
    private final Scanner scanner;
    private static ReadUtil readUtil;

    private ReadUtil()
    {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public static ReadUtil getInstance()
    {
        if(readUtil==null)
        {
            readUtil = new ReadUtil();
        }
        return readUtil;
    }

    public static String read()
    {
        return getInstance().getScanner().nextLine();
    }

    public static Integer readInt()
    {
        String valor = null;
        Integer aux = null;
        boolean flag = true;

        while(flag)
        {
            valor = read();
            if(valor!=null&&!valor.isEmpty())
            {
                try
                {
                    aux = Integer.valueOf(valor);
                    if(aux!=null)
                    {
                        return aux;
                    }
                }
                catch (Exception ignored)
                {
                }
            }
            System.out.println("Dato inválido. Inténtelo de nuevo.");
        }
        return null;
    }

    public static Double readDouble()
    {
        String valor = null;
        Double aux = null;
        boolean flag = true;

        while(flag)
        {
            valor = read();
            if(valor!=null&&!valor.isEmpty())
            {
                try
                {
                    aux = Double.valueOf(valor);
                    if(aux!=null)
                    {
                        return aux;
                    }
                }
                catch (Exception ignored)
                {
                }
            }
            System.out.println("Dato inválido. Inténtelo de nuevo.");
        }
        return null;
    }

}
