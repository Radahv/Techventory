package org.techventory.DAO;

import org.techventory.Modelo.Material;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    // Crear un nuevo material
    public boolean crearMaterial(Material material) {
        String query = "INSERT INTO inventario (nombre, tipo, cantidad, imagen, detalles) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, material.getNombre());
            stmt.setString(2, material.getTipo());
            stmt.setInt(3, material.getCantidad());
            stmt.setBytes(4, material.getImagen()); // Imagen como bytes
            stmt.setString(5, material.getDetalles());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Leer todos los materiales
    public List<Material> obtenerMateriales() {
        String query = "SELECT * FROM inventario";
        List<Material> materiales = new ArrayList<>();
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getBytes("imagen"),
                        rs.getString("detalles")
                );
                materiales.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materiales;
    }

    // Actualizar cantidad de un material
    public boolean actualizarCantidad(int id, int nuevaCantidad) {
        String query = "UPDATE inventario SET cantidad = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nuevaCantidad);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertarItemConImagen(String nombre, String tipo, int cantidad, File imagenFile, String detalles) {
        String sql = "INSERT INTO inventario (nombre, tipo, cantidad, imagen, detalles) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             FileInputStream fis = new FileInputStream(imagenFile);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, tipo);
            pst.setInt(3, cantidad);
            pst.setBinaryStream(4, fis, (int) imagenFile.length());
            pst.setString(5, detalles);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item insertado correctamente.");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar: " + e.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

