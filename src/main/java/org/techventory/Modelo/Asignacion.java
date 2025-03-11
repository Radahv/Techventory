package org.techventory.Modelo;

public class Asignacion {
    private int id;
    private int id_usuario;
    private int id_inventario;
    private int cantidad_asignada;

    public Asignacion(int id, int id_usuario, int id_inventario, int cantidad_asignada){
        this.setId(id);
        this.setId_usuario(id_usuario);
        this.setId_inventario(id_inventario);
        this.setCantidad_asignada(cantidad_asignada);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getCantidad_asignada() {
        return cantidad_asignada;
    }

    public void setCantidad_asignada(int cantidad_asignada) {
        this.cantidad_asignada = cantidad_asignada;
    }
}
