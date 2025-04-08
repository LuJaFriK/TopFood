package topfood;

public class Mesa implements description{
    private Mesero mesero;
    private int numero;
    private int personas;
    private boolean activo;
    private Platillo[]pedido;
    private Cafe[]cafeteria;
    private double total;
    
    public Mesa(Mesero mesero, int numero, int personas, boolean activo) {
        this.mesero = mesero;
        this.numero = numero;
        this.personas = personas;
        this.activo = activo;
        pedido = new Platillo[100];
        cafeteria = new Cafe[100];
        int i;
        total = 0;
    }
    public Mesa(int numero){
        mesero = null;
        this.numero = numero;
        personas = 0;
        activo = false;
        total = 0;
    }

    public Mesero getMesero(){
        return mesero;
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

    public void addPedido(Platillo orden, int cantidad) {
        int espaciosDisponibles = 0;
        for(Platillo platillo : pedido){
            if(platillo == null){
                espaciosDisponibles++;
            }
        }
        if (espaciosDisponibles < cantidad) {// Verificar si hay suficiente espacio
            System.out.println("Error: No hay suficiente espacio para agregar " + cantidad + " platillos. Espacios disponibles: " + espaciosDisponibles);
            return;
        }
        int agregados = 0;
        for(Platillo platillo : pedido){
            if(agregados < cantidad && platillo == null){
                platillo = orden;
                agregados++;
            }
        }
    }

    public int getPedidolength(){
        return pedido.length;
    }

    public Platillo getpedido(int i){
        return pedido[i];
    }

    public void printPedido(){
        for (int i=0; i < 100 && pedido[i] != null;i++) {
            if(i<100){
                System.out.println("===============");
                pedido[i].detalles();
                System.out.println("===============");
            }
        }
    }
    
    public void addCafe(Cafe orden, int cantidad) {
        int espaciosDisponibles = 0;
        
        for (int i = 0; i < cafeteria.length; i++) {// Contabilizar el espacio disponible en el arreglo
            if (cafeteria[i] == null) {
                espaciosDisponibles++;
            }
        }
        if (espaciosDisponibles < cantidad) {// Verificar si hay suficiente espacio
            System.out.println("Error: No hay suficiente espacio para agregar " + cantidad + " cafes. Espacios disponibles: " + espaciosDisponibles);
            return;
        }
        int agregados = 0;
        for (int i = 0; i < cafeteria.length && agregados < cantidad; i++) {// Agregar el platillo la cantidad de veces especificada
            if (cafeteria[i] == null) {
                cafeteria[i] = orden;
                agregados++;
            }
        }
    }

    public int getCafeLength(){
        return cafeteria.length;
    }

    public Cafe getCafe(int i){
        return cafeteria[i];
    }

    public void printCafe(){
        for (int i=0; i < 100 && cafeteria[i] != null;i++) {
            if(i<100){
                System.out.println("===============");
                cafeteria[i].detalles();
                System.out.println("===============");
            }
        }
    }

    public double getTotal(){
        for(int i=0; i<pedido.length&&pedido[i]!=null;i++){
            total+=pedido[i].getCosto();
        }
        return total;
    }

    public void detalles(){
        System.out.println("Mesero encargado: "+mesero.getCodigo()+" : "+mesero.getNombre());
        System.out.println("Mesa: "+numero);
        System.out.println("Cantidad de personas: "+personas);
        System.out.println("Activa: "+(activo ? "Si." : "No."));
        System.out.println("Total actual: "+getTotal());
    }
}