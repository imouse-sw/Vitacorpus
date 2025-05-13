package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Dieta;
import org.imouse.vitacorpus.model.Ejercicio;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.DietaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaEjercicioHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRutinas extends JFrame implements Ejecutable
{
    private final JFrame frame;
    private static VentanaRutinas ventanaRutinas;
    private Usuario usuarioActual;

    private VentanaRutinas()
    {
        frame = new JFrame("Vitacorpus - ¡Rutinas para todos!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(550,240);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaRutinas getInstance()
    {
        if(ventanaRutinas==null)
        {
            ventanaRutinas = new VentanaRutinas();
        }
        return ventanaRutinas;
    }

    @Override
    public void run()
    {
        SwingUtilities.invokeLater(() -> {
            usuarioActual = SessionManager.getUsuarioActual();
            frame.getContentPane().removeAll();

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titulo = new JLabel("Elige una rutina según tu objetivo:");
            titulo.setFont(new Font("Arial", Font.BOLD, 16));
            titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(titulo);
            mainPanel.add(Box.createVerticalStrut(15));

            JPanel botonesPanel = new JPanel(new GridLayout(0, 2, 10, 10));
            List<Rutina> rutinas = RutinaHiberImpl
                    .getInstance()
                    .findByObjetivoId(usuarioActual.getObjetivo().getId());

            for (Rutina rutina : rutinas)
            {
                JButton btn = new JButton(rutina.getRutina());
                btn.addActionListener(e -> {
                    frame.dispose();
                    rutinaSeleccionada(rutina.getId());
                });
                botonesPanel.add(btn);
            }

            mainPanel.add(botonesPanel);
            mainPanel.add(Box.createVerticalStrut(20));

            JButton volver = new JButton("Volver");
            volver.setAlignmentX(Component.CENTER_ALIGNMENT);
            volver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            mainPanel.add(volver);
            frame.setContentPane(mainPanel);
            frame.revalidate();
            frame.repaint();
            frame.setVisible(true);
            frame.toFront();
        });
    }


    private void rutinaSeleccionada(Integer idRutina)
    {
        usuarioActual = SessionManager.getUsuarioActual();

        JFrame frameEjercicios = new JFrame("Vitacorpus - Tu rutina");
        frameEjercicios.setSize(500, 500);
        frameEjercicios.setLocationRelativeTo(null);
        frameEjercicios.setResizable(false);
        frameEjercicios.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        Ejercicio ejercicios = RutinaEjercicioHiberImpl
                .getInstance()
                .getEjercicioByRutinaId(idRutina);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("¿Qué día deseas consultar?");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));

        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (String dia : dias)
        {
            JButton btn = new JButton(dia);
            btn.addActionListener(e -> getEjercicios(dia, ejercicios));
            gridPanel.add(btn);
        }

        JButton btnSemana = new JButton("Ver toda la semana");
        btnSemana.addActionListener(e -> getEjercicios("Semana", ejercicios));
        gridPanel.add(btnSemana);

        panel.add(gridPanel);
        panel.add(Box.createVerticalStrut(15));

        JPanel botonesAbajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnVideos = new JButton("Videos de ejercicios");
        btnVideos.addActionListener(e -> mostrarVideosEjercicios());
        botonesAbajo.add(btnVideos);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            frameEjercicios.dispose();
            run();
        });
        botonesAbajo.add(btnVolver);

        panel.add(botonesAbajo);

        // ESTO FALTABA:
        frameEjercicios.setContentPane(panel);
        frameEjercicios.setVisible(true);
        frameEjercicios.toFront();
    }



    private void getEjercicios(String dia, Ejercicio ejercicio)
    {
        usuarioActual = SessionManager.getUsuarioActual();

        JFrame framePlan = new JFrame("Plan del día");
        framePlan.setResizable(false);
        framePlan.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        framePlan.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        framePlan.setContentPane(panel);

        JLabel tituloTxt = new JLabel("");
        tituloTxt.setFont(new Font("Arial",Font.BOLD,18));
        tituloTxt.setForeground(Color.BLACK);
        tituloTxt.setHorizontalAlignment(SwingConstants.CENTER);

        String contenido = "";

        if(dia.equalsIgnoreCase("semana"))
        {
            contenido =
                    "Lunes:\n" + ejercicio.getEjercicioLunes() + "\n\n" +
                            "Martes:\n" + ejercicio.getEjercicioMartes() + "\n\n" +
                            "Miércoles:\n" + ejercicio.getEjercicioMiercoles() + "\n\n" +
                            "Jueves:\n" + ejercicio.getEjercicioJueves() + "\n\n" +
                            "Viernes:\n" + ejercicio.getEjercicioViernes() + "\n\n" +
                            "Sábado:\n" + ejercicio.getEjercicioSabado() + "\n\n" +
                            "Domingo:\n" + ejercicio.getEjercicioDomingo();
            framePlan.setSize(450,400);
            tituloTxt.setText("Estos son los ejercicios de la semana:");
        }
        else
        {
            contenido = switch (dia.toLowerCase())
            {
                case "lunes" -> ejercicio.getEjercicioLunes();
                case "martes" -> ejercicio.getEjercicioMartes();
                case "miércoles" -> ejercicio.getEjercicioMiercoles();
                case "jueves" -> ejercicio.getEjercicioJueves();
                case "viernes" -> ejercicio.getEjercicioViernes();
                case "sábado" -> ejercicio.getEjercicioSabado();
                case "domingo" -> ejercicio.getEjercicioDomingo();
                default -> contenido;
            };
            framePlan.setSize(450,180);
            tituloTxt.setText("Estos son los ejercicios del día "+dia.toLowerCase()+":");
        }

        panel.add(tituloTxt,BorderLayout.PAGE_START);

        JTextArea text = new JTextArea(contenido);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(new Font("Arial",Font.PLAIN,15));

        JScrollPane scrollPane = new JScrollPane(text);
        panel.add(scrollPane,BorderLayout.CENTER);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> framePlan.dispose());
        panel.add(btnVolver,BorderLayout.PAGE_END);

        framePlan.setVisible(true);
        framePlan.toFront();
        framePlan.requestFocus();
    }

    private void mostrarVideosEjercicios()
    {
        JFrame ventanaVideos = new JFrame("Videos de ejercicios");
        ventanaVideos.setSize(600, 400);
        ventanaVideos.setLocationRelativeTo(null);
        ventanaVideos.setResizable(false);
        ventanaVideos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Videos recomendados para tu rutina");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(titulo, BorderLayout.PAGE_START);

        JTextArea areaVideos = new JTextArea();
        areaVideos.setEditable(false);
        areaVideos.setFont(new Font("Arial", Font.PLAIN, 14));
        areaVideos.setText("""
            - ola aun no hay nada pero aqui se agregara todo jejejejejejejejejejej lol 
            - lol
            - setso
            """); // Aquí puedes luego integrar videos según el objetivo del usuario
        JScrollPane scroll = new JScrollPane(areaVideos);
        panel.add(scroll, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> ventanaVideos.dispose());
        panel.add(btnCerrar, BorderLayout.PAGE_END);

        ventanaVideos.setContentPane(panel);
        ventanaVideos.setVisible(true);
        ventanaVideos.toFront();
    }


    @Override
    public void setFlag(boolean flag)
    {

    }


}
