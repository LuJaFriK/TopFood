import topfood.Alimento;

public class Cafe extends Alimento{
     private boolean cafeina;
     private boolean hielo;
     private String leche; 
     public final String[] milkList= {"No","Entera","Deslactosada","Light","Almendras"};
     private String size;
     public final String[] sizeList={"CH","M","G"};

     public Cafe(String nombre, double costo,String comentario,boolean existencia){
          super(nombre,costo,comentario,existencia);
          this.cafeina=false;
          this.hielo=false;
          this.leche=milkList[0];
          this.size = sizeList[0];
     }
     public boolean hasCafeina() {
          System.out.println((cafeina ? "Si.":"No."));
          return cafeina;
     }

     public void setCafeina(boolean cafeina) {
          this.cafeina = cafeina;
     }

     public boolean hasHielo() {
          System.out.println((hielo ? "Si.":"No."));
          return hielo;
     }


     public void setHielo(boolean hielo) {
          this.hielo = hielo;
     }

     public void setMilk(int index){
          this.leche=milkList[index];
     }

     public String getLeche(){
          return leche;
     }

     public void setSize(int index){
          size=sizeList[index];
     }

     @Override
     public void setCosto(double costo){
          double newcosto=costo;
          if(leche.equals("Almendras")){newcosto+=10;}
          if(size.equals("CH")){return;}
          else if(size.equals("M")){
               newcosto*=1.10;
          }else if(size.equals("G")){
               newcosto*=1.20;
          }
          super.setCosto(newcosto);
     }
     @Override 
     public void detalles(){
          super.detalles();
          System.out.println("Cafeina: "+(cafeina ? "Si" : "No"));
          System.out.println("Hielo: "+(hielo ? "Si.":"No."));
     }

}
