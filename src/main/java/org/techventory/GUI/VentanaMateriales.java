package org.techventory.GUI;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaMateriales extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;

    public VentanaMateriales() {

        FlatLightLaf.setup();

        // Crear el marco principal
        JFrame frame = new JFrame("Gestión de Materiales");
        frame.setSize(900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        frame.add(panel);

        // Crear la tabla
        String[] columnNames = {"ID", "Nombre", "Cantidad", "Imagen", "Descripción"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        table.setRowHeight(30); // Aumentar la altura de las filas

        // Aumentar la altura del encabezado
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getWidth(), 30)); // Altura del encabezado
        header.setFont(new Font("Sans-serif", Font.BOLD, 16)); // Fuente del encabezado

        // Colocar la tabla en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones para las acciones
        JButton btnAgregar = new JButton("Agregar Material");
        styleButton(btnAgregar);

        JButton btnEditar = new JButton("Editar Material");
        styleButton(btnEditar);

        JButton btnEliminar = new JButton("Eliminar Material");
        styleButton(btnEliminar);

        // Crear un panel para los botones
        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Espaciado entre botones
        botonPanel.setBackground(new Color(240, 240, 240)); // Fondo claro
        botonPanel.add(btnAgregar);
        botonPanel.add(btnEditar);
        botonPanel.add(btnEliminar);

        // Añadir el panel de botones al panel principal
        panel.add(botonPanel, BorderLayout.SOUTH);

        // Añadir eventos a los botones
        btnAgregar.addActionListener(this::agregarMaterial);
        btnEditar.addActionListener(this::editarMaterial);
        btnEliminar.addActionListener(this::eliminarMaterial);

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void agregarMaterial(ActionEvent actionEvent) {
        // Implementar lógica para agregar material
        JOptionPane.showMessageDialog(this, "Función para agregar material");
    }

    private void editarMaterial(ActionEvent actionEvent) {
        // Implementar lógica para editar material
        JOptionPane.showMessageDialog(this, "Función para editar material");
    }

    private void eliminarMaterial(ActionEvent actionEvent) {
        // Implementar lógica para eliminar material
        JOptionPane.showMessageDialog(this, "Función para eliminar material");
    }

    // Método para dar estilo a los botones
    private static void styleButton(JButton button) {
        button.setFont(new Font("Sans-serif", Font.BOLD, 14)); // Fuente y tamaño
        button.setBackground(new Color(193, 18, 31)); // Fondo naranja
        button.setForeground(Color.WHITE); // Texto blanco
        button.setFocusPainted(false); // Quitar el borde de foco
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Borde sutil
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
        button.setPreferredSize(new Dimension(200, 40)); // Tamaño uniforme para los botones
    }

    public static void main(String[] args) {
        new VentanaMateriales();
    }
}
