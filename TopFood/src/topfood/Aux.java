package topfood;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aux {
    // metodo para esperar un momento
    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            // Aquí puedes registrar, ignorar o actuar según el caso
            System.out.println("La espera fue interrumpida.");
        }
    }   


    //metodos que optimizan el uso del scanner
    public static String InputString(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        String scanned;
        while (true) {
            System.out.println(mensaje);
            scanned = scanner.nextLine();
            if (!scanned.isEmpty()) {
                break;
            }
            System.out.println("Error, no se permite una entrada vacía. Intente nuevamente.");
            wait(2000);
        }
        
        return scanned;
    }    

    
    public static int InputInt(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        int scanned;
        while (true) {
            try {
                System.out.println(mensaje);
                scanned = scanner.nextInt(); 
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Error, caracteres introducidos no válidos. Intente nuevamente.");
                wait(2000);
            }
        }
        return scanned;
    }

    //Buscar archivos (On development)
    public static Object[] SearchFile(String filename){
          Object[] objectarray = new Object[10];
          try{
               FileReader file = new FileReader(filename+".txt");
          }catch(FileNotFoundException ex){
               System.out.println(filename+" no existe. Se creará automaticamente.");
               File file = new File(filename+".txt");
               return objectarray;
          }
          return objectarray;
     }

     //Escribir archivos (On development)
     public static void OverrideFile(String filename, String order){
        try{
            FileWriter file = new FileWriter(filename, true);
            file.write(order);
            file.close();
        }catch(IOException ex){
            System.out.println("Algo ha pasado. Intenta nuevamente.");
        }
     }
}
