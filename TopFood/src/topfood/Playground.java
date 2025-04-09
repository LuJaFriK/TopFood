package topfood;

import java.util.Scanner;

public class Playground {
     private static Scanner scanner = new Scanner(System.in);
     public static void main (String[]args){
          Alimento[] menu = new Alimento[2];
          menu[0] = new Platillo("Taco", 10, null, true);
          menu[1] = new Cafe("Mocca", 30, "Sin endulzante", false);
          if(menu[0] instanceof Platillo){
               Platillo platillo = (Platillo) menu[0];
               platillo.setOrdenCompleta(true);
               platillo.setPrioridadAlta(true);
          }
          if(menu[1] instanceof Cafe){
               Cafe cafe = (Cafe) menu[1];
               cafe.setCafeina(true);
               cafe.setHielo(false);
               cafe.setMilk(3);
          }
          menu[0].detalles();
          menu[1].detalles();
     }

     public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[] menu){
          if (!mesero.isMyMesa(mesa)){
               System.out.println("Error, esta mesa no está en control del mesero.");
               return;
           }
          boolean done = false;
          Alimento[] comanda = new Alimento[100];
          int contador;
          do{
               int i = 0, opc, condicion = -1, cantidad;
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
                                             System.out.println("Cantidad de platillos iguales:");// Ingresar cuantos platillos iguales a
                                             // este hay
                                             cantidad = scanner.nextInt();
                                             pedido.detalles();
                                             mesa.addPedido(pedido,cantidad);
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
                                        break;
                                   case 3:
                                        System.out.println("Saliendo...");
                                        return;
                                   default:
                                        System.out.println("Error. Opción no válida.");
                                        break;
                              }
                         }
                    }
               }
               System.out.println("No se pueden ingresar más alimentos a la cuenta.");

          }while(comanda[99]==null);
     }
     public static Alimento buscarAlimento(String nombre, Alimento[] menu) {
          for(Alimento alimento: menu){
               if(alimento != null && alimento.getNombre().equalsIgnoreCase(nombre)){
                    return alimento;
               }
          }
          System.out.println("No se encontró el alimento.");
          return null;
     }


}
