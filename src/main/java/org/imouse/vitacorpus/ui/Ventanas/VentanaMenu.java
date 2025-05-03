package org.imouse.vitacorpus.ui.Ventanas;

import org.imouse.vitacorpus.funciones.login.SessionManager;
import org.imouse.vitacorpus.ui.Ejecutable;

import javax.swing.*;
import java.awt.*;

import static java.lang.System.*;

public class VentanaMenu extends JFrame implements Ejecutable
{
    private static VentanaMenu ventanaMenu;
    private boolean flag = true;
    JFrame frame;

    private VentanaMenu()
    {
        frame = new JFrame("Vitacorpus - ¡Bienvenido!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static VentanaMenu getInstance()
    {
        if(ventanaMenu ==null)
        {
            ventanaMenu = new VentanaMenu();
        }
        return ventanaMenu;
    }

    @Override
    public void run()
    {
        SwingUtilities.invokeLater(() ->
        {
            frame.setLayout(new BorderLayout());
            JPanel panel = new JPanel(new GridBagLayout());
            frame.setContentPane(panel);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 20, 5, 20);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;

            JLabel saludo = new JLabel("Bienvenido al menú principal de Vitacorpus");
            saludo.setFont(new Font("Arial", Font.BOLD, 18));
            saludo.setHorizontalAlignment(SwingConstants.CENTER);
            saludo.setForeground(Color.BLACK);
            gbc.gridy = 0;
            panel.add(saludo,gbc);

            JLabel saludo2 = new JLabel("¿Qué vamos a hacer hoy, " +"?");
            saludo2.setFont(new Font("Arial", Font.ITALIC, 16));
            saludo2.setHorizontalAlignment(SwingConstants.CENTER);
            saludo2.setForeground(Color.BLACK);
            gbc.gridy = 1;
            panel.add(saludo2,gbc);

            BotonPersonalizado btnRegistro = new BotonPersonalizado("Registrar mis datos");
            gbc.gridy = 2;
            btnRegistro.addActionListener(e ->{
                frame.dispose();
                VentanaRegistroDatos.getInstance().run();
            });
            panel.add(btnRegistro,gbc);

            BotonPersonalizado btnObjetivos = new BotonPersonalizado("Manejar mis objetivos");
            gbc.gridy = 3;
            panel.add(btnObjetivos,gbc);

            BotonPersonalizado btnRestricciones = new BotonPersonalizado("Manejar mis restricciones alimenticias");
            gbc.gridy = 4;
            panel.add(btnRestricciones,gbc);

            BotonPersonalizado btnCalculadora = new BotonPersonalizado("Calculadora de sueño");
            gbc.gridy = 5;
            panel.add(btnCalculadora,gbc);

            BotonPersonalizado btnDietas = new BotonPersonalizado("Acceder al menú de dietas");
            gbc.gridy = 6;
            panel.add(btnDietas,gbc);

            BotonPersonalizado btnRutinas = new BotonPersonalizado("Acceder al menú de rutinas de ejercicio");
            gbc.gridy = 7;
            panel.add(btnRutinas,gbc);

            BotonPersonalizado btnSalir = new BotonPersonalizado("Salir");
            gbc.gridy = 8;
            panel.add(btnSalir,gbc);

            btnSalir.addActionListener(e-> {
                Object[] opciones = {"Salir","Cerrar sesión"};

                int opcionSeleccionada = JOptionPane.showOptionDialog(
                        null,
                        "¿Qué deseas realizar?",
                        "Vitacorpus - Saliendo...",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                if(opcionSeleccionada==0)
                {
                    exit(0);
                }
                else if (opcionSeleccionada==1)
                {
                    dispose();
                    JOptionPane.showMessageDialog(this,"Sesión cerrada. Dirigiendo a la ventana de Login...");
                    VentanaLoginSignUp.getInstance().LoginSignup();
                }
            });

            frame.setVisible(true);
            frame.toFront();
            frame.requestFocus();
        });
    }

    @Override
    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }
}
