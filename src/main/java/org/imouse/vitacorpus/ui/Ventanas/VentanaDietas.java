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

public class VentanaDietas extends JFrame implements Ejecutable {
    private final JFrame frame;
    private static VentanaDietas ventanaDietas;
    private Usuario usuarioActual;

    private VentanaDietas() {
        frame = new JFrame("Vitacorpus - ¡Dietas para todos!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(494, 240);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaDietas getInstance() {
        if (ventanaDietas == null) {
            ventanaDietas = new VentanaDietas();
        }
        return ventanaDietas;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            usuarioActual = SessionManager.getUsuarioActual();

            ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/img/fondo_dietas.jpeg"));
            JPanel panelConFondo = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            };
            panelConFondo.setOpaque(false);
            panelConFondo.setLayout(new GridBagLayout());
            frame.setContentPane(panelConFondo);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            JLabel tituloTxt = new JLabel("De acuerdo a tu objetivo, puedes elegir las siguientes dietas:");
            panelConFondo.add(tituloTxt, gbc);

            List<Dieta> dietasPosibles = DietaHiberImpl.getInstance()
                    .findByObjetivoId(usuarioActual.getObjetivo().getId());

            int fila = 1;
            int columna = 0;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            for (Dieta dieta : dietasPosibles) {
                JButton boton = new JButton(dieta.getDieta());
                boton.addActionListener(e -> {
                    frame.dispose();
                    dietaSeleccionada(dieta.getId());
                });

                gbc.gridx = columna;
                gbc.gridy = fila;

                panelConFondo.add(boton, gbc);

                columna++;
                if (columna > 1) {
                    columna = 0;
                    fila++;
                }
            }

            JButton volver = new JButton("Volver");
            volver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            gbc.gridx = 0;
            gbc.gridy = fila + 1;
            gbc.gridwidth = 2;
            panelConFondo.add(volver, gbc);

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void dietaSeleccionada(Integer idDieta) {
        usuarioActual = SessionManager.getUsuarioActual();

        JFrame frameComidas = new JFrame("Vitacorpus - Tu dieta");
        frameComidas.setResizable(false);
        frameComidas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frameComidas.setSize(648, 400);
        frameComidas.setLocationRelativeTo(null);

        Comida comidas = DietaComidaHiberImpl.getInstance().getComidaByDietaId(idDieta);

        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/img/fondo_d_dias.jpeg"));
        JPanel panelConFondo = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConFondo.setOpaque(false);
        panelConFondo.setLayout(new GridBagLayout());
        frameComidas.setContentPane(panelConFondo);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        JLabel tituloTxt = new JLabel("¿Las comidas de qué días te gustaría observar?");
        panelConFondo.add(tituloTxt, gbc);

        JButton[] botonesDias = {
                crearBotonDia("Lunes", comidas),
                crearBotonDia("Martes", comidas),
                crearBotonDia("Miércoles", comidas),
                crearBotonDia("Jueves", comidas),
                crearBotonDia("Viernes", comidas),
                crearBotonDia("Sábado", comidas),
                crearBotonDia("Domingo", comidas),
                crearBotonDia("Semana", comidas)
        };

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        for (int i = 0; i < 7; i++) {
            gbc.gridy = i + 1;
            panelConFondo.add(botonesDias[i], gbc);
        }
        gbc.gridy = 8;
        panelConFondo.add(botonesDias[7], gbc); // botón de toda la semana

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            frameComidas.dispose();
            run();
        });

        gbc.gridx = 3;
        gbc.gridy = 9;
        panelConFondo.add(btnVolver, gbc);

        JPanel restricciones = getRestricciones(comidas, usuarioActual.getId());
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.BOTH;
        panelConFondo.add(restricciones, gbc);

