package topfood;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
        System.out.println("Error. No se pueden agregar más perfumes.");
    }

    public static void mostrarPerfumes(Perfume[]perfumes){
        try{
            for(Perfume perfume : perfumes){
                if(perfume!=null){
                    System.out.println("---------------");
                    System.out.println(perfume);
                    System.out.println("---------------");
                }
            }
        }catch(NullPointerException ex){
            return;
        }
    }

    public static void guardarinventario(Perfume[]perfumes, int contador){
        File archivo = new File("Arshivo.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(perfumes);
            oos.writeInt(contador);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
