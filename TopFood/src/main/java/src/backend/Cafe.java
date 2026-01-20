package src.backend;


public class Cafe extends Alimento{
     private boolean caffeine;
     private boolean ice;
     private MilkType milk;
     private SizeType size;

     public Cafe(String nombre, double costo,boolean existencia){
          super(nombre,costo,existencia);
          this.caffeine=true;
          this.ice=false;
          this.milk=MilkType.NO;
          this.size = SizeType.CHICO;
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

     public void setSize(SizeType size){
          this.size = size;
     }

     public SizeType getSize(){
          return size;
     }

     @Override
     protected double configCosto(double costoBase) {
          double costoNuevo = costoBase;
          switch (size){
               case SizeType.MEDIANO -> costoNuevo*=0.2;
               case SizeType.GRANDE -> costoNuevo*=0.3;
          }
          if(milk==MilkType.ALMENDRAS) costoNuevo+=10;
          return costoNuevo;
     }


     @Override
     public double getCosto() {
          double newCosto = super.getCosto();

          if("Almendras".equals(milk.toString())) newCosto += 10;
          if(size.toString().equalsIgnoreCase("M")) newCosto *= 1.10;
          else if(size.toString().equalsIgnoreCase("G")) newCosto *= 1.20;

          return newCosto;
     }
     
     @Override
     public String toString() {
          return super.toString()+
                 "\n"+"Cafeina: "+(caffeine ? "Si" : "No")+
                 "\n"+"Hielo: "+(ice ? "Si.":"No.");
     }
}
