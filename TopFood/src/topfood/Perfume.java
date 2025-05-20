package topfood;

public class Perfume {
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

    public void detalles(){
        System.out.println("-------------------------");
        System.out.println("Clave del producto: "+clave);
        System.out.println("Nombre: "+nombre);
        System.out.println("Costo: "+precio);
        System.out.println("Disponibilidad: "+existencia);
        System.out.println("-------------------------");
    }
}
