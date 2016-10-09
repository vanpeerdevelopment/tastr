package be.vdab.vrijstellingenbeleid.e2e.tests;

import be.vdab.vrijstellingenbeleid.api.spring.ApiTestConfig;
import be.vdab.vrijstellingenbeleid.domain.spring.DomainConfig;
import be.vdab.vrijstellingenbeleid.e2e.tests.application.testapplication.TestApplication;
import be.vdab.vrijstellingenbeleid.e2e.tests.db.DbCleaner;
import be.vdab.vrijstellingenbeleid.e2e.tests.spring.E2EConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureConfig;
import be.vdab.vrijstellingenbeleid.infrastructure.spring.InfrastructureTestPropertiesConfig;
import be.vdab.vrijstellingenbeleid.service.spring.ServiceConfig;
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
