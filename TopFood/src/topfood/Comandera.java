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
public class Comandera {

    private static Scanner scanner = new Scanner(System.in);

    public static Mesero LogIn(Mesero[] meseros, int password) {
        int i = 0;
        while (i < meseros.length - 1) {
            if (meseros[i] != null && meseros[i].login(password)) {
                return meseros[i];
            }
            i++;
        }
        System.out.println("Error. Contraseña incorrecta.");
        return null;
    }

    public static void crearMesero(Mesero[] meseros) {
        int i;
        for (i = 0; i < meseros.length; i++) {
            if (meseros[i] == null) {
                System.out.println("Ingresa el nombre del mesero:");
                String nombre = scanner.nextLine();
                boolean done = false;
                int pass1, pass2;
                do {
                    System.out.println("Ingresa la contraseña: ");
                    pass1 = scanner.nextInt();
                    System.out.println("Confirma tu contraseña: ");
                    pass2 = scanner.nextInt();
                    if (pass1 == pass2) {
                        done = true;
                    } else {
                        System.out.println(
                            "Error. las contraseñas no coinciden."
                        );
                    }
                } while (!done);
                meseros[i] = new Mesero(nombre, i, pass1);
                return;
            }
        }
        if (i == meseros.length) {
            System.out.println("Error. No pueden ingresar más meseros.");
        }
    }
    public static void main(String[] args) {
        Mesero[] meseros = new Mesero[20];
        meseros[0] = new Mesero("Luis", 49, 2707);
        Mesa[] mesas = new Mesa[70];

        Alimento[] menu = new Alimento[90]; // puede ser variable de instancia
        menu[0] = new Platillo("Pizza", 4.9, "Some comment", true);
        menu[1] = new Platillo("Taco", 3.8, "Another comment", false);
        menu[2] = new Platillo("Platillo3", 5.7, "Yet another comment", true);
        menu[3] = new Platillo("Platillo4", 2.9, "The final comment", false);
        menu[4] = new Platillo("Platillo5", 1.8, "Final final comment", true);
        menu[5] = new Cafe("Expresso", 3.50, "No se especifica.", true);
        menu[6] = new Cafe("Americano", 2.80, "Sin crema.", true);
        menu[7] = new Cafe("Latte", 4.20, "Leche enteral y espuma.", false);
        menu[8] = new Cafe("Cappuccino", 4.00, "Espuma de leche.", true);
        menu[9] = new Cafe("Mocha", 5.00, "Chocolate y leche entera.", false);
        Mesero user = null;
        int opcion;

        do {
            System.out.println("Ingrese la acción que desea realizar a continuación:");
            System.out.println("1.- Iniciar sesión como mesero.");
            System.out.println("2.- Registrar un mesero nuevo.");
            System.out.println("3.- Ingresar nuevos alimentos.");
            System.out.println("4.- Dar de baja un producto.");
            System.out.println("5.- Dar de alta un producto.");
            System.out.println("6.- Salir del sistema.");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    // Iniciar sesion como mesero, si no, crear un mesero
                    MenuMesero(user, meseros, mesas, menu);
                    break;
                case 2:
                    crearMesero(meseros);
                    break;
                case 3:
                    crearAlimento(menu);
                    break;
                case 4:
                    System.out.println("Ingresa el nombre de el producto: ");
                    SetExistencia(scanner.nextLine(), menu, false);
                    break;
                case 5:
                    System.out.println("Ingresa el nombre de el producto: ");
                    SetExistencia(scanner.nextLine(), menu, true);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 6);
    }

