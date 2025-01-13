package org.techventory.GUI;

import javax.swing.*;
import java.awt.*;

public class VentanaMateriales extends JFrame {
    public VentanaMateriales(){
        setTitle("Gestión de Materiales");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Aquí puedes incluir tu tabla, botones para agregar/editar materiales, etc.
        JLabel etiqueta = new JLabel("Gestión de Materiales (en construcción)");
        add(etiqueta, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
