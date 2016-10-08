package be.vdab.vrijstellingenbeleid.api.tasting;

import be.vdab.vrijstellingenbeleid.domain.TastrAssertions;
import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand;
import be.vdab.vrijstellingenbeleid.infrastructure.test.UnitTest;
import org.junit.Before;
import org.junit.Test;

import static be.vdab.vrijstellingenbeleid.api.tasting.TastingDto.tastingDto;
import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;
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
