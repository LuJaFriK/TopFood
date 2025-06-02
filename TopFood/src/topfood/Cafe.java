package topfood;

public class Cafe extends Alimento{
     private boolean cafeina;
     private boolean hielo;
     private String leche; 
     private String[] milkList= {"No","Entera","Deslactosada","Light","Almendras"};
     private String size;
     private String[] sizeList={"CH","M","G"};

     public Cafe(String nombre, double costo,String comentario,boolean existencia){
          super(nombre,costo,comentario,existencia);
          this.cafeina=true;
          this.hielo=false;
          this.leche=milkList[0];
          this.size = sizeList[0];
     }
     
     public boolean hasCafeina() {
          return cafeina;
     }

     public void setCafeina(boolean cafeina) {
          this.cafeina = cafeina;
     }

     public boolean hasHielo() {
          return hielo;
     }


     public void setHielo(boolean hielo) {
          this.hielo = hielo;
     }
     
     public String printMilklist() {
          return "0. " + milkList[0] + "\n" +
                 "1. " + milkList[1] + "\n" +
                 "2. " + milkList[2] + "\n" +
                 "3. " + milkList[3] + "\n" +
                 "4. " + milkList[4];
}


     public int getMilklistSize(){
          return milkList.length;
     }

     public void setMilk(int index){
          this.leche=milkList[index];
     }

     public String getMilk(){
          return leche;
     }

     public void setSize(int index){
          size=sizeList[index];
     }

     public int getSizeListSize(){
          return sizeList.length;
     }

     public String printSizeList(){
          return "0. " + sizeList[0] + "\n" +
                 "1. " + sizeList[1] + "\n" +
                 "2. " + sizeList[2];
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
