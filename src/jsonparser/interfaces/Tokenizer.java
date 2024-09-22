package jsonparser.interfaces;


import jsonparser.exception.JsonParseException;
import jsonparser.model.Token;

import java.util.List;

public interface Tokenizer {
    List<Token> tokenize(String json) throws JsonParseException;
}
