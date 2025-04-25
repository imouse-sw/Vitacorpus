package org.imouse.vitacorpus.funciones;

import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.ReadUtil;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraSF implements Ejecutable {
    private boolean flag = true;

    private static CalculadoraSF calculadoraSF;

    private CalculadoraSF() {
    }

    public static CalculadoraSF getInstance() {
        if (calculadoraSF == null) {
            calculadoraSF = new CalculadoraSF();
        }
        return calculadoraSF;
    }

    @Override
    public void run() {
        while (flag) {
            System.out.println("¿Qué deseas calcular?");
            System.out.println("1. ¿A qué hora debo dormir si quiero despertar a cierta hora?");
            System.out.println("2. ¿A qué hora puedo despertar si me duermo a cierta hora?");
            System.out.println("3. ¿Qué son los ciclos de sueño?");
            System.out.println("Elige una opción (1, 2 o 3)");
            System.out.print("Su opción > ");

            String opcion = ReadUtil.read();

            switch (opcion) {
                case "1" -> calcularHorasDormir();
                case "2" -> calcularHorasDespertar();
                case "3" -> queEs();
                default -> System.out.println("Opción inválida. Intenta de nuevo.");
            }

            System.out.print("¿Deseas hacer otra operación? (s/n): ");
            String continuar = ReadUtil.read();
            if (!continuar.equalsIgnoreCase("s")) {
                flag = false;
            }
        }
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    private void calcularHorasDormir() {
        System.out.print("Ingresa la hora a la que necesitas despertarte (formato 24hrs, HH:mm): ");
        String hora = ReadUtil.read();

        try {
            LocalTime despertar = LocalTime.parse(hora);
            List<Time> horas = calcularHorasParaDormir(Time.valueOf(despertar));
            System.out.println("Podrías dormirte a:");
            for (int i = 0; i < horas.size(); i++) {
                System.out.println(" - " + horas.get(i) + " (" + (8 - i) + " ciclos)");
            }
        } catch (Exception e) {
            System.out.println("Formato inválido. Usa HH:mm, por ejemplo 06:30, formato 24hrs");
        }
    }

    private void calcularHorasDespertar() {
        System.out.print("Ingresa la hora a la que piensas dormir (formato 24hrs, HH:mm): ");
        String hora = ReadUtil.read();

        try {
            LocalTime dormir = LocalTime.parse(hora);
            List<Time> horas = calcularHorasParaDespertar(Time.valueOf(dormir));
            System.out.println("Podrías despertarte a:");
            for (int i = 0; i < horas.size(); i++) {
                System.out.println(" - " + horas.get(i) + " (" + (i + 3) + " ciclos)");
            }
        } catch (Exception e) {
            System.out.println("Formato inválido. Usa HH:mm, por ejemplo 22:45, formato 24hrs");
        }
    }

    private List<Time> calcularHorasParaDormir(Time horaDespertar) {
        List<Time> posiblesHoras = new ArrayList<>();
        LocalTime despertar = horaDespertar.toLocalTime();

        for (int ciclos = 8; ciclos >= 3; ciclos--) {
            LocalTime dormir = despertar.minusMinutes(ciclos * 90L);
            posiblesHoras.add(Time.valueOf(dormir));
        }

        return posiblesHoras;
    }

    private List<Time> calcularHorasParaDespertar(Time horaDormir) {
        List<Time> posiblesHoras = new ArrayList<>();
        LocalTime dormir = horaDormir.toLocalTime();

        for (int ciclos = 3; ciclos <= 8; ciclos++) {
            LocalTime despertar = dormir.plusMinutes(ciclos * 90L);
            posiblesHoras.add(Time.valueOf(despertar));
        }

        return posiblesHoras;
    }

    private void queEs() {
        System.out.println("El sueño se divide en varias fases que forman un ciclo completo de aproximadamente 90 minutos.");
        System.out.println("Cada ciclo de sueño incluye las siguientes fases:");
        System.out.println("1. **Sueño ligero**: Es la primera fase del sueño donde nuestro cuerpo comienza a relajarse.");
        System.out.println("2. **Sueño profundo**: En esta fase, el cuerpo se recupera físicamente y es cuando ocurren la mayoría de los procesos de restauración.");
        System.out.println("3. **Sueño REM (Movimiento Rápido de los Ojos)**: En esta fase, es cuando soñamos más vívidamente. También se cree que es importante para la memoria y el procesamiento emocional.");

        System.out.println("\nUn ciclo de sueño completo se repite varias veces durante la noche. En un ciclo completo, el sueño REM se va alargando conforme avanzan los ciclos.");
        System.out.println("Al principio de la noche, el sueño REM es breve, pero hacia el final, los ciclos REM se vuelven más largos, lo que es crucial para un descanso completo.");

        System.out.println("\nEl cuerpo humano generalmente necesita pasar por varios ciclos de sueño para sentirse realmente descansado.");
        System.out.println("Estos ciclos de sueño son importantes porque nuestro cuerpo se recupera y regenera en cada uno, y es preferible despertar al final de un ciclo completo.");
        System.out.println("Cuando interrumpimos un ciclo de sueño (por ejemplo, despertarnos en medio de un ciclo REM o profundo), podemos sentirnos más cansados y aturdidos.");

        System.out.println("\nLos ciclos de sueño son aproximadamente los siguientes en duración:");
        System.out.println(" - 3 ciclos = 4.5 horas");
        System.out.println(" - 4 ciclos = 6 horas");
        System.out.println(" - 5 ciclos = 7.5 horas");
        System.out.println(" - 6 ciclos = 9 horas");

        System.out.println("\nPor eso, para aprovechar al máximo el descanso, es ideal que los períodos de sueño sean múltiplos de 90 minutos (un ciclo completo).");
        System.out.println("De esta forma, te despiertas al final de un ciclo, lo que te hace sentir más descansado y renovado.");
    }


}
