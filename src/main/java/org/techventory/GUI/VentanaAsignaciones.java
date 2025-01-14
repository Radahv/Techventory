package org.techventory.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAsignaciones extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public VentanaAsignaciones() {
        // Crear el marco principal
        JFrame frame = new JFrame("Asignación de Materiales");
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout manual
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        frame.add(panel);

        // Crear la tabla
        String[] columnNames = {"Usuario", "Material", "Cantidad Asignada"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Colocar la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 540, 250);
        panel.add(scrollPane);

        // Botón para asignar material
        JButton btnAsignarMaterial = new JButton("Asignar Material");
        btnAsignarMaterial.setBounds(200, 300, 200, 40); // Centrado horizontalmente
        styleButton(btnAsignarMaterial); // Aplicar estilo

        // Agregar botón al panel
        panel.add(btnAsignarMaterial);

        // Mostrar la ventana
        frame.setVisible(true);

        // Evento del botón Asignar
        btnAsignarMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField campoUsuario = new JTextField();
                JTextField campoMaterial = new JTextField();
                JTextField campoCantidad = new JTextField();

                Object[] mensaje = {
                        "Usuario:", campoUsuario,
                        "Material:", campoMaterial,
                        "Cantidad:", campoCantidad
                };

                int opcion = JOptionPane.showConfirmDialog(null, mensaje, "Asignar Material", JOptionPane.OK_CANCEL_OPTION);
                if (opcion == JOptionPane.OK_OPTION) {
                    String usuario = campoUsuario.getText();
                    String material = campoMaterial.getText();
                    int cantidad = Integer.parseInt(campoCantidad.getText());

                    // Aquí iría la lógica para actualizar la base de datos
                    modeloTabla.addRow(new Object[]{usuario, material, cantidad});
                }
            }
        });
    }

    // Método para dar estilo a los botones
    private static void styleButton(JButton button) {
        button.setFont(new Font("Sans-serif", Font.BOLD, 14)); // Fuente y tamaño
        button.setBackground(new Color(193, 18, 31)); // Fondo naranja
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false); // Quitar el borde de foco
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Borde sutil
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
    }
}

