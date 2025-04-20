package org.imouse.vitacorpus.funciones.data;

import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.RegistroHiberImpl;
import org.imouse.vitacorpus.ui.ManejoMenus;
import org.imouse.vitacorpus.util.ReadUtil;

import java.util.List;


@NoArgsConstructor
public class Datos extends ManejoMenus
{
    private static Datos datos;
    Usuario usuarioActual = SessionManager.getUsuarioActual();

    public static Datos getInstance()
    {
        if(datos==null)
        {
            datos = new Datos();
        }
        return datos;
    }

    public void print( )
    {
        int idUsuarioActual = usuarioActual.getId();

        List<RegistroDatos> list = RegistroHiberImpl.getInstance().findAll();
        List<RegistroDatos> registrosUsuario = list.stream()
                .filter(registro -> registro.getUsuario().getId() == idUsuarioActual)
                .toList();

        if(registrosUsuario.isEmpty())
        {
            System.out.println("> No hay registros para este usuario.");
        }
        else
        {
            registrosUsuario.forEach(System.out::println);
        }
    }

    public void add()
    {
        RegistroDatos registroDatos = new RegistroDatos();
        double peso, estatura;

        System.out.print("> Ingresa el número de registro: ");
        registroDatos.setNumero(ReadUtil.readInt());

        registroDatos.setUsuario(usuarioActual);

        System.out.print("> Ingresa tu edad: ");
        registroDatos.setEdad(ReadUtil.readInt());

        do
        {
            System.out.print("> Ingresa tu peso (kg): ");
            peso = ReadUtil.readDouble();

            if(peso>=300||peso<=20)
            {
                System.out.println("❌ Introduce un peso válido.");
            }
        } while(peso>=300||peso<=20);

        do
        {
            System.out.print("> Ingresa tu estatura (m): ");
            estatura = ReadUtil.readDouble();

            if(estatura>=2.2||estatura<=1.2)
            {
                System.out.println("❌ Introduce una estatura válida.");
            }
        } while(estatura>=2.2||estatura<=1.2);

        RegistroHiberImpl.getInstance().save(registroDatos);
    }

    public void remove()
    {
        boolean flag2 = true;

        int idUsuarioActual = usuarioActual.getId();

        List<RegistroDatos> list = RegistroHiberImpl.getInstance().findAll();
        List<RegistroDatos> registrosUsuario = list.stream()
                .filter(e -> e.getUsuario().getId() == idUsuarioActual)
                .toList();

        if(registrosUsuario.isEmpty())
        {
            System.out.println("> No hay registros para este usuario.");
        }
        else
        {
            registrosUsuario.forEach(System.out::println);
        }

        while(flag2)
        {
            System.out.print("> Ingrese el ID del registro a eliminar: ");
            RegistroDatos registro = list.stream()
                    .filter( e -> e.getId().equals( ReadUtil.readInt( ) ) )
                    .findFirst()
                    .orElse( null );

            if( registro==null )
            {
                System.out.println( "> No se encontró el elemento." );
                System.out.print( "> Deseas volver a intentarlo? s/n: ");
                String respuesta = ReadUtil.read();

                flag2 = respuesta.equalsIgnoreCase("S");
            }
            else
            {
                if(RegistroHiberImpl.getInstance().delete(registro))
                {
                    System.out.println( "> Elemento eliminado con éxito." );
                }
                flag2 = false;
            }
        }
    }

    @Override
    public void despliegaMenu()
    {
        System.out.println("\n\t-> Manejo de tus datos personales <-");
        System.out.println("¿Qué deseas realizar?");
        System.out.println("1. Registrar mis datos");
        System.out.println("2. Ver mi historial de registros");
        System.out.println("3. Actualizar mis datos");
        System.out.println("4. Eliminar uno de mis registros");
        System.out.println("5. Regresar");
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
        return 5;
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
                print();
                break;
            case 3:
                add();
            case 4:
                remove();
            default:
                break;
        }
    }
}
