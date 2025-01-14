package org.techventory.Modelo;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private String rol;

    //Constructor, getters y setters
    public Usuario(int id, String nombre, String email, String contrasena, String rol){
        this.setId(id);
        this.setNombre(nombre);
        this.setEmail(email);
        this.setContrasena(contrasena);
        this.setRol(rol);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
