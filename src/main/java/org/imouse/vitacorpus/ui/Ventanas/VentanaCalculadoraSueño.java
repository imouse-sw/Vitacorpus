package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VentanaCalculadoraSueño extends JFrame implements Ejecutable {
    private static VentanaCalculadoraSueño instancia;
    private boolean flag;
    private JFrame frame;
    private JPanel panelPrincipal;

    private VentanaCalculadoraSueño() {
        frame = new JFrame("Vitacorpus - Calculadora de Sueño");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(500, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaCalculadoraSueño getInstance() {
        if (instancia == null) {
            instancia = new VentanaCalculadoraSueño();
        }
        return instancia;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            // Crear panel con fondo
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
            titulo.setFont(new Font("Arial", Font.BOLD, 18));
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setForeground(Color.WHITE);
            titulo.setBounds(0, 20, 500, 30);
            panelPrincipal.add(titulo);

            JButton btnDormir = new JButton("¿A qué hora dormir?");
            JButton btnDespertar = new JButton("¿A qué hora despertar?");
            JButton btnInfo = new JButton("¿Qué son los ciclos REM?");
            JButton btnVolver = new JButton("Volver al menú principal");

            JButton[] botones = {btnDormir, btnDespertar, btnInfo, btnVolver};
            int y = 80;
            for (JButton btn : botones) {
                btn.setBounds(150, y, 200, 40);
                panelPrincipal.add(btn);
                y += 50;
            }

            btnDormir.addActionListener(e -> mostrarVentanaHoraParaDormir());
            btnDespertar.addActionListener(e -> mostrarVentanaHoraParaDespertar());
            btnInfo.addActionListener(e -> mostrarVentanaInformacionREM());
            btnVolver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            frame.setVisible(true);
        });
    }

    // Método para validar si la hora es válida
    private boolean esHoraValida(String hora) {
        return hora.matches("\\d{2}:\\d{2}");
    }

    private LocalTime parsearHora(String texto, Component parent) {
        try {
            return LocalTime.parse(texto);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(parent, "Formato de hora inválido. Usa HH:mm.");
            return null;
        }
    }

    private void mostrarVentanaHoraParaDormir() {
        JFrame ventana = new JFrame("¿A qué hora dormir?");
        ventana.setSize(500, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        // Crear panel con fondo
        JPanel panel = new JPanel() {
            private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo_anochecer.jpeg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel labelHora = new JLabel("Hora para despertar:");
        labelHora.setFont(new Font("Arial", Font.PLAIN, 14));
        labelHora.setForeground(Color.WHITE);
        labelHora.setBounds(50, 30, 400, 30);

        PlaceholderTextField fieldHora = new PlaceholderTextField("En formato 24h, ej. 23:00");
        fieldHora.setBounds(50, 60, 400, 30);

        JButton btnCalcular = new JButton("Calcular hora para dormir");
        btnCalcular.setBounds(150, 110, 200, 40);

        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setBounds(150, 170, 200, 40);

        btnCalcular.addActionListener(e -> {
            String input = fieldHora.getText().trim();
            LocalTime horaDespertar = parsearHora(input, ventana);
            if (horaDespertar == null) return;

            List<LocalTime> opciones = new ArrayList<>();
            for (int ciclos = 6; ciclos >= 3; ciclos--) {
                opciones.add(horaDespertar.minusMinutes(ciclos * 90L));
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

    private void mostrarVentanaHoraParaDespertar() {
        JFrame ventana = new JFrame("¿A qué hora despertar?");
        ventana.setSize(500, 300);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);

        // Crear panel con fondo
        JPanel panel = new JPanel() {
            private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo_amanecer.jpeg")).getImage();
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);

        JLabel labelHora = new JLabel("Hora para dormir:");
        labelHora.setFont(new Font("Arial", Font.PLAIN, 14));
        labelHora.setForeground(Color.WHITE);
        labelHora.setBounds(50, 30, 400, 30);

        PlaceholderTextField fieldHora = new PlaceholderTextField("En formato 24h, ej. 23:00");
        fieldHora.setBounds(50, 60, 400, 30);

        JButton btnCalcular = new JButton("Calcular hora para despertar");
        btnCalcular.setBounds(150, 110, 200, 40);

        JButton btnVolver = new JButton("Cerrar");
        btnVolver.setBounds(150, 170, 200, 40);

        btnCalcular.addActionListener(e -> {
            String input = fieldHora.getText().trim();
            LocalTime horaDormir = parsearHora(input, ventana);
            if (horaDormir == null) return;

            List<LocalTime> opciones = new ArrayList<>();
            for (int ciclos = 3; ciclos <= 6; ciclos++) {
                opciones.add(horaDormir.plusMinutes(ciclos * 90L));
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

    private void mostrarVentanaInformacionREM() {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("¿Qué son los ciclos REM?");
            ventana.setSize(500, 420);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);

            JPanel panel = new JPanel() {
                private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo_calculadora.jpeg")).getImage();
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panel.setLayout(null);

            JTextArea infoArea = new JTextArea(""" 
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
            btnVolver.addActionListener(e -> ventana.dispose());

            panel.add(scrollPane);
            panel.add(btnVolver);

            ventana.setContentPane(panel);
            ventana.setVisible(true);
        });
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
