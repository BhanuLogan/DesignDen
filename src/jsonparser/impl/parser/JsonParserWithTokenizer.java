package jsonparser.impl.parser;


import jsonparser.exception.JsonParseException;
import jsonparser.impl.tokenizer.JsonTokenizer;
import jsonparser.impl.types.*;
import jsonparser.interfaces.JsonElement;
import jsonparser.interfaces.Parser;
import jsonparser.model.Token;
import util.Constants;

import java.util.*;

public class JsonParserWithTokenizer implements Parser {

    private static JsonParserWithTokenizer instance;

    private JsonParserWithTokenizer() {}

    // singleton
    public static JsonParserWithTokenizer getInstance() {
        if(instance == null) {
            synchronized (JsonParserWithTokenizer.class) {
                if(instance == null) {
                    instance = new JsonParserWithTokenizer();
                }
            }
        }
        return instance;
    }

    public JsonObject parse(String json) throws JsonParseException {
        JsonTokenizer tokenizer = JsonTokenizer.getInstance();
        Queue<Token> tokens = tokenizer.tokenize(json);
        return parseObject(tokens,1);
    }

    private Token currentToken(Queue<Token> tokens) {
        return tokens.peek();
    }

    private void nextToken(Queue<Token> tokens) {
        tokens.poll();
    }

    private JsonElement parseValue(Queue<Token> tokens, int level) throws JsonParseException {
        Token token = currentToken(tokens);
        switch (token.getType()) {
            case LEFT_BRACE:
                return parseObject(tokens, level);
            case LEFT_BRACKET:
                return parseArray(tokens, level);
            case STRING:
                nextToken(tokens);
                return new JsonString(token.getValue());
            case NUMBER:
                nextToken(tokens);
                return new JsonNumber(Double.parseDouble(token.getValue()));
            case TRUE:
                nextToken(tokens);
                return new JsonBoolean(true);
            case FALSE:
                nextToken(tokens);
                return new JsonBoolean(false);
            case NULL:
                nextToken(tokens);
                return null;
            default:
                throw new JsonParseException("Unexpected token: " + token.getType());
        }
    }

    private JsonObject parseObject(Queue<Token> tokens, int level) throws JsonParseException {
        expect(tokens, Constants.TokenType.LEFT_BRACE);

        Map<String, JsonElement> properties = new HashMap<>();

        while (currentToken(tokens).getType() != Constants.TokenType.RIGHT_BRACE) {
            Token keyToken = currentToken(tokens);
            if (keyToken.getType() != Constants.TokenType.STRING) {
                throw new JsonParseException("Expected STRING key but got: " + keyToken.getType());
            }

            String key = keyToken.getValue();
            nextToken(tokens);
            expect(tokens, Constants.TokenType.COLON);
            JsonElement value = parseValue(tokens,level + 1);

            properties.put(key, value);
            if (currentToken(tokens).getType() == Constants.TokenType.COMMA) {
                nextToken(tokens);
            }
        }
        expect(tokens, Constants.TokenType.RIGHT_BRACE);
        return new JsonObject(properties, level);
    }

    private JsonArray parseArray(Queue<Token> tokens, int level) throws JsonParseException {
        expect(tokens, Constants.TokenType.LEFT_BRACKET);
        List<JsonElement> elements = new ArrayList<>();
        while (currentToken(tokens).getType() != Constants.TokenType.RIGHT_BRACKET) {
            JsonElement value = parseValue(tokens, level + 1);
            elements.add(value);

            if (currentToken(tokens).getType() == Constants.TokenType.COMMA) {
                nextToken(tokens);
            }
        }
        expect(tokens, Constants.TokenType.RIGHT_BRACKET);
        return new JsonArray(elements, level);
    }

    private void expect(Queue<Token> tokens, Constants.TokenType expected) throws JsonParseException {
        Token token = currentToken(tokens);
        if (token.getType() != expected) {
            throw new JsonParseException("Expected " + expected + " but got " + token.getType());
        }
        nextToken(tokens);
    }
}