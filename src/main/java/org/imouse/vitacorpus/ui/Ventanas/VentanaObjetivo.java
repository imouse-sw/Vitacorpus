package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.ObjetivoHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import javax.swing.*;
import java.awt.*;

public class VentanaObjetivo extends JFrame implements Ejecutable
{
    private final JFrame frame;
    private boolean flag;
    private static VentanaObjetivo ventanaObjetivo;
    private Usuario usuarioActual;
    private String objetivoTxt;

    private VentanaObjetivo()
    {
        frame = new JFrame("Vitacorpus - Mi objetivo");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(450,220);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    public static VentanaObjetivo getInstance()
    {
        if(ventanaObjetivo==null)
        {
            ventanaObjetivo = new VentanaObjetivo();
        }
        return ventanaObjetivo;
    }

    @Override
    public void run()
    {
        SwingUtilities.invokeLater(()->
        {
            usuarioActual = SessionManager.getUsuarioActual();

            JPanel panel = new JPanel(new GridBagLayout());
            frame.setContentPane(panel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);

            Objetivo objetivoActual = usuarioActual.getObjetivo();
            if(objetivoActual==null)
            {
                objetivoTxt = "¡No tienes un objetivo asignado! D:";
            }
            else
            {
                objetivoTxt = objetivoActual.getDescripcion();
            }

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            JLabel txtTitulo = new JLabel("Tu objetivo actual es: "+objetivoTxt);
            txtTitulo.setFont(new Font("Arial",Font.BOLD,14));
            txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            txtTitulo.setForeground(Color.BLACK);
            panel.add(txtTitulo,gbc);

            JButton btnEscoger = new JButton("Elegir/actualizar mi objetivo");
            gbc.gridy = 1;
            btnEscoger.addActionListener(e -> eleccionObjetivo(usuarioActual));
            panel.add(btnEscoger,gbc);

            JButton btnEliminar = new JButton("Eliminar mi objetivo actual");
            gbc.gridy = 2;
            btnEliminar.addActionListener(e -> {
                UIManager.put("OptionPane.yesButtonText","Sí");
                UIManager.put("OptionPane.noButtonText","No");

                int confirmar = JOptionPane.showConfirmDialog(this,"Deseas eliminar tu objetivo?","Eliminando objetivo...",JOptionPane.YES_NO_OPTION);
                if(confirmar == JOptionPane.YES_OPTION)
                {
                    usuarioActual.setObjetivo(null);
                    UsuarioHiberImpl.getInstance().update(usuarioActual);
                }
                frame.dispose();
                run();
            });
            panel.add(btnEliminar,gbc);

            JButton btnVolver = new JButton("Volver");
            gbc.gridx = 1;
            gbc.gridy = 3;
            btnVolver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });
            panel.add(btnVolver,gbc);

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void eleccionObjetivo(Usuario usuarioActual)
    {
        JFrame frameEleccion = new JFrame("Eligiendo objetivo...");
        frameEleccion.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frameEleccion.setSize(300,240);
        frameEleccion.setLocationRelativeTo(null);
        frameEleccion.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(0,1));
        frameEleccion.setContentPane(panel);

        JLabel txt = new JLabel("Elige el objetivo de tu preferencia.");
        txt.setFont(new Font("Arial",Font.BOLD, 14));
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setForeground(Color.BLACK);
        panel.add(txt);

        JButton btnObj1 = new JButton("Bajar grasa");
        btnObj1.addActionListener(e -> {
            Objetivo objetivo = ObjetivoHiberImpl.getInstance().findById(1);
            usuarioActual.setObjetivo(objetivo);
            UsuarioHiberImpl.getInstance().update(usuarioActual);

            JOptionPane.showMessageDialog(frameEleccion, "Objetivo asignado: "+objetivo.getDescripcion(), "Objetivo asignado.", JOptionPane.INFORMATION_MESSAGE);
            frameEleccion.dispose();
            frame.dispose();
            run();
        });
        panel.add(btnObj1);

        JButton btnObj2 = new JButton("Subir masa muscular");
        btnObj2.addActionListener(e -> {
            Objetivo objetivo = ObjetivoHiberImpl.getInstance().findById(2);
            usuarioActual.setObjetivo(objetivo);
            UsuarioHiberImpl.getInstance().update(usuarioActual);

            JOptionPane.showMessageDialog(frameEleccion, "Objetivo asignado: "+objetivo.getDescripcion(), "Objetivo asignado.", JOptionPane.INFORMATION_MESSAGE);
            frameEleccion.dispose();
            frame.dispose();
            run();
        });
        panel.add(btnObj2);

        JButton btnObj3 = new JButton("Definición corporal");
        btnObj3.addActionListener(e -> {
            Objetivo objetivo = ObjetivoHiberImpl.getInstance().findById(3);
            usuarioActual.setObjetivo(objetivo);
            UsuarioHiberImpl.getInstance().update(usuarioActual);

            JOptionPane.showMessageDialog(frameEleccion, "Objetivo asignado: "+objetivo.getDescripcion(), "Objetivo asignado.", JOptionPane.INFORMATION_MESSAGE);
            frameEleccion.dispose();
            frame.dispose();
            run();
        });
        panel.add(btnObj3);

        JButton btnObj4 = new JButton("Mejorar resistencia");
        btnObj4.addActionListener(e -> {
            Objetivo objetivo = ObjetivoHiberImpl.getInstance().findById(4);
            usuarioActual.setObjetivo(objetivo);
            UsuarioHiberImpl.getInstance().update(usuarioActual);

            JOptionPane.showMessageDialog(frameEleccion, "Objetivo asignado: "+objetivo.getDescripcion(), "Objetivo asignado.", JOptionPane.INFORMATION_MESSAGE);
            frameEleccion.dispose();
            frame.dispose();
            run();
        });
        panel.add(btnObj4);

        JButton btnObj5 = new JButton("Ejercicio por salud");
        btnObj5.addActionListener(e -> {
            Objetivo objetivo = ObjetivoHiberImpl.getInstance().findById(5);
            usuarioActual.setObjetivo(objetivo);
            UsuarioHiberImpl.getInstance().update(usuarioActual);

            JOptionPane.showMessageDialog(frameEleccion, "Objetivo asignado: "+objetivo.getDescripcion(), "Objetivo asignado.", JOptionPane.INFORMATION_MESSAGE);
            frameEleccion.dispose();
            frame.dispose();
            run();
        });
        panel.add(btnObj5);

        frameEleccion.setVisible(true);
        frameEleccion.toFront();
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}
