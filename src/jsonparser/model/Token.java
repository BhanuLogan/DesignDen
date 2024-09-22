package jsonparser.model;


import util.Constants;

public class Token {
    private Constants.TokenType type;
    private final String value;

    public Token(Constants.TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Constants.TokenType getType() {
        return type;
    }

    public String toString() {
        return "Type: " + type + ", value: " + value;
    }
}
