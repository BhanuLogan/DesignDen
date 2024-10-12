package jsonparser.impl.types;

import jsonparser.interfaces.JsonElement;
import util.CommonUtils;

import java.util.List;
import java.util.stream.Collectors;

public class JsonArray implements JsonElement {
    private final List<JsonElement> elements;
    private final int level;

    public JsonArray(List<JsonElement> elements, int level) {
        this.elements = elements;
        this.level = level;
    }

    @Override
    public Object getValue() {
        return elements.stream().map(JsonElement::getValue).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        String tabs = CommonUtils.tabs(level);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(JsonElement element : elements) {
            if(sb.length() != 1) sb.append(",");
            sb.append(String.format("\n%s%s", tabs, element.toString()));
        }
        sb.append("\n");
        sb.append(CommonUtils.tabs(level - 1));
        sb.append("]");
        return sb.toString();
    }
}