package org.imouse.vitacorpus.inicio;

import org.imouse.vitacorpus.ui.SeleccionEjecutable;
import org.imouse.vitacorpus.ui.Ventanas.VentanaMenu;
import org.imouse.vitacorpus.ui.Ventanas.VentanaRegistroDatos;

public class Inicio
{
    public static void main(String[] args)
    {
        System.out.println("Ejecutando Vitacorpus...");
        SeleccionEjecutable.getInstance().run();
        System.out.println("¡Gracias por usar Vitacorpus!");
    }
}
