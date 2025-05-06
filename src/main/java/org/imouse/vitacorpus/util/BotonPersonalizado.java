package org.imouse.vitacorpus.util;
import java.awt.*;
import javax.swing.*;

public class BotonPersonalizado extends JButton {
    private boolean presionado = false;

    public BotonPersonalizado(String text) {
        super(text);
        setFont(new Font("Calibri", Font.BOLD, 16));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false); // usaremos nuestro propio relleno
        setOpaque(false);
        setForeground(Color.BLACK);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                presionado = true;
                setLocation(getX(), getY() + 2);
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

        // Usar el color dinámico asignado desde fuera
        Color colorNormal = getBackground();
        Color colorPresionado = colorNormal.darker();

        g2.setColor(presionado ? colorPresionado : colorNormal);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Borde
        g2.setColor(colorNormal.darker());
        g2.setStroke(new BasicStroke(presionado ? 1f : 2f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        // Borde ya pintado en paintComponent
    }
}


