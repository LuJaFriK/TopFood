package topfood;


public class Mesero{
    private String nombre;
    private int codigo;
    private int password;
    private Mesa[] mesas;
    private int contador;
    
    public Mesero(String nombre, int codigo, int password) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.password = password;
        this.mesas = new Mesa[60]; 
        this.contador = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public boolean login(int password) {
        if(this.password==password){return true;}
        else{return false;}
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void getMesas() {
        int x=0;
        while(mesas[x]!=null&&x<mesas.length-1){x++;}
        for(int i=0;i<x;i++){
            mesas[i].info();
        }
    }

    public void setMesa(Mesa mesa) {
        int x=0;
        while(this.mesas[x]!=null&&x>this.mesas.length-1){x++;}
        this.mesas[x] = mesa;
        this.contador++;
    }

    public int getContador() {
        return contador;
    }
}