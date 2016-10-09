package eu.tastr.domain.tasting;

import eu.tastr.domain.DomainIntegrationTest;
import eu.tastr.domain.TastrAssertions;
import org.junit.Test;

import javax.inject.Inject;

import static eu.tastr.domain.TastrAssertions.assertThat;
import static eu.tastr.domain.tasting.TastingId.tastingId;

public class TastingRepositoryIntegrationTest extends DomainIntegrationTest {

    @Inject
    private TastingRepository repository;

    @Test
    public void save() {
        Tasting tasting = TastingTestBuilder.eenTasting()
            .withId(tastingId())
            .withNaam("A")
            .build();

        Tasting savedTasting = repository.saveAndFlush(tasting);

        TastrAssertions.assertThat(savedTasting)
            .heeftId(tasting.getId())
            .heeftNaam(tasting.getNaam());
    }

    @Test
    public void findOne() {
        Tasting tasting = TastingTestBuilder.eenTasting()
            .withId(tastingId())
            .withNaam("A")
            .persist(applicationContext);

        Tasting savedTasting = repository.findOne(tasting.getId());

        TastrAssertions.assertThat(savedTasting)
            .heeftId(tasting.getId())
            .heeftNaam(tasting.getNaam());
    }
}
