package be.vdab.vrijstellingenbeleid.domain.spring;

import be.vdab.vrijstellingenbeleid.domain.DomainIntegrationTest;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingRepository;
import org.junit.Test;

import javax.inject.Inject;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingId.tastingId;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;

public class TransactionRollbackIntegrationTest extends DomainIntegrationTest {

    @Inject
    private TastingRepository repository;

    @Test
    public void insertEerste_GivenEenAangemaakteTasting_ThenSlechtsEenTastingInDb() {
        eenTasting().withId(tastingId()).persist(applicationContext);

        assertThat(repository.count()).isEqualTo(1);
    }

    @Test
    public void insertTweede_GivenEenAangemaakteTasting_ThenSlechtsEenTastingInDb() {
        eenTasting().withId(tastingId()).persist(applicationContext);

        assertThat(repository.count()).isEqualTo(1);
    }
}
