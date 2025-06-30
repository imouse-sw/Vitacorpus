package org.imouse.vitacorpus.funciones.data;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.RegistroHiberImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class HistorialDatosVentana extends JFrame {
    private JTable tablaRegistros;
    private DefaultTableModel modeloTabla;
    private JButton botonEliminar;

    public HistorialDatosVentana() {
        setTitle("Vitacorpus - Historial de registros");
        setSize(850, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        init();
        datos();
    }

    private void init() {
        JPanel panelConFondo = new JPanel(new BorderLayout()) {
            ImageIcon fondo = new ImageIcon(getClass().getResource("/img/fondo4.jpeg")); // Ruta de tu imagen
            Image imagen = fondo.getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panelConFondo.setOpaque(false);
        setContentPane(panelConFondo);

        String[] columnas = {"#", "ID del registro", "Fecha", "Edad", "Peso", "Estatura", "IMC"};

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaRegistros = new JTable(modeloTabla);
        tablaRegistros.setRowHeight(25);
        tablaRegistros.setFont(new Font("Arial", Font.PLAIN, 14));
        tablaRegistros.setForeground(Color.BLACK);
        tablaRegistros.setOpaque(false);
        tablaRegistros.setBackground(new Color(0, 0, 0, 0)); // Transparente

        //  fondo transparente
        JScrollPane scrollPane = new JScrollPane(tablaRegistros);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        botonEliminar = new JButton("Eliminar registro seleccionado");
        botonEliminar.addActionListener(e -> eliminarRegistro());

        panelConFondo.add(scrollPane, BorderLayout.CENTER);
        panelConFondo.add(botonEliminar, BorderLayout.SOUTH);
    }

    private void datos() {
        Usuario usuarioActual = SessionManager.getUsuarioActual();
        int idUsuarioActual = usuarioActual.getId();

        List<RegistroDatos> list = RegistroHiberImpl.getInstance().findAll();
        List<RegistroDatos> registrosUsuario = list.stream()
                .filter(registro -> registro.getUsuario().getId().equals(idUsuarioActual))
                .toList();

        if (registrosUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No tienes registros asociados.");
            return;
        }

        int i = 1;
        for (RegistroDatos registro : registrosUsuario) {
            Object[] filas = {
                    i++,
                    registro.getId(),
                    registro.getFechaActualizacion(),
                    registro.getEdad(),
                    registro.getPeso(),
                    registro.getEstatura(),
                    registro.getImc()
            };
            modeloTabla.addRow(filas);
        }
    }

    private void eliminarRegistro() {
        int fila = tablaRegistros.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(null,
                    "No has seleccionado ningún registro para ser eliminado. Selecciona uno y vuelve a intentarlo.");
        } else {
            UIManager.put("OptionPane.yesButtonText", "Sí");
            UIManager.put("OptionPane.noButtonText", "No");

            int confirmar = JOptionPane.showConfirmDialog(this, "¿Deseas eliminar este registro?", "Eliminando registro...", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_OPTION) {
                int id = (int) modeloTabla.getValueAt(fila, 1);
                RegistroDatos registro = RegistroHiberImpl.getInstance().findById(id);
                RegistroHiberImpl.getInstance().delete(registro);

                modeloTabla.removeRow(fila);
                JOptionPane.showMessageDialog(this, "Registro eliminado con éxito.");
            }
        }
    }
}