        frameComidas.setVisible(true);
        frameComidas.toFront();
        frameComidas.requestFocus();
    }

    private JButton crearBotonDia(String dia, Comida comidas) {
        JButton boton = new JButton(dia);
        boton.addActionListener(e -> getComidas(dia, comidas));
        return boton;
    }

    private void getComidas(String dia, Comida comida) {
        usuarioActual = SessionManager.getUsuarioActual();

        JFrame framePlan = new JFrame("Plan del día");
        framePlan.setResizable(false);
        framePlan.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        framePlan.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        framePlan.setContentPane(panel);

        JLabel tituloTxt = new JLabel();
        tituloTxt.setFont(new Font("Arial", Font.BOLD, 18));
        tituloTxt.setHorizontalAlignment(SwingConstants.CENTER);

        String contenido;
        if (dia.equalsIgnoreCase("semana")) {
            contenido =
                    "Lunes:\n" + comida.getComidasLunes() + "\n\n" +
                            "Martes:\n" + comida.getComidasMartes() + "\n\n" +
                            "Miércoles:\n" + comida.getComidasMiercoles() + "\n\n" +
                            "Jueves:\n" + comida.getComidasJueves() + "\n\n" +
                            "Viernes:\n" + comida.getComidasViernes() + "\n\n" +
                            "Sábado:\n" + comida.getComidasSabado() + "\n\n" +
                            "Domingo:\n" + comida.getComidasDomingo();
            framePlan.setSize(450, 400);
            tituloTxt.setText("Estas son las comidas de la semana:");
        } else {
            contenido = switch (dia.toLowerCase()) {
                case "lunes" -> comida.getComidasLunes();
                case "martes" -> comida.getComidasMartes();
                case "miercoles" -> comida.getComidasMiercoles();
                case "jueves" -> comida.getComidasJueves();
                case "viernes" -> comida.getComidasViernes();
                case "sabado" -> comida.getComidasSabado();
                case "domingo" -> comida.getComidasDomingo();
                default -> "";
            };
            framePlan.setSize(450, 180);
            tituloTxt.setText("Estas son las comidas del día " + dia.toLowerCase() + ":");
        }

        JTextArea text = new JTextArea(contenido);
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(text);

        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> framePlan.dispose());

        panel.add(tituloTxt, BorderLayout.PAGE_START);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(btnVolver, BorderLayout.PAGE_END);

        framePlan.setVisible(true);
        framePlan.toFront();
        framePlan.requestFocus();
    }

    private JPanel getRestricciones(Comida comidas, Integer idUsuario) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        Set<Integer> restriccionesUsuario = UsuarioRestriccionHiberImpl.getInstance()
                .findByUsuarioId(idUsuario)
                .stream()
                .map(ur -> ur.getRestriccion().getId())
                .collect(Collectors.toSet());

        List<ComidaRestriccion> todasLasRestricciones = ComidaRestriccionHiberImpl.getInstance()
                .findByComidaId(comidas.getId());

        List<ComidaRestriccion> restriccionesAplicables = todasLasRestricciones.stream()
                .filter(cr -> restriccionesUsuario.contains(cr.getRestriccion().getId()))
                .toList();

        if (restriccionesAplicables.isEmpty()) {
            JLabel label = new JLabel("¡Esta dieta no contiene alimentos restringidos por ti! 🥳");
            panel.add(label, BorderLayout.CENTER);
            return panel;
        }

        String[] columnas = {"Alimento restringido", "Sustituto 1", "Sustituto 2"};
        String[][] datos = new String[restriccionesAplicables.size()][3];

        for (int i = 0; i < restriccionesAplicables.size(); i++) {
            Restriccion restriccion = restriccionesAplicables.get(i).getRestriccion();
            datos[i][0] = restriccion.getAlimento();
            datos[i][1] = restriccion.getSustituto1();
            datos[i][2] = restriccion.getSustituto2();
        }

        JTable tabla = new JTable(datos, columnas);
        tabla.setPreferredScrollableViewportSize(new Dimension(400, 250));
        JScrollPane scrollPane = new JScrollPane(tabla);

        JLabel label = new JLabel("Esta dieta contiene los siguientes alimentos restringidos por ti:");

        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    @Override
    public void setFlag(boolean flag) {
        // No implementado
    }
}
