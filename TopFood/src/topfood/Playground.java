package topfood;

public class Playground {
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
}
