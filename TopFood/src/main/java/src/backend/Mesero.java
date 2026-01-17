package src.backend;

import java.io.Serializable;

public class Mesero implements Serializable{

    private String nombre;
    private int password;

    public Mesero(String nombre, int password) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.password = password;
    }

    public Mesero() {
        nombre = "Default";
        password = -1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean login(int password){
        return (this.password == password);
    }

    @Override
    public String toString() {
            "Nombre: " + nombre;
    }

}
