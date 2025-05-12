package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Comida;
import org.imouse.vitacorpus.model.Dieta;
import org.imouse.vitacorpus.model.Restriccion;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.model.relaciones.ComidaRestriccion;
import org.imouse.vitacorpus.sql.hiberimpl.*;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VentanaDietas extends JFrame implements Ejecutable
{
    private final JFrame frame;
    private static VentanaDietas ventanaDietas;
    private Usuario usuarioActual;

    private VentanaDietas()
    {
        frame = new JFrame("Vitacorpus - ¡Dietas para todos!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(550,240);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaDietas getInstance()
    {
        if(ventanaDietas==null)
        {
            ventanaDietas = new VentanaDietas();
        }
        return ventanaDietas;
    }

    @Override
    public void run()
    {
        SwingUtilities.invokeLater(() ->
        {
            usuarioActual = SessionManager.getUsuarioActual();

            JPanel panel = new JPanel(new GridBagLayout());
            frame.setContentPane(panel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            JLabel tituloTxt = new JLabel("De acuerdo a tu objetivo, puedes elegir las siguientes dietas:");
            panel.add(tituloTxt,gbc);

            List<Dieta> dietasPosibles = DietaHiberImpl
                    .getInstance()
                    .findByObjetivoId(usuarioActual.getObjetivo().getId());

            int fila = 1;
            int columna = 0;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            for(Dieta dieta : dietasPosibles)
            {
                JButton boton = new JButton(dieta.getDieta());
                boton.addActionListener(e -> {
                    frame.dispose();
                    dietaSeleccionada(dieta.getId());
                });

                gbc.gridx = columna;
                gbc.gridy = fila;

                panel.add(boton, gbc);

                columna++; // hola esto es para que cada vez el gridx sea uno más grande entonces se mueve la columna lol
                if (columna > 1) { // hola cuando la columna se vuelve 2 la regresa a 0 para q se apilen otra vez lol
                    columna = 0;
                    fila++; // cuando se vuelve a poner la primera columna se baja la fila y ya entonces queda para abajo
                }
            }

            JButton volver = new JButton("Volver");
            volver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            panel.add(volver,gbc);

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void dietaSeleccionada(Integer idDieta)
    {
        usuarioActual = SessionManager.getUsuarioActual();

        JFrame frameComidas = new JFrame("Vitacorpus - Tu dieta");
        frameComidas.setResizable(false);
        frameComidas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frameComidas.setSize(600,400);
        frameComidas.setLocationRelativeTo(null);

        Comida comidas = DietaComidaHiberImpl
                .getInstance()
                .getComidaByDietaId(idDieta);

        JPanel panel = new JPanel(new GridBagLayout());
        frameComidas.setContentPane(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JLabel tituloTxt = new JLabel("¿Las comidas de qué días te gustaría observar?");
        panel.add(tituloTxt,gbc);

        JButton btnLunes = new JButton("Lunes");
        btnLunes.addActionListener(e -> getComidas("Lunes", comidas));
        JButton btnMartes = new JButton("Martes");
        btnMartes.addActionListener(e -> getComidas("Martes", comidas));
        JButton btnMiercoles = new JButton("Miércoles");
        btnMiercoles.addActionListener(e -> getComidas("Miercoles", comidas));
        JButton btnJueves = new JButton("Jueves");
        btnJueves.addActionListener(e -> getComidas("Jueves", comidas));
        JButton btnViernes = new JButton("Viernes");
        btnViernes.addActionListener(e -> getComidas("Viernes", comidas));
        JButton btnSabado = new JButton("Sábado");
        btnSabado.addActionListener(e -> getComidas("Sabado", comidas));
        JButton btnDomingo = new JButton("Domingo");
        btnDomingo.addActionListener(e -> getComidas("Domingo", comidas));
        JButton btnSemana = new JButton("Quiero ver toda la semana");
        btnSemana.addActionListener(e -> getComidas("Semana", comidas));

        gbc.gridx = 0;
        gbc.gridwidth = 1;

        gbc.gridy = 1;
        panel.add(btnLunes,gbc);
        gbc.gridy = 2;
        panel.add(btnMartes,gbc);
        gbc.gridy = 3;
        panel.add(btnMiercoles,gbc);
        gbc.gridy = 4;
        panel.add(btnJueves,gbc);
        gbc.gridy = 5;
        panel.add(btnViernes,gbc);
        gbc.gridy = 6;
        panel.add(btnSabado,gbc);
        gbc.gridy = 7;
        panel.add(btnDomingo,gbc);
        gbc.gridy = 8;
        panel.add(btnSemana,gbc);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            frameComidas.dispose();
            run();
        });

        gbc.gridx = 3;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        panel.add(btnVolver,gbc);

        JPanel restricciones = getRestricciones(comidas,usuarioActual.getId());

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(restricciones,gbc);

        frameComidas.setVisible(true);
        frameComidas.toFront();
        frameComidas.requestFocus();
    }

    private void getComidas(String dia, Comida comida)
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
                    "Lunes:\n" + comida.getComidasLunes() + "\n\n" +
                    "Martes:\n" + comida.getComidasMartes() + "\n\n" +
                    "Miércoles:\n" + comida.getComidasMiercoles() + "\n\n" +
                    "Jueves:\n" + comida.getComidasJueves() + "\n\n" +
                    "Viernes:\n" + comida.getComidasViernes() + "\n\n" +
                    "Sábado:\n" + comida.getComidasSabado() + "\n\n" +
                    "Domingo:\n" + comida.getComidasDomingo();
            framePlan.setSize(450,400);
            tituloTxt.setText("Estas son las comidas de la semana:");
        }
        else
        {
            contenido = switch (dia.toLowerCase())
            {
                case "lunes" -> comida.getComidasLunes();
                case "martes" -> comida.getComidasMartes();
                case "miercoles" -> comida.getComidasMiercoles();
                case "jueves" -> comida.getComidasJueves();
                case "viernes" -> comida.getComidasViernes();
                case "sabado" -> comida.getComidasSabado();
                case "domingo" -> comida.getComidasDomingo();
                default -> contenido;
            };
            framePlan.setSize(450,180);
            tituloTxt.setText("Estas son las comidas del día "+dia.toLowerCase()+":");
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

    private JPanel getRestricciones(Comida comidas, Integer idUsuario)
    {
        JPanel panel = new JPanel(new BorderLayout(5,5));

        Set<Integer> restriccionesUsuario  = UsuarioRestriccionHiberImpl
                .getInstance()
                .findByUsuarioId(idUsuario)
                .stream()
                .map(ur -> ur.getRestriccion().getId())
                .collect(Collectors.toSet());

        // Esto obtiene las restricciones de estas comidas en particular (las del lunes, las del martes, etc.)
        List<ComidaRestriccion> todasLasRestricciones = ComidaRestriccionHiberImpl
                .getInstance()
                .findByComidaId(comidas.getId());

        // Se filtran las que estén en el set del usuario :D
        List<ComidaRestriccion> restriccionesAplicables = todasLasRestricciones
                .stream()
                .filter(cr -> restriccionesUsuario.contains(cr.getRestriccion().getId()))
                .toList();

        // Tabla vacía por si no hay restricciones :p
        if (restriccionesAplicables.isEmpty())
        {
            JLabel label = new JLabel("¡Esta dieta no contiene alimentos restringidos por ti! 🥳");
            panel.add(label,BorderLayout.CENTER);
            return panel;
        }

        String[] columnas = {
                "Alimento restringido",
                "Sustituto 1",
                "Sustituto 2"
        };
        String[][] datos = new String[restriccionesAplicables.size()][3];

        for(int i=0;i<restriccionesAplicables.size();i++)
        {
            Restriccion restriccion = restriccionesAplicables.get(i).getRestriccion();
            datos[i][0] = restriccion.getAlimento();
            datos[i][1] = restriccion.getSustituto1();
            datos[i][2] = restriccion.getSustituto2();
        }

        JTable tabla = new JTable(datos,columnas);
        tabla.setPreferredScrollableViewportSize(new Dimension(400,250));

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(400, 250));

        JLabel label = new JLabel("Esta dieta contiene los siguientes alimentos restringidos por ti:");

        panel.add(label,BorderLayout.NORTH);
        panel.add(scrollPane,BorderLayout.CENTER);

        return panel;
    }

    @Override
    public void setFlag(boolean flag)
    {

    }
}
