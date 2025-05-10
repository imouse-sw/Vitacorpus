package org.imouse.vitacorpus.funciones;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Ejercicio;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.model.relaciones.RutinaEjercicio;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaEjercicioHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.ReadUtil;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RutinasF implements Ejecutable
{
    private static RutinasF rutinasF;
    private boolean flag = true;

    private RutinasF() {}

    public static RutinasF getInstance() {
        if (rutinasF == null) {
            rutinasF = new RutinasF();
        }
        return rutinasF;
    }

    @Override
    public void run()
    {
        Usuario usuarioActual = SessionManager.getUsuarioActual();
        Objetivo objetivo = usuarioActual != null ? usuarioActual.getObjetivo() : null;

        if (usuarioActual == null) {
            System.out.println("> No hay ningún usuario loggeado.");
            return;
        }

        if (objetivo == null) {
            System.out.println("> El usuario no ha seleccionado ningún objetivo.");
            return;
        }

        System.out.println("=== Rutinas disponibles para tu objetivo: " + objetivo.getDescripcion() + " ===");

        boolean continuar = true;

        while (continuar)
        {
            List<Rutina> rutinas = RutinaHiberImpl.getInstance().findByObjetivoId(objetivo.getId());

            if (rutinas.isEmpty()) {
                System.out.println("No hay rutinas asociadas a este objetivo.");
                return;
            }

            // Mostrar lista de rutinas
            rutinas.forEach(rutina ->
                    System.out.println("ID: " + rutina.getId() + " | Rutina: " + rutina.getRutina()));

            Set<Integer> idsValidos = rutinas.stream().map(Rutina::getId).collect(Collectors.toSet());

            int seleccion;
            do {
                System.out.print("\nIngresa el ID de la rutina que deseas ver: ");
                seleccion = ReadUtil.readInt();

                if (!idsValidos.contains(seleccion)) {
                    System.out.println("❌ Ese ID no está en la lista. Intenta de nuevo.");
                }
            } while (!idsValidos.contains(seleccion));

            mostrarEjerciciosPorDia(seleccion);

            System.out.print("\n¿Deseas revisar otra rutina? (s/n): ");
            String respuesta = ReadUtil.read();

            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
    }

    private void mostrarEjerciciosPorDia(int rutinaId)
    {
        List<RutinaEjercicio> relaciones = RutinaEjercicioHiberImpl.getInstance().findByRutinaId(rutinaId);

        if (relaciones.isEmpty()) {
            System.out.println("Esta rutina no tiene ejercicios asignados.");
            return;
        }

        for (RutinaEjercicio re : relaciones)
        {
            Ejercicio ejercicio = re.getEjercicio();

            System.out.println("\n📆 Lunes:\n" + ejercicio.getEjercicioLunes());
            System.out.println("📆 Martes:\n" + ejercicio.getEjercicioMartes());
            System.out.println("📆 Miércoles:\n" + ejercicio.getEjercicioMiercoles());
            System.out.println("📆 Jueves:\n" + ejercicio.getEjercicioJueves());
            System.out.println("📆 Viernes:\n" + ejercicio.getEjercicioViernes());
            System.out.println("📆 Sábado:\n" + ejercicio.getEjercicioSabado());
            System.out.println("📆 Domingo:\n" + ejercicio.getEjercicioDomingo());
        }
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}