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

        Alimento [] menu = new Alimento[90]; // puede ser variable de instancia
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
        } while (opcion != 4);
    }


    public static void SetExistencia(String nombre,Alimento[]menu,boolean existencia){
        for(Alimento alimento : menu){
            if(alimento.getNombre().equalsIgnoreCase(nombre)){
                alimento.setExistencia(existencia);
                return;
            }
        }
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
                    asignarMesa(user, mesas, menu);
                    break;
                case 2:
                    user.printMesas();
                    break;
                case 3:
                    System.out.println("Ingresa el numero de la mesa:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(i);
                    if (myMesa) {
                        hacerPedido(mesas[i], user, menu);
                        System.out.println(user.getNombre());
                    } else {
                        System.out.println("Error. La mesa no fue encontrada activa o no es operada por el mesero.");
                    }
                    break;
                case 4:
                    System.out.println("Ingresa la cuenta que deseas cerrar:");
                    i = scanner.nextInt();
                    myMesa = user.isMyMesa(i);
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
                            System.out.println("Error. Opcion no valida");
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


    public static void crearAlimento(Alimento[] menu) {

        System.out.println("Crear:");
        System.out.println("1. Platillo");
        System.out.println("2. Café");
        int opc = scanner.nextInt();
        scanner.nextLine();
        for(Alimento alimento : menu){
            if(alimento==null){
                String nombre; double costo;
                switch (opc) {
                    case 1:
                        System.out.println("Ingresa el nombre del Platillo");
                        nombre = scanner.nextLine();
                        System.out.println("Ingresa el costo base del producto (Orden Completa):");
                        costo = scanner.nextDouble();
                        alimento = new Platillo(nombre, costo, null, true);
                        return;
                    case 2:
                        System.out.println("Ingresa el nombre del Cafe");
                        nombre = scanner.nextLine();
                        System.out.println("Ingresa el costo base del producto (Chico):");
                        costo = scanner.nextDouble();
                        alimento = new Cafe(nombre, costo, null, true);
                        return;
                
                    default:
                        System.out.println("Error. Opcion no valida");
                        break;
                }
            }
        }System.out.println("Error al crear alimento nuevo.");
    }
    
    public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[] menu){
        if (!mesero.isMyMesa(mesa.getNumero())){
             System.out.println("Error, esta mesa no está en control del mesero.");
             return;
        }
        Alimento[] comanda = new Alimento[100];
        int contador = 0;
        boolean platos = false, cafes = false;
        do{
             boolean done = false;
             int opc, condicion = -1, cantidad;
             String nombrealimento;

             System.out.println("Ingresar:");
             System.out.println("1. Platillo");
             System.out.println("2. Cafe");
             System.out.println("3. Salir");
             opc = scanner.nextInt();
             scanner.nextLine();
             
             for(Alimento pedido:comanda){
                  if(pedido == null){
                       System.out.println("Busqueda por nombre:");
                       nombrealimento = scanner.nextLine();
                       scanner.nextLine();
                       pedido = buscarAlimento(nombrealimento, menu);
                       if(pedido!=null){
                            System.out.println("Realizar un comentario: ");
                            pedido.setComentario(scanner.nextLine());
                            switch(opc){
                                 case 1:
                                      Platillo platillo = (Platillo) pedido;
                                      while (condicion == -1) {// Ciclo para ingresar orden o media orden
                                           System.out.println("Orden completa o media orden");
                                           System.out.println("1. Orden Completa");
                                           System.out.println("2. Media orden");
                                           condicion = scanner.nextInt();
                                           switch (condicion){
                                                case 1:
                                                     platillo.setOrdenCompleta(true);
                                                     break;
                                                case 2:
                                                     platillo.setOrdenCompleta(false);
                                                     break;
                                                default:
                                                     System.out.println("Error. Opcion no valida.");
                                                     condicion = -1;
                                                     break;
                                           }
                                      }
                                      done = true;
                                      platos = true;     
                                 break;
                                 case 2:
                                      Cafe cafe  = (Cafe) pedido;
                                      while(condicion ==-1){
                                           System.out.println("Cafeina:");
                                           System.out.println("1. Si");
                                           System.out.println("2. No");
                                           condicion = scanner.nextInt();
                                           switch(opc){
                                                case 1:
                                                     cafe.setCafeina(true);
                                                     break;
                                                case 2:
                                                     cafe.setCafeina(false);
                                                     break;
                                                default:
                                                     System.out.println("Error. valor no valido.");
                                                     condicion = -1;
                                                     break;
                                           }
                                      }
                                      condicion = -1;
                                      while (condicion == -1) {// Ciclo para ingresar Frio o Caliente
                                           System.out.println("Hielo:");
                                           System.out.println("1. Frio");
                                           System.out.println("2. Caliente");
                                           condicion = scanner.nextInt();
                                           switch (condicion){
                                                case 1:
                                                     cafe.setHielo(true);
                                                     break;
                                                case 2:
                                                     cafe.setHielo(false);
                                                     break;
                                                default:
                                                     System.out.println("Error. valor no valido.");
                                                     condicion = -1;
                                                     break;
                                           }
                                      }
                                      condicion = -1;
                                      while (condicion == -1) {// Ciclo para ingresar tipo de leche
                                           System.out.println("Tipo de leche:");
                                           cafe.printMilklist();
                                           condicion = scanner.nextInt();
                                           if(condicion>=0 && condicion < cafe.getMilklistSize()){
                                                cafe.setMilk(condicion);
                                           } else {
                                                System.out.println("Error. Opcion no valida.");
                                                condicion = -1;
                                           }
                                      }
                                      done = true;
                                      cafes = true;
                                 break;
                                 case 3:
                                      System.out.println("Saliendo...");
                                 return;
                                 default:
                                      System.out.println("Error. Opción no válida.");
                                 break;
                            }
                            if(done){
                                 scanner.nextLine();
                                 pedido.detalles();
                                 System.out.println("Cantidad de platillos iguales:");
                                 cantidad = scanner.nextInt();
                                 contador += cantidad;
                                 mesa.addPedido(pedido,cantidad);
                                 int pedidos = 0;
                                 for(Alimento llenado : comanda){
                                      if(llenado==null&&pedidos<cantidad){
                                           llenado = pedido;
                                      }
                                 }
                                 System.out.println("Desea ingresar otro pedido?");
                                 System.out.println("1. Si.");
                                 System.out.println("2. No.");
                                 opc = scanner.nextInt();
                                 if(opc!=1){
                                      if(contador>0){
                                           if(platos){
                                                System.out.println("=================================");
                                                System.out.println("Platillos:");
                                                for(Alimento alimento: comanda){
                                                     if(alimento instanceof Platillo){
                                                          alimento.detalles();
                                                     }
                                                }
                                                System.out.println("=================================");
                                           }
                                           if(cafes){
                                                System.out.println("=================================");
                                           System.out.println("Cafés:");
                                           for(Alimento alimento: comanda){
                                                if(alimento instanceof Cafe){
                                                     alimento.detalles();
                                                }
                                           }
                                           System.out.println("=================================");
                                           }
                                       }
                                      
                                      
                                      return;
                                 }
                            }
                       }
                  }
             }
             System.out.println("No se pueden ingresar más alimentos a la cuenta.");

        }while(comanda[99]==null);
   }

    // Metodo buscador de un alimento, si lo encuentra retorna el alimento, si no, retorna
    // null

    public static Alimento buscarAlimento(String nombre, Alimento[] menu) {
        for(Alimento alimento: menu){
            if(alimento != null && alimento.getNombre().equalsIgnoreCase(nombre)){
                return alimento;
            }
        }
        return null;
    }

    // Metodo para abrir una mesa

    public static void asignarMesa(Mesero mesero, Mesa[] mesas, Alimento[] menu) {
        System.out.println("Ingresa el numero de la mesa: ");
        int i = scanner.nextInt();
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
                    hacerPedido(mesas[i], mesero, menu);
                } else if (tomarorden == 2) {
                    return;
                } else {
                    System.out.println("Error, valor no válido.");
                }
            } while (tomarorden != 1);
        }
    }

    // Metodo para imprimir la cuenta y darla por terminada, necesita ser probado


    public static void Ticket(Mesa mesa) {
        System.out.println("=========TICKET=========");
        System.out.println("Mesa: " + mesa.getNumero());
        System.out.println("Mesero: " + mesa.getMesero());
        System.out.println("Personas: " + mesa.getPersonas());
        mesa.printPedido();
        System.out.println("Subtotal: ------------------------------------ $" + mesa.getTotal());
        System.out.println("IVA (16%): ------------------------------------ $" + mesa.getTotal() * 0.16);
        System.out.println("Total: ------------------------------------ $" + mesa.getTotal() * 1.16);
        mesa.setActivo(false);
    }
    

    public static void DeleteMesa(Mesero user, Mesa[] mesas){
        System.out.println("Ingresa el numero de la cuenta a eliminar:");
        int i = scanner.nextInt();
        boolean myMesa = user.isMyMesa(i);
        boolean isActive = mesas[i].isActivo();
        if (myMesa && !isActive) {
            user.cleanMesa(mesas[i]);
            mesas[i] = new Mesa(i);
        } else {
            System.out.println("Error. La mesa sigue activa o no es operada por el mesero.");
        }
    }
}