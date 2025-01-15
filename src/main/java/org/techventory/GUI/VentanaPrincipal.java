package org.techventory.GUI;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(){

        FlatLightLaf.setup();

        // Configurar la ventana principal (esta misma instancia)
        setTitle("Techventory");
        setSize(400, 400); // Tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana
        setLayout(null); // Layout manual para control total

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
        add(btnGestionMateriales);
        add(btnGestionAsignaciones);
        add(btnGestionUsuarios);

        //Eventos de los botones
        btnGestionMateriales.addActionListener(this::abrirMateriales);
        btnGestionAsignaciones.addActionListener(this::abrirAsignaciones);
        btnGestionUsuarios.addActionListener(this::abrirUsuarios);
    }

    // Método para dar estilo a los botones
    private static void styleButton(JButton button) {
        button.setFont(new Font("Sans-serif", Font.BOLD, 16)); // Fuente y tamaño
        button.setBackground(new Color(193, 18, 31)); // Fondo Naranja
        button.setForeground(Color.WHITE); // Texto blanco
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
