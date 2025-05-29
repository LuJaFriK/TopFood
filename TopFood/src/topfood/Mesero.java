package topfood;

import java.io.Serializable;

public class Mesero implements Serializable{

    private String nombre;
    private int codigo;
    private int password;

    public Mesero(String nombre, int codigo, int password) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.password = password;
    }

    public Mesero() {
        nombre = "Default";
        codigo = 0;
        password = -1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }    

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\n" +
            "Nombre: " + nombre + "\n";
    }

}
