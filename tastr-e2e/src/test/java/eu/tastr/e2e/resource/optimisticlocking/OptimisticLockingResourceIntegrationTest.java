package eu.tastr.e2e.resource.optimisticlocking;

import eu.tastr.e2e.E2EResourceIntegrationTest;
import org.junit.Ignore;
import org.junit.Test;

//TODO: implementeren van zodra wijzigingen gedaan kunnen worden
public class OptimisticLockingResourceIntegrationTest extends E2EResourceIntegrationTest {

//    @Inject
//    private VrijstellingRepository vrijstellingRepository;
//    @Inject
//    private VrijstellingMapper vrijstellingMapper;
//
    @Test
    @Ignore("TODO: implementeren van zodra wijzigingen gedaan kunnen worden")
    public void wijzig_GivenTweeSimultaneWijzigingen_ThenGeeftOptimisticLockingException() {
//        Vrijstelling tasting = eenVrijstelling().withNaam("Oud").persist(context);
//        Vrijstelling createdVrijstelling = vrijstellingRepository.findOne(tasting.getId());
//
//        VrijstellingDto updatedVrijstellingDto = givenRequestAsBemiddelaar()
//                .body(vrijstellingMapper.naarVrijstellingDto(createdVrijstelling)
//                        .withId(createdVrijstelling.getId().getValue())
//                        .withVersie(createdVrijstelling.getVersie())
//                        .withNaam("Nieuwe Naam"))
//                .when()
//                .put(urlFor(VRIJSTELLING_URL + "/" + createdVrijstelling.getId().getValue()))
//                .then()
//                .assertThat()
//                .statusCode(OK.value())
//                .contentType(ContentType.JSON)
//                .extract()
//                .body()
//                .as(VrijstellingDto.class);
//        assertThat(updatedVrijstellingDto.naam).isEqualTo("Nieuwe Naam");
//
//        givenRequestAsBemiddelaar()
//                .body(vrijstellingMapper.naarVrijstellingDto(createdVrijstelling)
//                        .withId(createdVrijstelling.getId().getValue())
//                        .withVersie(createdVrijstelling.getVersie())
//                        .withNaam("Nieuwere Naam"))
//                .when()
//                .put(urlFor(VRIJSTELLING_URL + "/" + createdVrijstelling.getId().getValue()))
//                .then()
//                .assertThat()
//                .statusCode(CONFLICT.value());
    }
//
    @Test
    @Ignore("TODO: implementeren van zodra wijzigingen gedaan kunnen worden")
    public void wijzig_GivenTweeSubmitsZonderWijzigingen_ThenGeeftOptimisticLockingException() {
//        Vrijstelling tasting = eenVrijstelling().withNaam("Oud").persist(context);
//        Vrijstelling createdVrijstelling = vrijstellingRepository.findOne(tasting.getId());
//
//        VrijstellingDto updatedVrijstellingDto = givenRequestAsBemiddelaar()
//                .body(vrijstellingMapper.naarVrijstellingDto(createdVrijstelling))
//                .when()
//                .put(urlFor(VRIJSTELLING_URL + "/" + createdVrijstelling.getId().getValue()))
//                .then()
//                .assertThat()
//                .statusCode(OK.value())
//                .contentType(ContentType.JSON)
//                .extract()
//                .body()
//                .as(VrijstellingDto.class);
//        assertThat(updatedVrijstellingDto.naam).isEqualTo("Oud");
//
//        givenRequestAsBemiddelaar()
//                .body(vrijstellingMapper.naarVrijstellingDto(createdVrijstelling))
//                .when()
//                .put(urlFor(VRIJSTELLING_URL + "/" + createdVrijstelling.getId().getValue()))
//                .then()
//                .assertThat()
//                .statusCode(CONFLICT.value());
    }
}
