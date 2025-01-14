package org.techventory.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuarios extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public VentanaUsuarios() {
        // Crear el marco principal
        JFrame frame = new JFrame("Gestión de Usuarios");
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout manual
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        frame.add(panel);

        // Etiqueta y campo de búsqueda
        JLabel lblBuscar = new JLabel("Buscar Usuario:");
        lblBuscar.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        lblBuscar.setBounds(30, 20, 120, 30);
        panel.add(lblBuscar);

        JTextField txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        txtBuscar.setBounds(150, 20, 400, 30);
        panel.add(txtBuscar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(560, 20, 100, 30);
        styleButton(btnBuscar);
        panel.add(btnBuscar);

        // Crear la tabla
        String[] columnNames = {"ID", "Nombre", "Usuario", "Rol"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Colocar la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 70, 630, 300);
        panel.add(scrollPane);

        // Botones inferiores
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(150, 390, 120, 40);
        styleButton(btnAgregar);
        panel.add(btnAgregar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(290, 390, 120, 40);
        styleButton(btnEditar);
        panel.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(430, 390, 120, 40);
        styleButton(btnEliminar);
        panel.add(btnEliminar);

        // Mostrar la ventana
        frame.setVisible(true);

        // Eventos de botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Abrir formulario para agregar usuario.");
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    JOptionPane.showMessageDialog(null, "Abrir formulario para editar usuario.");
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un usuario para editar.");
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
                if (filaSeleccionada != -1) {
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona un usuario para eliminar.");
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

