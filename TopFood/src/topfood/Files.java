package topfood;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Files {

     File file = new File("pepe.txt");
     public static void FileOutputstream(){
          //crea un flujo de salida de bytes
     }

     public static void ObjecOutputstream(){
           
     }

     public static void FileInputStream(){

     }

     public static void ObjectInputStream(){

     }

     
     public static void main (String[]args){
          String aviso;
          Scanner scanner = new Scanner(System.in);
          File archivo = null;
          System.setErr(System.out);
          try{
               System.out.println("Ingresa el nombre y el path del archivo");
               archivo = new File(scanner.nextLine());
               FileWriter fw = new FileWriter(archivo,true);
               System.out.println("Escribeme esta:");
               aviso = scanner.nextLine();
               fw.write(aviso);
               fw.close();
               System.out.println("Archivo creado con exito");
          }catch(IOException ex){
               System.out.println("Error al crear el archivo.");
          }
          try{
               FileReader fr = new FileReader(archivo.getAbsolutePath());
               int caracter;
               System.out.println("Iniciando lectura.");
               while((caracter = fr.read())!=1){
                    System.out.println((char)caracter);

               }
          }catch(IOException ex){
               System.out.println("Elpepe");
          }
          
     }

     public static void menu(Scanner scanner, File archivo){
               int opc = 0;
          do{
               System.out.println("Menu Clase File");
               System.out.println("1.- Crear archivo.");
               System.out.println("2.- Verificar si el archivo existe.");
               System.out.println("3.- Obtener el nombre del archivo.");
               System.out.println("4.- Salir.");
               opc = scanner.nextInt(); 
               scanner.nextLine();
               switch (opc) {
                    case 1:
                         String ruta = null;
                         System.out.println("Ruta:");
                         System.out.println("1.- Default");
                         System.out.println("2.- Personalizada");
                         opc = scanner.nextInt();
                         scanner.nextLine();
                         switch(opc){
                              case 1: 
                                   ruta = "/home/lujafrik/Documentos/";
                                   
                                   /* if(SO==Windows){
                                    * ruta = "C"
                                   } else if(SO==Linux){
                                    ruta = "/home/lujafrik/Documentos/ "
                                   }*/
                                   break;
                              case 2: 
                                   ruta = scanner.nextLine();
                                   break;
                         }
                         System.out.println("Ingrese el nombre del archivo");
                         archivo = new File(ruta+scanner.nextLine());
                         try{
                          if(archivo.createNewFile()){
                              System.out.println("Archivo creado correctamente.");
                              
                          }else{
                              System.out.println("El archivo ya existe.");
                         }    
                         }catch(IOException e){
                              System.out.println("Excepción al intentar crear el archivo.");
                              e.printStackTrace();
                         }
                         Aux.pause();
                         break;
                    case 2:
                         if(archivo!=null){
                              if(archivo.exists()){
                                   System.out.println("Existe el archivo");
                              }
                         }else{
                              System.out.println("No se ha encontrado el archivo.");
                         }
                         Aux.pause();
                         break;
                    case 3:
                         if(archivo!=null){
                              System.out.println("Nombre del archivo: "+archivo.getName());
                              System.out.println("Ruta: "+archivo.getAbsolutePath());
                         }
                         break;
                    case 4:
                    System.out.println("Saliendo...");
                    scanner.close();     
                         break;
               
                    default:
                         break;
               }
          }while(opc!=4);
     }

     public static void guardarobjetos(){

     }

     public static void retomarobjetos(){
          
     }
}
