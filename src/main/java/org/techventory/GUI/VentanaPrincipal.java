package org.techventory.GUI;
import com.formdev.flatlaf.FlatLightLaf;
import org.techventory.DAO.ConexionDB;
import org.techventory.DAO.MaterialDAO;
import org.techventory.Modelo.Material;
import org.techventory.Util.BlobImageRenderer;
import org.techventory.Util.FormularioAsignacion;
import org.techventory.Util.InsertInventoryItemFrame;
import org.techventory.Util.UpdateInventoryFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;

import static org.techventory.Util.styles.styleButton;
import static org.techventory.Util.styles.styleTable;

public class VentanaPrincipal extends JFrame {
    private String usuarioLogueado;
    private JTabbedPane tabbedPane;
    private JTable tblInventario, tblUsuarios, tblAsignaciones, tblPortatiles, tblMoviles;
    private JButton btnNuevoItem, btnModificarItem, btnEliminarItem, btnAsignacion, btnNuevoUsuario;

    public VentanaPrincipal(String usuario) {
        this.usuarioLogueado = usuario;
        setTitle("Techventory - Usuario: " + usuarioLogueado);
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
        styleTable(tblInventario);
        panelInventario.add(new JScrollPane(tblInventario), BorderLayout.CENTER);

        // Panel de botones para inventario
        JPanel panelBotonesInventario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnNuevoItem = new JButton("Añadir");
        btnEliminarItem = new JButton("Eliminar");
        btnModificarItem = new JButton("Modificar");
        styleButton(btnNuevoItem);
        styleButton(btnEliminarItem);
        styleButton(btnModificarItem);
        panelBotonesInventario.add(btnNuevoItem);
        panelBotonesInventario.add(btnEliminarItem);
        panelBotonesInventario.add(btnModificarItem);
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

        btnEliminarItem.addActionListener(e -> eliminarItem());
        btnModificarItem.addActionListener(e -> modificarItem());


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

        //panel de Portatiles
        JPanel panelPortatiles = new JPanel(new BorderLayout());
        tblPortatiles = new JTable();
        panelPortatiles.add(new JScrollPane(tblPortatiles), BorderLayout.CENTER);

        //Panel de botones para Portatiles


        //Panel de Moviles
        JPanel panelMoviles = new JPanel(new BorderLayout());
        tblMoviles = new JTable();
        panelMoviles.add(new JScrollPane(tblMoviles), BorderLayout.CENTER);

        //Panel de botones  para Moviles


        // Agregar pestañas
        tabbedPane.addTab("Inventario", panelInventario);
        tabbedPane.addTab("Usuarios", panelUsuarios);
        tabbedPane.addTab("Asignaciones", panelAsignaciones);
        tabbedPane.addTab("Portatiles", panelPortatiles);
        tabbedPane.addTab("Móviles", panelMoviles);
        add(tabbedPane);
    }

    private void eliminarItem(){
        int filaSeleccionada = tblInventario.getSelectedRow();
        if (filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Seleccione un material para eliminar");
            return;
        }
        int id = (int) tblInventario.getValueAt(filaSeleccionada, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eleminar el elemento?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            try(Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement("DELETE FROM inventario WHERE id = ?")){
                ps.setInt(1, id);
                ps.executeUpdate();
                cargarDatosInventario();
                JOptionPane.showMessageDialog(this,"Ítem eliminado correctamente");
            }catch (SQLException ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el ítem: " + ex.getMessage());
            }
        }
    }

    private void modificarItem(){
        int filaSeleccionada = tblInventario.getSelectedRow();
        if (filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Selecciona un ítem para modificar");
            return;
        }

        int id = (int) tblInventario.getValueAt(filaSeleccionada, 0);
        String nombre = (String) tblInventario.getValueAt(filaSeleccionada, 1);
        String tipo = (String) tblInventario.getValueAt(filaSeleccionada, 2);
        int cantidad = (int) tblInventario.getValueAt(filaSeleccionada, 3);
        String detalles = (String) tblInventario.getValueAt(filaSeleccionada, 5);
        File imagenFile = null; // Si tienes la imagen guardada en el modelo, la pasas aquí

        // Crear el objeto Material con los nuevos datos
        Material material = new Material(id, nombre, tipo, cantidad, null, detalles);

        // Crear la instancia del formulario de actualización, pasando el material
        UpdateInventoryFrame update = new UpdateInventoryFrame(material);

        // Mostrar el formulario para modificar el ítem
        update.setVisible(true);
    }


    private void cargarDatosInventario() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Tipo", "Cantidad", "Imagen", "Detalles"}, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
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
            tblInventario.getColumnModel().getColumn(4).setCellRenderer(new BlobImageRenderer(120, 120));
            tblInventario.setRowHeight(150);
            tblInventario.getColumnModel().getColumn(4).setPreferredWidth(120);

            tblInventario.getColumnModel().getColumn(5).setCellRenderer(new MultiLineCellRender());
            tblInventario.getColumnModel().getColumn(5).setPreferredWidth(300);

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar inventario: " + ex.getMessage());
        }
    }

    static class MultiLineCellRender extends JTextArea implements TableCellRenderer{
        public MultiLineCellRender(){
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
            setText(value != null ? value.toString() : "");
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

            // Ajustar el alto de la fila automáticamente
            if (table.getRowHeight(row) < getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
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
