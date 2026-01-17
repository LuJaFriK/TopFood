package src.backend;

public interface Modifier {

    static <T extends Enum<T> & Modifier> T fromString(Class<T> enumClass, String text, T defaultValue) {
        for (T constant : enumClass.getEnumConstants()) {
            if (constant.toString().equalsIgnoreCase(text)) {
                return constant;
            }
        }
        return defaultValue;
    }

}
