package org.imouse.vitacorpus.funciones.data;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.ObjetivoHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.ManejoMenus;
import org.imouse.vitacorpus.util.ReadUtil;
import java.util.List;

@NoArgsConstructor
public class EleccionObjetivos extends ManejoMenus
{
    private static EleccionObjetivos eleccionObjetivos;
    private Usuario usuarioActual;

    public static EleccionObjetivos getInstance()
    {
        if(eleccionObjetivos==null)
        {
            eleccionObjetivos = new EleccionObjetivos();
        }
        return eleccionObjetivos;
    }

    public void print()
    {
        //Verifica si el usuario ya tiene un objetivo asignado
        if(usuarioActual.getObjetivo()!=null)
        {
            System.out.println("> Tu objetivo actual es: "+usuarioActual.getObjetivo().getDescripcion());
        }

        List<Objetivo> list = ObjetivoHiberImpl.getInstance().findAll();
        if(list.isEmpty())
        {
            System.out.println("> No hay objetivos disponibles.");
        }
        else
        {
            int[] i = {1};
            System.out.println();
            list.forEach(objetivo -> {
                System.out.println("\t-> 🔢 Objetivo #"+objetivo.getId());
                System.out.println("\t-> 🏁 Descripción: "+objetivo.getDescripcion());
            });
        }
    }

    public void add()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        print();
        System.out.print("\n> Elige el objetivo que se adecúe a tus necesidades: ");
        int opcion = ReadUtil.readInt();

        Objetivo objetivo = ObjetivoHiberImpl
                .getInstance()
                .findById(opcion);

        if(objetivo!=null)
        {
            if(usuarioActual.getObjetivo()!=null)
            {
                System.out.print("> Ya tienes un objetivo asignado. ¿Deseas actualizarlo? (s/n): ");
                String flag = ReadUtil.read();

                if(flag.equalsIgnoreCase("S"))
                {
                    usuarioActual.setObjetivo(objetivo);
                    UsuarioHiberImpl.getInstance().update(usuarioActual); //Guardar el usuario
                    System.out.println("> ✅ Objetivo actualizado.");
                }
                else
                {
                    System.out.println("> ❌ Operación cancelada.");
                }
            }
            else
            {
                usuarioActual.setObjetivo(objetivo);
                UsuarioHiberImpl.getInstance().update(usuarioActual);
                System.out.println("> ✅ Objetivo agregado.");
            }
        }
    }

    public void remove()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        if(usuarioActual.getObjetivo()!=null)
        {
            System.out.println("🧐 ¿Estás seguro de que quieres borrar tu objetivo? (s/n): ");
            String flag = ReadUtil.read();

            if(flag.equalsIgnoreCase("S"))
            {
                usuarioActual.setObjetivo(null); //Elimina el objetivo actual jiji
                UsuarioHiberImpl.getInstance().update(usuarioActual);
                System.out.println("> ✅ El objetivo ha sido eliminado con éxito.");
            }
            else
            {
                System.out.println("> ❌ Operación cancelada.");
            }
        }
        else
        {
            System.out.println("> No tienes un objetivo asignado.");
        }
    }

    public void current()
    {
        usuarioActual = SessionManager.getUsuarioActual();
        if(usuarioActual.getObjetivo()!=null)
        {
            System.out.println("> Tu objetivo actual es: "+usuarioActual.getObjetivo().getDescripcion());
        }
        else
        {
            System.out.println("> No haz seleccionado un objetivo.");
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Manejo de tus objetivos <-");
        System.out.println("¿Qué deseas realizar?");
        System.out.println("1. Elegir un objetivo");
        System.out.println("2. Eliminar mi objetivo");
        System.out.println("3. Ver mi objetivo actual");
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
