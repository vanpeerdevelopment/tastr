package eu.tastr.e2e.resource.status;

import eu.tastr.e2e.E2EResourceIntegrationTest;
import org.junit.Test;

import static eu.tastr.fatjar.status.StatusResource.STATUS_URL;
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
