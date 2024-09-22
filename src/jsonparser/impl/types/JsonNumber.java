package jsonparser.impl.types;


import jsonparser.interfaces.JsonElement;

public class JsonNumber implements JsonElement {
    private final Number value;

    public JsonNumber(Number value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        if(value instanceof Integer) {
            return value.intValue();
        } else if(value instanceof Float) {
            return value.floatValue();
        } else if(value instanceof Double) {
            return value.doubleValue();
        } else if(value instanceof Long) {
            return value.longValue();
        } else if(value instanceof Short) {
            return value.shortValue();
        } else if(value instanceof Byte) {
            return value.byteValue();
        }
        return value;
    }

    @Override
    public String toString() {
        return value == null ? null : value.toString();
    }
}