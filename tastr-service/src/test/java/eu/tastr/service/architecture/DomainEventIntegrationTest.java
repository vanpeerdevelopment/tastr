package eu.tastr.service.architecture;

import eu.tastr.service.ServiceIntegrationTest;
import org.junit.Ignore;

@Ignore
// TODO: unignore en implement eens we events zouden (nodig) hebben
public class DomainEventIntegrationTest extends ServiceIntegrationTest {

//    @Inject
//    private KostenpostRepository kostenpostRepository;
//
//    @Inject
//    private ProjectonderdeelRepository projectonderdeelRepository;
//
//    @Before
//    public void setup() {
//        setupTestSecurityContextRegieMedewerker();
//    }
//
//    @Test
//    public void save_GivenKostenpost_ThenVerhoogGefactureerdBedragVanProjectOnderdeel() {
//        Projectonderdeel persistedProjectonderdeel = eenProjectonderdeel()
//            .withProjectonderdeelAfgeleideVelden(
//                eenProjectonderdeelAfgeleideVelden()
//                    .withResultaatsVergoeding(false)
//                    .build()
//            )
//            .persist(context);
//
//        Kostenpost kostenpost = eenKostenpost()
//            .withProjectonderdeel(persistedProjectonderdeel)
//            .withAlgemeneKostenpostInformatie(
//                eenAlgemeneKostenpostInformatie()
//                    .withKostenpostTypeCode(kostenpostTypeCode(RESULTAATS_VERGOEDING_KEY))
//                    .build())
//            .build();
//
//        assertThat(kostenpost.getUncommittedEvents()).hasSize(1);
//
//        kostenpostRepository.save(kostenpost);
//
//        Projectonderdeel gewijzigdProjectonderdeel = projectonderdeelRepository.findOne(persistedProjectonderdeel.getId());
//        assertThat(gewijzigdProjectonderdeel.isResultaatsVergoeding()).isTrue();
//        assertThat(kostenpost.getUncommittedEvents()).isEmpty();
//    }
}
