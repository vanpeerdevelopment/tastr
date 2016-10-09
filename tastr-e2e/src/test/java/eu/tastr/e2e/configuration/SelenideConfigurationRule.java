package eu.tastr.e2e.configuration;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SelenideConfigurationRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        SelenideConfiguration.configure();
        return base;
    }

}
