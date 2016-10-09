package eu.tastr.domain.spring;

import eu.tastr.domain.DomainIntegrationTest;
import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.TastingId;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.tastr.domain.TastrAssertions;
import org.junit.Test;

import javax.inject.Inject;
import java.io.IOException;

import static eu.tastr.domain.TastrAssertions.assertThat;
import static eu.tastr.domain.tasting.TastingTestBuilder.eenTasting;

public class ObjectMapperIntegrationTest extends DomainIntegrationTest {

    @Inject
    private ObjectMapper objectMapper;

    @Test
    public void serialization() throws IOException {
        Tasting tasting = eenTasting()
            .withId(TastingId.tastingId())
            .withNaam("naam")
            .build();

        String serializedTasting = objectMapper.writeValueAsString(tasting);
        Tasting deserializedTasting = objectMapper.readValue(serializedTasting, Tasting.class);

        TastrAssertions.assertThat(deserializedTasting).isEqualTo(tasting);
    }
}
