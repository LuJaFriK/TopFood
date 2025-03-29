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

     public static Mesero LogIn(Mesero[]meseros,int password){
        int i = 0;
        while(i < meseros.length-1){
            if(meseros[i] != null && meseros[i].login(password)){
                return meseros[i];
            }
            i++;
        }
        System.out.println("Error. Contraseña incorrecta.");
        return null;
    }
    
    public static void crearMesero(Mesero[]meseros){
        scanner.nextLine();
        int i;
        for(i=0;i<meseros.length;i++){
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
        if(i<meseros.length){System.out.println("Error. No pueden ingresar más meseros.");}
    }
    public static void main(String[] args) {
        
        Mesero[]meseros = new Mesero[20];
        Mesa[]mesas = new Mesa[70];

        for(int x = 0; x<mesas.length;x++){
            mesas[x]=new Mesa(x);
        }

        Platillo[]menu = new Platillo[90];
        Cafe[]cafeteria = new Cafe[50];
        Mesero user = new Mesero();
        int opc=0,fallas=0,i=0,opt=0,opcion=0;
        
        do{
            
            System.out.println("Ingrese la acción que desea realizar a continuación:");
            System.out.println("1.- Iniciar sesión como mesero.");
            System.out.println("2.- Registrar un mesero nuevo.");
            System.out.println("3.- Ingresar nuevos alimentos.");
            System.out.println("4.- Dar de baja un producto");
            System.out.println("5.- Salir del sistema.");
            opcion = scanner.nextInt();
            switch(opcion){
                case 1:
                //Iniciar sesion como mesero, si no, crear un mesero
                MenuMesero(user,meseros,mesas,menu,cafeteria);
                break;
                case 2:
                crearMesero(meseros);
                break;
                case 3:
                    crearAlimento(menu, cafeteria);
                break;
                case 4:
                break;
                case 5:
                System.out.println("Saliendo...");
                break;
            }
        }while(opcion!=4);
    }

    public static void MenuMesero(Mesero user, Mesero[] meseros, Mesa[]mesas,Platillo[]menu,Cafe[]cafeteria){
        int opc = 0,fallas=0,i=0,opt=0;
        boolean myMesa=false,isActive=false;
        do{
            scanner.nextLine();
            opc=0;
            while(user.getNombre().equals("Default")){
            System.out.println("Ingresa tu contraseña para iniciar sesión:");
            user = LogIn(meseros, scanner.nextInt());
            fallas+=1;
            if(fallas>=3){
                System.out.println("Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo?");
                System.out.println("1. Si      2. No");
                int crear = scanner.nextInt();
                scanner.nextLine();
                if(crear==1){
                    crearMesero(meseros);
                }else{return;}
            }
        }   
        scanner.nextLine();
            System.out.println("Bienvenido, elija la opción a continuación: ");
            System.out.println("1.- Abrir cuenta. ");
            System.out.println("2.- Visualizar mesas.");
            System.out.println("3.- Agregar productos.");
            System.out.println("4.- Cerrar una cuenta.");
            System.out.println("5.- Eliminar una cuenta.");
            System.out.println("6.- Separar o juntar cuentas.");
            System.out.println("7.- Salir.");
            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc){
                case 1:
                    asignarMesa(user, mesas, menu, cafeteria);
                break;
                case 2:
                    user.printMesas();
                break;
                case 3:
                    System.out.println("Ingresa el numero de la mesa:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(i);
                    if(myMesa){
                        hacerPedido(mesas[i], user, menu, cafeteria);
                    }else{System.out.println("Error. La mesa no fue encontrada activa o no es operada por el mesero.");}
                break;
                case 4:
                    System.out.println("Ingresa la cuenta que deseas cerrar:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(i);
                    isActive = mesas[i].isActivo();
                    if(myMesa&&isActive){
                        Ticket(mesas[i]);
                        mesas[i].setActivo(false);
                    }else{System.out.println("Error. La mesa no fue encontrada activa o no es operada por el mesero.");}
                break;
                case 5:
                    System.out.println("Ingresa el numero de la cuenta a eliminar:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(i);
                    isActive = mesas[i].isActivo();
                    if(myMesa&&!isActive){ 
                        user.cleanMesa(mesas[i]);
                        mesas[i]=new Mesa(i);
                    }else{
                        System.out.println("Error. La mesa sigue activa o no es operada por el mesero.");
                    }
                break;
                case 6:
                    opt=0;
                    System.out.println("Desea separar o juntar cuentas?");
                    System.out.println("1. Separar       2. Juntar");
                    opt=scanner.nextInt();
                    if(opt!=1&&opt!=2){
                        System.out.println("Error. Opción no válida.");
                        break;
                    }
                break;
                case 7:
                System.out.println("Saliendo...");
                break;
                default:
                System.out.println("Error. Opción no válida.");
                break;
            }
        }while(opc != 7);
    }

    public static void crearAlimento(Platillo[]menu,Cafe[]cafeteria){
        scanner.nextLine();
        int i=0,opc=0;
        String nombre = "";
        double costo = 0;
        System.out.println("Crear:");
        System.out.println("1. Platillo");
        System.out.println("2. Café");
        opc = scanner.nextInt();
        scanner.nextLine();
        if(opc==1){
            for(i=0;i<menu.length;i++){
                if(menu[i]==null){
                    System.out.println("Ingresa el nombre del Platillo");
                    nombre = scanner.nextLine();
                    System.out.println("Ingresa el costo base del producto (Orden Completa):");
                    costo = scanner.nextDouble();
                    menu[i]=new Platillo(nombre, costo, null, true);
                    return;
                }
            }
            System.out.println("Error al crear un Platillo nuevo.");
            
        }else if(opc==2){
            for(i=0;i<cafeteria.length;i++){
                if(cafeteria[i]==null){
                    System.out.println("Ingresa el nombre del Cafe");
                    nombre = scanner.nextLine();
                    System.out.println("Ingresa el costo base del producto (Chico):");
                    costo = scanner.nextDouble();
                    cafeteria[i]=new Cafe(nombre, costo, null, true);
                    return;
                }
            }
            System.out.println("Error al crear un Café nuevo.");
        }else{
            System.out.println("Error. Opcion no valida");
        }
    }     

    public static void hacerPedido(Mesa mesa,Mesero mesero,Platillo[] menu,Cafe[] cafeteria){
        boolean done = false;
        Platillo tempedido;
        Cafe tempcafe;
        do{
            System.out.println("Ingresar:");
            System.out.println("1. Platillo");
            System.out.println("2. Cafe");
            System.out.println("3. Salir");
            int opc = scanner.nextInt();
            //Ingresar un platillo
            if(opc == 1){
                System.out.println("Ingresa el nombre del Platillo");
                tempedido = buscarPlatillo(scanner.nextLine(), menu);
                if(tempedido!=null){

                }
            //Ingresar un cafe
            }else if(opc == 2){

            //Salir
            }else if(opc == 3){
                System.out.println("Saliendo...");
                done = true;
            }else{System.out.println("Error. Opcion no valida.");}
            System.out.println("");
        }while(!done);
        
    }

    public static Platillo buscarPlatillo(String nombre,Platillo[]menu){
        Platillo platillo;
        for(int i=0;i<menu.length;i++){
            if(menu[i].getNombre().equalsIgnoreCase(nombre)){
                platillo = menu[i];
                return platillo;
            }
        }
        return null;
    }

    public static Cafe buscarCafe(String nombre,Cafe[]menu){
        Cafe platillo;
        for(int i=0;i<menu.length;i++){
            if(menu[i].getNombre().equalsIgnoreCase(nombre)){
                platillo = menu[i];
                return platillo;
            }
        }
        return null;
    }
    

    public static void asignarMesa(Mesero mesero, Mesa[] mesas, Platillo[]menu, Cafe[]cafeteria){
        scanner.next();
        System.out.println("Ingresa el numero de la mesa: ");
        int i=0;
        i = scanner.nextInt();
        if(mesas[i].isActivo()){System.out.println("Error, la mesa está ocupada.");
        }else{
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
                    hacerPedido(mesas[i],mesero, menu,cafeteria);
                }else if(tomarorden == 2){return;}
                else{System.out.println("Error, valor no válido.");}
            }while(tomarorden!=1);
        }
    }

    public static void Ticket(Mesa mesa){
        System.out.println("=========TICKET=========");
        System.out.println("Mesa: "+mesa.getNumero());
        System.out.println("Mesero: "+mesa.getMesero());
        System.out.println("Personas: "+mesa.getPersonas());
        for(int i=0;i<mesa.getPedidolength();i++){
            Platillo alimento = mesa.getpedido(i);
            if(alimento!=null){System.out.println(alimento.getNombre()+" ------------------------------------ $"+alimento.getCosto());}
        }
        for(int i=0;i<mesa.getPedidolength();i++){
            Cafe alimento = mesa.getCafe(i);
            if(alimento!=null){System.out.println(alimento.getNombre()+" ------------------------------------ $"+alimento.getCosto());}
        }
        System.out.println("Subtotal: ------------------------------------ $"+mesa.getTotal());
        System.out.println("IVA (16%): ------------------------------------ $"+mesa.getTotal()*0.16);
        System.out.println("Total: ------------------------------------ $"+mesa.getTotal()*1.16);
        mesa.setActivo(false);
    }
}