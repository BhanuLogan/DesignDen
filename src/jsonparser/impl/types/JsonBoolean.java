package jsonparser.impl.types;

import jsonparser.interfaces.JsonElement;

public class JsonBoolean implements JsonElement {
    private final Boolean value;

    public JsonBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value == null ? null : value.toString();
    }
}
