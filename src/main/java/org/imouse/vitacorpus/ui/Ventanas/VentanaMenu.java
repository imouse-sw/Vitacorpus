package org.imouse.vitacorpus.ui.Ventanas;
import org.imouse.vitacorpus.ui.Ejecutable;

import org.imouse.vitacorpus.ui.Ejecutable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaMenu extends JFrame implements Ejecutable {
    private static VentanaMenu ventanaMenu;
    private boolean flag = true;
    private JFrame frame;
    private JPanel fondoPanel;

    private VentanaMenu() {
        frame = new JFrame("Vitacorpus - ¡Bienvenido!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1700, 1181);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true); // Ahora la ventana puede redimensionarse
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
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            JLabel saludo2 = new JLabel("¿Qué vamos a hacer hoy?");
            saludo2.setFont(new Font("Angsana New", Font.ITALIC,18));
            saludo2.setHorizontalAlignment(SwingConstants.CENTER);
            saludo2.setForeground(Color.BLACK);
            fondoPanel.add(saludo2, gbc);

            gbc.gridy++;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.gridwidth = 2;

            JPanel botonesPanel = new JPanel(new GridBagLayout());
            botonesPanel.setOpaque(false);

            // Configurar las columnas para que tengan el mismo tamaño
            GridBagConstraints gbcBotones = new GridBagConstraints();
            gbcBotones.insets = new Insets(15, 15, 15, 15);
            gbcBotones.fill = GridBagConstraints.BOTH;
            gbcBotones.weightx = 1;
            gbcBotones.weighty = 1;

            // Definir las columnas con el mismo tamaño
            GridBagLayout layout = (GridBagLayout) botonesPanel.getLayout();
            layout.columnWeights = new double[] {1.0, 1.0};  // Ambas columnas tienen el mismo peso (1.0)
            layout.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0};  // Ajustar también el peso de las filas

            int columna = 0;
            int fila = 0;

            JButton[] botones = {
                    crearBoton("<html><div style='text-align: center;'>Manejar mis<br>restricciones alimenticias</div></html>", "/img/restricciones.png", new Color(185, 0, 255), e -> {
                        frame.dispose();
                        VentanaRestricciones.getInstance().run();
                    }, 140, 80),
                    crearBoton("Calculadora de sueño", "/img/calculadora.png", new Color(70, 130, 180), e -> {
                        frame.dispose();
                        VentanaCalculadoraSueño.getInstance().run();
                    }, 110, 80),
                    crearBoton("Registrar mis datos", "/img/registro.png", new Color(143, 188, 143), e -> {
                        frame.dispose();
                        VentanaRegistroDatos.getInstance().run();
                    }, 80, 80),
                    crearBoton("Acceder al menú de dietas", "/img/dietas.png", new Color(240, 230, 140), null,
                            90, 90),
                    crearBoton("<html>Acceder al menú de<br>rutinas de ejercicio</html>", "/img/ejercicios.png", new Color(255, 140, 0), null,
                            100, 100),
                    crearBoton("Gestionar mi objetivo", "/img/objetivos.png", new Color(255, 99, 71), e -> {
                        frame.dispose();
                        VentanaObjetivo.getInstance().run();
                    }, 80, 100)
            };


            for (int i = 0; i < botones.length; i++) {
                gbcBotones.gridx = columna;
                gbcBotones.gridy = fila;
                botonesPanel.add(botones[i], gbcBotones);
                columna++;
                if (columna > 1) {
                    columna = 0;
                    fila++;
                }
            }

            // Agregar el botón "Salir" al centro de la fila, ocupando ambas columnas
            gbcBotones.gridx = 0;  // Primera columna
            gbcBotones.gridy = fila; // Fila donde estará el botón de salir
            gbcBotones.gridwidth = 2; // Hacer que ocupe ambas columnas
            gbcBotones.weighty = 1; // Asegurarse de que tenga el mismo peso que los demás botones
            JButton botonSalir = crearBoton("Salir", "/img/salir.png", new Color(255, 105, 180), e -> {
                Object[] opciones = {"Salir", "Cerrar sesión"};
                int opcion = JOptionPane.showOptionDialog(
                        null, "¿Qué deseas realizar?", "Vitacorpus - Saliendo...",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, opciones, opciones[0]);

                if (opcion == 0) System.exit(0);
                else if (opcion == 1) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(this, "Sesión cerrada. Dirigiendo a la ventana de Login...");
                    VentanaLoginSignUp.getInstance().LoginSignup();
                }
            }, 100, 100); // Tamaño del icono del botón "Salir"
            botonesPanel.add(botonSalir, gbcBotones);

            fondoPanel.add(botonesPanel, gbc);
            frame.setVisible(true);
        });
    }

    private JButton crearBoton(String texto, String rutaIcono, Color fondo, ActionListener accion, int width, int height) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setBackground(fondo);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        boton.setIconTextGap(5);

        try {
            ImageIcon icono = new ImageIcon(getClass().getResource(rutaIcono));
            Image img = icono.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH); // Usando el tamaño personalizado
            boton.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.err.println("No se pudo cargar el icono: " + rutaIcono);
        }

        if (accion != null) {
            boton.addActionListener(accion);
        }

        return boton;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
