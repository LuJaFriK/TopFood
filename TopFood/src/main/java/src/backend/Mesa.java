package src.backend;

import java.io.Serializable;

public class Mesa implements Serializable{

    private Mesero mesero;
    private int numero;
    private boolean activo;
    private Alimento[] pedido;
    private double total;

    public Mesa(Mesero mesero, int numero, boolean activo) {
        this.mesero = mesero;
        this.numero = numero;
        this.activo = activo;
        pedido = new Alimento[100];
        total = 0;
    }

    public Mesa(int numero){
        this.mesero = null;
        this.numero=numero;
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

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void addPedido(Alimento orden, int cantidad) {
        int agregados = 0;
        for (int i = 0; i < pedido.length && agregados < cantidad; i++) {
            if (pedido[i] == null) {
                pedido[i] = orden;
                agregados++;
            }
        }

        if (agregados < cantidad) {
            System.out.println("Aviso: Solo se agregaron " + agregados + " de " + cantidad + " snacks por falta de espacio.");
        }
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

    public void addTotal(double agg) {
        this.total += agg;
    }    
    public double getTotal() {
        return total;
    }

    @Override

    public String toString() {
        return "Mesero encargado: " + mesero.getCodigo() + " : " + mesero.getNombre() + "\n" +
                "Mesa: " + numero + "\n" +
                "Activa: " + (activo ? "Si." : "No.") + "\n" +
                "Total actual: " + getTotal();
    }

}
