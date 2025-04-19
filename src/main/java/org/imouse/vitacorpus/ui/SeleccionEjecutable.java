package org.imouse.vitacorpus.ui;

public class SeleccionEjecutable extends ManejoMenus
{
    private static SeleccionEjecutable seleccionEjecutable;

    private SeleccionEjecutable()
    {
    }

    public static SeleccionEjecutable getInstance()
    {
        if(seleccionEjecutable==null)
        {
            seleccionEjecutable = new SeleccionEjecutable();
        }
        return seleccionEjecutable;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Bienvenido a Vitacorpus <-");
        System.out.println("¿Cómo deseas ingresar?");
        System.out.println("1. Consola");
        System.out.println("2. Ventana");
        System.out.println("3. Salir");
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
                ejecutable = Consola.getInstance();
                break;
            case 2:
                ejecutable = Ventana.getInstance();
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
