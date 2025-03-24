package topfood;
public class Alimento{
    private String nombre;
    private double costo;
    private String comentario;
    private boolean existencia;
    
    public Alimento(String nombre, double costo, String comentario, boolean existencia) {
        this.nombre = nombre;
        this.costo = costo;
        this.comentario = comentario;
        this.existencia = existencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getComentario() {
        return comentario;
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

    public void detalles(){
        System.out.println(nombre);
        System.out.println("Precio: "+ costo);
        System.out.println("Comentarios: "+ comentario);
        System.out.println("Existencia: "+ (existencia ? "Si." : "No."));
    }

}