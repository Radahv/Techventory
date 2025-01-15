package org.techventory.GUI;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.techventory.DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginVentana extends JFrame {

    private JTextField userText;
    private JPasswordField passwordText;
    private UsuarioDAO usuarioDAO;

    public LoginVentana(){
        usuarioDAO = new UsuarioDAO();

        FlatLightLaf.setup();

        // Crear el marco principal
        JFrame frame = new JFrame("Techventory");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centrar la ventana

        // Crear el panel principal con un diseño
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240)); // Fondo claro
        panel.setLayout(new GridBagLayout()); // Usar GridBagLayout para centrar elementos
        frame.add(panel);

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
        loginButton.setBackground(new Color(193, 18, 31)); // Naranja
        loginButton.setForeground(Color.WHITE); // Texto blanco
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Ocupar todo el ancho
        panel.add(loginButton, gbc);

        // Mostrar el marco
        frame.setVisible(true);

        loginButton.addActionListener((ActionEvent e) -> iniciarSesion());
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

            //Crea la ventana principal solo si las credenciales son correctas
            VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
            ventanaPrincipal.setVisible(true);
            dispose();//Cierra la ventana actual
        } else {
           JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
       }
    }

    public static void main(String[] args) {
        new LoginVentana();
    }
}
