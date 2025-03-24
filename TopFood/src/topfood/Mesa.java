package topfood;

public class Mesa{
    private Mesero mesero;
    private int numero;
    private int personas;
    private boolean activo;
    private Alimento[]pedido;
    
    
    public Mesa(Mesero mesero, int numero, int personas, boolean activo) {
        this.mesero = mesero;
        this.numero = numero;
        this.personas = personas;
        this.activo = activo;
        pedido = new Alimento[100];
    }
    public Mesa(int numero){
        mesero = null;
        this.numero = numero;
        personas = 0;
        activo = false;
    }

    public void getMesero(){
        mesero.info();
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

    public void addPedido(Alimento orden){
        for (int i = 0; i < pedido.length; i++) {
            if (pedido[i] == null) {
                pedido[i] = orden;
                return;
            }
        }
        System.out.println("Error: Pedido lleno. No se puede agregar más alimentos.");;
    }

    public void getPedido(){
        for (int i=0; i < 100 && pedido[i] != null;i++) {
            if(i<100){
                System.out.println("===============");
                pedido[i].detalles();
                System.out.println("===============");
            }
        }
    }

    public void info(){
        System.out.println("Mesero encargado: "+mesero.getCodigo());
        System.out.println("Mesa: "+numero);
        System.out.println("Cantidad de personas: "+personas);
        System.out.println("Activa: "+(activo ? "Si." : "No."));
    }
}