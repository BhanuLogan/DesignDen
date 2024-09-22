package jsonparser.impl.tokenizer;


import jsonparser.exception.JsonParseException;
import jsonparser.interfaces.Tokenizer;
import jsonparser.model.Token;
import util.Constants;

import java.util.ArrayList;
import java.util.List;

public class JsonTokenizer implements Tokenizer {

    private int index;

    public JsonTokenizer(String json) {
        this.index = 0;
    }

    private String parseString(String json) {
        index++;  // Skip the opening quote
        StringBuilder result = new StringBuilder();

        while (json.charAt(index) != '"') {
            result.append(json.charAt(index));
            index++;
        }

        index++;  // Skip the closing quote
        return result.toString();
    }

    private String parseNumber(String json) {
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
    public List<Token> tokenize(String json) throws JsonParseException {
        List<Token> tokens = new ArrayList<>();

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
                tokens.add(new Token(Constants.TokenType.STRING, parseString(json)));
            } else if (Character.isDigit(current) || current == '-') {
                tokens.add(new Token(Constants.TokenType.NUMBER, parseNumber(json)));
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
