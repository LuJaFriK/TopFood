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

    public Mesero(){
        nombre = "Default";
        codigo = 0;
        password=-1;
        contador = 0;
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

    public void printMesas() {
        for(int i=0;i<mesas.length;i++){
            if(mesas[i]!=null){
                mesas[i].info();
            }else{return;}
        }
    }

    public void addMesa(Mesa mesa) {
        for(int i = 0;i < this.mesas.length-1;i++){
            if(!mesas[i].isActivo()){
                mesas[i] = mesa;
                contador++;
                return;
            }
        }System.out.println("Error. La mesa no puede ingresar más pedidos.");
    }

    public boolean isMyMesa(int i){
        if(mesas[i]!=null){return true;}
        else{return false;}
    }
    
    public void cleanMesa(Mesa mesa){
        mesas[mesa.getNumero()] = new Mesa(mesa.getNumero());  
    }

    public int getContador() {
        return contador;
    }

    public void info(){
        int x=0;
        System.out.println("Codigo: "+codigo);
        System.out.println("Nombre: "+nombre);
        while(this.mesas[x]!=null&&x>this.mesas.length-1){
            System.out.print(mesas[x].getNumero()+" ");
            x++;
        }
        System.out.println("Contador de mesas: "+contador);
    }
}