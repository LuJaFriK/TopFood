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

        for (int fallas = 0; fallas<4;fallas++) {//Registra cuantas veces ha fallado
            int password = Aux.InputInt("Ingresa tu contraseña para iniciar sesión:");
            if(password==-1) break;
            for(int i = 0;i<meseros.length;i++){ //Compara con las contraseñas de cada mesero
                if (meseros[i] == null) {
                    System.out.println("Error. Contraseña incorrecta.");
                    Aux.wait(1500);
                    break;//En caso de que la contraseña no pertenezca a ningun mesero, regresa al ciclo externo
                }else if ( meseros[i].getPassword()==password){
                    return meseros[i];
                }
            }
        if(fallas == 3){
            int crear = Aux.InputIntRange("Parece que haz ingresado una clave incorrecta muchas veces, deseas crear un mesero nuevo? \n 1. Si      2. No",1,2);
            if (crear == 1) crearMesero(meseros);
            else if (crear == -1) continue; 
        }
        }
        //En caso que no se haya creado un mesero nuevo ni la contraseña sea correcta, devuelve null
        return null;            
    }

    public static void crearMesero(Mesero[] meseros) {
        for (int i = 0; i < meseros.length; i++) {
            if (meseros[i] == null) {
                String nombre = Aux.InputString("Ingresa el nombre del mesero:");
                if(nombre == null) return;
                // Generar código único entre 1 y 100
                int codigodemesero;
                do {
                    codigodemesero = (int) (Math.random() * 100) + 1;
                } while (codigoExistente(meseros, codigodemesero));
                
                // Pedir y confirmar contraseña
                while (true) {
                    int password1 = Aux.InputInt("Ingresa la contraseña: ");
                    if(password1 == -1) return;
                    int password2 = Aux.InputInt("Confirma tu contraseña: ");
                    if(password2 == -1) return;
                    if (password1 == password2 && !passwordExistente(meseros, password2)) {
                        meseros[i] = new Mesero(nombre, codigodemesero, password1);
                        return;
                    } else {
                        System.out.println("Error. Las contraseñas no coinciden.");
                        Aux.wait(1500);
                    }
                }
            }
        }
        System.out.println("Error. No pueden ingresar más meseros.");
        Aux.wait(2000);
    }

    public static void eliminarMesero(Mesero[]meseros){
        for(Mesero mesero : meseros){
            if(mesero !=null){
                System.out.println("---------------");
                System.out.println(mesero);
                System.out.println("---------------");
            }
        }
        int codigo = Aux.InputInt("Ingresa el código del mesero");
        if(codigo == -1) return;
        for (int i = 0; i < meseros.length; i++) {
        if (meseros[i] != null && meseros[i].getCodigo() == codigo) {
            meseros[i] = null;
            System.out.println("Mesero con código " + codigo + " eliminado exitosamente.");
            return;
        }
        }
    System.out.println("Error: No se encontró ningún mesero con el código " + codigo + ".");
    }

    // Verificar si el código ya existe
    private static boolean codigoExistente(Mesero[] meseros, int codigo) {
        for (Mesero m : meseros) {
            if (m != null && m.getCodigo() == codigo) {
                return true;
            }
        }
        return false;
    }
    // Verifica si la contraseña no existe
    private static boolean passwordExistente(Mesero[] meseros, int password) {
        for (Mesero m : meseros) {
            if (m != null && m.getPassword() == password) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Mesero[] meseros = new Mesero[20]; 
        Alimento[] menu = new Alimento[90];
        int mesasLength = 0;

        /*Carga los datos de los archivos*/{
                Object[] Datos = Aux.cargarDatos();
                if (Datos != null) {
                    if (Datos[0] != null) meseros = (Mesero[]) Datos[0];
                    if (Datos[1] != null) menu = (Alimento[]) Datos[1];
                    if (Datos[2] != null) mesasLength = (int) Datos[2];
                }
                if(mesasLength == 0||mesasLength == -1) mesasLength = Aux.InputInt("Ingrese la cantidad de mesas disponibles:")+1;
                if(mesasLength == -1) return;
                
        }

        Mesa[] mesas = new Mesa[mesasLength];     
        String menus = """
            Ingrese la acción que desea realizar a continuación:
            1.- Iniciar sesión como mesero.
            2.- Registrar un mesero nuevo.
            3.- Eliminar un mesero.
            4.- Ingresar nuevos alimentos.
            5.- Dar de baja un producto.
            6.- Dar de alta un producto.
            7.- Modificar la cantidad de mesas.
            8.- Salir del sistema.""";
        // menu en bucle
        while (true /*case 7 hace return*/){
            int opcion = Aux.InputIntRange(menus,1,8);
            switch (opcion) {
                case 1:
                // Iniciar sesion como mesero, si no, crear un mesero
                    MenuMesero(null, meseros, mesas, menu);
                    break;
                case 2:
                //Registrar un mesero nuevo
                    crearMesero(meseros);
                    break;
                case 3:
                //Eliminar un mesero.
                    eliminarMesero(meseros);
                    break;
                case 4:
                //Ingresar nuevos alimentos
                    crearAlimento(menu);
                    break;
                case 5:
                //Dar de baja un producto
                    SetExistencia(menu, false);
                    break;
                case 6:
                //Dar de alta un producto
                    SetExistencia(menu, true);
                    break;
                case 7:
                    int index = Aux.InputInt("Ingrese la cantidad de mesas disponibles:")+1;
                    if(index == -1) break; 
                    mesasLength = index;
                    break;
                case 8:
                //Salir del sistema
                    System.out.println("Saliendo...");
                    Aux.guardarDatos(meseros, menu, mesasLength);
                    return;
            }
        }
    }

    public static void SetExistencia(Alimento[] menu,boolean existencia) {
        try {
            mostrarMenu(menu);
            Alimento alimento = buscarAlimento(Aux.InputString("Ingresa el nombre de el producto: "), menu);//Busca el alimento
            alimento.setExistencia(existencia); //Da de alta o da de baja
            System.out.println("Alimento dado de "+(existencia ? "alta" : "baja")+" exitosamente");
        } catch (NullPointerException e) {//Si a caso el snack fuera nulo
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
                //Abrir una cuenta
                    asignarMesa(user, mesas, menu);
                    break;
                case 2:
                //Visualizar las mesas propias
                    for(Mesa mesa : mesas){
                        if(mesa != null && mesa.getMesero() == user){
                            System.out.println(mesa);
                            mesa.printPedido();
                        }
                    }                
                    break;
                case 3:
                //Agregar productos a una mesa propia ya existente
                    try {
                        int i = Aux.InputInt("Ingresa el numero de la mesa:");
                        if(i==-1) return;
                        if (mesas[i].getMesero()==user) {
                        hacerPedido(mesas[i], user, menu);
                        System.out.println(user.getNombre());
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Error. La mesa no existe.");
                    }
                    break;
                case 4:
                //Cerrar una cuenta abierta
                    try{
                        int i = Aux.InputInt("Ingresa la cuenta que deseas cerrar:");
                        if(i==-1) return;
                        if (mesas[i].getMesero()==user && mesas[i].isActivo()) {
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
                //Eliminar una cuenta cerrada
                    DeleteMesa(user, mesas);
                    break;
                case 6:
                //Juntar dos mesas propias en una sola cuenta
                    juntarMesas(user, mesas);
                    break;
                case 7:
                //Cerrar cesión como mesero (Las mesas no se manipulan)
                    System.out.println("Saliendo...");
                    return;
            }
        }
    }

    public static void juntarMesas(Mesero mesero, Mesa[]mesas){
        try{
            System.out.println("Ingresa los números de las mesas:"); 
            int a = Aux.InputIntRange("Mesa 1:",1,mesas.length);
            if(a==-1) return;
            int b = Aux.InputIntRange("Mesa 2:",1,mesas.length);
            if(b==-1) return;
            int newmesa = Aux.InputIntRange("Ingresa el numero de la nueva mesa:",0,mesas.length);
            Mesa mesa1 = mesas[a];
            Mesa mesa2 = mesas[b];
            if(mesa1.getNumero() == newmesa || mesa2.getNumero() == newmesa || mesas[newmesa]==null){ //Algoritmo para verificar si las mesas existen
                if(mesa1.getMesero()==mesero && mesa2.getMesero()==mesero ){                          
                    Mesa newMesa = new Mesa(mesero, newmesa,true);                            //Construye la mesa nueva
                    Mesa[] temparray = {mesa1, mesa2};
                    for (Mesa mesa : temparray) {             //Este ciclo integra los alimentos de ambas mesas a la nueva mesa
                        for(int i=0;i<100;i++){
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
    

    public static void crearAlimento(Alimento[] menu) { //Crear nuevo alimento
        
        mostrarMenu(menu);
        
        String menuCrear = """
        Crear:
        1. Snack
        2. Café
        3. Salir""";

        int opc = Aux.InputIntRange(menuCrear,1,3); //Decide el tipo de alimento
        for (int i = 0; i < menu.length; i++) {     //Busca un espacio vacio en el arreglo
            if (menu[i] == null) {  
                switch (opc) {
                    case 1: // construye un Snack
                        menu[i] = new Snack(Aux.InputString("Ingresa el nombre del snack:"),
                         Aux.InputDouble("Ingresa el costo del snack:"),
                          "",
                           true,
                           Aux.InputInt("Ingresa el tamaño del Paquete / Porcion Grande:"));
                        return;
                    case 2: // construye un Cafe
                       menu[i] = new Cafe(Aux.InputString("Ingresa el nombre del cafe:"),
                         Aux.InputDouble("Ingresa el costo del cafe:"),
                          "",
                           true);
                        return;
                    case 3:
                        return;
                }
            }
        }     
        System.out.println("Error al crear alimento nuevo.");
        Aux.wait(2000);
    }

    public static void hacerPedido(Mesa mesa, Mesero mesero, Alimento[] menu) {
        if (mesa.getMesero()!=mesero) return;
        
        Alimento[] comanda = new Alimento[100];
        int contador = 0;
        int intento = 0;
        while (contador < comanda.length) {
            mostrarMenu(menu);
            String nombre = Aux.InputString("Busqueda por nombre:"); //Busca un alimento para agregarlo y configurarlo
            Alimento pedido = buscarAlimento(nombre, menu);
            if(pedido == null){  
                intento++;              //Si fall
                if(intento==3) break;
            }else{
                intento = 0;
                if (pedido instanceof Snack) {
                configurarSnack((Snack) pedido);
                } else if (pedido instanceof Cafe) {
                configurarCafe((Cafe) pedido);
                }
                String comentario = Aux.InputString("Realizar un comentario:");
                if(comentario==null) return;
                pedido.setComentario(comentario); //Agregar comentario

                int cantidad = Aux.InputInt(
                (pedido instanceof Cafe) ? "Cantidad de cafés iguales:" : "Cantidad de snacks iguales:");
                if(cantidad == -1) return;
                agregarPedido(comanda, pedido, cantidad, mesa); 
                contador += cantidad;
                String otropedido = 
                """
                ¿Desea ingresar otro pedido?
                1. Sí
                2. No""";

                if (Aux.InputIntRange(otropedido, 1, 2) != 1) {
                    mesa.addTotal(pedido.getCosto()*cantidad);
                    mostrarResumen(comanda);
                    return;
                }
            }     
        }
        System.out.println("No se ingresarán más alimentos a la cuenta.");
        Aux.wait(2000);
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
    
    private static void configurarSnack(Snack snack) {
        String indOpaquete = """
        1. Porcion individual
        2. Porcion Completa""";
        //Capturar si se trata de una porcion o de un paquete
        int tipoporcion = Aux.InputIntRange(indOpaquete, 1, 2);
        if(tipoporcion==-1)return;
        snack.setPorcionIndividual(tipoporcion == 1);  
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

    private static void mostrarResumen(Alimento[] comanda){  //Imprime una comanda
        Class<?>[] clases = new Class<?>[] { Snack.class , Cafe.class};
        String[] etiquetas = { "Snacks:", "Cafés:" };
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

    private static void mostrarMenu(Alimento[] menu){  //Imprime solo el nombre y el precio de los alimentos de un menu
        Class<?>[] clases = new Class<?>[] { Snack.class , Cafe.class};
        String[] etiquetas = { "Snacks:", "Cafés:" };
        for(int i = 0; i<clases.length;i++){
            System.out.println("=================================");
            System.out.println(etiquetas[i]);
            for(Alimento alimento : menu){
                if(alimento!=null && clases[i].isInstance(alimento)){
                    System.out.println("-----------------");
                    alimento.detalles();
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
                    System.out.println("El snack está dado de baja indefinidamente.");
                    break;
                } else {
                    return alimento;
                }
            }
        }
        System.out.println("No se ha encontrado el alimento.");
        Aux.wait(1000);
        return null;
    }

    // Metodo para abrir una mesa

    public static void asignarMesa(Mesero mesero, Mesa[] mesas, Alimento[] menu) {//Metodo que asigna una mesa no usada 
        int i = Aux.InputInt("Ingresa el numero de la mesa: ");           //Al mesero que esté ocupando el programa
        if(i==-1) return;
        try{
            if (mesas[i]!=null) {
            System.out.println("Error, la mesa está ocupada.");
        } else {
            mesas[i] = new Mesa(mesero, i, true);           //Construye la mesa nueva

                String ordenar = """
                Desea tomar la orden?
                1. Si       2. No""";

                if (Aux.InputIntRange(ordenar,1,2) == 1) hacerPedido(mesas[i], mesero, menu); //Toma la orden de la mesa nueva
                else return;
        }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error. No existen tantas mesas.");
        }
    }

    public static void Ticket(Mesa mesa) { //Exporta el ticker a un archivo.txt
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "=========TICKET=========");
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Mesa: " + mesa.getNumero());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt","Mesero: " + mesa.getMesero());
        Aux.OverrideFile(mesa.getNumero()+"_temp.txt", "Nombre  =========   Costo");
        for(int i=0;i<100;i++){
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


    public static void DeleteMesa(Mesero user, Mesa[] mesas){ //Vacia un espacio del arreglo de las mesas si esta ya ha sido 
        try{                                                  //cobrada
            int i = Aux.InputInt("Ingresa el numero de la cuenta a eliminar:");
            if(i==-1) return;
            if(mesas[i].getMesero()==user&&!mesas[i].isActivo()){
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
