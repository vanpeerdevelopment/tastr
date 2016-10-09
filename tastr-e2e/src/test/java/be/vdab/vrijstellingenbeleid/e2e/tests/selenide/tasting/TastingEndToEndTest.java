package be.vdab.vrijstellingenbeleid.e2e.tests.selenide.tasting;

import be.vdab.vrijstellingenbeleid.e2e.tests.E2ESelenideIntegrationTest;
import org.junit.Test;

import static be.vdab.vrijstellingenbeleid.e2e.tests.application.testdata.TastingGenerator.tastingGenerator;

public class TastingEndToEndTest extends E2ESelenideIntegrationTest {

    @Test
    public void tastingOverzicht() {
        tastingGenerator(applicationContext).generate(5);
        openTastr()
                .assertAantalTastingsIs(5);
    }
}
