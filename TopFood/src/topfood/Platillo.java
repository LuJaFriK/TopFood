package topfood;

public class Platillo extends Alimento {

    private boolean ordenCompleta;
    private boolean prioridadAlta;

    public Platillo(String nombre,double costo,String comentario,boolean existencia) {
        super(nombre, costo, comentario, existencia);
        this.ordenCompleta = true;
        this.prioridadAlta = false;
    }

    public boolean isOrdenCompleta() {
        return ordenCompleta;
    }

    public void setOrdenCompleta(boolean ordenCompleta) {
        this.ordenCompleta = ordenCompleta;
    }

    public Boolean getPrioridadAlta() {
        return prioridadAlta;
    }

    public void setPrioridadAlta(Boolean prioridadAlta) {
        this.prioridadAlta = prioridadAlta;
    }

    @Override
    public double getCosto() {
        double newCosto=super.getCosto();
        if (!ordenCompleta) newCosto *= 0.80;
        return newCosto;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" +
            (prioridadAlta ? "Prioridad: Alta" : "Prioridad: Baja");
    }
}
