package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal implements Ejecutable {

    private static VentanaPrincipal ventanaPrincipal;
    private boolean flag = false;
    private JFrame frame;

    private VentanaPrincipal() {
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
            frame = new JFrame("Vitacorpus");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 500);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setBackground(new Color(255, 255, 255));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

            ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logo.jpg"));
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
            logo.setAlignmentX(Component.CENTER_ALIGNMENT);


            JButton iniciarBtn = new JButton("Iniciar");
            iniciarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            iniciarBtn.setFocusPainted(false);
            iniciarBtn.setBackground(new Color(144, 238, 144));
            iniciarBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
            iniciarBtn.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2));
            iniciarBtn.addActionListener(e -> new VentanaLoginSignUp());


            panel.add(logo);
            panel.add(Box.createVerticalStrut(40));
            panel.add(iniciarBtn);

            frame.add(panel);
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
