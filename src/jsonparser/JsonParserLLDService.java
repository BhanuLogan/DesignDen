package jsonparser;

import interfaces.LLDService;
import jsonparser.exception.JsonParseException;
import jsonparser.impl.parser.JsonParserWithTokenizer;
import jsonparser.impl.serializer.JsonSerializer;
import jsonparser.impl.types.JsonArray;
import jsonparser.impl.types.JsonObject;
import jsonparser.impl.types.JsonString;
import jsonparser.interfaces.JsonElement;
import jsonparser.interfaces.Parser;
import jsonparser.interfaces.Serializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParserLLDService implements LLDService {

    @Override
    public void runExamples() throws JsonParseException {
        runExample1();
        runExample2();
    }

    private void runExample1() throws JsonParseException {
        System.out.println("------------ JSON PARSER -------------");
        System.out.println("------------ EXAMPLE 1 ---------------");
        String json = """
                    {
                        "key1" : "value1",
                        "key2" : [
                            {
                                "key3" : "value3"
                            },
                            {
                                "key4" : "value4"
                            }
                        ],
                        "key5" : -120.50,
                        "key6" : 10,
                        "key7" : false,
                        "key8" : null,
                        "key9" : {
                            "key10" : {
                                "key11" : {
                                    "key12" : "value12",
                                    "key13" : "value13"
                                }
                            }
                        }
                    }
                """;

        System.out.println("ORIGINAL JSON: " + json);
        Parser parser = new JsonParserWithTokenizer();
        JsonObject obj = parser.parse(json);

        Serializer serializer = new JsonSerializer();

        System.out.println("PARSED AND SERIALIZED JSON: " + serializer.serialize(obj));
        System.out.println("--------------------------------------");
        System.out.println();
    }

    private void runExample2() {
        System.out.println("------------ EXAMPLE 2 -------------");
        JsonString val1 = new JsonString("val1");
        JsonString val2 = new JsonString("val2");
        JsonString val3 = new JsonString("val3");

        Map<String, JsonElement> nested = new HashMap<>();
        nested.put("key3", val3);
        JsonArray jsonArray = new JsonArray(List.of(new JsonObject(nested, 3)), 2);

        Map<String, JsonElement> properties = Map.ofEntries(
                Map.entry("key1", val1),
                Map.entry("key2", val2),
                Map.entry("key4", jsonArray)
        );

        JsonObject jsonObj = new JsonObject(properties, 1);

        Serializer serializer = new JsonSerializer();
        System.out.println("PARSED JSON: " + serializer.serialize(jsonObj));
        System.out.println("------------------------------------");
    }
}
