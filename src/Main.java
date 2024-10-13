import factory.LLDServiceFactory;
import interfaces.LLDService;
import util.Constants;

public class Main {
    public static void main(String[] args) throws Exception {
        LLDService service = LLDServiceFactory.getService(Constants.Design.RATE_LIMITER);
        service.runExamples();
    }
}