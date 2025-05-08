package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Objetivo;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.model.Restriccion;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.model.relaciones.UsuarioRestriccion;
import org.imouse.vitacorpus.sql.hiberimpl.RegistroHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.RestriccionHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioRestriccionHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaRestricciones extends JFrame implements Ejecutable
{
    private final JFrame frame;
    private static VentanaRestricciones ventanaRestricciones;
    private Usuario usuarioActual;
    private JList<String> restriccionesJList;
    private List<UsuarioRestriccion> restriccionesUsuario;
    private DefaultListModel<String> modelo;

    private VentanaRestricciones()
    {
        frame = new JFrame("Vitacorpus - Mis restricciones");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaRestricciones getInstance()
    {
        if(ventanaRestricciones ==null)
        {
            ventanaRestricciones = new VentanaRestricciones();
        }
        return ventanaRestricciones;
    }

    @Override
    public void run()
    {
        SwingUtilities.invokeLater(()->
        {
            usuarioActual = SessionManager.getUsuarioActual();

            JPanel panel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            frame.setContentPane(panel);

            JLabel tituloTxt = new JLabel("Tus restricciones actuales son:");
            tituloTxt.setFont(new Font("Arial", Font.BOLD, 16));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            panel.add(tituloTxt, gbc);

            restriccionesUsuario = UsuarioRestriccionHiberImpl
                    .getInstance()
                    .findByUsuarioId(usuarioActual.getId());

            modelo = new DefaultListModel<>();

            if (restriccionesUsuario.isEmpty()) {
                modelo.addElement("No tienes restricciones asignadas.");
            } else {
                for (UsuarioRestriccion up : restriccionesUsuario) {
                    Restriccion r = up.getRestriccion();
                    modelo.addElement("#" + r.getId() + ": " + r.getAlimento());
                }
            }

            restriccionesJList = new JList<>(modelo);
            restriccionesJList.setVisibleRowCount(10);
            JScrollPane listaScroll = new JScrollPane(restriccionesJList);
            gbc.gridy = 1;
            gbc.gridwidth = 3;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            panel.add(listaScroll, gbc);

            JButton btnAgregar = new JButton("Agregar una nueva restricción");
            gbc.gridy = 2;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            btnAgregar.addActionListener( e -> {
                frame.dispose();
                eleccionRestriccion();
            });
            panel.add(btnAgregar, gbc);

            JButton btnEliminar = new JButton("Eliminar la restricción seleccionada");
            gbc.gridx = 1;
            btnEliminar.addActionListener(e -> eliminarRegistro());
            panel.add(btnEliminar, gbc);

            JButton btnVolver = new JButton("Volver");
            gbc.gridx = 2;
            btnVolver.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });
            panel.add(btnVolver, gbc);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void eliminarRegistro()
    {
        int seleccion = restriccionesJList.getSelectedIndex();
        if(restriccionesUsuario.isEmpty())
        {
            JOptionPane.showMessageDialog(frame,"No tienes restricciones asignadas.");
            return;
        }

        if(seleccion!=-1)
        {
            UIManager.put("OptionPane.yesButtonText","Sí");
            UIManager.put("OptionPane.noButtonText","No");

            int confirmar = JOptionPane.showConfirmDialog(this,"Deseas eliminar esta restricción?","Eliminando restricción...",JOptionPane.YES_NO_OPTION);
            if(confirmar == JOptionPane.YES_OPTION)
            {
                UsuarioRestriccion restriccionSeleccionada = restriccionesUsuario.get(seleccion);
                UsuarioRestriccionHiberImpl.getInstance().delete(restriccionSeleccionada);

                modelo.remove(seleccion);
                restriccionesUsuario.remove(seleccion);

                JOptionPane.showMessageDialog(frame,"Restricción eliminada.");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(frame,"Selecciona una restricción primero.");
        }

    }

    private void eleccionRestriccion()
    {
        JFrame frameEleccion = new JFrame("Agregando nueva restricción...");
        frameEleccion.setResizable(false);
        frameEleccion.setSize(450,400);
        frameEleccion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frameEleccion.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frameEleccion.setContentPane(panel);

        JLabel tituloTxt = new JLabel("Elige una restricción que se adapte a tus gustos:");
        tituloTxt.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(tituloTxt, gbc);

        List<Restriccion> restricciones = RestriccionHiberImpl
                .getInstance()
                .findAll();

        DefaultListModel<String> modelo2 = new DefaultListModel<>();
        for (Restriccion r : restricciones)
        {
            modelo2.addElement("#" + r.getId() + ": " + r.getAlimento());
        }

        JList<String> todasLasRestricciones = new JList<>(modelo2);
        todasLasRestricciones.setVisibleRowCount(6);
        JScrollPane listaScroll = new JScrollPane(todasLasRestricciones);
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel.add(listaScroll, gbc);

        JButton btnAgregar = new JButton("Agregar la selección");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        btnAgregar.addActionListener(e -> {
            int seleccion = todasLasRestricciones.getSelectedIndex();
            if(seleccion!=-1)
            {
                Restriccion restriccionSeleccionada = restricciones.get(seleccion);

                boolean restriccionYaAsignada = restriccionesUsuario
                        .stream()
                        .anyMatch(ur -> ur.getRestriccion().getId().equals(restriccionSeleccionada.getId()));

                if(!restriccionYaAsignada)
                {
                    UsuarioRestriccion usuarioRestriccion = new UsuarioRestriccion(usuarioActual,restriccionSeleccionada);
                    UsuarioRestriccionHiberImpl.getInstance().save(usuarioRestriccion);
                    modelo.addElement("#" + restriccionSeleccionada.getId() + ": " + restriccionSeleccionada.getAlimento());

                    JOptionPane.showMessageDialog(frame,"Restricción agregada.");
                    frameEleccion.dispose();
                    run();
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,"Ya tienes esta restricción asignada.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(frame,"Selecciona una restricción primero.");
            }
        });

        panel.add(btnAgregar, gbc);

        JButton btnVolver = new JButton("Volver");
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        btnVolver.addActionListener( e -> {
            frameEleccion.dispose();
            run();
        });

        panel.add(btnVolver,gbc);

        frameEleccion.setVisible(true);
        frameEleccion.toFront();
        frameEleccion.requestFocus();
    }

    @Override
    public void setFlag(boolean flag)
    {

    }
}
