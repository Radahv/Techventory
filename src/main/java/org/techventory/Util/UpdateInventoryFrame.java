package org.techventory.Util;

import org.techventory.DAO.MaterialDAO;
import org.techventory.Modelo.Material;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UpdateInventoryFrame extends JFrame {
    private JTextField txtNombre;
    private JTextField txtTipo;
    private JTextField txtCantidad;
    private JLabel lblImagen;
    private JTextField txtDetalles;
    private JButton btnSeleccionarImagen;
    private JButton btnGuardar;

    private File imagenFile = null;
    private Material material;  // Para almacenar el material que se está editando

    public UpdateInventoryFrame(Material material) {
        this.material = material;  // Guardamos el material recibido
        setTitle("Actualizar Material");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
        cargarDatosMaterial();  // Llenamos los campos con la información actual
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);

        txtNombre = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(txtNombre, gbc);
        gbc.gridwidth = 1;

        // Tipo
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Tipo:"), gbc);

        txtTipo = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        panel.add(txtTipo, gbc);
        gbc.gridwidth = 1;

        // Cantidad
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Cantidad:"), gbc);

        txtCantidad = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 2;
        panel.add(txtCantidad, gbc);
        gbc.gridwidth = 1;

        // Imagen
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Imagen:"), gbc);

        lblImagen = new JLabel("Ningún archivo seleccionado");
        lblImagen.setPreferredSize(new Dimension(200, 20));
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(lblImagen, gbc);
        gbc.gridwidth = 1;

        // Detalles
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Detalles:"), gbc);

        txtDetalles = new JTextField();
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        panel.add(txtDetalles, gbc);
        gbc.gridwidth = 1;

        // Botón para seleccionar imagen
        btnSeleccionarImagen = new JButton("Seleccionar Imagen");
        gbc.gridx = 1; gbc.gridy = 5;
        panel.add(btnSeleccionarImagen, gbc);

        btnSeleccionarImagen.addActionListener(e -> seleccionarImagen());

        // Botón Guardar
        btnGuardar = new JButton("Guardar");
        gbc.gridx = 1; gbc.gridy = 6;
        panel.add(btnGuardar, gbc);

        btnGuardar.addActionListener(e -> guardarItem());

        add(panel);
    }

    private void cargarDatosMaterial() {
        if (material != null) {
            txtNombre.setText(material.getNombre());
            txtTipo.setText(material.getTipo());
            txtCantidad.setText(String.valueOf(material.getCantidad()));
            txtDetalles.setText(material.getDetalles());
            lblImagen.setText(material.getImagen() != null ? "Imagen cargada" : "Ningún archivo seleccionado");
        }
    }

    private void seleccionarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes", "jpg", "png", "gif", "jpeg"));

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            imagenFile = fileChooser.getSelectedFile();
            lblImagen.setText(imagenFile.getName());
        }
    }

    private void guardarItem() {
        String nombre = txtNombre.getText().trim();
        String tipo = txtTipo.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();
        String detalles = txtDetalles.getText().trim();

        if (nombre.isEmpty() || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número.");
            return;
        }

        // Actualizamos el objeto Material con los nuevos datos
        material.setNombre(nombre);
        material.setTipo(tipo);
        material.setCantidad(cantidad);
        material.setDetalles(detalles);

        // Llamamos al DAO para modificar el material en la base de datos
        MaterialDAO dao = new MaterialDAO();
        dao.modificarItem(material, imagenFile);

        JOptionPane.showMessageDialog(this, "Material actualizado correctamente.");
        dispose(); // Cierra la ventana tras guardar
    }
}
