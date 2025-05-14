package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.ObjetivoHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;

public class VentanaObjetivo extends JFrame implements Ejecutable {
    private final JFrame frame;
    private boolean flag;
    private static VentanaObjetivo ventanaObjetivo;
    private Usuario usuarioActual;
    private String objetivoTxt;

    private VentanaObjetivo() {
        frame = new JFrame("Vitacorpus - Mi objetivo");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(753, 436);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        BackgroundPanel fondoPanel = new BackgroundPanel("/img/fondo_objetivos.jpeg");
        fondoPanel.setLayout(new GridBagLayout());
        frame.setContentPane(fondoPanel);
    }

    public static VentanaObjetivo getInstance() {
        if (ventanaObjetivo == null) {
            ventanaObjetivo = new VentanaObjetivo();
        }
        return ventanaObjetivo;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            usuarioActual = SessionManager.getUsuarioActual();

            frame.getContentPane().removeAll();

            JPanel panelInterno = new JPanel(new GridBagLayout());
            panelInterno.setOpaque(false);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            Objetivo objetivoActual = usuarioActual.getObjetivo();
            objetivoTxt = (objetivoActual == null)
                    ? "¡No tienes un objetivo asignado!"
                    : objetivoActual.getDescripcion();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            JLabel txtTitulo = new JLabel("Tu objetivo actual es: " + objetivoTxt);
            txtTitulo.setFont(new Font("Arial", Font.BOLD, 14));
            txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            txtTitulo.setForeground(Color.BLACK);
            panelInterno.add(txtTitulo, gbc);

            JButton btnEscoger = new JButton("Elegir/actualizar mi objetivo");
            gbc.gridy = 1;
            panelInterno.add(btnEscoger, gbc);
            btnEscoger.addActionListener(e -> eleccionObjetivo(usuarioActual));

            JButton btnEliminar = new JButton("Eliminar mi objetivo actual");
            gbc.gridy = 2;
            panelInterno.add(btnEliminar, gbc);
            btnEliminar.addActionListener(e -> {
                UIManager.put("OptionPane.yesButtonText", "Sí");
                UIManager.put("OptionPane.noButtonText", "No");

                int confirmar = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar tu objetivo?", "Eliminando objetivo...", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                    usuarioActual.setObjetivo(null);
                    UsuarioHiberImpl.getInstance().update(usuarioActual);
                }
                frame.dispose();
                run();
            });

            JButton btnVolver = new JButton("Volver");
            gbc.gridx = 1;
            gbc.gridy = 3;
            panelInterno.add(btnVolver, gbc);
            btnVolver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            frame.getContentPane().add(panelInterno);
            frame.revalidate();
            frame.repaint();

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void eleccionObjetivo(Usuario usuarioActual) {
        JFrame frameEleccion = new JFrame("Eligiendo objetivo...");
        frameEleccion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameEleccion.setSize(350, 380);
        frameEleccion.setLocationRelativeTo(null);
        frameEleccion.setResizable(false);

        BackgroundPanel fondoPanel = new BackgroundPanel("/img/fondo_o_opciones.jpeg");
        fondoPanel.setLayout(new GridBagLayout());
        frameEleccion.setContentPane(fondoPanel);

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setOpaque(false);
        fondoPanel.add(panel);

        JLabel txt = new JLabel("Elige el objetivo de tu preferencia.");
        txt.setFont(new Font("Arial", Font.BOLD, 14));
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setForeground(Color.BLACK);
        panel.add(txt);

        String[] opciones = {
                "Bajar grasa",
                "Subir masa muscular",
                "Definición corporal",
                "Mejorar resistencia",
                "Ejercicio por salud"
        };

        for (int i = 0; i < opciones.length; i++) {
            int id = i + 1;
            JButton btn = new JButton(opciones[i]);
            btn.addActionListener(e -> {
                Objetivo objetivo = ObjetivoHiberImpl.getInstance().findById(id);
                usuarioActual.setObjetivo(objetivo);
                UsuarioHiberImpl.getInstance().update(usuarioActual);

                JOptionPane.showMessageDialog(frameEleccion, "Objetivo asignado: " + objetivo.getDescripcion(), "Objetivo asignado.", JOptionPane.INFORMATION_MESSAGE);
                frameEleccion.dispose();
                frame.dispose();
                run();
            });
            panel.add(btn);
        }

        frameEleccion.setVisible(true);
        frameEleccion.toFront();
    }


    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    static class BackgroundPanel extends JPanel {
        private final Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
