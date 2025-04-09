package topfood;

public class Mesa implements description{
    private Mesero mesero;
    private int numero;
    private int personas;
    private boolean activo;
    private Alimento[]pedido;
    private double total;
    
    public Mesa(Mesero mesero, int numero, int personas, boolean activo) {
        this.mesero = mesero;
        this.numero = numero;
        this.personas = personas;
        this.activo = activo;
        pedido = new Platillo[100];
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

    public void addPedido(Alimento orden, int cantidad) {
        int espaciosDisponibles = 0;
        for(Alimento alimento : pedido){
            if(alimento == null){
                espaciosDisponibles++;
            }
        }
        if (espaciosDisponibles < cantidad) {// Verificar si hay suficiente espacio
            System.out.println("Error: No hay suficiente espacio para agregar " + cantidad + " platillos. Espacios disponibles: " + espaciosDisponibles);
            return;
        }
        int agregados = 0;
        for(Alimento alimento : pedido){
            if(agregados < cantidad && alimento == null){
                alimento = orden;
                agregados++;
            }
        }
    }

    public int getPedidolength(){
        return pedido.length;
    }

    public Alimento getpedido(int i){
        return pedido[i];
    }

    public void printPedido(){
        System.out.println("Nombre  ================   Costo");
        for(Alimento alimento : pedido){
            System.out.print(alimento.getNombre()+" =============  $"+alimento.getCosto());
            if(alimento == null){break;}
        }
        System.out.println("===============================");
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