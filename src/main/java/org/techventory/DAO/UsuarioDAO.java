package org.techventory.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarUsuario(String nombreUsuario, String contrasena){
        String query = "SELECT contrasena FROM usuarios WHERE nombre = ?";
        try(Connection conexion = ConexionDB.getConexion()){
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                String contrasenaHash = rs.getString("contrasena");
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
