package org.imouse.vitacorpus.ui;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.CalculadoraSF;
import org.imouse.vitacorpus.funciones.RutinasF;
import org.imouse.vitacorpus.funciones.data.Datos;
import org.imouse.vitacorpus.funciones.data.EleccionObjetivos;
import org.imouse.vitacorpus.funciones.data.EleccionRestricciones;

@NoArgsConstructor
public class MenuPrincipal extends ManejoMenus
{
    private static MenuPrincipal menuPrincipal;

    public static MenuPrincipal getInstance()
    {
        if(menuPrincipal==null)
        {
            menuPrincipal = new MenuPrincipal();
        }
        return menuPrincipal;
    }

    public static void resetInstance()
    {
        menuPrincipal = null;
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Bienvenido al menú principal de Vitacorpus <-");
        System.out.println("¿Qué vamos a hacer hoy?");
        System.out.println("1. Registrar mis datos");
        System.out.println("2. Manejar mis objetivos");
        System.out.println("3. Manejar mis restricciones alimenticias");
        System.out.println("4. Calculadora de sueño");
        System.out.println("5. Rutinas de ejercicio");
        System.out.println("6. Salir");
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
        return 6;
    }

    @Override
    public void manejoOpcion()
    {
        Ejecutable ejecutable = null;
        switch (opcion)
        {
            case 1:
                ejecutable = Datos.getInstance();
                break;
            case 2:
                ejecutable = EleccionObjetivos.getInstance();
                break;
            case 3:
                ejecutable = EleccionRestricciones.getInstance();
                break;
            case 4:
                ejecutable = CalculadoraSF.getInstance();
                break;
            case 5:
                ejecutable = RutinasF.getInstance();
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
