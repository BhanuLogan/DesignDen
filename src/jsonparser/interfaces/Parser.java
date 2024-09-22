package jsonparser.interfaces;

import jsonparser.exception.JsonParseException;
import jsonparser.impl.types.JsonObject;

public interface Parser {
    JsonObject parse(String json) throws JsonParseException;
}