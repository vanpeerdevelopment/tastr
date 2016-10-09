package eu.tastr.api.tasting;

import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.command.VoegTastingToeCommand;
import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Before;
import org.junit.Test;

import static eu.tastr.api.tasting.TastingDto.tastingDto;
import static eu.tastr.domain.TastrAssertions.assertThat;
import static eu.tastr.domain.tasting.TastingTestBuilder.eenTasting;
import static org.assertj.core.api.Assertions.assertThat;

public class TastingMapperTest extends UnitTest {

    private TastingMapper mapper;

    @Before
    public void setup() {
        mapper = new TastingMapper();
    }

    @Test
    public void naarTastingDto() {
        Tasting tasting = eenTasting().build();

        TastingDto tastingDto = mapper.naarTastingDto(tasting);

        assertThat(tastingDto).isEqualTo(
            tastingDto()
                .withId(tasting.getId().getValue())
                .withVersie(tasting.getVersie())
                .withNaam(tasting.getNaam()));
    }

    @Test
    public void naarVoegTastingToeCommand() {
        TastingDto tastingDto = tastingDto().withNaam("Tasting naam");

        VoegTastingToeCommand command = mapper.naarVoegTastingToeCommand(tastingDto);

        assertThat(command)
            .heeftGegenereerdeAggregateId()
            .heeftVersie(0)
            .heeftNaam(tastingDto.naam);
    }
}
