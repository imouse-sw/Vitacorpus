package org.imouse.vitacorpus.ui;

public class MenuPrincipal extends ManejoMenus
{
    private static MenuPrincipal menuPrincipal;

    private MenuPrincipal()
    {
    }

    public static MenuPrincipal getInstance()
    {
        if(menuPrincipal==null)
        {
            menuPrincipal = new MenuPrincipal();
        }
        return menuPrincipal;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("En proceso... :P");
        System.out.println("1. Salir");
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
        return 1;
    }

    @Override
    public void manejoOpcion()
    {
    }
}
