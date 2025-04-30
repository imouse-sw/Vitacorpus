package org.imouse.vitacorpus.funciones;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.model.relaciones.RutinaEjercicio;
import org.imouse.vitacorpus.model.relaciones.UsuarioRestriccion;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaEjercicioHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioRestriccionHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.ReadUtil;

import java.lang.reflect.Method;
import java.util.List;

public class RutinasF implements Ejecutable
{
    private boolean flag = true;
    private static RutinasF rutinasF;

    private RutinasF() {
    }

    public static RutinasF getInstance() {
        if (rutinasF == null) {
            rutinasF = new RutinasF();
        }
        return rutinasF;
    }
    @Override
    public void run()
    {
        System.out.println("=== Rutinas ===");

        Objetivo objetivo = getObjetivoUsuarioActual();

        if (objetivo != null) {
            mostrarRutinasPorDia(objetivo);
        }

        System.out.println("\nPresiona ENTER para volver al menú...");
        ReadUtil.read();
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
    public Objetivo getObjetivoUsuarioActual() {
        Usuario usuario = SessionManager.getUsuarioActual();

        if (usuario == null) {
            System.out.println("> No hay ningún usuario loggeado.");
            return null;
        }

        Objetivo objetivo = usuario.getObjetivo();

        if (objetivo == null) {
            System.out.println("> El usuario no ha seleccionado ningún objetivo.");
        } else {
            System.out.println("> Objetivo actual del usuario: " + objetivo.getDescripcion());
        }

        return objetivo;
    }
    public void mostrarRutinasPorDia(Objetivo objetivo) {
        List<Rutina> rutinas = RutinaHiberImpl.getInstance().findByObjetivo(objetivo);

        if (rutinas.isEmpty()) {
            System.out.println("No hay rutinas para este objetivo.");
            return;
        }

        List<String> dias = List.of("Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo");

        System.out.println("¿Qué día deseas ver? Elige uno de estos:");
        dias.forEach(System.out::println);

        String diaSeleccionado = ReadUtil.read().trim();

        if (!dias.contains(diaSeleccionado)) {
            System.out.println("Día no válido.");
            return;
        }

        for (Rutina rutina : rutinas) {
            System.out.println("\nRutina: " + rutina.getDescripcion());

            List<RutinaEjercicio> ejercicios = RutinaEjercicioHiberImpl.getInstance().findByRutinaId(rutina.getId());

            for (RutinaEjercicio rutinaEjercicio : ejercicios) {
                Object ejercicio = rutinaEjercicio.getEjercicio();

                try {
                    Method metodo = ejercicio.getClass().getMethod("getEjercicio" + diaSeleccionado);
                    String resultado = (String) metodo.invoke(ejercicio);
                    System.out.println("- " + resultado);
                } catch (Exception e) {
                    System.out.println("Error accediendo al ejercicio del día " + diaSeleccionado);
                }
            }
        }
    }


}
