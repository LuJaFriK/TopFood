package topfood;

public class Cafe extends Alimento{
     private boolean caffeine;
     private boolean ice;
     private MilkType milk;
     private Size size;

     public Cafe(String nombre, double costo,boolean existencia){
          super(nombre,costo,existencia);
          this.cafeina=true;
          this.hielo=false;
          this.leche=MilkType.NO;
          this.size = Size.CHICO;
     }

     public Cafe(final Cafe original,String comentario){
          super(original,comentario);
     }
     
     public boolean hasCafeina() {
          return caffeine;
     }

     public void setCafeina(boolean caffeine) {
          this.caffeine = caffeine;
     }

     public boolean hasHielo() {
          return ice;
     }


     public void setHielo(boolean ice) {
          this.ice = ice;
     }

     public void setMilk(MilkType milk){
          this.milk = milk;
     }

     public MilkType getMilk(){
          return milk;
     }

     public void setSize(Size size){
          this.size = size;
     }

     public Size getSize(){
          return size;
     }

     @Override
     public double getCosto() {
          double newCosto = super.getCosto();

          if("Almendras".equals(leche)) newCosto += 10;
          if(size.equalsIgnoreCase("M")) newCosto *= 1.10;
          else if(size.equalsIgnoreCase("G")) newCosto *= 1.20;

          return newCosto;
     }
     
     @Override
     public String toString() {
          return super.toString()+
                 "\n"+"Cafeina: "+(cafeina ? "Si" : "No")+
                 "\n"+"Hielo: "+(hielo ? "Si.":"No.");
     }
}
