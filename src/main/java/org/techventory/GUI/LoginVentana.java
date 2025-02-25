package org.techventory.GUI;

import org.techventory.DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginVentana extends JFrame {

    private JTextField userText;
    private JPasswordField passwordText;
    private UsuarioDAO usuarioDAO;

    public LoginVentana() {
        usuarioDAO = new UsuarioDAO();

        // Configuración de la ventana principal
        setTitle("Techventory - Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal con un diseño
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        panel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar elementos
        add(panel);

        // Configuración de GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Etiqueta de "Usuario"
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        // Campo de texto para el usuario
        userText = new JTextField(15);
        userText.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(userText, gbc);

        // Etiqueta de "Contraseña"
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passwordLabel, gbc);

        // Campo de texto para la contraseña
        passwordText = new JPasswordField(15);
        passwordText.setFont(new Font("Sans-serif", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordText, gbc);

        // Botón "Iniciar Sesión"
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setFont(new Font("Sans-serif", Font.BOLD, 14));
        loginButton.setBackground(new Color(193, 18, 31)); // Rojo oscuro
        loginButton.setForeground(Color.WHITE); // Texto blanco
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupar todo el ancho
        panel.add(loginButton, gbc);

        // Agregar acción al botón
        loginButton.addActionListener((ActionEvent e) -> iniciarSesion());

        // Mostrar la ventana
        setVisible(true);
    }

    private void iniciarSesion() {
        String usuario = userText.getText();
        String password = new String(passwordText.getPassword());

        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        if (usuarioDAO.validarUsuario(usuario, password)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");

            // Crea la ventana principal con el usuario logueado
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(usuario);
            ventanaPrincipal.setVisible(true);

            // Cierra la ventana de login
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        }
    }
}
