package src.backend;

public enum MilkType implements Modifier{
    NO("No"),
    ENTERA("Entera"),
    DESLACTOSADA("Deslactosada"),
    LIGHT("Light"),
    ALMENDRAS("Almendras");

    private final String description;

    public static final int length = MilkType.values().length;

    @Override
    public String toString(){
        return description;
    }

    MilkType(String description){
        this.description = description;
    }

    public static String print(){
        StringBuilder sb = new StringBuilder();
        int i=0;
        for(var opt:MilkType.values()){
            sb.append(i);
            sb.append(" :");
            sb.append(opt.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static MilkType fromInt(int option){
        try{
            return MilkType.values()[option];
        }catch(IndexOutOfBoundsException ex){
            return NO;
        }
    }
}