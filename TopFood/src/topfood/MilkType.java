public enum MilkType {
    NO("No"),
    ENTERA("Entera"),
    DESLACTOSADA("Deslactosada"),
    LIGHT("Light"),
    ALMENDRAS("Almendras");

    private final String description;

    public static MilkType fromString(String text) {
        for (MilkType tipo : MilkType.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        return MilkType.NO;
    }

    MilkType(String description){
        this.description = description;
    }

    @Override
    public String ToString() {
        return description;
    }

    public static MilkType fromString(String text) {
        for (MilkType tipo : MilkType.values()) {
            if (tipo.descripcion.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        return MilkType.NO;
    }
}