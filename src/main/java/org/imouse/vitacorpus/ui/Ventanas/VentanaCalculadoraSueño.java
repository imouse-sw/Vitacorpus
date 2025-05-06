package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.data.HistorialDatosVentana;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.sql.hiberimpl.RegistroHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.BotonPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.*;

public class VentanaCalculadoraSueño extends JFrame implements Ejecutable {
    private static VentanaCalculadoraSueño ventanaCalculadoraSueño;
    private boolean flag;
    private JFrame frame;

    private JPanel panelPrincipal;

    private VentanaCalculadoraSueño() {
        frame = new JFrame("Vitacorpus - Calculadora de Sueño");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(626, 417);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaCalculadoraSueño getInstance() {
        if (ventanaCalculadoraSueño == null) {
            ventanaCalculadoraSueño = new VentanaCalculadoraSueño();
        }
        return ventanaCalculadoraSueño;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            // Panel principal con fondo personalizado
            panelPrincipal = new JPanel() {
                private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo_sueno.jpg")).getImage();
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            frame.setContentPane(panelPrincipal);
            panelPrincipal.setLayout(null);

            JLabel titulo = new JLabel("Calculadora de Ciclos de Sueño");
            titulo.setFont(new Font("Britannic Bold", Font.BOLD, 24));
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setBounds(0, 20, 500, 30);
            panelPrincipal.add(titulo);

            JButton btnDormir = new JButton("¿A qué hora dormir?");
            JButton btnDespertar = new JButton("¿A qué hora despertar?");
            JButton btnInfo = new JButton("¿Qué son los ciclos REM?");
            JButton btnVolver = new JButton("Volver al menú principal");

            btnDormir.setBounds(150, 80, 200, 40);
            btnDespertar.setBounds(150, 130, 200, 40);
            btnInfo.setBounds(150, 180, 200, 40);
            btnVolver.setBounds(150, 230, 200, 40);

            Font fuente = new Font("Angsana New", Font.PLAIN, 20);
            Color fondoBoton = new Color(100, 110, 140);
            Color fondoAzul = new Color(100, 110, 140);

            for (JButton btn : new JButton[]{btnDormir, btnDespertar, btnInfo}) {
                btn.setFont(fuente);
                btn.setBackground(fondoBoton);
                btn.setForeground(Color.WHITE);
            }

            btnVolver.setFont(fuente);
            btnVolver.setBackground(fondoAzul);
            btnVolver.setForeground(Color.WHITE);

            panelPrincipal.add(btnDormir);
            panelPrincipal.add(btnDespertar);
            panelPrincipal.add(btnInfo);
            panelPrincipal.add(btnVolver);

            btnDormir.addActionListener(e -> crearVentanaDormir());
            btnDespertar.addActionListener(e -> crearVentanaDespertar());
            btnInfo.addActionListener(e -> crearVentanaInfo());
            btnVolver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            frame.setVisible(true);
        });
    }

