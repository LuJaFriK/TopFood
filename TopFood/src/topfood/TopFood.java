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
 
    private static Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Mesero[]meseros = new Mesero[10];
        Mesa[]mesas = new Mesa[5];
        Alimento[]menu = new Alimento[10];

        Mesero user = new Mesero();
        int opc=0,fallas=0;
        do{
            while(user == null){
            System.out.println("Ingresa tu contraseña para iniciar sesión:");
            user = LogIn(meseros, scanner.nextInt());
            fallas+=1;
            if(fallas>3){
                System.out.println("Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo?");
                System.out.println("1. Si      2. No");
                int crear = scanner.nextInt();
                if(crear==1){crearMesero(meseros);}
            }
        }   
        scanner.nextLine();
            System.out.println("Bienvenido, elija la opción a continuación: ");
            System.out.println("1.- Abrir cuenta. ");
            System.out.println("2.- Visualizar mesas.");
            System.out.println("3.- Agregar productos.");
            System.out.println("4.- Cerrar una cuenta.");
            System.out.println("5.- Eliminar una cuenta.");
            opc = scanner.nextInt();
            switch (opc){
                case 1:
                break;
                case 2:
                break;
                case 3:
                break;
                case 4:
                break;
                case 5:
                break;
                case 6:
                break;
                case 7:
                break;
                default:
                break;
            }
        }while(opc != 7);
        
        
    }
    

    public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[]menu){
        scanner.next();
        boolean done = false;
        int i = 0;
        do{
            System.out.println("Ingresa el nombre del alimento:");
            String nombre =  scanner.nextLine();
            while(i < menu.length){
                if(!nombre.equals(menu[i].getNombre())){
                    i++;
                }
                else if(nombre.equals(menu[i].getNombre())){
                    mesa.addPedido(menu[i]);
                    i = menu.length;
                }
                
            }
            System.out.println("Agregar otro pedido?");
            System.out.println("1. Si       2. No");
            int other = scanner.nextInt();
                if(other == 1){}
                else {return;}
        }while(done);
    }

    public static Mesero LogIn(Mesero[]meseros,int password){
        int i = 0;
        while(i < meseros.length-1){
            if(meseros[i] != null && meseros[i].login(password)){
                return meseros[i];
            }
            i++;
        }
        return null;
    } 

    public static void crearMesero(Mesero[]meseros){
        scanner.next();
        for(int i=0;i<meseros.length;i++){
            if(meseros[i] == null){
                System.out.println("Ingresa el nombre del mesero:");
                String nombre = scanner.nextLine();
                boolean done = false;
                int pass1 = 0,pass2 = 0;
                do{
                    System.out.println("Ingresa la contraseña: ");
                    pass1 = scanner.nextInt();
                    System.out.println("Confirma tu contraseña: ");
                    pass2 = scanner.nextInt();
                    if(pass1 == pass2){
                        done = true;
                    }else{
                        System.out.println("Error. las contraseñas no coinciden.");
                    }
                }while(done!=true);
            meseros[i] = new Mesero(nombre,i,pass1);
            return;
            }
        } 
        System.out.println("Error. No pueden ingresar más meseros.");
    }

    public static void asignarMesa(Mesero mesero, Mesa[] mesas, Alimento[]menu){
        scanner.next();
        System.out.println("Ingresa el numero de la mesa: ");
        int i = scanner.nextInt();
        if(mesas[i].isActivo() == true){System.out.println("Error, la mesa está ocupada.");}
        System.out.println("Ingresa el numero de personas: ");
        int personas = scanner.nextInt();
        mesas[i] = new Mesa(mesero, i++, personas, true);
        mesero.addMesa(mesas[i]);
        int tomarorden = 0;
        do{
            System.out.println("Desea tomar la orden?");
            System.out.println("1. Si       2. No");
            tomarorden = scanner.nextInt();
            if(tomarorden == 1){
                hacerPedido(mesas[i],mesero, menu);
            }else if(tomarorden == 2){}
            else{System.out.println("Error, valor no válido.");}
        }while(tomarorden!=1);
    }

    public static void Ticket(Mesa mesa){
        System.out.println("=========TICKET=========");
        System.out.println("Mesa: "+mesa.getNumero());
        System.out.println("Mesero: "+mesa.getMesero());
        System.out.println("Personas: "+mesa.getPersonas());
        for(int i=0;i<mesa.getPedidolength();i++){
            Alimento alimento = mesa.getpedido(i);
            if(alimento!=null){System.out.println(alimento.getNombre()+" ------------------------------------ $"+alimento.getCosto());}
        }
        System.out.println("Subtotal: ------------------------------------ $"+mesa.getTotal());
        System.out.println("IVA (16%): ------------------------------------ $"+mesa.getTotal()*0.16);
        System.out.println("Total: ------------------------------------ $"+mesa.getTotal()*1.16);
        mesa.setActivo(false);
    }
}