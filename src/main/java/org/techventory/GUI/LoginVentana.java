package org.techventory.GUI;

import org.techventory.DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.techventory.Util.styles.styleButton;

public class LoginVentana extends JFrame {

    private JTextField userText;
    private JPasswordField passwordText;
    private UsuarioDAO usuarioDAO;

    public LoginVentana() {
        usuarioDAO = new UsuarioDAO();

        // Configuración de la ventana principal
        setTitle("Techventory");
        // 🔹 Cargar el icono desde recursos
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/iconos/alt-de-inventario.png"));
        setIconImage(imageIcon.getImage());
        setSize(500, 300);
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
        ImageIcon original = new ImageIcon(getClass().getResource("/iconos/circulo-de-usuario.png"));
        Image img = original.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(img);

        JLabel userLabel = new JLabel(iconoEscalado);
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
        ImageIcon original1 = new ImageIcon(getClass().getResource("/iconos/cerrar.png"));
        Image img1 = original1.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado1 = new ImageIcon(img1);
        JLabel passwordLabel = new JLabel(iconoEscalado1);
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
        ImageIcon original2 = new ImageIcon(getClass().getResource("/iconos/acceso.png"));
        Image img2 = original2.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado2 = new ImageIcon(img2);

        JButton loginButton = new JButton("Acceder");
        loginButton.setIcon(iconoEscalado2);
        loginButton.setHorizontalTextPosition(SwingConstants.LEFT);// Movemos el icono a la derecha del texto
        styleButton(loginButton);
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
