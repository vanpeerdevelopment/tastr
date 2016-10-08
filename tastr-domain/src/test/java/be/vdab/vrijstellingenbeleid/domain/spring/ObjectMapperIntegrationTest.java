package be.vdab.vrijstellingenbeleid.domain.spring;

import be.vdab.vrijstellingenbeleid.domain.DomainIntegrationTest;
import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingId;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import javax.inject.Inject;
import java.io.IOException;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;

public class ObjectMapperIntegrationTest extends DomainIntegrationTest {

    @Inject
    private ObjectMapper objectMapper;

    @Test
    public void serialization() throws IOException {
        Tasting tasting = eenTasting()
            .withId(TastingId.tastingId())
            .withNaam("naam")
            .build();

        String serializedVrijstelling = objectMapper.writeValueAsString(tasting);
        Tasting deserializedTasting = objectMapper.readValue(serializedVrijstelling, Tasting.class);

        assertThat(deserializedTasting).isEqualTo(tasting);
    }
}
