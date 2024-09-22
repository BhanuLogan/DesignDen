package jsonparser.impl.serializer;


import jsonparser.interfaces.JsonElement;
import jsonparser.interfaces.Serializer;

public class JsonSerializer implements Serializer {
    @Override
    public String serialize(JsonElement json) {
        return json.toString();
    }
}
