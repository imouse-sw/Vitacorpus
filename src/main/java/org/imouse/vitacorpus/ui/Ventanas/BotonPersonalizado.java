package org.imouse.vitacorpus.ui.Ventanas;
import java.awt.*;
import javax.swing.*;

public class BotonPersonalizado extends JButton {
    private boolean presionado = false;

    public BotonPersonalizado(String text) {
        super(text);
        setFont(new Font("Calibri", Font.PLAIN | Font.BOLD, 16));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setForeground(Color.BLACK);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter()
        {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                presionado = true;
                setLocation(getX(), getY() + 2); // efecto hundido
                repaint();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                presionado = false;
                setLocation(getX(), getY() - 2);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int arc = 20;

        // Colores
        Color colorNormal = new Color(143,188,143);       // verde claro
        Color colorPresionado = new Color(34, 139, 34);     // verde oscuro

        g2.setColor(presionado ? colorPresionado : colorNormal);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Borde
        g2.setColor(new Color(0, 100, 0)); // borde más oscuro
        g2.setStroke(new BasicStroke(presionado ? 1f : 2f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        // Lo pintamos manualmente arriba
    }
}

