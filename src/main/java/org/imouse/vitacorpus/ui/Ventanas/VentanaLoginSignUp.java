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
public class VentanaLoginSignUp extends JFrame
{
    private static VentanaLoginSignUp ventanaLoginSignUp;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField usernameField;
    private boolean esRegistro = false;

    public static VentanaLoginSignUp getInstance()
    {
        if(ventanaLoginSignUp==null)
        {
            ventanaLoginSignUp = new VentanaLoginSignUp();
        }
        return ventanaLoginSignUp;
    }

    public void LoginSignup() {
        setTitle("Vitacorpus - Iniciar sesión / Registrarse");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel con imagen de fondo
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

        JLabel labelModo = new JLabel("Iniciar sesión");
        labelModo.setFont(new Font("Arial", Font.BOLD, 18));
        labelModo.setHorizontalAlignment(SwingConstants.CENTER);
        labelModo.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        fondoPanel.add(labelModo, gbc);

        gbc.gridwidth = 1;

        usernameField = new JTextField();
        usernameField.setVisible(false);
        gbc.gridy = 1;
        fondoPanel.add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        fondoPanel.add(usernameField, gbc);
        gbc.gridx = 0;

        emailField = new JTextField();
        gbc.gridy = 2;
        fondoPanel.add(new JLabel("Correo:"), gbc);
        gbc.gridx = 1;
        fondoPanel.add(emailField, gbc);
        gbc.gridx = 0;

        passwordField = new JPasswordField();
        gbc.gridy = 3;
        fondoPanel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        fondoPanel.add(passwordField, gbc);
        gbc.gridx = 0;

        JButton accionButton = new JButton("Iniciar sesión");
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        fondoPanel.add(accionButton, gbc);

        JButton cambiarModo = new JButton("¿No tienes cuenta? Regístrate");
        cambiarModo.setBorderPainted(false);
        cambiarModo.setFocusPainted(false);
        cambiarModo.setContentAreaFilled(false);
        cambiarModo.setForeground(Color.BLUE);
        cambiarModo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 5;
        fondoPanel.add(cambiarModo, gbc);

        accionButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (esRegistro)
            {
                String username = usernameField.getText().trim();
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Completa todos los campos.");
                    return;
                }

                List<Usuario> usuarios = UsuarioHiberImpl.getInstance().findAll();
                boolean userExists = usuarios.stream().anyMatch(u -> u.getUsuario().equals(username));
                boolean emailExists = usuarios.stream().anyMatch(u -> u.getEmail().equals(email));

                if(!email.contains("@") || !email.endsWith(".com"))
                {
                    JOptionPane.showMessageDialog(this,"Correo inválido. Asegúrate de que tenga un '@' y termine en '.com'");
                    return;
                }

                if (userExists || emailExists) {
                    JOptionPane.showMessageDialog(this, "Usuario o correo ya en uso.");
                    return;
                }

                Usuario nuevo = new Usuario();
                nuevo.setUsuario(username);
                nuevo.setEmail(email);
                nuevo.setPassword(password);
                UsuarioHiberImpl.getInstance().save(nuevo);
                JOptionPane.showMessageDialog(this, "Usuario registrado con éxito.");
                cambiarModo.doClick();
            }
            else
            {
                Usuario usuario = UsuarioHiberImpl.getInstance().findAll().stream()
                        .filter(u -> u.getEmail().equals(email) && u.getPassword().equals(password))
                        .findFirst()
                        .orElse(null);

                if (usuario != null)
                {
                    SessionManager.setUsuarioActual(usuario);
                    JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario.getUsuario() + "!");
                    dispose();
                    VentanaMenu.getInstance().run();
                } else {
                    JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");
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
}