    public static void SetExistencia(String nombre,Alimento[] menu,boolean existencia) {
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] != null && menu[i].getNombre().equalsIgnoreCase(nombre)) {
                menu[i].setExistencia(existencia);
                return;
            }
        }
        System.out.println("no se ha encontrado el producto.");
    }

    public static void MenuMesero(Mesero user, Mesero[] meseros, Mesa[] mesas, Alimento[] menu) {
        int opc, fallas = 0, i, opt;
        do {
            boolean myMesa, isActive;

            while (user == null) {
                System.out.println("Ingresa tu contraseña para iniciar sesión:");
                user = LogIn(meseros, scanner.nextInt());
                fallas += 1;
                if (fallas >= 3) {
                    System.out.println("Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo?");
                    System.out.println("1. Si      2. No");
                    fallas = 0;
                    int crear = scanner.nextInt();
                    scanner.nextLine();
                    if (crear == 1) {
                        crearMesero(meseros);
                    } else {
                        return;
                    }
                }
            }
            System.out.println("Bienvenido " + user.getNombre() + ", elija la opción a continuación: ");
            System.out.println("1.- Abrir cuenta. ");
            System.out.println("2.- Visualizar mesas.");
            System.out.println("3.- Agregar productos.");
            System.out.println("4.- Cerrar una cuenta.");
            System.out.println("5.- Eliminar una cuenta.");
            System.out.println("6.- Juntar cuentas.");
            System.out.println("7.- Salir.");
            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 1:
                    asignarMesa(user, mesas, menu);
                    break;
                case 2:
                    for (i = 0; i < mesas.length; i++) {
                        if (mesas[i] != null) {
                            if (mesas[i].getMesero() == user) {
                                mesas[i].detalles();
                                mesas[i].printPedido();
                            }
                        }
                    }                
                    break;
                case 3:
                    System.out.println("Ingresa el numero de la mesa:");
                    i = scanner.nextInt();
                    myMesa = isMyMesa(user, mesas[i]);
                    if (myMesa) {
                        hacerPedido(mesas[i], user, menu);
                        System.out.println(user.getNombre());
                    }
                    break;
                case 4:
                    System.out.println("Ingresa la cuenta que deseas cerrar:");
                    i = scanner.nextInt();
                    myMesa = isMyMesa(user, mesas[i]);
                    isActive = mesas[i].isActivo();
                    if (myMesa && isActive) {
                        Ticket(mesas[i]);
                        mesas[i].setActivo(false);
                    } else {
                        System.out.println("Error. La mesa no fue encontrada activa o no es operada por el mesero.");
                    }
                    break;
                case 5:
                    System.out.println("Ingresa el numero de la cuenta a eliminar:");
                    i = scanner.nextInt();
                    if(isMyMesa(user, mesas[i])){
                        DeleteMesa(user,i,mesas,mesas[i].isActivo());
                    }
                    break;
                case 6:
                    System.out.println("Ingresa los números de las mesas:");
                    System.out.print("Mesa 1:");
                    int mesa1= scanner.nextInt();
                    System.out.print("Mesa 2:");
                    int mesa2= scanner.nextInt();
                    System.out.println("Ingresa el numero de la nueva mesa:");
                    int newmesa = scanner.nextInt();
                    juntarMesas(user, mesas, mesas[mesa1], mesas[mesa2],newmesa);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error. Opción no válida.");
                    break;
            }
        } while (opc != 7);
    }

    public static void juntarMesas(Mesero mesero, Mesa[]mesas,Mesa mesa1,Mesa mesa2, int newmesa){
        if(isMyMesa(mesero, mesa1) && isMyMesa(mesero, mesa2) ){
            Mesa newMesa = new Mesa(mesero, newmesa,(mesa1.getPersonas()+mesa2.getPersonas()),true);
            Mesa[] temparray = {mesa1, mesa2};
            for (Mesa mesa : temparray) {
                for(int i=0;i<mesa.getPedidolength();i++){
                    newMesa.addPedido(mesa.getpedido(i), 1);
                }
            }
            if(mesa1.getNumero() == newmesa || mesa2.getNumero()==newmesa || mesas[newmesa]==null){
                DeleteMesa(mesero,mesa1.getNumero(), mesas, false);
                DeleteMesa(mesero,mesa2.getNumero(), mesas, false);
                mesas[newmesa] = newMesa;
            }
        }else{
            System.out.println("Error. La/s mesas no corresponden al mesero.");
        }
        
    }

    public static boolean isMyMesa (Mesero mesero, Mesa mesa){
        if(mesa!=null){
            if(mesa.getMesero()==mesero){
                return true;
            }
        }
        
        System.out.println("Error, la mesa no ha sido encontrada o no es operada por el mesero.");
        
        return false;
    }

    public static void crearAlimento(Alimento[] menu) {
        System.out.println("Crear:");
        System.out.println("1. Platillo");
        System.out.println("2. Café");
        int opc = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] == null) {
                String nombre;
                double costo;
                switch (opc) {
                    case 1:
                        System.out.println("Ingresa el nombre del Platillo");
                        nombre = scanner.nextLine();
                        System.out.println("Ingresa el costo base del producto (Orden Completa):");
                        costo = scanner.nextDouble();
                        scanner.nextLine(); // Limpia el salto de línea
                        menu[i] = new Platillo(nombre, costo, null, true);
                        return;
                    case 2:
                        System.out.println("Ingresa el nombre del Cafe");
                        nombre = scanner.nextLine();
                        System.out.println("Ingresa el costo base del producto (Chico):");
                        costo = scanner.nextDouble();
                        scanner.nextLine(); // Limpia el salto de línea
                        menu[i] = new Cafe(nombre, costo, null, true);
                        return;
                    default:
                        System.out.println("Error. Opcion no valida");
                        break;
                }
            }
        }     
        System.out.println("Error al crear alimento nuevo.");
    }

    public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[] menu) {
        if (!isMyMesa(mesero, mesa)) {
            return;
        }
        
        Alimento[] comanda = new Alimento[100];
        int contador = 0;
        boolean platos = false, cafes = false;
        scanner.nextLine();
        while (contador < comanda.length) {
            
            System.out.println("Busqueda por nombre:");
            String nombre = scanner.nextLine();
            Alimento pedido = buscarAlimento(nombre, menu);
    
            if (pedido != null) {
                System.out.println("Realizar un comentario:");
                String comentario =  scanner.nextLine();
                pedido.setComentario(comentario);
    
                if (pedido instanceof Platillo) {
                configurarPlatillo((Platillo) pedido);
                platos = true;
                } else if (pedido instanceof Cafe) {
                configurarCafe((Cafe) pedido);
                cafes = true;
                }
            
                System.out.println(pedido instanceof Cafe ? "Cantidad de cafés iguales:" : "Cantidad de platillos iguales:");
                int cantidad = scanner.nextInt();
                agregarPedido(comanda, pedido, cantidad,mesa);
                contador += cantidad;
                if (!deseaOtroPedido()) {
                    mesa.setTotal();
                    mostrarResumen(comanda, platos, cafes);
                    return;
                }
                scanner.nextLine();
            }     
        }
        System.out.println("No se pueden ingresar más alimentos a la cuenta.");
    }
    
    private static void configurarPlatillo(Platillo platillo) {
        int opcion;
        do {
            System.out.println("Orden completa o media orden:");
            System.out.println("1. Orden Completa");
            System.out.println("2. Media Orden");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1: 
                    platillo.setOrdenCompleta(true);
                    break;
                case 2:  
                    platillo.setOrdenCompleta(false);
                    break;
                default:
                    System.out.println("Error. Opción no válida.");
                    opcion = -1;
                break;
            }
        } while (opcion == -1);
    }
    
    private static void configurarCafe(Cafe cafe) {
        int opcion;
        
        do {
            System.out.println("Cafeina:");
            System.out.println("1. Sí");
            System.out.println("2. No");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1: 
                    cafe.setCafeina(true);
                    break;
                case 2: 
                    cafe.setCafeina(false);
                    break;
                default:
                    System.out.println("Error. Opción no válida.");
                    opcion = -1;
                break;
            }
        } while (opcion == -1);
    
        do {
            System.out.println("Hielo:");
            System.out.println("1. Frío");
            System.out.println("2. Caliente");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1: 
                    cafe.setHielo(true);
                    break;
                case 2: 
                    cafe.setHielo(false);
                    break;
                default: 
                    System.out.println("Error. Opción no válida.");
                    opcion = -1;
                    break;
                
            }
        } while (opcion == -1);
    
        do {
            System.out.println("Tipo de leche:");
            cafe.printMilklist();
            opcion = scanner.nextInt();
            if (opcion >= 0 && opcion < cafe.getMilklistSize()) {
                cafe.setMilk(opcion);
            } else {
                System.out.println("Error. Opción no válida.");
                opcion = -1;
            }
        } while (opcion == -1);
    }
    

    
    private static void agregarPedido(Alimento[] comanda, Alimento pedido, int cantidad, Mesa mesa) {
        int pedidosAgregados = 0;
        for (int i = 0; i < comanda.length && pedidosAgregados < cantidad; i++) {
            if (comanda[i] == null) {
                comanda[i] = pedido;
                pedidosAgregados++;
            }
        }
        mesa.addPedido(pedido, cantidad);
    }
    
    private static boolean deseaOtroPedido() {
        System.out.println("¿Desea ingresar otro pedido?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        int condicion = scanner.nextInt();
        if(condicion==1){
            return true;
        }
        return false;
    }
    
    private static void mostrarResumen(Alimento[] comanda, boolean platos, boolean cafes) {
        if (platos) {
            System.out.println("=================================");
            System.out.println("Platillos:");
            for (Alimento alimento : comanda) {
                if (alimento instanceof Platillo) {
                    System.out.println("-----------------");
                    alimento.detalles();
                    System.out.println("-----------------");
                }
            }
        }
        if (cafes) {
            System.out.println("=================================");
            System.out.println("Cafés:");
            for (Alimento alimento : comanda) {
                if (alimento instanceof Cafe) {
                    System.out.println("-----------------");
                    alimento.detalles();
                    System.out.println("-----------------");
                }
            }
        }
        System.out.println("=================================");
    }
    
    // Metodo buscador de un alimento, si lo encuentra retorna el alimento, si no, retorna
    // null

    public static Alimento buscarAlimento(String nombre, Alimento[] menu) {
        for (Alimento alimento : menu) {
            if (alimento != null && alimento.getNombre().equalsIgnoreCase(nombre)) {
                if (!alimento.isExistencia()) {
                    System.out.println("El platillo está dado de baja indefinidamente.");
                    return null;
                } else {
                    return alimento;
                }
            }
        }
        System.out.println("No se ha encontrado el alimento.");
        return null;
    }

    // Metodo para abrir una mesa

    public static void asignarMesa(Mesero mesero, Mesa[] mesas, Alimento[] menu) {
        System.out.println("Ingresa el numero de la mesa: ");
        int i = scanner.nextInt();
        if (mesas[i]!=null) {
            System.out.println("Error, la mesa está ocupada.");
        } else {
            System.out.println("Ingresa el numero de personas: ");
            int personas = scanner.nextInt();
            mesas[i] = new Mesa(mesero, i, personas, true);
            mesero.addContador();
            int tomarorden = 0;
            do {
                System.out.println("Desea tomar la orden?");
                System.out.println("1. Si       2. No");
                tomarorden = scanner.nextInt();
                if (tomarorden == 1) {
                    hacerPedido(mesas[i], mesero, menu);
                } else if (tomarorden == 2) {
                    return;
                } else {
                    System.out.println("Error, valor no válido.");
                }
            } while (tomarorden != 1);
        }
    }

    public static void Ticket(Mesa mesa) {
        System.out.println("=========TICKET=========");
        System.out.println("Mesa: " + mesa.getNumero());
        System.out.println("Mesero: " + mesa.getMesero());
        System.out.println("Personas: " + mesa.getPersonas());
        mesa.printPedido();
        System.out.println("Subtotal: ------------------------------------ $" + mesa.getTotal());
        System.out.println("IVA (16%): ------------------------------------ $" + (mesa.getTotal() * 0.16));
        System.out.println("Total: ------------------------------------ $" + (mesa.getTotal() * 1.16));
        mesa.setActivo(false);

    }


    public static void DeleteMesa(Mesero user,int i, Mesa[] mesas, boolean condition){
        if (condition==false) {
            mesas[i] = null;
        } else {
            System.out.println("Error. La mesa sigue activa o no es operada por el mesero.");
        }
        
    }
}
