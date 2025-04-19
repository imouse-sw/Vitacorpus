package org.imouse.vitacorpus.ui;

public class Ventana implements Ejecutable {
    private static Ventana ventana;

    private Ventana()
    {
    }

    public static Ventana getInstance()
    {
        if(ventana==null)
        {
            ventana = new Ventana();
        }
        return ventana;
    }

    @Override
    public void run()
    {

    }

    @Override
    public void setFlag(boolean flag)
    {

    }
}
