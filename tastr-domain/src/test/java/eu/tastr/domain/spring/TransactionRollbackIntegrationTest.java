package eu.tastr.domain.spring;

import eu.tastr.domain.DomainIntegrationTest;
import eu.tastr.domain.tasting.TastingRepository;
import org.junit.Test;

import javax.inject.Inject;

import static eu.tastr.domain.TastrAssertions.assertThat;
import static eu.tastr.domain.tasting.TastingId.tastingId;
import static eu.tastr.domain.tasting.TastingTestBuilder.eenTasting;

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
