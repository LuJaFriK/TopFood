/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package topfood;


/**
 *
 * @author Luis Javier Robles Topete 25460001
 */
public class Comandera {
    
    
    public static Mesero LogIn(Mesero[] meseros, int password) {
        int i = 0;
        while (i < meseros.length - 1) {
            if (meseros[i] != null && meseros[i].login(password)) {
                return meseros[i];
            }
            i++;
        }
        System.out.println("Error. Contraseña incorrecta.");
        Aux.wait(1000);
        return null;
    }

    public static void crearMesero(Mesero[] meseros) {
        int i;
        for (i = 0; i < meseros.length; i++) {
            if (meseros[i] == null) {
                String nombre = Aux.InputString("Ingresa el nombre del mesero:");
                boolean done = false;
                int pass1, pass2;
                do {
                    pass1 = Aux.InputInt("Ingresa la contraseña: ");
                    pass2 = Aux.InputInt("Confirma tu contraseña: ");
                    if (pass1 == pass2) {
                        done = true;
                    } else {
                        System.out.println("Error. las contraseñas no coinciden.");
                        Aux.wait(2000);
                    }
                } while (!done);
                meseros[i] = new Mesero(nombre, i, pass1);
                return;
            }
        }
        if (i == meseros.length) {
            System.out.println("Error. No pueden ingresar más meseros.");
            Aux.wait(2000);
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
        
        String menus = """
            Ingrese la acción que desea realizar a continuación:
            1.- Iniciar sesión como mesero.
            2.- Registrar un mesero nuevo.
            3.- Ingresar nuevos alimentos.
            4.- Dar de baja un producto.
            5.- Dar de alta un producto.
            6.- Salir del sistema.""";

        Mesero user = null;
        int opcion;

        do {

            opcion = Aux.InputInt(menus);
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
                    SetExistencia(Aux.InputString("Ingresa el nombre de el producto: "), menu, false);
                    break;
                case 5:
                    SetExistencia(Aux.InputString("Ingresa el nombre de el producto: "), menu, true);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error. Opción no válida.");
                    Aux.wait(2000);
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
        Aux.wait(2000);
    }

    public static void MenuMesero(Mesero user, Mesero[] meseros, Mesa[] mesas, Alimento[] menu) {
        int opc, fallas = 0, i;
        String menumesero = """
        1.- Abrir cuenta.
        2.- Visualizar mesas.
        3.- Agregar productos.
        4.- Cerrar una cuenta.
        5.- Eliminar una cuenta.
        6.- Juntar cuentas.
        7.- Salir.""";
        do {
            boolean myMesa, isActive;

            while (user == null) {
                user = LogIn(meseros, Aux.InputInt("Ingresa tu contraseña para iniciar sesión:"));
                fallas += 1;
                if (fallas >= 3) {
                    fallas = 0;
                    int crear = Aux.InputInt("Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo? \n 1. Si      2. No");
                    if (crear == 1) {
                        crearMesero(meseros);
                    } else {
                        return;
                    }
                }
            }
            System.out.println("Bienvenido " + user.getNombre() + ", elija la opción a continuación: ");
            
            opc = Aux.InputInt(menumesero);
            switch (opc) {
                case 1:
                    asignarMesa(user, mesas, menu);
                    break;
                case 2:
                    for (i = 0; i < mesas.length; i++) {
                        if (mesas[i] != null && mesas[i].getMesero() == user) {
                            mesas[i].detalles();
                            mesas[i].printPedido();
                        }
                    }                
                    break;
                case 3:
                    i = Aux.InputInt("Ingresa el numero de la mesa:");
                    myMesa = isMyMesa(user, mesas[i]);
                    if (myMesa) {
                        hacerPedido(mesas[i], user, menu);
                        System.out.println(user.getNombre());
                    }
                    break;
                case 4:
                    i = Aux.InputInt("Ingresa la cuenta que deseas cerrar:");
                    try{
                        myMesa = isMyMesa(user, mesas[i]);
                    isActive = mesas[i].isActivo();
                    if (myMesa && isActive) {
                        Ticket(mesas[i]);
                        mesas[i].setActivo(false);
                    } else {
                        System.out.println("Error. La mesa ya fue cerrada o no es operada por el mesero.");
                        Aux.wait(2000);
                    }
                    }catch(NullPointerException ex){
                        System.out.println("Error, la mesa no está siendo operada.");
                        Aux.wait(2000);
                    }
                    break;
                case 5:
                    DeleteMesa(user, mesas);
                    break;
                case 6:
                    juntarMesas(user, mesas);
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Error. Opción no válida.");
                    Aux.wait(2000);
                    break;
            }
        } while (opc != 7);
    }

    public static void juntarMesas(Mesero mesero, Mesa[]mesas){
        try{
            System.out.println("Ingresa los números de las mesas:");
            int a = Aux.InputInt("Mesa 1:");
            int b = Aux.InputInt("Mesa 2:");
            int newmesa = Aux.InputInt("Ingresa el numero de la nueva mesa:");
            Mesa mesa1 = mesas[a];
            Mesa mesa2 = mesas[b];
            if(isMyMesa(mesero, mesa1) && isMyMesa(mesero, mesa2) ){
                Mesa newMesa = new Mesa(mesero, newmesa,(mesa1.getPersonas()+mesa2.getPersonas()),true);
                Mesa[] temparray = {mesa1, mesa2};
                for (Mesa mesa : temparray) {
                    for(int i=0;i<mesa.getPedidolength();i++){
                        newMesa.addPedido(mesa.getpedido(i), 1);
                    }
                }
                if(mesa1.getNumero() == newmesa || mesa2.getNumero()== newmesa || mesas[newmesa]==null){
                    mesas[a] = null;
                    mesas[b] = null;
                }
            }else{
                System.out.println("Error. La/s mesas no corresponden al mesero.");
                Aux.wait(2000);
            }
        }catch(NullPointerException ex){
            System.out.println("Error. La/s mesa/s no han sido encontrada/s.");
            Aux.wait(2000);
        }
        
    }

    public static boolean isMyMesa (Mesero mesero, Mesa mesa){
        try{
            if(mesa.getMesero()==mesero){
                return true;
            }
        }catch(NullPointerException ex){
            System.out.println("Error, la mesa no ha sido encontrada o no es operada por el mesero.");
            Aux.wait(2000);
        }
        return false;
    }

    public static void crearAlimento(Alimento[] menu) {
        String menuCrear = """
        Crear:
        1. Platillo
        2. Café""";
        int opc = Aux.InputInt(menuCrear);
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] == null) {
                switch (opc) {
                    case 1:
                        Aux.OverrideFile("Menú.txt", i+"\n Platillo");
                        Aux.OverrideFile("Menú.txt", Aux.InputString("Ingresa el nombre del Platillo"));
                        Aux.OverrideFile("Menú.txt", Aux.InputString("Ingresa el costo base del producto (Orden Completa):"));
                        Aux.OverrideFile("Menú.txt", "True");
                        return;
                    case 2:
                        Aux.OverrideFile("Menú.txt", i+" Café");
                        Aux.OverrideFile("Menú.txt", Aux.InputString("Ingresa el nombre del Café"));
                        Aux.OverrideFile("Menú.txt", Aux.InputString("Ingresa el costo base del producto (Chico):"));
                        return;
                    default:
                        System.out.println("Error. Opcion no valida");
                        Aux.wait(2000);
                        break;
                }
            }
        }     
        System.out.println("Error al crear alimento nuevo.");
        Aux.wait(2000);
    }

    public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[] menu) {
        if (!isMyMesa(mesero, mesa)) {
            System.out.println("La mesa a la que desea agregar alimentos no pertenece al mesero.");
            return;
        }
        
        Alimento[] comanda = new Alimento[100];
        int contador = 0;
        boolean platos = false, cafes = false;
        while (contador < comanda.length) {

            Alimento pedido = buscarAlimento(Aux.InputString("Busqueda por nombre:"), menu);
    
            if (pedido != null) {
                String comentario =  Aux.InputString("Realizar un comentario:");
                pedido.setComentario(comentario);
    
                if (pedido instanceof Platillo) {
                configurarPlatillo((Platillo) pedido);
                platos = true;
                } else if (pedido instanceof Cafe) {
                configurarCafe((Cafe) pedido);
                cafes = true;
                }
                int cantidad = Aux.InputInt(pedido instanceof Cafe ? "Cantidad de cafés iguales:" : "Cantidad de platillos iguales:");
                agregarPedido(comanda, pedido, cantidad,mesa);
                contador += cantidad;
                if (!deseaOtroPedido()) {
                    mesa.setTotal();
                    mostrarResumen(comanda, platos, cafes);
                    return;
                }
            }     
        }
        System.out.println("No se pueden ingresar más alimentos a la cuenta.");
        Aux.wait(2000);
    }
    
    private static void configurarPlatillo(Platillo platillo) {
        int opcion;
        
        String menuorden = """
        Orden completa o media orden:
        1. Orden Completa
        2. Media Orden""";

        do {
            
            opcion = Aux.InputInt(menuorden);
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
                    Aux.wait(2000);
                break;
            }
        } while (opcion == -1);
    }
    
    private static void configurarCafe(Cafe cafe) {
        int opcion;
        String[] menucafeteria = {
            """
            Cafeina:
            1. Sí
            2. No""",
            """
            Hielo:
            1. Frío
            2. Caliente         """,
        };        
        do {
            opcion = Aux.InputInt(menucafeteria[0]);
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
                    Aux.wait(2000);
                break;
            }
        } while (opcion == -1);
    
        do {
            opcion = Aux.InputInt(menucafeteria[1]);
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
                    Aux.wait(2000);
                    break;
                
            }
        } while (opcion == -1);

        do {
            cafe.printMilklist();
            opcion = Aux.InputInt("Tipo de leche:");
            if (opcion >= 0 && opcion < cafe.getMilklistSize()) {
                cafe.setMilk(opcion);
            } else {
                System.out.println("Error. Opción no válida.");
                opcion = -1;
                Aux.wait(2000);
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
        String otropedido = 
        """
        ¿Desea ingresar otro pedido?
        1. Sí
        2. No""";
        int condicion = Aux.InputInt(otropedido);
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
        int i = Aux.InputInt("Ingresa el numero de la mesa: ");
        if (mesas[i]!=null) {
            System.out.println("Error, la mesa está ocupada.");
        } else {
            int personas = Aux.InputInt("Ingresa el numero de personas: ");
            mesas[i] = new Mesa(mesero, i, personas, true);
            mesero.addContador();
            int tomarorden = 0;
            do {
                String ordenar = """
                Desea tomar la orden?
                1. Si       2. No""";
                tomarorden = Aux.InputInt(ordenar);
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
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "=========TICKET=========");
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Mesa: " + mesa.getNumero());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt","Mesero: " + mesa.getMesero().getNombre() +" : "+ mesa.getMesero().getCodigo());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Personas: " + mesa.getPersonas());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Nombre  =========   Costo");
        for(int i=0;i<mesa.getPedidolength();i++){
            if(mesa.getpedido(i)!=null){
                Aux.OverrideFile(mesa.getNumero()+"_temp.txt", mesa.getpedido(i).getNombre() + " =============  $" + mesa.getpedido(i).getCosto());
            }
        }
        System.out.println("===============================");
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Subtotal: ---------- $" + mesa.getTotal());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "IVA (16%): --------- $" + (mesa.getTotal() * 0.16));
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Total: ------------- $" + (mesa.getTotal() * 1.16));
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "=========================");
        mesa.setActivo(false);

    }


    public static void DeleteMesa(Mesero user, Mesa[] mesas){
        try{
            int i = Aux.InputInt("Ingresa el numero de la cuenta a eliminar:");
            if(isMyMesa(user, mesas[i])){
                if (mesas[i].isActivo()==false) {
                    mesas[i] = null;
                } else {
                    System.out.println("Error. La mesa sigue activa o no es operada por el mesero.");
                }
            }
        }catch(NullPointerException ex){
            System.out.println("Error, la mesa no existe.");
            Aux.wait(2000);
        }
        
        
    }
}
