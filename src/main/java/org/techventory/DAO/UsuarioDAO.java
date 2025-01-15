package org.techventory.DAO;

import org.techventory.Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarUsuario(String nombreUsuario, String contrasena){
        String query = "SELECT contrasena FROM usuarios WHERE email = ?";
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

    public boolean agregarUsuario(Usuario usuario){
        String query = "INSERT INTO usuarios (nombre, email, contrasena, rol) VALUES(?,?,?,?)";
        try(Connection conn = ConexionDB.getConexion()){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getContrasena());
            ps.setString(4, usuario.getRol());
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


}
