package be.vdab.vrijstellingenbeleid.e2e.tests.application.testdata;

import be.vdab.vrijstellingenbeleid.domain.spring.DomainConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureTestPropertiesConfig;
import be.vdab.vrijstellingenbeleid.service.spring.ServiceConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static be.vdab.vrijstellingenbeleid.e2e.tests.application.testdata.TastingGenerator.tastingGenerator;
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
        testData().generate(numberOfProjects(args));
    }

    public static TestData testData() {
        return new TestData();
    }

    private static int numberOfProjects(String[] args) {
        return args.length == 0 ? DEFAULT_NUMBER_OF_TASTINGS : parseInt(args[0]);
    }
}
