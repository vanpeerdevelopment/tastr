package be.vdab.vrijstellingenbeleid.e2e.tests.resource.status;

import be.vdab.vrijstellingenbeleid.e2e.tests.E2EResourceIntegrationTest;
import org.junit.Test;

import static be.vdab.vrijstellingenbeleid.war.status.StatusResource.STATUS_URL;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;

public class StatusResourceIntegrationTest extends E2EResourceIntegrationTest {

    @Test
    public void status() {
        givenRequest()
                .when()
                .get(urlFor(STATUS_URL))
                .then()
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .body(equalTo("up"));
    }
}
