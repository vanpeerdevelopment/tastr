package be.vdab.vrijstellingenbeleid.service.tasting;

import be.vdab.vrijstellingenbeleid.domain.TastrAssertions;
import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingId;
import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand;
import be.vdab.vrijstellingenbeleid.service.ServiceIntegrationTest;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingId.tastingId;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;
import static be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommandTestBuilder.eenVoegTastingToeCommand;

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