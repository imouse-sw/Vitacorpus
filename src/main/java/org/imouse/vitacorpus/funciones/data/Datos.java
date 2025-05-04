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
    private Usuario usuarioActual;

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
        usuarioActual = SessionManager.getUsuarioActual();
        int idUsuarioActual = usuarioActual.getId();

        List<RegistroDatos> list = RegistroHiberImpl.getInstance().findAll();
        List<RegistroDatos> registrosUsuario = list.stream()
                .filter(registro -> registro.getUsuario().getId().equals(idUsuarioActual))
                .toList();

        if(registrosUsuario.isEmpty())
        {
            System.out.println("> No hay registros para este usuario.");
        }
        else
        {
            int[] i = {1};
            registrosUsuario.forEach(registro ->
            {
                System.out.println("\n\t-> 🔢 Registro #"+i[0]++);
                System.out.println("\t-> 💻 ID del registro: "+registro.getId());
                System.out.println("\t-> 📆 Fecha del registro: "+registro.getFechaActualizacion());
                System.out.println("\t-> 🎂 Edad registrada: "+registro.getEdad());
                System.out.println("\t-> 🏋️ Peso registrado: "+registro.getPeso());
                System.out.println("\t-> 📏 Altura registrada: "+registro.getEstatura());
                System.out.println("\t-> ⚖️ IMC: "+registro.getImc());
            });
        }
    }

    public void add()
    {
        RegistroDatos registroDatos = new RegistroDatos();
        usuarioActual = SessionManager.getUsuarioActual();
        double peso, estatura;
        Integer edad;

        registroDatos.setUsuario(usuarioActual);

        do
        {
            System.out.print("> Ingresa tu edad: ");
            edad = ReadUtil.readInt();

            if(edad<=0 || edad>=120)
            {
                System.out.println("❌ Introduce una edad válida.");
            }
        }while(edad<=0 || edad>=120);
        registroDatos.setEdad(ReadUtil.readInt());

        do
        {
            System.out.print("> Ingresa tu peso (kg): ");
            peso = ReadUtil.readDouble();

            if(peso>=300||peso<=20)
            {
                System.out.println("❌ Introduce un peso válido.");
            }
        }while(peso>=300||peso<=20);
        registroDatos.setPeso(peso);

        do
        {
            System.out.print("> Ingresa tu estatura (m): ");
            estatura = ReadUtil.readDouble();

            if(estatura>=2.2||estatura<=1.2)
            {
                System.out.println("❌ Introduce una estatura válida.");
            }
        } while(estatura>=2.2||estatura<=1.2);
        registroDatos.setEstatura(estatura);

        RegistroHiberImpl.getInstance().save(registroDatos);

        double imcRegistro = registroDatos.getImc();
        mostrarImc(imcRegistro);
    }

    private void mostrarImc(double imcRegistro)
    {
        System.out.println("> Tu IMC es de: "+imcRegistro);
        if(imcRegistro<18.5)
        {
            System.out.println("> Esto indica un peso por debajo de lo normal.");
        }
        else if(imcRegistro>=18.5&&imcRegistro<=24.9)
        {
            System.out.println("> Estás en un peso saludable. Sigue así! 💪");
        }
        else if(imcRegistro>=25&&imcRegistro<=29.9)
        {
            System.out.println("> Esto indica un ligero sobrepeso.");
        }
        else if(imcRegistro>=30&&imcRegistro<=34.9)
        {
            System.out.println("> Esto indica una obesidad leve.");
        }
        else if(imcRegistro>=35&&imcRegistro<=39.9)
        {
            System.out.println("> Esto indica una obesidad moderada.");
        }
        else
        {
            System.out.println("> Esto indica una obesidad mórbida. Te recomendamos enfocarte en ello! 😟");
        }
    }

    public void remove()
    {
        boolean flag2 = true;
        usuarioActual = SessionManager.getUsuarioActual();

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
            int[] i = {1};
            registrosUsuario.forEach(registro -> {
                System.out.println("\n\t-> 🔢 Registro #"+i[0]++);
                System.out.println("\t-> 💻 ID del registro: "+registro.getId());
                System.out.println("\t-> 📆 Fecha del registro: "+registro.getFechaActualizacion());
                System.out.println("\t-> 🎂 Edad registrada: "+registro.getEdad());
                System.out.println("\t-> 🏋️ Peso registrado: "+registro.getPeso());
                System.out.println("\t-> 📏 Altura registrada: "+registro.getEstatura());
                System.out.println("\t-> ⚖️ IMC: "+registro.getImc());
            });
        }

        while(flag2)
        {
            System.out.print("> Ingrese el ID del registro a eliminar: ");
            Integer idRegistro = ReadUtil.readInt();

            RegistroDatos registro = registrosUsuario.stream()
                    .filter( e -> e.getId().equals(idRegistro) )
                    .findFirst()
                    .orElse( null );

            if( registro==null )
            {
                System.out.println( "> ❌ No se encontró el registro con el ID proporcionado o el registro no pertenece a este usuario." );
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

    public void explicacionImc()
    {
        System.out.println("\n\t🧠 ¿Qué es el IMC?");
        System.out.println("El IMC (Índice de Masa Corporal) es una medida que relaciona tu peso con tu estatura.");
        System.out.println("Se calcula dividiendo tu peso en kilogramos entre tu estatura en metros cuadrados (kg/m²).");
        System.out.println("Sirve como una referencia general para saber si estás en un peso saludable," +
                "si estás por debajo o por encima de lo considerado normal.");
        System.out.println("Sin embargo, ten en cuenta que no considera tu masa muscular ni tu distribución de grasa, por lo que es meramente orientativo!");
        System.out.print("> Deseas conocer los rangos que el IMC maneja? (s/n): ");

        boolean flag = ReadUtil.read().equalsIgnoreCase("s");

        if(flag)
        {
            System.out.println("\n\t🍏 Las divisiones del IMC de acuerdo al rango son:");
            System.out.println("> Menor a 18.5: Peso bajo");
            System.out.println("> Entre 18.5 y 24.9: Peso saludable");
            System.out.println("> Entre 25 y 29.9: Sobrepeso");
            System.out.println("> Entre 30 y 34.9: Obesidad leve");
            System.out.println("> Entre 35 y 39.9: Obesidad moderada");
            System.out.println("> Mayor a 40: Obesidad mórbida");
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
        System.out.println("5. ¿Qué es el IMC?");
        System.out.println("6. Regresar");
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
                break;
            case 4:
                remove();
                break;
            case 5:
                explicacionImc();
                break;
            default:
                break;
        }
    }
}
