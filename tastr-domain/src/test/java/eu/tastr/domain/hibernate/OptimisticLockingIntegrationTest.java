package eu.tastr.domain.hibernate;

import eu.tastr.domain.spring.DomainConfig;
import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.TastingRepository;
import eu.tastr.domain.TastrAssertions;
import eu.tastr.domain.tasting.TastingTestBuilder;
import eu.tastr.infrastructure.spring.InfrastructureConfig;
import eu.tastr.infrastructure.spring.InfrastructureTestPropertiesConfig;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.sql.DataSource;

import static eu.tastr.domain.TastrAssertions.assertThat;

@ContextConfiguration(classes = {
    DomainConfig.class,
    InfrastructureTestPropertiesConfig.class,
    InfrastructureConfig.class
})
public class OptimisticLockingIntegrationTest extends AbstractJUnit4SpringContextTests {

    @Inject
    private Environment environment;
    @Inject
    private DataSource dataSource;
    @Inject
    private TastingRepository tastingRepository;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @After
    public void cleanupCommittedEntities() {
        String schema = environment.getProperty("db.schema");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM " + schema + "." + Tasting.TABLE_NAME);
    }

    @Test
    public void save_WhenEenAggregateRootWordtOpnieuwOpgeslagenZonderAanpassingen_ThenVersieWordtOpgehoogdWantLockModeTypeWrite() {
        Tasting tasting = TastingTestBuilder.eenTasting().build();
        tastingRepository.save(tasting);
        Tasting mergedTasting = tastingRepository.findOne(tasting.getId());
        int initialVersion = 0;
        TastrAssertions.assertThat(mergedTasting).heeftVersie(initialVersion);

        tastingRepository.save(mergedTasting);
        mergedTasting = tastingRepository.findOne(mergedTasting.getId());
        TastrAssertions.assertThat(mergedTasting).heeftVersie(initialVersion + 1);
    }

    @Test
    // TODO: implementeren eens we een wijzig-command hebben
    public void save_WhenEenVeldVanEenAggregateRootWordtAangepastEnOpnieuwOpgeslagen_ThenVersieWordtOpgehoogdMetTwee_Plus1VoorDeAanpassingZelf_Plus1DoorLockModeTypeWrite() {
//        Tasting tasting = eenTasting().withNaam("A").build();
//        tastingRepository.save(tasting);
//        Tasting mergedTasting = tastingRepository.findOne(tasting.getId());
//        assertThat(mergedTasting).heeftVersie(0);
//
//        mergedTasting.execute(eenWijzigVrijstellingCommand()
//            .withVrijstellingId(mergedTasting.getId())
//            .withVersie(mergedTasting.getVersie())
//            .withNaam("B")
//            .build());
//        tastingRepository.save(mergedTasting);
//        mergedTasting = tastingRepository.findOne(mergedTasting.getId());
//        assertThat(mergedTasting).heeftVersie(2);
    }

    @Test
    // TODO: implementeren eens we een wijzig-command hebben
    public void save_WhenErGebeurenTweeAanpassingenGebaseerdOpDezelfdeVersie_ThenDeTweedeAanpassingThrowsOptimisticLockException() {
//        Tasting tasting = eenTasting().withNaam("A").build();
//        tastingRepository.save(tasting);
//        Tasting mergedTasting = tastingRepository.findOne(tasting.getId());
//        int initialVersion = 0;
//        assertThat(mergedTasting).heeftVersie(initialVersion);
//
//        mergedTasting.execute(eenWijzigVrijstellingCommand()
//            .withVrijstellingId(mergedTasting.getId())
//            .withVersie(mergedTasting.getVersie())
//            .withNaam("B")
//            .build());
//        tastingRepository.save(mergedTasting);
//        assertThat(tastingRepository.findOne(tasting.getId())).heeftVersieGroterDan(initialVersion);
//
//        mergedTasting.execute(eenWijzigVrijstellingCommand()
//            .withVrijstellingId(mergedTasting.getId())
//            .withVersie(mergedTasting.getVersie())
//            .withNaam("C")
//            .build());
//        expectedException.expect(OptimisticLockException.class);
//        tastingRepository.save(mergedTasting);
    }

    @Test
    public void save_WhenErGebeurenTweeSavesZonderAanpassingenGebaseerdOpDezelfdeVersie_ThenDeTweedeAanpassingThrowsOptimisticLockException() {
        Tasting tasting = TastingTestBuilder.eenTasting().withNaam("A").build();
        tastingRepository.save(tasting);
        Tasting mergedTasting = tastingRepository.findOne(tasting.getId());
        int initialVersion = 0;
        TastrAssertions.assertThat(mergedTasting).heeftVersie(initialVersion);

        tastingRepository.save(mergedTasting);
        assertThat(tastingRepository.findOne(tasting.getId())).heeftVersie(initialVersion + 1);

        expectedException.expect(OptimisticLockException.class);
        tastingRepository.save(mergedTasting);
    }

    @Test
    // TODO: implementeren eens we een delete-command hebben
    public void delete_WhenErGebeurtEenAanpassingEnDaarnaEenDeleteVanDezelfdeVersie_ThenDeDeleteThrowsOptimisticLockException() {
//        Kostenpost kostenpost = eenKostenpost()
//            .withAlgemeneKostenpostInformatie(eenAlgemeneKostenpostInformatie()
//                .withBtwBedragInfoPerJaar(eenBtwBedragInfoPerJaar().withGefactureerdBedrag(inclusiefBtw(new Bedrag(510), btwPercentage(0))).build())
//                .build())
//            .persist(applicationContext);
//
//        Kostenpost mergedKostenpost = kostenpostRepository.findOne(kostenpost.getId());
//        int initialVersion = 0;
//        assertThat(mergedKostenpost.getVersie()).isEqualTo(initialVersion);
//
//        mergedKostenpost.execute(eenWijzigAlgemeneKostenpostInformatieCommand()
//            .withKostenpostId(mergedKostenpost.getId())
//            .withVersie(mergedKostenpost.getVersie())
//            .withAlgemeneKostenpostInformatie(eenAlgemeneKostenpostInformatie()
//                .withBtwBedragInfoPerJaar(eenBtwBedragInfoPerJaar().withGefactureerdBedrag(inclusiefBtw(new Bedrag(75), btwPercentage(0))).build())
//                .build())
//            .build());
//        kostenpostRepository.save(mergedKostenpost);
//        assertThat(kostenpostRepository.findOne(mergedKostenpost.getId()).getVersie()).isGreaterThan(initialVersion);
//
//        mergedKostenpost.execute(eenVerwijderKostenpostCommand()
//            .withKostenpostId(mergedKostenpost.getId())
//            .withVersie(mergedKostenpost.getVersie())
//            .build());
//        expectedException.expect(OptimisticLockException.class);
//        kostenpostRepository.delete(mergedKostenpost);
    }

}
