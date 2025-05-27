package topfood;

import java.io.Serializable;

public class Mesero implements Serializable{

    private String nombre;
    private int codigo;
    private int password;
    private Mesa[] mesas;
    private int contador;

    public Mesero(String nombre, int codigo, int password) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.password = password;
        this.contador = 0;
    }

    public Mesero() {
        nombre = "Default";
        codigo = 0;
        password = -1;
        contador = 0;
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

    public boolean login(int password) {
        return (this.password == password);
    }

    public void setPassword(int password) {
        this.password = password;
    }    
    
    public void addContador(){
        contador++;
    }

    public void rebootContador(){
        contador = 0;
    }

    public int getContador() {
        return contador;
    }

    @Override
    public String toString() {
        return "Codigo: " + codigo + "\n" +
            "Nombre: " + nombre + "\n";
    }

}
