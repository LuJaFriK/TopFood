package topfood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
                System.out.println("Error. Caracteres introducidos no válidos. Intente nuevamente.");
                scanner.nextLine();
                wait(2000);
            }
        }
        return scanned;
    }

    public static double InputDouble(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        double scanned;
        while (true) {
            try {
                System.out.println(mensaje);
                scanned = scanner.nextDouble(); 
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Error. Caracteres introducidos no válidos. Intente nuevamente.");
                scanner.nextLine();
                wait(2000);
            }
        }
        return scanned;
    }

    //nuevo metodo input que tambien solo te permite ingresar un numero entre un rango determinado
    public static int InputIntRange(String mensaje, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int scanned;
        while (true) {
            try {
                System.out.println(mensaje);
                scanned = scanner.nextInt(); 
                if(scanned>=min&&scanned<=max){
                    break;
                }else{
                    System.out.println("Error. Opción no válida.");
                    wait(1500);
                }
            } catch (InputMismatchException ex) {
                System.out.println("Error. Caracteres introducidos no válidos. Intente nuevamente.");
                scanner.nextLine();
                wait(1500);
            }
        }
        return scanned;
    }

    //Buscar archivos (On development)
    public static String Converter(String filename, int index){
        String data = null;
        File file = new File(filename+".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            FileReader fr = new FileReader(filename+".txt");
            
        }catch(FileNotFoundException ex){
            System.out.println("Error. Archivo no encontrado.");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
        
     }

     public static void BufferedReader(String filename){
        File file = new File(filename+".txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String data;
            while((data = reader.readLine())!=null){
                System.out.println(data);
            }
            reader.close();
        } catch(FileNotFoundException ex){
            System.out.println("Error. Archivo no encontrado.");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
     }

     //Escribir archivos 
     public static void OverrideFile(String filename, String order){
        try{
            FileWriter file = new FileWriter(filename, true);
            file.write(order + System.lineSeparator());
            file.close();
        }catch(IOException ex){
            System.out.println("Algo ha pasado. Intenta nuevamente.");
            ex.printStackTrace();
        }
     }

     public static void pause(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Presiona enter para continuar");
        String enter = scanner.nextLine();
        if(enter.equals("/n")){
             scanner.nextLine();
             return;
        }
   }
   
   public static void guardarDatos(Mesero[] meseros, Alimento[] menu){
        File archivo = new File("Data.dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(meseros);
            oos.writeObject(menu);
            System.out.println("Datos guardados exitosamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public static Object[] cargarDatos(){
        File archivo = new File("Data.dat");
        if(!archivo.exists()) return null;
        Object[] Datos = new Object[2];
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))){
            Datos[0] = (Mesero[]) ois.readObject();
            Datos[1] = (Alimento[]) ois.readObject();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return Datos;    
    }
}
