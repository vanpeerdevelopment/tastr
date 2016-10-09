package be.vdab.vrijstellingenbeleid.e2e.tests.application.testdata;

import org.springframework.context.ApplicationContext;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public abstract class DataGenerator {

    protected ApplicationContext context;

    protected DataGenerator(ApplicationContext context) {
        this.context = context;
    }

    protected int randomNumberBetween(int minimum, int maximum) {
        return nextInt(minimum, maximum);
    }

    protected int randomNumberBetweenZeroAnd(int maximum) {
        return randomNumberBetween(0, maximum);
    }
}
