package topfood;

public class Platillo extends Alimento {

    private boolean ordenCompleta;
    private boolean prioridadAlta;

    public Platillo(
        String nombre,
        double costo,
        String comentario,
        boolean existencia
    ) {
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
    public void setCosto(double costo) {
        double newCosto = costo;
        if (!ordenCompleta) {
            newCosto = costo * 0.80;
        }
        super.setCosto(newCosto);
    }

    @Override
    public void detalles() {
        String prioridad;
        if (prioridadAlta == true) {
            prioridad = "Prioridad: Alta";
        } else if (prioridadAlta == false) {
            prioridad = "Prioridad: Baja";
        }
        super.detalles();
    }
}
