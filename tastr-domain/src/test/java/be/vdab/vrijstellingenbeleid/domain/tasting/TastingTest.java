package be.vdab.vrijstellingenbeleid.domain.tasting;

import be.vdab.vrijstellingenbeleid.infrastructure.test.UnitTest;
import be.vdab.vrijstellingenbeleid.infrastructure.validation.VrijstellingenbeleidValidator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;
import static be.vdab.vrijstellingenbeleid.infrastructure.validation.VrijstellingenbeleidValidator.validator;
import static org.apache.commons.lang3.StringUtils.repeat;

public class TastingTest extends UnitTest {

    @Test
    public void testBuilder_GivenDefaultTasting_ThenGeeftGeldigeTasting() {
        expectNoException();

        eenTasting().build();
    }

    @Test
    public void tasting_GivenGeenId_ThenThrowsException() {
        expectConstraintViolationWithMessage();

        Tasting tasting = eenTasting().withoutId().build();
    }

    @Test
    public void tasting_GivenGeenNaam_ThenThrowsException() {
        expectConstraintViolationExceptionWithMessages("Naam moet ingevuld zijn.");

        Tasting tasting = eenTasting().withNaam(null).build();
    }

    @Test
    public void tasting_GivenTeLangeNaam_ThenThrowsException() {
        expectConstraintViolationExceptionWithMessages("Naam kan maximaal 200 letters zijn.");

        Tasting tasting = eenTasting().withNaam(repeat("a", 201)).build();
    }

    @Test
    // TODO: implementeren eens we een wijzig-command hebben
    public void wijzigTasting_givenCommandMetFoutieveVersie_ThenThrowConcurrentModificationException() {
//        Tasting tasting = eenTasting().build();
//
//        expectException(OptimisticLockException.class);
//        tasting.execute(WijzigVrijstellingCommandTestBuilder.eenWijzigVrijstellingCommand().withVersie(tasting.getVersie() - 1).build());
    }

}
