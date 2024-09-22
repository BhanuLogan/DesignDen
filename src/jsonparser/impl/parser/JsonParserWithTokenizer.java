package jsonparser.impl.parser;


import jsonparser.exception.JsonParseException;
import jsonparser.impl.tokenizer.JsonTokenizer;
import jsonparser.impl.types.*;
import jsonparser.interfaces.JsonElement;
import jsonparser.interfaces.Parser;
import jsonparser.model.Token;
import util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParserWithTokenizer implements Parser {

    private List<Token> tokens;
    private int index = 0;

    public JsonObject parse(String json) throws JsonParseException {
        JsonTokenizer tokenizer = new JsonTokenizer(json);
        this.tokens = tokenizer.tokenize(json);
        this.index = 0;
        return parseObject(1);
    }

    private Token currentToken() {
        return tokens.get(index);
    }

    private void nextToken() {
        index++;
    }

    private JsonElement parseValue(int level) throws JsonParseException {
        Token token = currentToken();
        switch (token.getType()) {
            case LEFT_BRACE:
                return parseObject(level);
            case LEFT_BRACKET:
                return parseArray(level);
            case STRING:
                nextToken();
                return new JsonString(token.getValue());
            case NUMBER:
                nextToken();
                return new JsonNumber(Double.parseDouble(token.getValue()));
            case TRUE:
                nextToken();
                return new JsonBoolean(true);
            case FALSE:
                nextToken();
                return new JsonBoolean(false);
            case NULL:
                nextToken();
                return null;
            default:
                throw new JsonParseException("Unexpected token: " + token.getType());
        }
    }

    private JsonObject parseObject(int level) throws JsonParseException {
        expect(Constants.TokenType.LEFT_BRACE);

        Map<String, JsonElement> properties = new HashMap<>();

        while (currentToken().getType() != Constants.TokenType.RIGHT_BRACE) {
            Token keyToken = currentToken();
            if (keyToken.getType() != Constants.TokenType.STRING) {
                throw new JsonParseException("Expected STRING key but got: " + keyToken.getType());
            }

            String key = keyToken.getValue();
            nextToken();
            expect(Constants.TokenType.COLON);
            JsonElement value = parseValue(level + 1);

            properties.put(key, value);
            if (currentToken().getType() == Constants.TokenType.COMMA) {
                nextToken();
            }
        }
        expect(Constants.TokenType.RIGHT_BRACE);
        return new JsonObject(properties, level);
    }

    private JsonArray parseArray(int level) throws JsonParseException {
        expect(Constants.TokenType.LEFT_BRACKET);
        List<JsonElement> elements = new ArrayList<>();
        while (currentToken().getType() != Constants.TokenType.RIGHT_BRACKET) {
            JsonElement value = parseValue(level + 1);
            elements.add(value);

            if (currentToken().getType() == Constants.TokenType.COMMA) {
                nextToken();
            }
        }
        expect(Constants.TokenType.RIGHT_BRACKET);
        return new JsonArray(elements, level);
    }

    private void expect(Constants.TokenType expected) throws JsonParseException {
        if (currentToken().getType() != expected) {
            throw new JsonParseException("Expected " + expected + " but got " + currentToken().getType());
        }
        nextToken();
    }
}