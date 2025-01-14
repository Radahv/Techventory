package org.techventory.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaMateriales extends JFrame {

    public VentanaMateriales(){

        // Crear el marco principal
        JFrame frame = new JFrame("Gestión de Materiales");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout manual
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        frame.add(panel);

        // Crear la tabla
        String[] columnNames = {"ID", "Nombre", "Cantidad", "Descripción"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        table.setRowHeight(25);

        // Colocar la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 30, 540, 200);
        panel.add(scrollPane);

        // Botones para las acciones
        JButton btnAgregar = new JButton("Agregar Material");
        btnAgregar.setBounds(50, 260, 150, 40);
        styleButton(btnAgregar);

        JButton btnEditar = new JButton("Editar Material");
        btnEditar.setBounds(225, 260, 150, 40);
        styleButton(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Material");
        btnEliminar.setBounds(400, 260, 150, 40);
        styleButton(btnEliminar);

        // Añadir eventos a los botones
        btnAgregar.addActionListener(this::agregarMaterial);
        btnEditar.addActionListener(this::editarMaterial);
        btnEliminar.addActionListener(this::eliminarMaterial);

        // Agregar los botones al panel
        panel.add(btnAgregar);
        panel.add(btnEditar);
        panel.add(btnEliminar);

        // Mostrar la ventana
        frame.setVisible(true);

        // cargarMateriales();
    }

    private void eliminarMaterial(ActionEvent actionEvent) {
    }

    private void editarMaterial(ActionEvent actionEvent) {
    }

    private void agregarMaterial(ActionEvent actionEvent) {
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
