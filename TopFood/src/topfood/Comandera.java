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
    
    
    private static Mesero LogIn(Mesero[] meseros) {

        for (int fallas = 0; fallas<3;fallas++) {//Registra cuantas veces ha fallado
            int password = Aux.InputInt("Ingresa tu contraseña para iniciar sesión:");
            for(int i = 0;i<meseros.length;i++){ //Compara con las contraseñas de cada mesero
                if (meseros[i] == null) {
                    System.out.println("Error. Contraseña incorrecta.");
                    Aux.wait(1500);
                    break;//En caso de que la contraseña no pertenezca a ningun mesero, regresa al ciclo externo
                }else if ( meseros[i].login(password)){
                    return meseros[i];
                }
            }
        }
        int crear = Aux.InputIntRange("Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo? \n 1. Si      2. No",1,2);
        if (crear == 1) crearMesero(meseros); 
        //En caso que no se haya creado un mesero nuevo ni la contraseña sea correcta, devuelve null
        return null;            
    }

    public static void crearMesero(Mesero[] meseros) {
        for (int i = 0; i < meseros.length; i++) {
            if (meseros[i] == null) {
                String nombre = Aux.InputString("Ingresa el nombre del mesero:");
                do {
                    int password = Aux.InputInt("Ingresa la contraseña: ");
                    if (password == Aux.InputInt("Confirma tu contraseña: ")) {
                        meseros[i] = new Mesero(nombre, i, password);
                        return;
                    } else {
                        System.out.println("Error. las contraseñas no coinciden.");
                        Aux.wait(1500);
                    }
                } while (true);
            }
        }
        System.out.println("Error. No pueden ingresar más meseros.");
        Aux.wait(2000);
    }
    public static void main(String[] args) {
        Mesero[] meseros = new Mesero[20];
         Alimento[] menu = new Alimento[90];
        Object[] Datos = Aux.cargarDatos();
        if(Datos!=null){
            meseros = (Mesero[]) Datos[0];
            menu = (Alimento[]) Datos[1];
        }      
        Mesa[] mesas = new Mesa[70];     
        String menus = """
            Ingrese la acción que desea realizar a continuación:
            1.- Iniciar sesión como mesero.
            2.- Registrar un mesero nuevo.
            3.- Ingresar nuevos alimentos.
            4.- Dar de baja un producto.
            5.- Dar de alta un producto.
            6.- Salir del sistema.""";
        // menu en bucle
        while (true /*case 6 hace return*/){
            int opcion = Aux.InputIntRange(menus,1,6);
            switch (opcion) {
                case 1:
                    // Iniciar sesion como mesero, si no, crear un mesero
                    MenuMesero(null, meseros, mesas, menu);
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
                    Aux.guardarDatos(meseros, menu);
                    return;
            }
        }
    }

    public static void SetExistencia(String nombre,Alimento[] menu,boolean existencia) {
        try {
            Alimento alimento = buscarAlimento(nombre, menu);
            alimento.setExistencia(existencia);
            System.out.print("Alimento dado de ");
            System.out.print(existencia ? "alta" : "baja");
            System.out.println(" exitosamente");
        } catch (NullPointerException e) {
            return;
        }

    }

    public static void MenuMesero(Mesero user, Mesero[] meseros, Mesa[] mesas, Alimento[] menu) {
        
        String menumesero = """
        1.- Abrir cuenta.
        2.- Visualizar mesas.
        3.- Agregar productos.
        4.- Cerrar una cuenta.
        5.- Eliminar una cuenta.
        6.- Juntar cuentas.
        7.- Salir.""";
        // inicio de sesion como mesero
        user = LogIn(meseros);
        if(user == null){
            return;
        }
        // menu en bucle
        while (true /*case 7 hace return*/) {
            System.out.println("Bienvenido " + user.getNombre() + ", elija la opción a continuación: ");
            int opc = Aux.InputIntRange(menumesero,1,7);            
            switch (opc) {
                case 1:
                    asignarMesa(user, mesas, menu);
                    break;
                case 2:
                    for(Mesa mesa : mesas){
                        if(mesa != null && mesa.getMesero() == user){
                            System.out.println(mesa);
                            mesa.printPedido();
                        }
                    }                
                    break;
                case 3:
                    try {
                        int i = Aux.InputInt("Ingresa el numero de la mesa:");
                        if (isMyMesa(user, mesas[i])) {
                        hacerPedido(mesas[i], user, menu);
                        System.out.println(user.getNombre());
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Error. La mesa no existe.");
                    }
                    break;
                case 4:
                    try{
                        int i = Aux.InputInt("Ingresa la cuenta que deseas cerrar:");
                        if (isMyMesa(user, mesas[i]) && mesas[i].isActivo()) {
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
                    return;
            }
        }
    }

    public static void juntarMesas(Mesero mesero, Mesa[]mesas){
        try{
            System.out.println("Ingresa los números de las mesas:");
            int a = Aux.InputIntRange("Mesa 1:",1,mesas.length);
            int b = Aux.InputIntRange("Mesa 2:",1,mesas.length);
            int newmesa = Aux.InputIntRange("Ingresa el numero de la nueva mesa:",1,mesas.length);
            Mesa mesa1 = mesas[a];
            Mesa mesa2 = mesas[b];
            if(mesa1.getNumero() == newmesa || mesa2.getNumero() == newmesa || mesas[newmesa]==null){
                if(isMyMesa(mesero, mesa1) && isMyMesa(mesero, mesa2) ){
                    Mesa newMesa = new Mesa(mesero, newmesa,(mesa1.getPersonas()+mesa2.getPersonas()),true);
                    Mesa[] temparray = {mesa1, mesa2};
                    for (Mesa mesa : temparray) {
                        for(int i=0;i<mesa.getPedidolength();i++){
                            newMesa.addPedido(mesa.getpedido(i), 1);
                        }
                    }
                    
                }else{
                    System.out.println("Error. La/s mesas no corresponden al mesero.");
                    Aux.wait(2000);
                }
                mesas[a] = null;
                mesas[b] = null;
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

        int opc = Aux.InputIntRange(menuCrear,1,2);
        for (int i = 0; i < menu.length; i++) {
            if (menu[i] == null) {
                switch (opc) {
                    case 1:
                        menu[i] = new Platillo(Aux.InputString("Ingresa el nombre del platillo:"),
                         Aux.InputDouble("Ingresa el costo del platillo:"),
                          "",
                           true);
                        return;
                    case 2:
                       menu[i] = new Cafe(Aux.InputString("Ingresa el nombre del cafe:"),
                         Aux.InputDouble("Ingresa el costo del cafe:"),
                          "",
                           true);
                        return;
                }
            }
        }     
        System.out.println("Error al crear alimento nuevo.");
        Aux.wait(2000);
    }

    public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[] menu) {
        if (!isMyMesa(mesero, mesa)) {
            return;
        }
        
        Alimento[] comanda = new Alimento[100];
        int contador = 0;
        while (contador < comanda.length) {

            Alimento pedido = buscarAlimento(Aux.InputString("Busqueda por nombre:"), menu);
    
            if (pedido != null) {

                pedido.setComentario(Aux.InputString("Realizar un comentario:"));//Agregar comentario
    
                if (pedido instanceof Platillo) {
                configurarPlatillo((Platillo) pedido);
                } else if (pedido instanceof Cafe) {
                configurarCafe((Cafe) pedido);
                }

                int cantidad = Aux.InputInt(
                (pedido instanceof Cafe) ? "Cantidad de cafés iguales:" : "Cantidad de platillos iguales:");
                agregarPedido(comanda, pedido, cantidad, mesa);
                contador += cantidad;
                if (!deseaOtroPedido()) {
                    mesa.setTotal();
                    mostrarResumen(comanda);
                    return;
                }
            }     
        }
        System.out.println("No se pueden ingresar más alimentos a la cuenta.");
        Aux.wait(2000);
    }
    
    private static void configurarPlatillo(Platillo platillo) {
        int opcion;
        String[] ordenyprioridad = {
            """
        Orden completa o media orden:
        1. Orden Completa
        2. Media Orden""",
        """
        1. Prioridad Alta
        2. Prioridad Baja"""
        };

            opcion = Aux.InputIntRange(ordenyprioridad[0],1,2);
            if(opcion == 1) platillo.setOrdenCompleta(true);
            else platillo.setOrdenCompleta(false);
            
            opcion = Aux.InputIntRange(ordenyprioridad[1],1,2);
            if(opcion == 1) platillo.setPrioridadAlta(true);
            else platillo.setPrioridadAlta(false);
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
            2. Caliente         """,};
      
        opcion = Aux.InputIntRange(menucafeteria[0],1,2);//Para agregar o no cafeina
        if(opcion == 1) cafe.setCafeina(true);
        else cafe.setCafeina(false);

        opcion = Aux.InputIntRange(menucafeteria[1],1,2);//Para elegir entre frio y caliente
        if(opcion == 1) cafe.setHielo(true);
        else cafe.setHielo(false);       

        opcion = Aux.InputIntRange("Tipo de leche:\n"+cafe.printMilklist(),0,(cafe.getMilklistSize()-1));//Para elegir una leche o no
        if (opcion >= 0 && opcion < cafe.getMilklistSize()) cafe.setMilk(opcion);
    }
    
    
    private static void agregarPedido(Alimento[] comanda, Alimento alimento, int cantidad, Mesa mesa) {
        mesa.addPedido(alimento, cantidad);
            for (int i = 0,pedidosAgregados = 0; i < comanda.length && pedidosAgregados < cantidad; i++) {
                if (comanda[i] == null) {
                    comanda[i] = alimento;
                    pedidosAgregados++;
                }
            }
    }
    
    private static boolean deseaOtroPedido() {
        String otropedido = 
        """
        ¿Desea ingresar otro pedido?
        1. Sí
        2. No""";

        int condicion = Aux.InputIntRange(otropedido,1,2);
        if(condicion==1){
            return true;
        }
        return false;
    }

    private static void mostrarResumen(Alimento[] comanda){
        Class<?>[] clases = new Class<?>[] { Platillo.class , Cafe.class};
        String[] etiquetas = { "Platillos:", "Cafés:" };
        for(int i = 0; i<clases.length;i++){
            System.out.println("=================================");
            System.out.println(etiquetas[i]);
            for(Alimento pedido : comanda){
                if(pedido!=null && clases[i].isInstance(pedido)){
                    System.out.println("-----------------");
                    System.out.println(pedido);
                    System.out.println("-----------------");
                }
            }
            System.out.println("=================================");
        }
    }
    
    // Metodo buscador de un alimento, si lo encuentra retorna el alimento, si no, retorna
    // null

    public static Alimento buscarAlimento(String nombre, Alimento[] menu) {
        for (Alimento alimento : menu) {
            if(alimento == null) continue;
            if (alimento.getNombre().equalsIgnoreCase(nombre)) {
                if (!alimento.isExistencia()) {
                    System.out.println("El platillo está dado de baja indefinidamente.");
                    break;
                } else {
                    return alimento;
                }
            }
        }
        Aux.wait(300);
        System.out.println("No se ha encontrado el alimento.");
        Aux.wait(1500);
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

                String ordenar = """
                Desea tomar la orden?
                1. Si       2. No""";

                int tomarorden = Aux.InputIntRange(ordenar,1,2);
                if (tomarorden == 1) hacerPedido(mesas[i], mesero, menu); 
                else return;
        }
    }

    public static void Ticket(Mesa mesa) {
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "=========TICKET=========");
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Mesa: " + mesa.getNumero());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt","Mesero: " + mesa.getMesero());
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
            if(isMyMesa(user, mesas[i])&&!mesas[i].isActivo()){
                mesas[i] = null;
            } else {
                System.out.println("Error. La mesa sigue activa o no es operada por el mesero.");   
            }
        }catch(NullPointerException ex){
            System.out.println("Error, la mesa no existe.");
            Aux.wait(2000);
        }
    }
}
