package eu.tastr.e2e;

import eu.tastr.api.spring.ApiTestConfig;
import eu.tastr.domain.spring.DomainConfig;
import eu.tastr.e2e.application.testapplication.TestApplication;
import eu.tastr.e2e.db.DbCleaner;
import eu.tastr.e2e.spring.E2EConfig;
import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import eu.tastr.service.spring.ServiceConfig;
import org.junit.After;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.inject.Inject;

import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

@ContextConfiguration(classes = {
    E2EConfig.class,
    ApiTestConfig.class,
    ServiceConfig.class,
    DomainConfig.class,
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public abstract class E2EIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Inject
    private DbCleaner dbCleaner;

    protected TestApplication testApplication;

    @Before
    public void startTastr(){
        testApplication = TestApplication.getInstance().start();
    }

    @Before
    public void cleanDbBefore(){
        dbCleaner.clean();
    }

    @Before
    public void clearCookies() {
        clearBrowserCache();
    }

    @After
    public void cleanDbAfter(){
        dbCleaner.clean();
    }
}
