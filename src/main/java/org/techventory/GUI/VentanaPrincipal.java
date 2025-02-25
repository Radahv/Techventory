package org.techventory.GUI;
import org.techventory.DAO.ConexionDB;
import org.techventory.Util.BlobImageRenderer;
import org.techventory.Util.FormularioAsignacion;
import org.techventory.Util.InsertInventoryItemFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Style;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class VentanaPrincipal extends JFrame {
    private String usuarioLogueado;
    private JTabbedPane tabbedPane;
    private JTable tblInventario, tblUsuarios, tblAsignaciones;
    private JButton btnNuevoItem, btnAsignacion, btnNuevoUsuario;

    public VentanaPrincipal(String usuario) {
        this.usuarioLogueado = usuario;
        setTitle("Inventario - Usuario: " + usuarioLogueado);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        cargarDatosInventario();
        cargarDatosUsuarios();
        cargarAsignaciones();
    }

    private void initComponents() {
        tabbedPane = new JTabbedPane();

        // Panel de Inventario
        JPanel panelInventario = new JPanel(new BorderLayout());
        tblInventario = new JTable();
        panelInventario.add(new JScrollPane(tblInventario), BorderLayout.CENTER);

        // Panel de botones para inventario
        JPanel panelBotonesInventario = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnNuevoItem = new JButton("Añadir Material");
        styleButton(btnNuevoItem);
        panelBotonesInventario.add(btnNuevoItem);
        panelInventario.add(panelBotonesInventario, BorderLayout.SOUTH);

        btnNuevoItem.addActionListener(e -> {
            InsertInventoryItemFrame insertFrame = new InsertInventoryItemFrame();
            insertFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarDatosInventario(); // Recargar datos al cerrar
                }
            });
            insertFrame.setVisible(true);
        });

        // Panel de Usuarios
        JPanel panelUsuarios = new JPanel(new BorderLayout());
        tblUsuarios = new JTable();
        panelUsuarios.add(new JScrollPane(tblUsuarios), BorderLayout.CENTER);

        // Panel de botones para Usuarios
        JPanel panelBotonesUsuarios = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnNuevoUsuario = new JButton("Añadir usuario");
        styleButton(btnNuevoUsuario);
        panelBotonesUsuarios.add(btnNuevoUsuario);
        panelUsuarios.add(panelBotonesUsuarios, BorderLayout.SOUTH);

        // Panel de Asignaciones
        JPanel panelAsignaciones = new JPanel(new BorderLayout());
        tblAsignaciones = new JTable();
        panelAsignaciones.add(new JScrollPane(tblAsignaciones), BorderLayout.CENTER);

        // Panel de botones para asignaciones
        JPanel panelBotonesAsignaciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnAsignacion = new JButton("Asignar");
        styleButton(btnAsignacion);
        panelBotonesAsignaciones.add(btnAsignacion);
        panelAsignaciones.add(panelBotonesAsignaciones, BorderLayout.SOUTH);

        btnAsignacion.addActionListener(e -> {
            FormularioAsignacion formularioAsignacion = new FormularioAsignacion();
            formularioAsignacion.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarAsignaciones(); // Recargar datos al cerrar
                }
            });
            formularioAsignacion.setVisible(true);
        });

        // Agregar pestañas
        tabbedPane.addTab("Inventario", panelInventario);
        tabbedPane.addTab("Usuarios", panelUsuarios);
        tabbedPane.addTab("Asignaciones", panelAsignaciones);

        add(tabbedPane);
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

    private void cargarDatosInventario() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Tipo", "Cantidad", "Imagen", "Detalles"}, 0);
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, nombre, tipo, cantidad, imagen, detalles FROM inventario")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                int cantidad = rs.getInt("cantidad");
                String detalles = rs.getString("detalles");

                // Convertir BLOB a byte[]
                Blob blob = rs.getBlob("imagen");
                byte[] imageBytes = (blob != null) ? blob.getBytes(1, (int) blob.length()) : null;
                if (blob != null) blob.free();

                model.addRow(new Object[]{id, nombre, tipo, cantidad, imageBytes, detalles});
            }

            tblInventario.setModel(model);
            tblInventario.getColumnModel().getColumn(4).setCellRenderer(new BlobImageRenderer(150, 150));
            tblInventario.setRowHeight(150);
            tblInventario.getColumnModel().getColumn(4).setPreferredWidth(150);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + ex.getMessage());
        }
    }

    private void cargarDatosUsuarios() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Email"}, 0);
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, nombre, email FROM usuarios")) {

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nombre"), rs.getString("email")});
            }

            tblUsuarios.setModel(model);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar usuarios: " + ex.getMessage());
        }
    }

    private void cargarAsignaciones() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Usuario", "Material", "Cantidad", "Fecha de Asignación"}, 0);
        String sql = "SELECT a.id, u.nombre AS nombre_usuario, i.nombre AS nombre_material, a.cantidad_asignada, a.asignado_en " +
                "FROM asignaciones a " +
                "JOIN usuarios u ON a.id_usuario = u.id " +
                "JOIN inventario i ON a.id_inventario = i.id";

        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nombre_usuario"),
                        rs.getString("nombre_material"),
                        rs.getInt("cantidad_asignada"),
                        rs.getString("asignado_en")
                });
            }

            tblAsignaciones.setModel(model);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar asignaciones: " + e.getMessage());
        }
    }
}
