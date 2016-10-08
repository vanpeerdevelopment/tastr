package be.vdab.vrijstellingenbeleid.domain.tasting;

import be.vdab.vrijstellingenbeleid.domain.DomainIntegrationTest;
import org.junit.Test;

import javax.inject.Inject;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingId.tastingId;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;

public class TastingRepositoryIntegrationTest extends DomainIntegrationTest {

    @Inject
    private TastingRepository repository;

    @Test
    public void save() {
        Tasting tasting = eenTasting()
            .withId(tastingId())
            .withNaam("A")
            .build();

        Tasting savedTasting = repository.saveAndFlush(tasting);

        assertThat(savedTasting)
            .heeftId(tasting.getId())
            .heeftNaam(tasting.getNaam());
    }

    @Test
    public void findOne() {
        Tasting tasting = eenTasting()
            .withId(tastingId())
            .withNaam("A")
            .persist(applicationContext);

        Tasting savedTasting = repository.findOne(tasting.getId());

        assertThat(savedTasting)
            .heeftId(tasting.getId())
            .heeftNaam(tasting.getNaam());
    }
}
