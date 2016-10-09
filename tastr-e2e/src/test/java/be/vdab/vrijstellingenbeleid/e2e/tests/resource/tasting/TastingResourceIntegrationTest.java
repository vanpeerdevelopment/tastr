package be.vdab.vrijstellingenbeleid.e2e.tests.resource.tasting;

import be.vdab.vrijstellingenbeleid.api.tasting.TastingDto;
import be.vdab.vrijstellingenbeleid.api.tasting.TastingMapper;
import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingId;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingRepository;
import be.vdab.vrijstellingenbeleid.e2e.tests.E2EResourceIntegrationTest;
import org.junit.Test;

import javax.inject.Inject;

import static be.vdab.vrijstellingenbeleid.api.tasting.TastingResource.TASTING_URL;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class TastingResourceIntegrationTest extends E2EResourceIntegrationTest {

    @Inject
    private TastingRepository repository;
    @Inject
    private TastingMapper mapper;

    @Test
    public void findOne() {
        Tasting tasting = eenTasting().persist(applicationContext);
        Tasting expectedTasting = repository.findOne(tasting.getId());

        TastingDto actualTastingDto = givenRequest()
                .when()
                .get(urlFor(TASTING_URL + "/" + tasting.getId().getValue()))
                .then()
                .assertThat()
                .statusCode(OK.value())
                .contentType(JSON)
                .extract()
                .body()
                .as(TastingDto.class);

        assertThat(actualTastingDto).isEqualTo(mapper.naarTastingDto(expectedTasting));
    }

    @Test
    public void findAllFor() {
        Tasting tasting1 = eenTasting().persist(applicationContext);
        Tasting tasting2 = eenTasting().persist(applicationContext);
        Tasting expectedTasting1 = repository.findOne(tasting1.getId());
        Tasting expectedTasting2 = repository.findOne(tasting2.getId());

        TastingDto[] actualTastingDtos = givenRequest()
                .when()
                .get(urlFor(TASTING_URL))
                .then()
                .assertThat()
                .statusCode(OK.value())
                .contentType(JSON)
                .extract()
                .body()
                .as(TastingDto[].class);

        assertThat(actualTastingDtos).containsOnly(
                mapper.naarTastingDto(expectedTasting1),
                mapper.naarTastingDto(expectedTasting2)
        );
    }

    @Test
    public void create() {
        Tasting tasting = eenTasting().build();

        TastingDto actualTastingDto = givenRequest()
                .when()
                .body(mapper.naarTastingDto(tasting))
                .post(urlFor(TASTING_URL))
                .then()
                .assertThat()
                .statusCode(OK.value())
                .contentType(JSON)
                .extract()
                .body()
                .as(TastingDto.class);

        Tasting tastingUitDb = repository.findOne(TastingId.fromUUID(actualTastingDto.id));
        assertThat(actualTastingDto).isEqualTo(mapper.naarTastingDto(tastingUitDb));
    }
}
