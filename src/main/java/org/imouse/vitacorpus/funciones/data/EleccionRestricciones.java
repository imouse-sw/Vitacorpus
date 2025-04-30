package org.imouse.vitacorpus.funciones.data;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Restriccion;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.model.relaciones.UsuarioRestriccion;
import org.imouse.vitacorpus.sql.hiberimpl.RestriccionHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioRestriccionHiberImpl;
import org.imouse.vitacorpus.ui.ManejoMenus;
import org.imouse.vitacorpus.util.ReadUtil;

import java.util.List;

@NoArgsConstructor
public class EleccionRestricciones extends ManejoMenus
{
    private static EleccionRestricciones eleccionRestricciones;
    private Usuario usuarioActual;

    public static EleccionRestricciones getInstance()
    {
        if(eleccionRestricciones ==null)
        {
            eleccionRestricciones = new EleccionRestricciones();
        }
        return eleccionRestricciones;
    }

    public void current()
    {
        usuarioActual = SessionManager.getUsuarioActual();

        //Verifica si el usuario ya tiene preferencias establecidas
        List<UsuarioRestriccion> usuarioRestriccions = UsuarioRestriccionHiberImpl
                .getInstance()
                .findByUsuarioId(usuarioActual.getId());

        if(usuarioRestriccions.isEmpty())
        {
            System.out.println("> No tienes restricciones asignadas.");
        }
        else
        {
            System.out.println("> 🙅 Tus alimentos actualmente restringidos son: ");
            usuarioRestriccions.forEach((up -> {
                Restriccion restriccion = up.getRestriccion();
                System.out.println("\t-> 🍽️ #"+ restriccion.getId() + ": "+restriccion.getAlimento());
            }));
        }
    }

    public void add()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        printAll();
        System.out.print("\n> Elige el ID de alguna restricción que se adecúe a tus gustos: ");
        int opcion = ReadUtil.readInt();

        Restriccion restriccion = RestriccionHiberImpl
                .getInstance()
                .findById(opcion);

        if(restriccion !=null)
        {
            // Esto va a verificar si el usuario ya tiene la restricción que eligió asignada.
            List<UsuarioRestriccion> usuarioRestriccions = UsuarioRestriccionHiberImpl
                    .getInstance()
                    .findByUsuarioId(usuarioActual.getId());

            boolean restriccionYaAsignada = usuarioRestriccions
                    .stream()
                    .anyMatch(up -> up.getRestriccion().getId().equals(restriccion.getId()));

            if (restriccionYaAsignada)
            {
                System.out.println("> ❌ Ya tienes esta restricción asignada.");
            }
            else
            {
                UsuarioRestriccion relacion = new UsuarioRestriccion();
                relacion.setUsuario(usuarioActual);
                relacion.setRestriccion(restriccion);

                if(UsuarioRestriccionHiberImpl.getInstance().save(relacion))
                {
                    System.out.println("> ✅ Restricción añadida con éxito; se te recomendarán sustitutos de este alimento en las comidas que lo contengan.");
                }
                else
                {
                    System.out.println("> ❌ Hubo un error al guardar la restricción.");
                }
            }
        }
        else
        {
            System.out.println("> ❌ Restricción inválida.");
        }
    }

    public void remove()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        current();

        System.out.print("> Elige el ID de la preferencia que quieres eliminar: ");
        int opcion = ReadUtil.readInt();

        UsuarioRestriccion relacion = UsuarioRestriccionHiberImpl
                .getInstance()
                .findByUsuarioIdYRestriccionId(usuarioActual.getId(),opcion);

        if(UsuarioRestriccionHiberImpl.getInstance().delete(relacion))
        {
            System.out.println("> ✅ Restricción eliminada correctamente.");
        }
        else
        {
            System.out.println("> ❌ Error al eliminar la preferencia.");
        }
    }

    public void printAll()
    {
        List<Restriccion> restriccions = RestriccionHiberImpl
                .getInstance()
                .findAll();

        if(restriccions.isEmpty())
        {
            System.out.println("> No hay restricciones disponibles.");
        }
        else
        {
            System.out.println();
            restriccions.forEach(restriccion -> {
                System.out.println("\t> 🔢 ID: "+ restriccion.getId() + " | 🍽️ Alimento: "+ restriccion.getAlimento());
            });
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Manejo de tus restricciones alimenticias <-");
        System.out.println("¿Qué deseas realizar?");
        System.out.println("1. Asignar una nueva restricción");
        System.out.println("2. Eliminar una de mis restricciones");
        System.out.println("3. Ver mis restricciones");
        System.out.println("4. Volver");
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
        switch(opcion)
        {
            case 1:
                add();
                break;
            case 2:
                remove();
                break;
            case 3:
                current();
                break;
            default:
                break;
        }
    }
}
