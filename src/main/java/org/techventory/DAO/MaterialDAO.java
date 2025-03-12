package org.techventory.DAO;

import org.techventory.Modelo.Material;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {

    //Insertar Material al inventario con imagen
    public void insertarItemConImagen(Material material, File imagenFile) {
        String sql = "INSERT INTO inventario (nombre, tipo, cantidad, imagen, detalles) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             FileInputStream fis = new FileInputStream(imagenFile);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, material.getNombre());
            pst.setString(2, material.getTipo());
            pst.setInt(3, material.getCantidad());
            pst.setBinaryStream(4, fis, (int) imagenFile.length());
            pst.setString(5, material.getDetalles());

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

    public void eliminarItem(int id){
        String sql = "DELETE FROM inventario WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, id);
            int filasAfectadad = ps.executeUpdate();
            if (filasAfectadad > 0){
                JOptionPane.showMessageDialog(null, "Item eliminado correctamnente.");
            }else {
                JOptionPane.showMessageDialog(null, "No se encontró el ítem a eliminar.");
            }
        }catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + exception.getMessage());
        }
    }

    public void modificarItem(Material material, File imageFile){
        String sql = "UPDATE inventario SET nombre = ?, tipo = ?, cantidad = ?, imagen = ?, detalles = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, material.getNombre());
            ps.setString(2, material.getTipo());
            ps.setInt(3, material.getCantidad());

            //Si hay imagen nueva, la convertimos a bytes
            if (imageFile != null){
                FileInputStream fis = new FileInputStream(imageFile);
                ps.setBinaryStream(4, fis, (int) imageFile.length());
            }else {
                ps.setNull(4, Types.BLOB); // Si no hay imagen nueva, se mantiene la actual
            }
            ps.setString(5, material.getDetalles());
            ps.setInt(6, material.getId());

            int filasAfectadad = ps.executeUpdate();
            if (filasAfectadad > 0){
                JOptionPane.showMessageDialog(null, "Item actualizado correctamnente.");
            }else {
                JOptionPane.showMessageDialog(null, "No se encontró el ítem a actualizar.");
            }

        }catch (SQLException exception){
            JOptionPane.showMessageDialog(null, "Error al actualizar: " + exception.getMessage());
        }catch (FileNotFoundException exception){
            JOptionPane.showMessageDialog(null, "Archivo no encontrado: " + exception.getMessage());
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}

