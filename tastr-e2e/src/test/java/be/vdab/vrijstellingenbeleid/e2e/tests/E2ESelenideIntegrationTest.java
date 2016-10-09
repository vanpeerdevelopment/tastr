package be.vdab.vrijstellingenbeleid.e2e.tests;

import be.vdab.vrijstellingenbeleid.e2e.tests.configuration.SelenideConfigurationRule;
import be.vdab.vrijstellingenbeleid.e2e.tests.pagina.tasting.TastingOverzichtPagina;
import com.codeborne.selenide.junit.ScreenShooter;
import org.junit.Rule;
import org.junit.rules.RuleChain;

public abstract class E2ESelenideIntegrationTest extends E2EIntegrationTest {

    @Rule
    public RuleChain chain = RuleChain
        .outerRule(new SelenideConfigurationRule())
        .around(ScreenShooter.failedTests().to("target/screenshots/"));

    protected TastingOverzichtPagina openTastr() {
        return testApplication.open();
    }
}
