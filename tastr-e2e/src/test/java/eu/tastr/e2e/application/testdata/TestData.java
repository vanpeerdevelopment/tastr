package eu.tastr.e2e.application.testdata;

import eu.tastr.domain.spring.DomainConfig;
import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import eu.tastr.service.spring.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static eu.tastr.e2e.application.testdata.TastingGenerator.tastingGenerator;
import static java.lang.Integer.parseInt;

public class TestData {

    public static final int DEFAULT_NUMBER_OF_TASTINGS = 10;

    private ApplicationContext context;

    private TestData() {
        initApplicationContext();
    }

    private void initApplicationContext() {
        this.context = new AnnotationConfigApplicationContext(
            ServiceConfig.class,
            DomainConfig.class,
            InfrastructureTestPropertiesConfig.class,
            InfrastructureConfig.class);
    }

    public void generate(int numberOfTastings) {
        tastingGenerator(context).generate(numberOfTastings);
    }

    public static void main(String[] args) {
        testData().generate(numberOfTastings(args));
    }

    public static TestData testData() {
        return new TestData();
    }

    private static int numberOfTastings(String[] args) {
        return args.length == 0 ? DEFAULT_NUMBER_OF_TASTINGS : parseInt(args[0]);
    }
}
