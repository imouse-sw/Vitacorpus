package org.imouse.vitacorpus.funciones.data;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Preferencia;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.model.relaciones.UsuarioPreferencia;
import org.imouse.vitacorpus.sql.hiberimpl.PreferenciaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsPrefHiberImpl;
import org.imouse.vitacorpus.ui.ManejoMenus;
import org.imouse.vitacorpus.util.ReadUtil;

import java.util.List;

@NoArgsConstructor
public class EleccionPreferencias extends ManejoMenus
{
    private static EleccionPreferencias eleccionPreferencias;
    private Usuario usuarioActual;

    public static EleccionPreferencias getInstance()
    {
        if(eleccionPreferencias==null)
        {
            eleccionPreferencias = new EleccionPreferencias();
        }
        return eleccionPreferencias;
    }

    public void current()
    {
        usuarioActual = SessionManager.getUsuarioActual();

        //Verifica si el usuario ya tiene preferencias establecidas
        List<UsuarioPreferencia> usuarioPreferencias = UsPrefHiberImpl
                .getInstance()
                .findByUsuarioId(usuarioActual.getId());

        if(usuarioPreferencias.isEmpty())
        {
            System.out.println("> No tienes preferencias asignadas.");
        }
        else
        {
            System.out.println("> Tus preferencias actuales son: ");
            usuarioPreferencias.forEach(up -> {
                Preferencia preferencia = up.getPreferencia();
                System.out.println("\n\t-> 🔢 Preferencia #"+preferencia.getId());
                System.out.println("\t-> 📃 De tipo: "+preferencia.getTipo());
                System.out.println("\t-> 🌱 Descripción: "+preferencia.getDescripcion());
            });
        }
    }

    public void add()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        printAll();
        System.out.print("\n> Elige alguna preferencia que se adecúe a tus gustos: ");
        int opcion = ReadUtil.readInt();

        Preferencia preferencia = PreferenciaHiberImpl
                .getInstance()
                .findById(opcion);

        if(preferencia!=null)
        {
            UsuarioPreferencia relacion = new UsuarioPreferencia();
            relacion.setUsuario(usuarioActual);
            relacion.setPreferencia(preferencia);

            if(UsPrefHiberImpl.getInstance().save(relacion))
            {
                System.out.println("> ✅ Preferencia añadida con éxito.");
            }
            else
            {
                System.out.println("> ❌ Hubo un error al guardar la preferencia.");
            }
        }
        else
        {
            System.out.println("> ❌ Preferencia inválida.");
        }
    }

    public void remove()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        current();

        System.out.print("> Elige el ID de la preferencia que quieres eliminar: ");
        int opcion = ReadUtil.readInt();

        UsuarioPreferencia relacion = UsPrefHiberImpl
                .getInstance()
                .findByUsuarioIdYPreferenciaId(usuarioActual.getId(),opcion);

        if(UsPrefHiberImpl.getInstance().delete(relacion))
        {
            System.out.println("> ✅ Preferencia eliminada correctamente.");
        }
        else
        {
            System.out.println("> ❌ Error al eliminar la preferencia.");
        }
    }

    public void printAll()
    {
        List<Preferencia> preferencias = PreferenciaHiberImpl
                .getInstance()
                .findAll();

        if(preferencias.isEmpty())
        {
            System.out.println("> No hay preferencias disponibles.");
        }
        else
        {
            preferencias.forEach(preferencia -> {
                System.out.println("> 🖥️ ID: "+preferencia.getId());
                System.out.println("> 📃 De tipo: "+preferencia.getTipo());
                System.out.println("\t-> 🌱 Descripción: "+preferencia.getDescripcion());
            });
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Manejo de tus preferencias <-");
        System.out.println("¿Qué deseas realizar?");
        System.out.println("1. Asignar una nueva preferencia");
        System.out.println("2. Eliminar una de mis preferencias");
        System.out.println("3. Ver mis preferencias");
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
