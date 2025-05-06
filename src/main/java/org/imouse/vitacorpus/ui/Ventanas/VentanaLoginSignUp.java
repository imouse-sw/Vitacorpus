package org.imouse.vitacorpus.ui.Ventanas;
import lombok.NoArgsConstructor;
import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.model.Usuario;
import org.imouse.vitacorpus.sql.hiberimpl.UsuarioHiberImpl;
import org.imouse.vitacorpus.ui.MenuPrincipal;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@NoArgsConstructor
public class VentanaLoginSignUp extends JFrame {
    private static VentanaLoginSignUp ventanaLoginSignUp;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private boolean esRegistro = false;

    public static VentanaLoginSignUp getInstance() {
        if (ventanaLoginSignUp == null)
        {
            ventanaLoginSignUp = new VentanaLoginSignUp();
        }
        return ventanaLoginSignUp;
    }

    public void LoginSignup() {
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/img/logo.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(120, 110, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(imagenEscalada));
        logo.setOpaque(false);
        logo.setHorizontalAlignment(SwingConstants.CENTER);

        setTitle("Vitacorpus - Iniciar sesión / Registrarse");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel fondoPanel = new JPanel(new GridBagLayout()) {
            private final Image fondo = new ImageIcon(getClass().getResource("/img/fondito.jpeg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        fondoPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        fondoPanel.add(logo, gbc);

        JLabel labelModo = new JLabel("Iniciar sesión");
        labelModo.setFont(new Font("Arial", Font.BOLD, 18));
        labelModo.setHorizontalAlignment(SwingConstants.CENTER);
        labelModo.setForeground(Color.BLACK);
        gbc.gridy = 1;
        fondoPanel.add(labelModo, gbc);
        gbc.gridwidth = 1;

        usernameField = new JTextField();
        usernameField.setVisible(false);
        agregarPlaceholder(usernameField, "Usuario");
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        fondoPanel.add(usernameField, gbc);
        gbc.gridwidth = 1;

        emailField = new JTextField();
        agregarPlaceholder(emailField, "Correo electrónico");
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        fondoPanel.add(emailField, gbc);
        gbc.gridwidth = 1;

        passwordField = new JPasswordField();
        agregarPlaceholder(passwordField, "Contraseña");
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        fondoPanel.add(passwordField, gbc);
        gbc.gridwidth = 1;

        JButton accionButton = new JButton("Iniciar sesión");
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        fondoPanel.add(accionButton, gbc);

        JButton cambiarModo = new JButton("¿No tienes cuenta? Regístrate");
        cambiarModo.setBorderPainted(false);
        cambiarModo.setFocusPainted(false);
        cambiarModo.setContentAreaFilled(false);
        cambiarModo.setForeground(Color.BLUE);
        cambiarModo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 6;
        fondoPanel.add(cambiarModo, gbc);

        accionButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (esRegistro) {
                String username = usernameField.getText().trim();
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    mostrarMensaje("Completa todos los campos.", false);
                    return;
                }

                if (!email.contains("@") || !email.endsWith(".com")) {
                    mostrarMensaje("Correo inválido. Asegúrate de que tenga un '@' y termine en '.com'", false);
                    return;
                }

                List<Usuario> usuarios = UsuarioHiberImpl.getInstance().findAll();
                boolean userExists = usuarios.stream().anyMatch(u -> u.getUsuario().equals(username));
                boolean emailExists = usuarios.stream().anyMatch(u -> u.getEmail().equals(email));

                if (userExists || emailExists) {
                    mostrarMensaje("Usuario o correo ya en uso.", false);
                    return;
                }

                Usuario nuevo = new Usuario();
                nuevo.setUsuario(username);
                nuevo.setEmail(email);
                nuevo.setPassword(password);
                UsuarioHiberImpl.getInstance().save(nuevo);
                mostrarMensaje("Usuario registrado con éxito.", false);
                cambiarModo.doClick();
            } else {
                Usuario usuario = UsuarioHiberImpl.getInstance().findAll().stream()
                        .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                        .findFirst()
                        .orElse(null);

                if (usuario != null) {
                    SessionManager.setUsuarioActual(usuario);
                    mostrarMensaje("Bienvenid@, " + usuario.getUsuario() + "!", true);
                    dispose();
                    VentanaMenu.getInstance().run();
                } else {
                    mostrarMensaje("Correo o contraseña incorrectos.", true);
                }
            }
        });

        cambiarModo.addActionListener(e -> {
            esRegistro = !esRegistro;
            usernameField.setVisible(esRegistro);
            labelModo.setText(esRegistro ? "Registrarse" : "Iniciar sesión");
            accionButton.setText(esRegistro ? "Registrarse" : "Iniciar sesión");
            cambiarModo.setText(esRegistro ? "¿Ya tienes cuenta? Inicia sesión" : "¿No tienes cuenta? Regístrate");
            pack();
        });

        setContentPane(fondoPanel);
        setVisible(true);
    }

    private void mostrarMensaje(String mensaje, boolean autoCerrar) {
        JDialog dialog = new JDialog(this, "Mensaje", true);
        dialog.setSize(600, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel(mensaje, SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
        label.setBackground(new Color(143, 188, 143));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        dialog.add(label, BorderLayout.CENTER);

        if (!autoCerrar) {
            JButton cerrar = new JButton("Aceptar");
            cerrar.setFont(new Font("Arial", Font.PLAIN, 14));
            cerrar.setBackground(new Color(60, 179, 113));
            cerrar.setForeground(Color.BLACK);
            cerrar.addActionListener(e -> dialog.dispose());

            JPanel panelBoton = new JPanel();
            panelBoton.setBackground(new Color(224, 255, 255));
            panelBoton.add(cerrar);
            dialog.add(panelBoton, BorderLayout.SOUTH);
        } else {
            new javax.swing.Timer(2000, e -> dialog.dispose()).start();
        }

        dialog.setVisible(true);
    }

    private void agregarPlaceholder(JTextField campo, String placeholder) {
        campo.setText(placeholder);
        campo.setForeground(Color.LIGHT_GRAY);

        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }
        });

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }
}




