package com.example.TDD.view;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {

    private final Color color;
    private final int circleSize;

    public Circle(Color color, int circleSize, String tooltip) {
        this.color = color;
        this.circleSize = circleSize;
        this.setPreferredSize(new Dimension(circleSize, circleSize));
        this.setToolTipText(tooltip);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Cast the Graphics object to Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(color);
        g2d.fillOval(0, 0, circleSize, circleSize);

        // Dispose the Graphics2D object to release system resources
        g2d.dispose();
    }

}
