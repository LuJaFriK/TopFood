package topfood;

public class Bebida extends Alimento{
    private boolean hielo;
    private boolean azucar;
    
    public Bebida(String nombre, double precio, String comentario,boolean existencia, boolean hielo, boolean azucar) {
        super(nombre, precio,comentario,existencia);
        this.hielo = hielo;
        this.azucar = azucar;
    }
    public boolean tienehielo() {
        return hielo;
    }

    public void hielo(boolean hielo) {
        this.hielo = hielo;
    }

    public boolean getAzucar() {
        return azucar;
    }

    public void setAzucar(boolean azucar) {
        this.azucar = azucar;
    }
}