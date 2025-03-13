package org.techventory.Util;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class styles {

    // Método para dar estilo a los botones
    public static void styleButton(JButton button) {
        button.setFont(new Font("Sans-serif", Font.BOLD, 14)); // Fuente y tamaño
        button.setBackground(new Color(193, 18, 31)); // Fondo naranja
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false); // Quitar el borde de foco
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Borde sutil
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
        button.setPreferredSize(new Dimension(200, 40)); // Tamaño uniforme para los botones
    }

    public static void styleTable(JTable table) {
        // 🔹 Cambiar color de fondo y texto de la tabla
        table.setBackground(Color.WHITE);
        table.setForeground(Color.DARK_GRAY);
        table.setSelectionBackground(new Color(33, 150, 243)); // Azul
        table.setSelectionForeground(Color.WHITE);

        // 🔹 Espaciado entre filas y altura
        table.setRowHeight(25);
        table.setIntercellSpacing(new Dimension(1, 1)); // Espacio entre celdas

        // 🔹 Estilo del borde externo
        table.setBorder(new LineBorder(new Color(200, 200, 200), 1)); // Borde gris claro

        // 🔹 Bordes internos entre celdas
        table.setGridColor(new Color(220, 220, 220)); // Gris claro para líneas internas
        table.setShowGrid(true);

        // 🔹 Estilo del encabezado
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(new Color(193, 18, 31)); // Naranja
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false); // Evita mover columnas
        header.setBorder(new LineBorder(new Color(180, 180, 180), 1)); // Borde para el header

        // 🔹 Alternar colores en filas (Efecto zebra)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(240, 240, 240)); // Gris claro alternado
                }

                // 🔹 Bordes en las celdas
                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(new LineBorder(new Color(210, 210, 210), 1));
                }

                return c;
            }
        });
    }
}
