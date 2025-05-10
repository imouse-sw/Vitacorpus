package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Dieta;
import org.imouse.vitacorpus.model.Rutina;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.DietaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.RutinaHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRutinas extends JFrame implements Ejecutable
{
    private final JFrame frame;
    private static VentanaRutinas ventanaRutinas;
    private Usuario usuarioActual;

    private VentanaRutinas()
    {
        frame = new JFrame("Vitacorpus - ¡Rutinas para todos!");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(550,240);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaRutinas getInstance()
    {
        if(ventanaRutinas==null)
        {
            ventanaRutinas = new VentanaRutinas();
        }
        return ventanaRutinas;
    }

    @Override
    public void run()
    {
        SwingUtilities.invokeLater(() ->
        {
            usuarioActual = SessionManager.getUsuarioActual();

            JPanel panel = new JPanel(new GridBagLayout());
            frame.setContentPane(panel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5,5,5,5);
            gbc.fill = GridBagConstraints.CENTER;
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;

            JLabel tituloTxt = new JLabel("De acuerdo a tu objetivo, puedes elegir las siguientes rutinas:");
            panel.add(tituloTxt,gbc);

            List<Rutina> rutinasPosibles = RutinaHiberImpl
                    .getInstance()
                    .findByObjetivoId(usuarioActual.getObjetivo().getId());

            int fila = 1;
            int columna = 0;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            for(Rutina rutina : rutinasPosibles)
            {
                JButton boton = new JButton(rutina.getRutina());
                boton.addActionListener(e -> mostrarEjercicios(rutina.getId()));

                gbc.gridx = columna;
                gbc.gridy = fila;

                panel.add(boton, gbc);

                columna++; // hola esto es para que cada vez el gridx sea uno más grande entonces se mueve la columna lol
                if (columna > 1) { // hola cuando la columna se vuelve 2 la regresa a 0 para q se apilen otra vez lol
                    columna = 0;
                    fila++; // cuando se vuelve a poner la primera columna se baja la fila y ya entonces queda para abajo lol
                }
            }

            JButton volver = new JButton("Volver");
            volver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            panel.add(volver,gbc);

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void mostrarEjercicios(Integer idRutina)
    {
        JOptionPane.showMessageDialog(frame,"todavia no hay nada lol la rutina tiene id "+idRutina+" pa saber");
    }

    @Override
    public void setFlag(boolean flag)
    {

    }
}
