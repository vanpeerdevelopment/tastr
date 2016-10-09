package eu.tastr.e2e.application.testapplication;

import eu.tastr.e2e.application.testapplication.proxy.TastrProxy;
import eu.tastr.e2e.application.testapplication.tomcat.TastrTomcat;
import eu.tastr.e2e.pagina.tasting.TastingOverzichtPagina;
import eu.tastr.infrastructure.test.poller.Assertion;
import com.codeborne.selenide.Selenide;
import com.jayway.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;

import static eu.tastr.infrastructure.test.poller.Poller.aPoller;
import static eu.tastr.fatjar.status.StatusResource.STATUS_URL;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

public class TestApplication {

    private static TestApplication instance = null;

    private final TastrTomcat tomcat;
    private final TastrProxy proxy;
    private final String baseUri = "http://localhost";
    private boolean started = false;

    private TestApplication() {
        tomcat = TastrTomcat.getInstance();
        proxy = TastrProxy.getInstance();
    }

    public String baseUri() {
        return baseUri;
    }

    public int port() {
        return proxy.port();
    }

    public String contextPath() {
        return tomcat.contextPath();
    }

    public String tastrUrl() {
        return baseUri() + ":" + port() + contextPath();
    }

    public TestApplication start() {
        if (!started) {
            startTomcat();
            startProxy();
            waitUntilStarted();
            verifyHealth();
            started = true;
        }
        return this;
    }

    private void startTomcat() {
        tomcat.start();
    }

    private void startProxy() {
        proxy.start();
    }

    private void waitUntilStarted() {
        aPoller()
            .doAssert((Assertion) () ->
                givenRequest()
                    .when()
                    .get(contextPath() + STATUS_URL)
                    .then()
                    .assertThat()
                    .statusCode(not(NOT_FOUND.value()))
            );
    }

    private void verifyHealth() {
        givenRequest()
            .when()
            .get(contextPath() + "/health")
            .then()
            .assertThat()
            .statusCode(OK.value())
            .contentType(JSON)
            .body(CoreMatchers.not(containsString("DOWN")));
        System.out.println("Health page ok!");
    }

    private RequestSpecification givenRequest() {
        return given()
            .baseUri(baseUri())
            .port(port());
    }

    public TastingOverzichtPagina open() {
        Selenide.open(tastrUrl());
        return new TastingOverzichtPagina();
    }

    public static TestApplication getInstance() {
        if (instance == null)
            instance = new TestApplication();
        return instance;
    }

    public static void main(String[] args) {
        TestApplication.getInstance().start();
    }
}
