package org.imouse.vitacorpus.ui;

import org.imouse.vitacorpus.funciones.CalculadoraSF;
import org.imouse.vitacorpus.util.Login;
import org.imouse.vitacorpus.util.SignUp;

public class Consola extends ManejoMenus
{
    private static Consola consola;

    private Consola()
    {
    }

    public static Consola getInstance()
    {
        if(consola==null)
        {
            consola = new Consola();
        }
        return consola;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Menú principal <-");
        System.out.println("1. Registro");
        System.out.println("2. Login");
        System.out.println("3. Calculadora de sueño");
        System.out.println("4. Regresar");
        System.out.print("> Ingresa tu opción: ");
    }

    @Override
    public int valorMinMenu()
    {
        return 1;
    }

    @Override
    public int valorMaxMenu()
    {
        return 4;
    }

    @Override
    public void manejoOpcion()
    {
        Ejecutable ejecutable = null;
        switch(opcion)
        {
            case 1:
                ejecutable = SignUp.getInstance();
                break;
            case 2:
                ejecutable = Login.getInstance();
                break;
            case 3:
                ejecutable = CalculadoraSF.getInstance();
            default:
                break;
        }
        if(ejecutable!=null)
        {
            ejecutable.setFlag(true);
            ejecutable.run();
        }
    }
}
