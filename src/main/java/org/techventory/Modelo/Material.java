package org.techventory.Modelo;

public class Material {
    private int id;
    private String nombre;
    private String tipo;
    private int cantidad;
    private byte[] imagen;
    private String detalles;

    public Material(int id, String nombre, String tipo, int cantidad, byte[] imagen, String detalles){
        this.setId(id);
        this.setNombre(nombre);
        this.setTipo(tipo);
        this.setCantidad(cantidad);
        this.setImagen(imagen);
        this.setDetalles(detalles);
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
