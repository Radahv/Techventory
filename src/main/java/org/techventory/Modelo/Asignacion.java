package org.techventory.Modelo;

public class Asignacion {
    private int id;
    private int id_usuario;
    private int id_material;
    private int cantidad_asignada;

    public Asignacion(int id, int id_usuario, int id_material, int cantidad_asignada){
        this.setId(id);
        this.setId_usuario(id_usuario);
        this.setId_material(id_material);
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

    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public int getCantidad_asignada() {
        return cantidad_asignada;
    }

    public void setCantidad_asignada(int cantidad_asignada) {
        this.cantidad_asignada = cantidad_asignada;
    }
}
