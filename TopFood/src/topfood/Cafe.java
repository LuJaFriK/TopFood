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
     
     public void printMilklist(){
          for(int i = 0;i<milkList.length;i++){
               System.out.println(i+". "+milkList[i]);
          }
     }

     public int getMilklistSize(){
          return milkList.length;
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

     public void printSizeList(){
          for(int i = 0;i<sizeList.length;i++){
               System.out.println(i+". "+sizeList[i]);
          }
     }

     @Override
     public void setCosto(double costo) {
     double newCosto = costo;
     if("Almendras".equals(leche)) { 
          newCosto += 10;
     }
     
     if(size != null) {
          switch(size.toUpperCase()) { 
               case "M":
                    newCosto *= 1.10;
                    break;
               case "G":
                    newCosto *= 1.20;
                    break;
          }
     }
     super.setCosto(newCosto); 
     }

     @Override 
     public void detalles(){
          super.detalles();
          System.out.println("Cafeina: "+(cafeina ? "Si" : "No"));
          System.out.println("Hielo: "+(hielo ? "Si.":"No."));
     }

}
