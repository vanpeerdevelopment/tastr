package eu.tastr.service.tasting;

import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.TastingId;
import eu.tastr.domain.tasting.command.VoegTastingToeCommand;
import eu.tastr.service.ServiceIntegrationTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static eu.tastr.domain.TastrAssertions.assertThat;
import static eu.tastr.domain.tasting.TastingId.tastingId;
import static eu.tastr.domain.tasting.TastingTestBuilder.eenTasting;
import static eu.tastr.domain.tasting.command.VoegTastingToeCommandTestBuilder.eenVoegTastingToeCommand;

public class TastingServiceIntegrationTest extends ServiceIntegrationTest {

    @Inject
    private TastingService service;

    @Test
    public void findAll_ThenReturnsAlleTastings() {
        Tasting tastingA = eenTasting()
            .withId(tastingId())
            .withNaam("Tasting A")
            .persist(applicationContext);
        Tasting tastingB = eenTasting()
            .withId(tastingId())
            .withNaam("Tasting B")
            .persist(applicationContext);

        List<Tasting> result = service.findAll();

        assertThat(result).containsOnly(tastingA, tastingB);
    }

    @Test
    public void findById_GivenBestaandId_ThenReturnsTasting() {
        eenTasting().withId(tastingId()).persist(applicationContext);
        Tasting tasting = eenTasting().withId(tastingId()).persist(applicationContext);

        Tasting tastingUitDb = service.findById(tasting.getId());

        assertThat(tastingUitDb).isEqualTo(tasting);
    }

    @Test
    public void findById_GivenOnbestaandId_ThenThrowsException() {
        eenTasting().withId(tastingId()).persist(applicationContext);
        TastingId randomId = tastingId();

        expectTastrEntityBestaatNietExceptionWithMessage(randomId);
        service.findById(randomId);
    }

    @Test
    public void create_GivenTasting_ThenTastingCreated() {
        VoegTastingToeCommand command = eenVoegTastingToeCommand().withNaam("Tasting naam").build();

        Tasting tastingUitDb = service.create(command);

        assertThat(tastingUitDb).isNotNull();
        assertThat(service.findById(command.getAggregateId()))
            .isNotNull()
            .heeftId(command.getAggregateId())
            .heeftNaam(command.getNaam());
    }
}
