package org.techventory.DAO;

import org.techventory.Modelo.Material;
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
}

