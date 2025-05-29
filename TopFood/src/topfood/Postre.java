package topfood;

public class Postre extends Alimento {

    private boolean porcionIndividual;
    private int porcionGrande; 

    public Postre(String nombre,double costo,String comentario,boolean existencia, int porcion) {
        super(nombre, costo, comentario, existencia);
        this.porcionIndividual = true;
        this.porcionGrande = porcion;
    }



    public boolean isPorcionIndividual() {
        return porcionIndividual;
    }

    public void setPorcionIndividual(boolean porcionIndividual) {
        this.porcionIndividual = porcionIndividual;
    }

    public int getPorcionGrande() {
        return porcionGrande;
    }

    public void setPorcionGrande(int porcionGrande) {
        this.porcionGrande = porcionGrande;
    }
    @Override
    public double getCosto(){
        double newCosto = getCosto();
        if (!isPorcionIndividual()) newCosto*=(porcionGrande-2);
        return newCosto;
    }
    
    @Override
    public String toString() {
        return 
        ("Nombre: "+getNombre()+
        "\n"+(porcionIndividual ? "Porcion Individual.":"Paquete/Completo.")+
        "\nPrecio: " +getCosto()+
        "\nComentarios: " + getComentario());
    }
}
