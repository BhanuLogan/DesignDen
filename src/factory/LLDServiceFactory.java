package factory;

import interfaces.LLDService;
import jsonparser.JsonParserLLDService;
import util.Constants;

public class LLDServiceFactory {

    public static LLDService getService(Constants.Design design) {
        switch (design) {
            case JSON_PARSER -> {
                return new JsonParserLLDService();
            }
            default -> throw new UnsupportedOperationException("invalid design");
        }
    }
}
