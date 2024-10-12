package jsonparser.interfaces;


import jsonparser.exception.JsonParseException;
import jsonparser.model.Token;

import java.util.Queue;

public interface Tokenizer {
    Queue<Token> tokenize(String json) throws JsonParseException;
}
