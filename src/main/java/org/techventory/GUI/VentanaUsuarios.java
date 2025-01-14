package org.techventory.GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaUsuarios extends JFrame {
    public VentanaUsuarios(){
        setTitle("Gestión de Usuarios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Aquí puedes incluir tu tabla, botones para agregar/editar materiales, etc.
        JLabel etiqueta = new JLabel("Gestión de Usuarios (en construcción)");
        add(etiqueta, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
