package eu.tastr.e2e;

import eu.tastr.e2e.configuration.SelenideConfigurationRule;
import eu.tastr.e2e.pagina.tasting.TastingOverzichtPagina;
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
