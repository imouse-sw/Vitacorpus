package org.imouse.vitacorpus.ui;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.Login;
import org.imouse.vitacorpus.funciones.login.SignUp;

@NoArgsConstructor
public class Consola extends ManejoMenus
{
    private static Consola consola;

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
        System.out.println("3. Regresar");
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
        return 3;
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
