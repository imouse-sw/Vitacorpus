package org.imouse.vitacorpus.funciones.dietas;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.*;
import org.imouse.vitacorpus.model.relaciones.ComidaRestriccion;
import org.imouse.vitacorpus.model.relaciones.UsuarioRestriccion;
import org.imouse.vitacorpus.sql.hiberimpl.ComidaRestriccionHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.DietaComidaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.DietaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioRestriccionHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.ReadUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
public class EleccionDietas implements Ejecutable
{
    private static EleccionDietas eleccionDietas;
    private Usuario usuarioActual;
    private boolean flag;

    public static EleccionDietas getInstance()
    {
        if(eleccionDietas==null)
        {
            eleccionDietas = new EleccionDietas();
        }
        return eleccionDietas;
    }

    @Override
    public void run()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        Objetivo objetivoActual = usuarioActual.getObjetivo();
        boolean flag = true;

        if(usuarioActual.getObjetivo()==null)
        {
            System.out.println("❌ No tienes un objetivo seleccionado. Por favor, accede a la gestión de objetivos.");
        }
        else
        {
            while(flag)
            {
                int idObjetivoActual = objetivoActual.getId();

                List<Dieta> dietasPosibles = DietaHiberImpl
                        .getInstance()
                        .findByObjetivoId(idObjetivoActual);

                System.out.println("-> 📋 De acuerdo a tu objetivo, puedes elegir entre estas dietas: ");
                dietasPosibles.forEach(dieta -> System.out.println("🔢 ID: " + dieta.getId() + " | " + dieta.getDieta()));

                Set<Integer> idsValidos = dietasPosibles
                        .stream()
                        .map(Dieta::getId)
                        .collect(Collectors.toSet());

                Integer seleccion;
                do
                {
                    System.out.print("-> Ingresa el ID de la dieta que más te interese: ");
                    seleccion = ReadUtil.readInt();
                    if(!idsValidos.contains(seleccion))
                    {
                        System.out.println("❌ Ese ID no se encuentra en la lista de los mostrados.");
                    }
                } while(!idsValidos.contains(seleccion));

                getComidas(seleccion);

                System.out.print("🍽️️ ¿Deseas revisar otra dieta? (s/n): ");
                String respuesta = ReadUtil.read();

                if(!respuesta.equalsIgnoreCase("S"))
                {
                    flag = false;
                }
            }
        }
    }

    private void getComidas(Integer seleccion)
    {
        List<Comida> comidas = DietaComidaHiberImpl
            .getInstance()
            .getComidasByDietaId(seleccion);

        System.out.println("-> 🍽️ Este es tu plan semanal para esta dieta:");
        comidas.forEach(comida -> {
            System.out.println("\n\t🗓️ Lunes");
            System.out.println(comida.getComidasLunes());

            System.out.println("\n\t🗓️ Martes");
            System.out.println(comida.getComidasMartes());

            System.out.println("\n\t🗓️ Miércoles");
            System.out.println(comida.getComidasMiercoles());

            System.out.println("\n\t🗓️ Jueves");
            System.out.println(comida.getComidasJueves());

            System.out.println("\n\t🗓️ Viernes");
            System.out.println(comida.getComidasViernes());

            System.out.println("\n\t🗓️ Sábado");
            System.out.println(comida.getComidasSabado());

            System.out.println("\n\t🗓️ Domingo");
            System.out.println(comida.getComidasDomingo());
        });

        printRestricciones(comidas, usuarioActual.getId());
    }

    private void printRestricciones(List<Comida> comidas, Integer idUsuario)
    {
        Set<Integer> restriccionesDelUsuario  = UsuarioRestriccionHiberImpl
                .getInstance()
                .findByUsuarioId(idUsuario)
                .stream()
                .map(ur -> ur.getRestriccion().getId())
                .collect(Collectors.toSet());

        for(Comida comida : comidas)
        {
            // Esto obtiene las restricciones de estas comidas en particular (las del lunes, las del martes, etc.)
            List<ComidaRestriccion> todasLasRestricciones = ComidaRestriccionHiberImpl
                    .getInstance()
                    .findByComidaId(comida.getId());

            // Se filtran las que estén en el set del usuario :D
            List<ComidaRestriccion> restriccionesAplicables = todasLasRestricciones
                    .stream()
                    .filter(cr -> restriccionesDelUsuario.contains(cr.getRestriccion().getId()))
                    .toList();

            if(restriccionesAplicables.isEmpty())
            {
                System.out.println("\n✅ No tienes alimentos restringidos para las comidas de esta dieta.");
            }
            else
            {
                System.out.println("\n⚠️ Una o más comidas de esta dieta contienen los siguientes alimentos restringidos por ti:");
                restriccionesAplicables.forEach(cr->{
                    Restriccion restriccion = cr.getRestriccion();
                    System.out.println(
                            "\t-> 🙅 "+restriccion.getAlimento()+" 🔃 Puedes sustituirlo por "+restriccion.getSustituto1()+" o "+restriccion.getSustituto2()
                    );
                });
            }
        }
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = true;
    }
}
