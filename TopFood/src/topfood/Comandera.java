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
                        System.out.println("Error. las contraseñas no coinciden.");
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

        for (int x = 0; x < mesas.length; x++) {
            mesas[x] = new Mesa(x);
        }

        Platillo[] menu = new Platillo[90]; // puede ser variable de instancia
        menu[0] = new Platillo("Pizza", 4.9, "Some comment", true);
        menu[1] = new Platillo("Taco", 3.8, "Another comment", false);
        menu[2] = new Platillo("Platillo3", 5.7, "Yet another comment", true);
        menu[3] = new Platillo("Platillo4", 2.9, "The final comment", false);
        menu[4] = new Platillo("Platillo5", 1.8, "Final final comment", true);
        Cafe[] cafeteria = new Cafe[50];
        cafeteria[0] = new Cafe("Expresso", 3.50, "No se especifica.", true);
        cafeteria[1] = new Cafe("Americano", 2.80, "Sin crema.", true);
        cafeteria[2] = new Cafe("Latte", 4.20, "Leche enteral y espuma.", false);
        cafeteria[3] = new Cafe("Cappuccino", 4.00, "Espuma de leche.", true);
        cafeteria[4] = new Cafe("Mocha", 5.00, "Chocolate y leche entera.", false);
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
                    MenuMesero(user, meseros, mesas, menu, cafeteria);
                    break;
                case 2:
                    crearMesero(meseros);
                    break;
                case 3:
                    crearAlimento(menu, cafeteria);
                    break;
                case 4:
                    System.out.println("Ingresa el nombre de el producto: ");
                    SetExistencia(scanner.nextLine(), menu, cafeteria, false);
                    break;
                case 5:
                    System.out.println("Ingresa el nombre de el producto: ");
                    SetExistencia(scanner.nextLine(), menu, cafeteria, true);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
            }
        } while (opcion != 4);
    }


    public static void SetExistencia(String nombre,Platillo[]menu,Cafe[]cafeteria,boolean existencia){
        int i;
        for(i=0;i<menu.length;i++){
            if(menu[i].getNombre().equalsIgnoreCase(nombre)){
                menu[i].setExistencia(existencia);
                return;
            }
        }
        for(i=0;i<cafeteria.length;i++){
            if(cafeteria[i].getNombre().equalsIgnoreCase(nombre)){
                cafeteria[i].setExistencia(existencia);
                return;
            }
        }
    }

    public static void MenuMesero(Mesero user, Mesero[] meseros, Mesa[] mesas, Platillo[] menu, Cafe[] cafeteria) {
        int opc, fallas = 0, i, opt;
        do {
            boolean myMesa, isActive;

            while (user == null) {
                System.out.println("Ingresa tu contraseña para iniciar sesión:");
                user = LogIn(meseros, scanner.nextInt());
                fallas += 1;
                if (fallas >= 3) {
                    System.out.println(
                            "Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo?");
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
            System.out.println("6.- Separar o juntar cuentas.");
            System.out.println("7.- Salir.");
            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 1:
                    asignarMesa(user, mesas, menu, cafeteria);
                    break;
                case 2:
                    user.printMesas();
                    break;
                case 3:
                    System.out.println("Ingresa el numero de la mesa:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(mesas[i]);
                    if (myMesa) {
                        hacerPedido(mesas[i], user, menu, cafeteria);
                        System.out.println(user.getNombre());
                    } else {
                        System.out.println("Error. La mesa no fue encontrada activa o no es operada por el mesero.");
                    }
                    break;
                case 4:
                    System.out.println("Ingresa la cuenta que deseas cerrar:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(mesas[i]);
                    isActive = mesas[i].isActivo();
                    if (myMesa && isActive) {
                        Ticket(mesas[i]);
                        mesas[i].setActivo(false);
                    } else {
                        System.out.println("Error. La mesa no fue encontrada activa o no es operada por el mesero.");
                    }
                    break;
                case 5:
                    DeleteMesa(user,mesas);
                    break;
                case 6:
                    System.out.println("Desea separar o juntar cuentas?");
                    System.out.println("1. Separar       2. Juntar");
                    opt = scanner.nextInt();
                    switch (opt){
                        case 1:
                            
                        break;
                        case 2:

                        break;
                        default:

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
        } while (opc != 7);
    }


    public static void crearAlimento(Platillo[] menu, Cafe[] cafeteria) {
        scanner.nextLine();
        int i, opc;
        String nombre;
        double costo = 0;
        System.out.println("Crear:");
        System.out.println("1. Platillo");
        System.out.println("2. Café");
        opc = scanner.nextInt();
        scanner.nextLine();
        if (opc == 1) {
            for (i = 0; i < menu.length; i++) {
                if (menu[i] == null) {
                    System.out.println("Ingresa el nombre del Platillo");
                    nombre = scanner.nextLine();
                    System.out.println("Ingresa el costo base del producto (Orden Completa):");
                    costo = scanner.nextDouble();
                    menu[i] = new Platillo(nombre, costo, null, true);
                    return;
                }
            }
            System.out.println("Error al crear un Platillo nuevo.");

        } else if (opc == 2) {
            for (i = 0; i < cafeteria.length; i++) {
                if (cafeteria[i] == null) {
                    System.out.println("Ingresa el nombre del Cafe");
                    nombre = scanner.nextLine();
                    System.out.println("Ingresa el costo base del producto (Chico):");
                    costo = scanner.nextDouble();
                    cafeteria[i] = new Cafe(nombre, costo, null, true);
                    return;
                }
            }
            System.out.println("Error al crear un Café nuevo.");
        } else {
            System.out.println("Error. Opcion no valida");
        }
    }
    
    public static void hacerPedido(Mesa mesa, Mesero mesero, Platillo[] menu, Cafe[] cafeteria) {
        boolean done = false;
        Platillo[] comanda = new Platillo[100];
        Cafe[] bebidas = new Cafe[100];
        int contPlat = 0, contBeb = 0,x = 0;
        if (!mesero.isMyMesa(mesa))
        do{
            // Los objetos temporales que registran las ordenes, se limpian luego de cada registro.
            Platillo tempedido = null;
            Cafe tempcafe = null;
            int i = 0, opc = 0, condicion = -1, cantidad = 0;
            String nombrealimento = null;

            System.out.println("Ingresar:");
            System.out.println("1. Platillo");
            System.out.println("2. Cafe");
            System.out.println("3. Salir");
            opc = scanner.nextInt();
            scanner.nextLine();
            switch (opc) {
                case 1:
                System.out.println("Ingresa el nombre del Platillo");
                nombrealimento = scanner.nextLine();
                // Busca el platillo por nombre, si lo encuentra, regresa el platillo, si no,
                // null
                tempedido = buscarPlatillo(nombrealimento, menu);
                if (tempedido != null) {// Si se encontro un platillo, se registra en el pedido de la mesa
                    for (i = 0; i < mesa.getPedidolength(); i++) {// ciclo para encontrar el proximo espacio vacio del
                                                                  // pedido para insertarlo
                        if (mesa.getpedido(i) == null) {// Si el espacio del arreglo esta vacio, se inserta el platillo
                                                        // en el pedido

                            // Inicia registro del pedido

                            System.out.println("Realizar un comentario: ");
                            tempedido.setComentario(scanner.nextLine());// Realizar un comentario para el pedido antes
                                                                        // de ingresarlo
                            while (condicion == -1) {// Ciclo para ingresar orden o media orden
                                System.out.println("Orden completa o media orden");
                                System.out.println("1. Orden Completa");
                                System.out.println("2. Media orden");
                                condicion = scanner.nextInt();
                                if (condicion == 1) {
                                    tempedido.setOrdenCompleta(true);
                                } else if (condicion == 2) {
                                    tempedido.setOrdenCompleta(false);
                                } else {
                                    System.out.println("Error. Opcion no valida.");
                                    condicion = -1;
                                }
                            }
                            System.out.println("Cantidad de platillos iguales:");// Ingresar cuantos platillos iguales a
                                                                                 // este hay
                            cantidad = scanner.nextInt();

                            mesa.addPedido(tempedido, cantidad);// Se agrega el platillo
                            for(i=0;i<cantidad;i++){
                                comanda[contPlat]=tempedido;
                                contPlat++;
                            }
                            // Termina registro del pedido

                            // Continuar con el pedido o cerrar el pedido.
                            System.out.println("Desea agregar otro pedido?");
                            System.out.println("1. Si");
                            System.out.println("2. No");
                            condicion = scanner.nextInt();
                            if (condicion == 2) {
                                done = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                } else {// Si platillo es null, no sucede nada, solo se repite el ciclo
                    System.out.println("Error. Platillo no encontrado.");
                }
                    break;
                
                case 2:
                System.out.println("Ingresa el nombre del Cafe");
                nombrealimento = scanner.nextLine();
                // Busca el platillo por nombre, si lo encuentra, regresa el platillo, si no,
                // null
                tempcafe = buscarCafe(nombrealimento, cafeteria);
                if (tempcafe != null) {// Si se encontro un platillo, se registra en el pedido de la mesa
                    for (i = 0; i < mesa.getCafeLength(); i++) {// ciclo para encontrar el proximo espacio vacio del
                                                                // pedido para insertarlo
                        if (mesa.getCafe(i) == null) {// Si el espacio del arreglo esta vacio, se inserta el platillo en
                                                      // el pedido

                            // Inicia registro del pedido

                            System.out.println("Realizar un comentario: ");
                            tempcafe.setComentario(scanner.nextLine());// Realizar un comentario para el pedido antes de
                                                                       // ingresarlo
                            while (condicion == -1) {// Ciclo para ingresar Cafeina o Descafeinado
                                System.out.println("Cafeina:");
                                System.out.println("1. Si");
                                System.out.println("2. No");
                                condicion = scanner.nextInt();
                                if (condicion == 1) {
                                    tempcafe.setCafeina(true);
                                } else if (condicion == 2) {
                                    tempcafe.setCafeina(false);
                                } else {
                                    System.out.println("Error. Opcion no valida.");
                                    condicion = -1;
                                }
                            }
                            condicion = -1;
                            while (condicion == -1) {// Ciclo para ingresar Frio o Caliente
                                System.out.println("Hielo:");
                                System.out.println("1. Frio");
                                System.out.println("2. Caliente");
                                condicion = scanner.nextInt();
                                if (condicion == 1) {
                                    tempcafe.setHielo(true);
                                } else if (condicion == 2) {
                                    tempcafe.setHielo(false);
                                } else {
                                    System.out.println("Error. Opcion no valida.");
                                    condicion = -1;
                                }
                            }
                            condicion = -1;
                            while (condicion == -1) {// Ciclo para ingresar tipo de leche
                                System.out.println("Tipo de leche:");
                                tempcafe.printMilklist();
                                condicion = scanner.nextInt();
                                if(condicion>=0&&condicion<tempcafe.getMilklistSize()){
                                    tempcafe.setMilk(i);
                                } else {
                                    System.out.println("Error. Opcion no valida.");
                                    condicion = -1;
                                }
                            }
                            condicion = -1;
                            while (condicion == -1) {// Ciclo para ingresar tamaño de la bebida
                                System.out.println("Tamaños:");
                                tempcafe.printSizeList();
                                condicion = scanner.nextInt();
                                if(condicion>=0&&condicion<tempcafe.getSizeListSize()){
                                    tempcafe.setMilk(i);
                                } else {
                                    System.out.println("Error. Opcion no valida.");
                                    condicion = -1;
                                }
                            }

                            System.out.println("Cantidad de bebidas iguales:");// Ingresar cuantos platillos iguales a
                                                                               // este hay
                            cantidad = scanner.nextInt();

                            mesa.addCafe(tempcafe, cantidad);// Se agrega el platillo
                            for(i=0;i<cantidad;i++){
                                bebidas[contBeb]=tempcafe;
                                contBeb++;
                            }
                            // Termina registro del pedido

                            // Continuar con el pedido o cerrar el pedido.
                            System.out.println("Desea agregar otro pedido?");
                            System.out.println("1. Si");
                            System.out.println("2. No");
                            condicion = scanner.nextInt();
                            if (condicion == 2) {
                                done = true;
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                } else {// Si platillo es null, no sucede nada, solo se repite el ciclo
                    System.out.println("Error. cafe no encontrado.");
                }
                    break;
                    
                case 3:
                System.out.println("Saliendo...");
                done = true;
                    break;
            
                default:
                System.out.println("Error. Opcion no valida.");
                    break;
            }
        } while (!done);
        //Imprimir comanda
        if(contPlat>0){
            System.out.println("=================================");
            System.out.println("Platillos:");
            for(x=0;x<contPlat;x++){
                comanda[x].detalles();
            }
            System.out.println("=================================");
        }
        
        if(contBeb>0){
            System.out.println("Café");
            for(x=0;x<contBeb;x++){
                bebidas[x].detalles();
            }
            System.out.println("=================================");

        }
    }

    // Metodo buscador de un platillo, si lo encuentra retorna el platillo, si no,
    // retorna null

    public static Platillo buscarPlatillo(String nombre, Platillo[] menu) {
        for(Platillo platillo:menu){
            if(platillo != null && platillo.getNombre().equalsIgnoreCase(nombre)){
                return platillo;
            }
        }
        return null;
    }

    // Metodo buscador de un cafe, si lo encuentra retorna el cafe, si no, retorna
    // null

    public static Cafe buscarCafe(String nombre, Cafe[] cafeteria) {
        for(Cafe cafe: cafeteria){
            if(cafe != null && cafe.getNombre().equalsIgnoreCase(nombre)){
                return cafe;
            }
        }
        return null;
    }

    // Metodo para abrir una mesa, actualmente pendiente de correccion

    public static void asignarMesa(Mesero mesero, Mesa[] mesas, Platillo[] menu, Cafe[] cafeteria) {
        System.out.println("Ingresa el numero de la mesa: ");
        int i = 0;
        i = scanner.nextInt();
        if (mesas[i].isActivo()) {
            System.out.println("Error, la mesa está ocupada.");
        } else {
            System.out.println("Ingresa el numero de personas: ");
            int personas = scanner.nextInt();
            mesas[i] = new Mesa(mesero, i, personas, true);
            mesero.addMesa(mesas[i]);
            int tomarorden = 0;
            do {
                System.out.println("Desea tomar la orden?");
                System.out.println("1. Si       2. No");
                tomarorden = scanner.nextInt();
                if (tomarorden == 1) {
                    hacerPedido(mesas[i], mesero, menu, cafeteria);
                } else if (tomarorden == 2) {
                    return;
                } else {
                    System.out.println("Error, valor no válido.");
                }
            } while (tomarorden != 1);
        }
    }

    // Metodo para imprimir la cuenta y darla por terminada, necesita ser probado
    // aun

    public static void Ticket(Mesa mesa) {
        System.out.println("=========TICKET=========");
        System.out.println("Mesa: " + mesa.getNumero());
        System.out.println("Mesero: " + mesa.getMesero());
        System.out.println("Personas: " + mesa.getPersonas());
        for (int i = 0; i < mesa.getPedidolength(); i++) {// Ciclo que imprime todos los alimentos de la mesa
            Platillo alimento = mesa.getpedido(i);
            if (alimento != null) {
                System.out.println(
                        alimento.getNombre() + " ------------------------------------ $" + alimento.getCosto());
            }
        }
        for (int i = 0; i < mesa.getPedidolength(); i++) {// Ciclo que imprime todo lo relacionado a cafeteria
            Cafe alimento = mesa.getCafe(i);
            if (alimento != null) {
                System.out.println(
                        alimento.getNombre() + " ------------------------------------ $" + alimento.getCosto());
            }
        }
        System.out.println("Subtotal: ------------------------------------ $" + mesa.getTotal());
        System.out.println("IVA (16%): ------------------------------------ $" + mesa.getTotal() * 0.16);
        System.out.println("Total: ------------------------------------ $" + mesa.getTotal() * 1.16);
        mesa.setActivo(false);
    }
    

    public static void DeleteMesa(Mesero user, Mesa[] mesas){
        int i =0;
        System.out.println("Ingresa el numero de la cuenta a eliminar:");
        i = scanner.nextInt();
        boolean myMesa = user.isMyMesa(mesas[i]);
        boolean isActive = mesas[i].isActivo();
        if (myMesa && !isActive) {
            user.cleanMesa(mesas[i]);
            mesas[i] = new Mesa(i);
        } else {
            System.out.println("Error. La mesa sigue activa o no es operada por el mesero.");
        }
    }
}