package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.data.HistorialDatosVentana;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.RegistroDatos;
import org.imouse.vitacorpus.sql.hiberimpl.RegistroHiberImpl;
import org.imouse.vitacorpus.ui.Ejecutable;
import org.imouse.vitacorpus.util.BotonPersonalizado;
import javax.swing.*;
import java.awt.*;

public class VentanaRegistroDatos extends JFrame implements Ejecutable {
    private static VentanaRegistroDatos ventanaRegistroDatos;
    private boolean flag;
    private JFrame frame;

    private VentanaRegistroDatos() {
        frame = new JFrame("Vitacorpus - Registro de datos personales");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setSize(480, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaRegistroDatos getInstance() {
        if (ventanaRegistroDatos == null) {
            ventanaRegistroDatos = new VentanaRegistroDatos();
        }
        return ventanaRegistroDatos;
    }

    @Override
    public void run() {
        SwingUtilities.invokeLater(() -> {
            JPanel fondoPanel = new JPanel(new GridBagLayout()) {
                private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo2.jpeg")).getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            fondoPanel.setOpaque(false);
            fondoPanel.setLayout(new GridBagLayout());

            frame.setContentPane(fondoPanel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 20, 5, 20);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;

            JLabel text = new JLabel("Menú de gestión de datos personales");
            text.setFont(new Font("Arial", Font.BOLD, 16));
            text.setHorizontalAlignment(SwingConstants.CENTER);
            text.setForeground(Color.BLACK);
            gbc.gridy = 0;
            fondoPanel.add(text, gbc);

            BotonPersonalizado btnNuevo = new BotonPersonalizado("Añadir un nuevo registro");
            gbc.gridy = 1;
            btnNuevo.addActionListener(e -> nuevoRegistro());
            fondoPanel.add(btnNuevo, gbc);

            BotonPersonalizado btnGestion = new BotonPersonalizado("Gestionar mis registros");
            gbc.gridy = 2;
            btnGestion.addActionListener(e -> new HistorialDatosVentana().setVisible(true));
            fondoPanel.add(btnGestion, gbc);

            BotonPersonalizado btnImc = new BotonPersonalizado("¿Qué es el IMC?");
            gbc.gridy = 3;
            btnImc.addActionListener(e -> explicacionImc());
            fondoPanel.add(btnImc, gbc);

            BotonPersonalizado btnRegresar = new BotonPersonalizado("Volver al menú principal");
            gbc.gridy = 4;
            btnRegresar.addActionListener(e -> {
                frame.dispose();
                VentanaMenu.getInstance().run();
            });
            fondoPanel.add(btnRegresar, gbc);

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    private void explicacionImc()
    {
        SwingUtilities.invokeLater(()-> {
            JFrame frameExplicacion = new JFrame("¿Qué es el IMC?");
            frameExplicacion.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frameExplicacion.setSize(1080,600);
            frameExplicacion.setLocationRelativeTo(null);
            frameExplicacion.setResizable(false);

            JPanel panel = new JPanel(new BorderLayout());
            frameExplicacion.setContentPane(panel);

            JLabel titulo = new JLabel("¿Qué es el IMC?");
            titulo.setFont(new Font("Arial", Font.BOLD, 24));
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setForeground(Color.BLACK);
            panel.add(titulo, BorderLayout.PAGE_START);

            JLabel explicacion = new JLabel(
                    "<html><p style='width: 400px;'>"
                            + "El IMC (Índice de Masa Corporal) es una medida que relaciona tu peso con tu estatura, "
                            + "y se calcula dividiendo tu peso en kilogramos entre tu estatura en metros cuadrados (kg/m²).<br><br>"
                            + "Sirve como una referencia general para saber si estás en un peso saludable, "
                            + "si estás por debajo o por encima de lo considerado normal.<br><br>"
                            + "Sin embargo, ten en cuenta que no considera tu masa muscular ni tu distribución de grasa, "
                            + "por lo que es meramente orientativo."
                            + "</p></html>"
            );
            explicacion.setFont(new Font("Arial", Font.PLAIN, 18));
            explicacion.setHorizontalAlignment(SwingConstants.LEFT);
            explicacion.setForeground(Color.BLACK);
            panel.add(explicacion, BorderLayout.LINE_START);

            JLabel imcImagen = new JLabel(new ImageIcon(getClass().getResource("/img/imc.png")));
            panel.add(imcImagen,BorderLayout.LINE_END);

            frameExplicacion.setVisible(true);
            frameExplicacion.toFront();
            frameExplicacion.requestFocus();
        });
    }

    private void nuevoRegistro() {
        SwingUtilities.invokeLater(() -> {
            JFrame frameRegistro = new JFrame("Creando nuevo registro...");
            frameRegistro.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frameRegistro.setSize(400, 500);
            frameRegistro.setLocationRelativeTo(null);
            frameRegistro.setResizable(false);

            JPanel fondoRegistro = new JPanel(new GridBagLayout()) {
                private final Image fondo = new ImageIcon(getClass().getResource("/img/fondo3.jpeg")).getImage();

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
                }
            };
            fondoRegistro.setOpaque(false);
            fondoRegistro.setLayout(new GridBagLayout());
            frameRegistro.setContentPane(fondoRegistro);

            GridBagConstraints gbcNew = new GridBagConstraints();
            gbcNew.insets = new Insets(10, 15, 10, 15);
            gbcNew.fill = GridBagConstraints.HORIZONTAL;

            JLabel titulo = new JLabel("Nuevo registro");
            titulo.setFont(new Font("Arial", Font.BOLD, 18));
            titulo.setHorizontalAlignment(SwingConstants.CENTER);
            titulo.setForeground(Color.BLACK);
            gbcNew.gridx = 0;
            gbcNew.gridy = 0;
            gbcNew.gridwidth = 2;
            fondoRegistro.add(titulo, gbcNew);

            gbcNew.gridwidth = 1;

            PlaceholderTextField edadField = new PlaceholderTextField("Ej. 25");
            PlaceholderTextField pesoField = new PlaceholderTextField("Ej. 75");
            PlaceholderTextField estaturaField = new PlaceholderTextField("Ej. 1.75");

            gbcNew.gridx = 0;
            gbcNew.gridy = 1;
            fondoRegistro.add(new JLabel("Edad:"), gbcNew);
            gbcNew.gridx = 1;
            fondoRegistro.add(edadField, gbcNew);

            gbcNew.gridx = 0;
            gbcNew.gridy = 2;
            fondoRegistro.add(new JLabel("Peso en kilogramos:"), gbcNew);
            gbcNew.gridx = 1;
            fondoRegistro.add(pesoField, gbcNew);

            gbcNew.gridx = 0;
            gbcNew.gridy = 3;
            fondoRegistro.add(new JLabel("Estatura en metros:"), gbcNew);
            gbcNew.gridx = 1;
            fondoRegistro.add(estaturaField, gbcNew);

            gbcNew.gridx = 0;
            gbcNew.gridy = 4;
            fondoRegistro.add(new JLabel("IMC:"), gbcNew);
            gbcNew.gridx = 1;
            JLabel imcLabel = new JLabel("");
            fondoRegistro.add(imcLabel, gbcNew);

            JButton btnCrear = new JButton("Crear registro");
            gbcNew.gridx = 1;
            gbcNew.gridy = 5;
            fondoRegistro.add(btnCrear, gbcNew);

            JLabel imcInfo = new JLabel("");
            gbcNew.gridx = 0;
            gbcNew.gridy = 6;
            gbcNew.gridwidth = 2;
            gbcNew.gridheight = 2;
            fondoRegistro.add(imcInfo,gbcNew);

            btnCrear.addActionListener(e -> {
                RegistroDatos nuevo = new RegistroDatos();
                String edadTxt = edadField.getText().trim();
                String pesoTxt = pesoField.getText().trim();
                String estaturaTxt = estaturaField.getText().trim();

                if (edadTxt.isBlank() || pesoTxt.isBlank() || estaturaTxt.isBlank()) {
                    JOptionPane.showMessageDialog(frameRegistro, "Completa todos los campos.");
                    return;
                }

                try {
                    int edad = Integer.parseInt(edadTxt);
                    if (edad <= 0 || edad >= 120) {
                        JOptionPane.showMessageDialog(frameRegistro, "Edad inválida. No debe ser mayor a 120, ni menor a 0.");
                        return;
                    }

                    double peso = Double.parseDouble(pesoTxt);
                    if (peso <= 20 || peso >= 300) {
                        JOptionPane.showMessageDialog(frameRegistro, "Peso inválido. No debe ser menor de 20 ni mayor a 300.");
                        return;
                    }

                    double estatura = Double.parseDouble(estaturaTxt);
                    if (estatura >= 2.2 || estatura <= 1.2) {
                        JOptionPane.showMessageDialog(frameRegistro, "Estatura inválida. No debe ser menor a 1.2m ni mayor a 2.2m.");
                        return;
                    }

                    nuevo.setUsuario(SessionManager.getUsuarioActual());
                    nuevo.setEdad(edad);
                    nuevo.setPeso(peso);
                    nuevo.setEstatura(estatura);
                    RegistroHiberImpl.getInstance().save(nuevo);

                    imcLabel.setText(String.valueOf(nuevo.getImc()));
                    mostrarInfoImc(nuevo.getImc(), imcInfo);
                    fondoRegistro.revalidate();
                    fondoRegistro.repaint();
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "❌ Has introducido un formato inválido para los campos.");
                }
            });

            frameRegistro.setVisible(true);
            frameRegistro.toFront();
            frameRegistro.requestFocus();
        });
    }

    private void mostrarInfoImc(double imcRegistro, JLabel info)
    {
        String resultado;
        if(imcRegistro<18.5)
        {
            resultado = "esto indica un peso por debajo de lo normal.";
        }
        else if(imcRegistro>=18.5&&imcRegistro<=24.9)
        {
            resultado = "estás en un peso saludable. ¡Sigue así!";
        }
        else if(imcRegistro>=25&&imcRegistro<=29.9)
        {
            resultado = "esto indica un ligero sobrepeso.";
        }
        else if(imcRegistro>=30&&imcRegistro<=34.9)
        {
            resultado = "esto indica una obesidad leve.";
        }
        else if(imcRegistro>=35&&imcRegistro<=39.9)
        {
            resultado = "esto indica una obesidad moderada.";
        }
        else
        {
            resultado = "esto indica una obesidad mórbida. Te recomendamos enfocarte en ello! 😟";
        }
        info.setText("<html>Tu IMC fue de "+imcRegistro+", "+resultado+"</html>");
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    // Clase interna para placeholder funcional
    static class PlaceholderTextField extends JTextField {
        private String placeholder;
        private boolean showingPlaceholder = true;

        public PlaceholderTextField(String placeholder) {
            this.placeholder = placeholder;
            setText(placeholder);
            setForeground(Color.GRAY);

            addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent e) {
                    if (showingPlaceholder) {
                        setText("");
                        setForeground(Color.BLACK);
                        showingPlaceholder = false;
                    }
                }

                public void focusLost(java.awt.event.FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(placeholder);
                        setForeground(Color.GRAY);
                        showingPlaceholder = true;
                    }
                }
            });
        }

        @Override
        public String getText() {
            return showingPlaceholder ? "" : super.getText();
        }
    }
}