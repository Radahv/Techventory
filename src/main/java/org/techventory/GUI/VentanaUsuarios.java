package org.techventory.GUI;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuarios extends JFrame {
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public VentanaUsuarios() {

        FlatLightLaf.setup();

        // Crear el marco principal
        JFrame frame = new JFrame("Gestión de Usuarios");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        frame.add(panel);

//        // Etiqueta y campo de búsqueda
//        JLabel lblBuscar = new JLabel("Buscar Usuario:");
//        lblBuscar.setFont(new Font("Sans-serif", Font.PLAIN, 14));
//        lblBuscar.setBounds(30, 20, 120, 30);
//        panel.add(lblBuscar);
//
//        JTextField txtBuscar = new JTextField();
//        txtBuscar.setFont(new Font("Sans-serif", Font.PLAIN, 14));
//        txtBuscar.setBounds(150, 20, 400, 30);
//        panel.add(txtBuscar);
//
//        JButton btnBuscar = new JButton("Buscar");
//        btnBuscar.setBounds(560, 20, 100, 30);
//        styleButton(btnBuscar);
//        panel.add(btnBuscar);

        // Crear la tabla
        String[] columnNames = {"ID", "Nombre", "Usuario", "Rol"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Aumentar la altura del encabezado
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 30)); // Altura del encabezado
        header.setFont(new Font("Sans-serif", Font.BOLD, 16)); // Fuente del encabezado

        // Colocar la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones inferiores
        JButton btnAgregar = new JButton("Agregar Usuario");
        btnAgregar.setPreferredSize(new Dimension(200, 50));
        styleButton(btnAgregar);

        JButton btnEditar = new JButton("Editar Usuario");
        btnEditar.setPreferredSize(new Dimension(200, 50));
        styleButton(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Usuario");
        btnEliminar.setPreferredSize(new Dimension(200, 50));
        styleButton(btnEliminar);

        // Crear un panel para los botones
        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Espaciado entre botones
        botonPanel.setBackground(new Color(240, 240, 240)); // Fondo claro
        botonPanel.add(btnAgregar);
        botonPanel.add(btnEditar);
        botonPanel.add(btnEliminar);

        // Añadir el panel de botones al panel principal
        panel.add(botonPanel, BorderLayout.SOUTH);

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

    public static void main(String[] args) {
        new VentanaUsuarios();
    }
}

