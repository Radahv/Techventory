package org.techventory.DAO;

import org.techventory.Modelo.Asignacion;

import java.sql.*;

public class AsignacionDAO {
    // Asignar material a un usuario
    public boolean asignarMaterial(Asignacion asignacion) {
        String query = "INSERT INTO asignaciones (id_usuario, id_inventario, cantidad_asignada) VALUES (?, ?, ?)";
        String updateInventario = "UPDATE inventario SET cantidad = cantidad - ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmtAsignacion = conn.prepareStatement(query);
             PreparedStatement stmtUpdateInventario = conn.prepareStatement(updateInventario)) {
            // Insertar en asignaciones
            stmtAsignacion.setInt(1, asignacion.getId_usuario());
            stmtAsignacion.setInt(2, asignacion.getId_inventario());
            stmtAsignacion.setInt(3, asignacion.getCantidad_asignada());
            stmtAsignacion.executeUpdate();

            // Actualizar inventario
            stmtUpdateInventario.setInt(1, asignacion.getCantidad_asignada());
            stmtUpdateInventario.setInt(2, asignacion.getId_inventario());
            return stmtUpdateInventario.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

