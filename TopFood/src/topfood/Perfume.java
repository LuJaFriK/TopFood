package topfood;

import java.io.Serializable;

public class Perfume implements Serializable {
    //Para convertir un objeto a serializable debo de implementar la interfaz
    private String clave;
    private String nombre;
    private double precio;
    private int existencia;

    public Perfume(String clave,String nombre, double precio, int existencia){
        this.clave = clave;
        this.nombre = nombre;
        this.precio = precio;
        this.existencia = existencia;   
    }

    @Override
    public String toString() {
        return ("Clave: "+clave+"\nNombre: "+nombre+"\nPrecio: "+precio+"\nExistencia: "+existencia);
    }
}
