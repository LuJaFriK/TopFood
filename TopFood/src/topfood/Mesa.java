package topfood;

import java.io.Serializable;

public class Mesa implements Serializable{

    private Mesero mesero;
    private int numero;
    private int personas;
    private boolean activo;
    private Alimento[] pedido;
    private double total;

    public Mesa(Mesero mesero, int numero, int personas, boolean activo) {
        this.mesero = mesero;
        this.numero = numero;
        this.personas = personas;
        this.activo = activo;
        pedido = new Alimento[100];
        total = 0;
    }

    public Mesa(int numero){
        this.mesero = null;
        this.numero=numero;
        this.personas=0;
        this.activo=false;
        this.total=0;    
    }


    public Mesero getMesero() {
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

        for (int i = 0; i < pedido.length; i++) {
            if (pedido[i] == null) {
                espaciosDisponibles++;
            }
        }

        if (espaciosDisponibles < cantidad) { // Verificar si hay suficiente espacio
            System.out.println("Error: No hay suficiente espacio para agregar " +cantidad +" platillos. Espacios disponibles: " +espaciosDisponibles);
            return;
        }

        int agregados = 0;
        for (int i = 0; i < pedido.length; i++) {
            if (agregados < cantidad && pedido[i] == null) {
                pedido[i] = orden;
                agregados++;
            }
        }
    }

    public int getPedidolength() {
        int pedidolength = 0;
        for(int i=0;i<pedido.length;i++){
            if(pedido[i]!=null){
                pedidolength++;
            }
        }
        return pedidolength;
    }

    public Alimento getpedido(int i) {
        return pedido[i];
    }

    public void printPedido() {
        System.out.println("Nombre  ================   Costo");
        
        for(int i=0;i<pedido.length;i++){
            if(pedido[i]!=null){
                System.out.println(pedido[i].getNombre() + " =============  $" + pedido[i].getCosto());
            }
        }
        System.out.println("===============================");
    }

    public void setTotal() {
        total = 0;              // ← reiniciar antes de sumar
        for (int i = 0; i < pedido.length; i++) {
            if (pedido[i] != null) {
                total += pedido[i].getCosto();
            }
        }
    }    
    public double getTotal() {
        return total;
    }

    @Override

    public String toString() {
        return "Mesero encargado: " + mesero.getCodigo() + " : " + mesero.getNombre() + "\n" +
                "Mesa: " + numero + "\n" +
                "Cantidad de personas: " + personas + "\n" +
                "Activa: " + (activo ? "Si." : "No.") + "\n" +
                "Total actual: " + getTotal();
    }

}
