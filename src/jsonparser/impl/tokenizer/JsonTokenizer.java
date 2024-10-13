package jsonparser.impl.tokenizer;


import jsonparser.exception.JsonParseException;
import jsonparser.interfaces.Tokenizer;
import jsonparser.model.Token;
import util.Constants;

import java.util.LinkedList;
import java.util.Queue;

public class JsonTokenizer implements Tokenizer {

    private JsonTokenizer() {}

    private static final class JsonTokenizerHolder {
        private static final JsonTokenizer instance = new JsonTokenizer();
    }

    // singleton
    public static JsonTokenizer getInstance() {
        return JsonTokenizerHolder.instance;
    }

    private String parseString(String json, int index) {
        index++;  // Skip the opening quote
        StringBuilder result = new StringBuilder();

        while (json.charAt(index) != '"') {
            result.append(json.charAt(index));
            index++;
        }
        return result.toString();
    }

    private String parseNumber(String json, int index) {
        StringBuilder result = new StringBuilder();
        char current = json.charAt(index);

        if (current == '-') {
            result.append(current);
            index++;
        }

        while (index < json.length() && (Character.isDigit(json.charAt(index)) || json.charAt(index) == '.')) {
            result.append(json.charAt(index));
            index++;
        }

        return result.toString();
    }

    @Override
    public Queue<Token> tokenize(String json) throws JsonParseException {
        Queue<Token> tokens = new LinkedList<>();
        int index = 0;
        while (index < json.length()) {
            char current = json.charAt(index);

            if (Character.isWhitespace(current)) {
                index++;  // Skip whitespace
            } else if (current == '{') {
                tokens.add(new Token(Constants.TokenType.LEFT_BRACE, "{"));
                index++;
            } else if (current == '}') {
                tokens.add(new Token(Constants.TokenType.RIGHT_BRACE, "}"));
                index++;
            } else if (current == '[') {
                tokens.add(new Token(Constants.TokenType.LEFT_BRACKET, "["));
                index++;
            } else if (current == ']') {
                tokens.add(new Token(Constants.TokenType.RIGHT_BRACKET, "]"));
                index++;
            } else if (current == ',') {
                tokens.add(new Token(Constants.TokenType.COMMA, ","));
                index++;
            } else if (current == ':') {
                tokens.add(new Token(Constants.TokenType.COLON, ":"));
                index++;
            } else if (current == '"') {
                String token = parseString(json, index);
                tokens.add(new Token(Constants.TokenType.STRING, token));
                index += token.length() + 2; // + 2 is for skipping quotes
            } else if (Character.isDigit(current) || current == '-') {
                String token = parseNumber(json, index);
                tokens.add(new Token(Constants.TokenType.NUMBER, token));
                index += token.length();
            } else if (json.startsWith("true", index)) {
                tokens.add(new Token(Constants.TokenType.TRUE, "true"));
                index += 4;
            } else if (json.startsWith("false", index)) {
                tokens.add(new Token(Constants.TokenType.FALSE, "false"));
                index += 5;
            } else if (json.startsWith("null", index)) {
                tokens.add(new Token(Constants.TokenType.NULL, "null"));
                index += 4;
            } else {
                throw new JsonParseException("Unexpected character: " + current);
            }
        }

        tokens.add(new Token(Constants.TokenType.EOF, null));
        return tokens;
    }
}
