public enum MilkType implements Modifier{
    NO("No"),
    ENTERA("Entera"),
    DESLACTOSADA("Deslactosada"),
    LIGHT("Light"),
    ALMENDRAS("Almendras");

    private final String description;

    @Override
    public String toString(){
        return description;
    }

    MilkType(String description){
        this.description = description;
    }
}