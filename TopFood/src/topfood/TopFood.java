/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package topfood;
import java.util.Scanner;
/**
 *
 * @author lujafrik
 */
public class TopFood {

    Mesero[]meseros = new Mesero[10];
    Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Mesa[]mesas = new Mesa[5];
        Alimento[]menu = new Alimento[10];
        
    }

    public Mesero LogIn(int password){
        int i = 0;
        while(meseros[i].login(password)==false&&i<meseros.length-1){i++;}
        return meseros[i];
    } 

    public void crearMesero(Mesero[] meseros, Mesa[]mesas){
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

    public void asignarMesa(Mesero mesero, Mesa[] mesas){
        scanner.next();

        
    }
}
