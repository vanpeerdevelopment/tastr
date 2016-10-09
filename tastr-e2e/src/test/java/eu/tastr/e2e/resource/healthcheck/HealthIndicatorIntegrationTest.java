package eu.tastr.e2e.resource.healthcheck;

import eu.tastr.e2e.E2EResourceIntegrationTest;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.anything;

public class HealthIndicatorIntegrationTest extends E2EResourceIntegrationTest {

    @Test
    public void healthCheckIsNietSecured() {
        given()
            .baseUri(testApplication.baseUri())
            .port(testApplication.port())
            .contentType(JSON)
                .when()
                    .get(urlFor("/health"))
                .then()
                    .assertThat()
                    .contentType(JSON)
                    .body("db.status", anything())
        ;
    }
}