    private void crearVentanaDormir() {
        JFrame ventana = new JFrame("¿A qué hora dormir?");
        ventana.setSize(500, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setLayout(null);

        // Panel con imagen de fondo
        JPanel panel = new JPanel() {
            private final Image fondo = new ImageIcon(getClass().getResource("/img/dormir.jpeg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel labelHora = new JLabel("Hora para despertar :");
        labelHora.setFont(new Font("Arial", Font.PLAIN, 14));
        labelHora.setForeground(Color.WHITE);
        labelHora.setBounds(50, 30, 400, 30);

        PlaceholderTextField fieldHora = new PlaceholderTextField("formato 24h, ej. 23:00");
        fieldHora.setBounds(50, 60, 400, 30);

        JButton btnCalcular = new JButton("Calcular hora para dormir");
        btnCalcular.setBounds(150, 110, 200, 40);
        estilizarBoton(btnCalcular);

        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setBounds(150, 170, 200, 40);
        estilizarRojo(btnVolver);

        btnCalcular.addActionListener(e -> {
            String input = fieldHora.getText().trim();
            if (!input.matches("\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(ventana, "Formato de hora inválido. Usa HH:mm.");
                return;
            }

            LocalTime horaDespertar = LocalTime.parse(input);
            List<LocalTime> opciones = new ArrayList<>();
            for (int ciclos = 6; ciclos >= 3; ciclos--) {
                LocalTime horaDormir = horaDespertar.minusMinutes(ciclos * 90L);
                opciones.add(horaDormir);
            }

            StringBuilder mensaje = new StringBuilder("Para despertarte a las " + horaDespertar + ", podrías dormirte a:\n\n");
            for (int i = 0; i < opciones.size(); i++) {
                mensaje.append("• ").append(opciones.get(i)).append(" (").append(6 - i).append(" ciclos)\n");
            }

            mensaje.append("\nConsidera 15 min para quedarte dormido.");
            JOptionPane.showMessageDialog(ventana, mensaje.toString());
        });

        btnVolver.addActionListener(e -> ventana.dispose());

        panel.add(labelHora);
        panel.add(fieldHora);
        panel.add(btnCalcular);
        panel.add(btnVolver);

        ventana.setContentPane(panel);
        ventana.setVisible(true);
    }



    private void crearVentanaDespertar() {
        JFrame ventana = new JFrame("¿A qué hora despertar?");
        ventana.setSize(500, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 30));

        JLabel labelHora = new JLabel("Hora para dormir (formato 24h, ej. 23:00):");
        labelHora.setFont(new Font("Arial", Font.PLAIN, 14));
        labelHora.setForeground(Color.WHITE);
        labelHora.setBounds(50, 30, 400, 30);

        JTextField fieldHora = new JTextField();
        fieldHora.setBounds(50, 60, 400, 30);

        JButton btnCalcular = new JButton("Calcular hora para despertar");
        btnCalcular.setBounds(150, 110, 200, 40);
        estilizarBoton(btnCalcular);

        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setBounds(150, 170, 200, 40);
        estilizarRojo(btnVolver);

        btnCalcular.addActionListener(e -> {
            String input = fieldHora.getText().trim();
            if (!input.matches("\\d{2}:\\d{2}")) {
                JOptionPane.showMessageDialog(ventana, "Formato de hora inválido. Usa HH:mm.");
                return;
            }

            LocalTime horaDormir = LocalTime.parse(input);
            List<LocalTime> opciones = new ArrayList<>();
            for (int ciclos = 3; ciclos <= 6; ciclos++) {
                LocalTime horaDespertar = horaDormir.plusMinutes(ciclos * 90L);
                opciones.add(horaDespertar);
            }

            StringBuilder mensaje = new StringBuilder("Si te duermes a las " + horaDormir + ", podrías despertarte a:\n\n");
            for (int i = 0; i < opciones.size(); i++) {
                mensaje.append("• ").append(opciones.get(i)).append(" (").append(i + 3).append(" ciclos)\n");
            }

            mensaje.append("\nLos ciclos duran aprox. 90 minutos.");
            JOptionPane.showMessageDialog(ventana, mensaje.toString());
        });

        btnVolver.addActionListener(e -> ventana.dispose());

        panel.add(labelHora);
        panel.add(fieldHora);
        panel.add(btnCalcular);
        panel.add(btnVolver);

        ventana.setContentPane(panel);
        ventana.setVisible(true);
    }

    private void crearVentanaInfo() {
        JFrame ventana = new JFrame("¿Qué son los ciclos REM?");
        ventana.setSize(500, 420);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 30, 30));

        JTextArea infoArea = new JTextArea();
        infoArea.setText("""
            ¿Qué son los ciclos REM?

            El sueño se divide en varias fases que forman un ciclo completo de aproximadamente 90 minutos. Cada ciclo incluye varias etapas esenciales para el descanso y la recuperación del cuerpo.

            Las fases del sueño son:

            1. Fase 1 - Sueño Ligero (N1)
            2. Fase 2 - Sueño Ligero Profundo (N2)
            3. Fase 3 - Sueño Profundo (N3)
            4. Fase 4 - Sueño REM (Rapid Eye Movement)

            El sueño REM es crucial para la memoria y la salud mental. Durante la noche, se repiten varios ciclos.

            Duración de los ciclos:
            3 ciclos = 4.5 horas
            4 ciclos = 6 horas
            5 ciclos = 7.5 horas
            6 ciclos = 9 horas
        """);
        infoArea.setWrapStyleWord(true);
        infoArea.setLineWrap(true);
        infoArea.setEditable(false);
        infoArea.setBackground(new Color(50, 50, 50));
        infoArea.setForeground(Color.WHITE);
        infoArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(infoArea);
        scrollPane.setBounds(30, 20, 430, 300);

        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setBounds(150, 330, 200, 40);
        estilizarRojo(btnVolver);

        btnVolver.addActionListener(e -> ventana.dispose());

        panel.add(scrollPane);
        panel.add(btnVolver);

        ventana.setContentPane(panel);
        ventana.setVisible(true);
    }

    private void estilizarBoton(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setBackground(new Color(50, 50, 50));
        boton.setForeground(Color.WHITE);
    }

    private void estilizarRojo(JButton boton) {
        boton.setFont(new Font("Arial", Font.PLAIN, 14));
        boton.setBackground(new Color(150, 50, 50));
        boton.setForeground(Color.WHITE);
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    static class PlaceholderTextField extends JTextField {
        private String placeholder;
        private boolean showingPlaceholder = true;

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;
            setText(placeholder);
            setForeground(Color.GRAY);

            addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) {
                    if (showingPlaceholder) {
                        setText("");
                        setForeground(Color.BLACK);
                        showingPlaceholder = false;
                    }
                }

                public void focusLost(java.awt.event.FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(placeholder);
                        setForeground(Color.GRAY);
                        showingPlaceholder = true;
                    }
                }
            });
        }

        @Override
        public String getText() {
            return showingPlaceholder ? "" : super.getText();
        }
    }
}