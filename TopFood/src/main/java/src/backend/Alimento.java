package src.backend;

import java.io.Serializable;

public abstract class Alimento implements Serializable {
    private String nombre;
    private double costo;
    private String comentario;
    private boolean existencia;

    public Alimento(String nombre, double costo, boolean existencia) {
        this.nombre = nombre;
        this.costo = costo;
        this.comentario = null;
        this.existencia = existencia;
    }

    public Alimento(final Alimento original,String comentario){
        this.nombre = original.nombre;
        this.costo = configCosto(original.costo);
        this.comentario = comentario;
        this.existencia = original.existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public  double getCosto(){
        return costo;
    }


    public void setCosto(double costo) {
        this.costo = costo;
    }


    protected abstract double configCosto(double costoBase);

    public String getComentario() {
        return (comentario!=null?comentario:"");
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    @Override
    public String toString() {
        return 
        ("Nombre: "+nombre+
        "\nPrecio: " + costo+
        (comentario!=null?"\nComentarios: " + comentario:""));
    }
}