package util;

public class Constants {
    public enum TokenType {
        LEFT_BRACE,  // {
        RIGHT_BRACE,  // }
        LEFT_BRACKET, // [
        RIGHT_BRACKET, // ]
        COMMA,   // ,
        COLON,   // :
        STRING,  // "..."
        NUMBER,  // 123, -456, 7.89
        TRUE,    // true
        FALSE,   // false
        NULL,    // null
        EOF      // End of file/input
    }

    public enum Design {
        JSON_PARSER, RATE_LIMITER
    }
}