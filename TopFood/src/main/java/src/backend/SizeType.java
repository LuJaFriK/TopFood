package src.backend;

public enum SizeType implements Modifier{
    CHICO("CH"),
    MEDIANO("M"),
    GRANDE("G");

    private final String description;

    @Override
    public String toString(){
        return description;
    }

    SizeType(String description){
        this.description = description;
    }


}