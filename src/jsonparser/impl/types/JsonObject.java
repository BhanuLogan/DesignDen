package jsonparser.impl.types;


import jsonparser.interfaces.JsonElement;
import util.CommonUtils;

import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonElement {
    private final Map<String, JsonElement> properties;
    private final int level;

    public JsonObject(Map<String, JsonElement> properties, int level) {
        this.properties = properties;
        this.level = level;
    }

    @Override
    public Object getValue() {
        Map<String, Object> obj = new HashMap<>();
        for(String key : properties.keySet()) {
            obj.put(key, properties.get(key).getValue());
        }
        return obj;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        String tabs =  CommonUtils.tabs(level);
        for(String key : properties.keySet()) {
            if(sb.length() != 1) sb.append(",");

            String value = properties.get(key) != null ? properties.get(key).toString() : null;
            sb.append(String.format("\n%s\"%s\" : %s", tabs, key, value));
        }
        sb.append("\n");
        sb.append(CommonUtils.tabs(level - 1));
        sb.append("}");
        return sb.toString();
    }
}
