package eu.tastr.e2e.selenide.tasting;

import eu.tastr.e2e.E2ESelenideIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;

import static eu.tastr.e2e.application.testdata.TastingGenerator.tastingGenerator;

public class TastingEndToEndTest extends E2ESelenideIntegrationTest {

    @Test
    @Ignore
    public void tastingOverzicht() {
        tastingGenerator(applicationContext).generate(5);
        openTastr();
    }
}
