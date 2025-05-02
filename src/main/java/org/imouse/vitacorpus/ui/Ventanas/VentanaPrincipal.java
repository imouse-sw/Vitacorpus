package org.imouse.vitacorpus.ui.Ventanas;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal implements Ejecutable {

    private static VentanaPrincipal ventanaPrincipal;
    private boolean flag = false;
    private JFrame frame;

    private VentanaPrincipal() {}

    public static VentanaPrincipal getInstance() {
        if (ventanaPrincipal == null) {
            ventanaPrincipal = new VentanaPrincipal();
        }
        return ventanaPrincipal;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Vitacorpus");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLocationRelativeTo(null);

            // Panel con imagen de fondo
            JPanel panel = new JPanel() {
                private Image imagenFondo = new ImageIcon(getClass().getResource("/img/fondito.jpeg")).getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panel.setOpaque(false); // Necesario para mostrar el fondo
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            // Logo

            // Logo con fondo transparente (logito.png debe tener transparencia)
            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logito.png"));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(325, 300, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            logo.setOpaque(false); // Esto permite mostrar la transparencia del PNG


            // Botón personalizado
            BotonPersonalizado iniciarBtn = new BotonPersonalizado("Iniciar");
            iniciarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            iniciarBtn.addActionListener(e -> new VentanaLoginSignUp());

            // Añadir componentes al panel
            panel.add(logo);
            panel.add(Box.createVerticalStrut(40));
            panel.add(iniciarBtn);

            frame.setContentPane(panel); // Usar setContentPane en lugar de add
            frame.setVisible(true);
        });
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public JFrame getFrame() {
        return frame;
    }
}
