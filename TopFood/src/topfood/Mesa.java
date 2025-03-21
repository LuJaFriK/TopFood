package topfood;

public class Mesa{
    private Mesero mesero;
    private int numero;
    private int personas;
    private boolean activo;
    
    public Mesa(int numero, int personas, boolean activo) {
        this.numero = numero;
        this.personas = personas;
        this.activo = activo;
    }
    public Mesa(int numero){
        this.numero = numero;
        personas = 0;
        activo = false;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPersonas() {
        return personas;
    }

    public void setPersonas(int personas) {
        this.personas = personas;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    public void info(){
        String confirmacion = "";
        if(activo==true){confirmacion="Si.";}
        if(activo==false){confirmacion="No.";}
        System.out.println("Mesa: "+numero);
        System.out.println("Cantidad de personas: "+personas);
        System.out.println("Activa: "+confirmacion);
    }
}