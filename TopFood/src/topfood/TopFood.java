/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package topfood;
import java.util.Scanner;
/**
 *
 * @author Luis Javier Robles Topete 25460001
 */
public class TopFood {

    private static Mesero[]meseros = new Mesero[10];
    private static Mesa[]mesas = new Mesa[5];
    private static Alimento[]menu = new Alimento[10];
    private static Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesero user = new Mesero();
        int opc=0;
        do{
            while(user == null){
            System.out.println("Ingresa tu contraseña para iniciar sesión:");
            user = LogIn(scanner.nextInt());
        }
            System.out.println("Bienvenido, elija la opción a continuación: ");
            System.out.println("1.- Asignar una mesa: ");
            System.out.println("2.- ");
        }while(opc!=4);
        
        
    }

    public static void hacerPedido(){
        scanner.next();
        
    }

    public static Mesero LogIn(int password){
        int i = 0;
        while(i<meseros.length-1){
            if(meseros[i] != null && meseros[i].login(password)){
                return meseros[i];
            }
            i++;
        }
        return null;
    } 

    public static void crearMesero(){
        scanner.next();
        int x=0;
        while(mesas[x]!=null&&x<mesas.length-1){x++;} 
        System.out.println("Ingresa el nombre del mesero:");
        String nombre = scanner.nextLine();
        boolean done = false;
        int pass1=0,pass2=0;
        do{
        System.out.println("Ingresa la contraseña: ");
        pass1 = scanner.nextInt();
        System.out.println("Ingresa confirma tu contraseña: ");
        pass2 = scanner.nextInt();
        if(pass1==pass2){done=true;}else{}
        }while(done!=true);
        meseros[x] = new Mesero(nombre,x,pass1);

    }

    public void asignarMesa(Mesero mesero){
        scanner.next();
        System.out.println("Ingresa el numero de la mesa: ");
        int i = scanner.nextInt();
        if(mesas[i].isActivo()==true){System.out.println("Error, la mesa está ocupada.");}
        System.out.println("Ingresa el numero de personas: ");
        int personas = scanner.nextInt();
        mesas[i] = new Mesa(mesero, i++, personas, true);
    }
}
