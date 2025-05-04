package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.exit;

public class VentanaMenu extends JFrame implements Ejecutable {
    private static VentanaMenu ventanaMenu;
    private boolean flag = true;
    private JFrame frame;
    private JPanel fondoPanel;

    private VentanaMenu() {
        frame = new JFrame("Vitacorpus - ¡Bienvenido!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        fondoPanel = new JPanel(new GridBagLayout()) {
            private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo2.jpeg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondoPanel.setOpaque(false);
    }

    public static VentanaMenu getInstance() {
        if (ventanaMenu == null) {
            ventanaMenu = new VentanaMenu();
        }
        return ventanaMenu;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            frame.setContentPane(fondoPanel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 20, 5, 20);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;

            JLabel saludo = new JLabel("Bienvenido al menú principal de Vitacorpus");
            saludo.setFont(new Font("Arial", Font.BOLD, 18));
            saludo.setHorizontalAlignment(SwingConstants.CENTER);
            saludo.setForeground(Color.BLACK);
            gbc.gridy = 0;
            fondoPanel.add(saludo, gbc);

            JLabel saludo2 = new JLabel("¿Qué vamos a hacer hoy?");
            saludo2.setFont(new Font("Arial", Font.ITALIC, 16));
            saludo2.setHorizontalAlignment(SwingConstants.CENTER);
            saludo2.setForeground(Color.BLACK);
            gbc.gridy = 1;
            fondoPanel.add(saludo2, gbc);

            BotonPersonalizado btnRegistro = new BotonPersonalizado("Registrar mis datos");
            gbc.gridy = 2;
            btnRegistro.addActionListener(e -> {
                frame.dispose();
                VentanaRegistroDatos.getInstance().run();
            });
            fondoPanel.add(btnRegistro, gbc);

            BotonPersonalizado btnObjetivos = new BotonPersonalizado("Manejar mis objetivos");
            gbc.gridy = 3;
            fondoPanel.add(btnObjetivos, gbc);

            BotonPersonalizado btnRestricciones = new BotonPersonalizado("Manejar mis restricciones alimenticias");
            gbc.gridy = 4;
            fondoPanel.add(btnRestricciones, gbc);

            BotonPersonalizado btnCalculadora = new BotonPersonalizado("Calculadora de sueño");
            gbc.gridy = 5;
            fondoPanel.add(btnCalculadora, gbc);

            BotonPersonalizado btnDietas = new BotonPersonalizado("Acceder al menú de dietas");
            gbc.gridy = 6;
            fondoPanel.add(btnDietas, gbc);

            BotonPersonalizado btnRutinas = new BotonPersonalizado("Acceder al menú de rutinas de ejercicio");
            gbc.gridy = 7;
            fondoPanel.add(btnRutinas, gbc);

            BotonPersonalizado btnSalir = new BotonPersonalizado("Salir");
            gbc.gridy = 8;
            fondoPanel.add(btnSalir, gbc);

            btnSalir.addActionListener(e -> {
                Object[] opciones = {"Salir", "Cerrar sesión"};

                int opcionSeleccionada = JOptionPane.showOptionDialog(
                        null,
                        "¿Qué deseas realizar?",
                        "Vitacorpus - Saliendo...",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if (opcionSeleccionada == 0) {
                    exit(0);
                } else if (opcionSeleccionada == 1) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(this, "Sesión cerrada. Dirigiendo a la ventana de Login...");
                    VentanaLoginSignUp.getInstance().LoginSignup();
                }
            });

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
