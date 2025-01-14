package org.techventory.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal(){

        // Crear el marco principal
        JFrame frame = new JFrame("Techventory");
        frame.setSize(400, 400); // Tamaño de la ventana
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal con un diseño nulo
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout manual para control total
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        frame.add(panel);

        // Crear botones para el menú principal
        JButton btnGestionMateriales = new JButton("Gestión de Materiales");
        btnGestionMateriales.setBounds(50, 50, 300, 50); // Posición y tamaño
        styleButton(btnGestionMateriales); // Aplicar estilo

        JButton btnGestionAsignaciones = new JButton("Gestión de Asignaciones");
        btnGestionAsignaciones.setBounds(50, 130, 300, 50); // Posición y tamaño
        styleButton(btnGestionAsignaciones); // Aplicar estilo

        JButton btnGestionUsuarios = new JButton("Gestión de Usuarios");
        btnGestionUsuarios.setBounds(50, 210, 300, 50); // Posición y tamaño
        styleButton(btnGestionUsuarios); // Aplicar estilo

        // Agregar botones al panel
        panel.add(btnGestionMateriales);
        panel.add(btnGestionAsignaciones);
        panel.add(btnGestionUsuarios);

        //Mostrar el marco
        frame.setVisible(true);

        //Eventos de los botones
        btnGestionMateriales.addActionListener(this::abrirMateriales);
        btnGestionAsignaciones.addActionListener(this::abrirAsignaciones);
        btnGestionUsuarios.addActionListener(this::abrirUsuarios);
    }

    // Método para dar estilo a los botones
    private static void styleButton(JButton button) {
        button.setFont(new Font("Sans-serif", Font.BOLD, 16)); // Fuente y tamaño
        button.setBackground(new Color(193, 18, 31)); // Fondo Naranja
        button.setForeground(Color.WHITE); // Texto negro
        button.setFocusPainted(false); // Quitar el borde de foco
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Borde sutil
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
    }

    private void abrirMateriales(ActionEvent e) {
        new VentanaMateriales();
    }

    private void abrirAsignaciones(ActionEvent e) {
        new VentanaAsignaciones();
    }

    private void abrirUsuarios(ActionEvent e) {
        new VentanaUsuarios();
    }
}
