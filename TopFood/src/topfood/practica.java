package topfood;


public class practica {
    public static void main(String[]args){
        Perfume[]perfumes = new Perfume[100];
        String opciones = 
        """
        1.-Capturar perfume
        2.-Imprimir el inventario
        3.-Salir        
                """;
        do{
            int opc = Aux.InputIntRange(opciones,1,3);
            switch(opc){
                case 1:
                    capturarPerfume(perfumes);
                break;
                case 2:
                    mostrarPerfumes(perfumes);
                break;
                case 3:
                System.out.println("Saliendo...");
                return;
            }
        }while(true);
    }

    public static void capturarPerfume(Perfume[]perfumes){
        for(int i = 0; i<perfumes.length; i++){
            if(perfumes[i]==null){
                perfumes[i] = new Perfume(
                    Aux.InputString("Ingresa la clave del producto"),
                    Aux.InputString("Ingresa el nombre del producto:"),
                    Aux.InputDouble("Ingresa el precio del producto:"),
                    Aux.InputInt("Ingresa la cantidad de productos:")
                );
                return;
            }
        }
    }

    public static void mostrarPerfumes(Perfume[]perfumes){
        try{
            for(Perfume perfume : perfumes){
                perfume.detalles();
            }
        }catch(NullPointerException ex){
            return;
        }
    }
}
