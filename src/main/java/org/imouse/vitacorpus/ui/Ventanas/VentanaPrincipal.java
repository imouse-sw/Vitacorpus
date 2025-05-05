package org.imouse.vitacorpus.ui.Ventanas;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.BotonPersonalizado;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal implements Ejecutable {
    private static VentanaPrincipal ventanaPrincipal;
    private boolean flag = false;
    private JFrame frame;

    private VentanaPrincipal() {
        frame = new JFrame("Vitacorpus");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaPrincipal getInstance() {
        if (ventanaPrincipal == null) {
            ventanaPrincipal = new VentanaPrincipal();
        }
        return ventanaPrincipal;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            JPanel panel = new JPanel() {
                private Image imagenFondo = new ImageIcon(getClass().getResource("/img/fondito.jpeg")).getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            panel.setOpaque(false);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logo.png"));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(325, 300, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);
            logo.setOpaque(false);

            ImageIcon iconoOriginal2 = new ImageIcon(getClass().getResource("/img/nombre.png"));
            Image imagenEscalada2 = iconoOriginal2.getImage().getScaledInstance(90, 40, Image.SCALE_SMOOTH);
            JLabel logo2 = new JLabel(new ImageIcon(imagenEscalada2));
            logo2.setAlignmentX(Component.CENTER_ALIGNMENT);
            logo2.setOpaque(false);

            BotonPersonalizado iniciarBtn = new BotonPersonalizado("Iniciar");
            iniciarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            iniciarBtn.addActionListener(e -> {
                frame.dispose();
                VentanaLoginSignUp.getInstance().LoginSignup();
            });

            panel.add(logo);
            panel.add(logo2);
            panel.add(Box.createVerticalStrut(40));
            panel.add(iniciarBtn);

            frame.setContentPane(panel);
            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
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
