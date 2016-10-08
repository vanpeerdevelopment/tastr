package be.vdab.vrijstellingenbeleid.service.validation;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.service.ServiceIntegrationTest;
import be.vdab.vrijstellingenbeleid.service.tasting.TastingService;
import org.junit.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingTestBuilder.eenTasting;
import static org.fest.reflect.core.Reflection.field;

public class ServiceValidationIntegrationTest extends ServiceIntegrationTest {

    @Inject
    private TastingService tastingService;

    @Test
    public void validatedMethod_WhenInvalidObject_ThenThrowsException(){
        Tasting invalidTasting = eenTasting().build();
        field("naam").ofType(String.class).in(invalidTasting).set(null);

        expectException(ConstraintViolationException.class);
        tastingService.create(invalidTasting);
    }
}
