package org.techventory;

import com.formdev.flatlaf.FlatLightLaf;
import org.techventory.GUI.LoginVentana;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new LoginVentana().setVisible(true));
    }
}
