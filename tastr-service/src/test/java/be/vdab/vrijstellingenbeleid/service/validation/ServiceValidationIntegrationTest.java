package be.vdab.vrijstellingenbeleid.service.validation;

import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand;
import be.vdab.vrijstellingenbeleid.service.ServiceIntegrationTest;
import be.vdab.vrijstellingenbeleid.service.tasting.TastingService;
import org.junit.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommandTestBuilder.eenVoegTastingToeCommand;
import static org.fest.reflect.core.Reflection.field;

public class ServiceValidationIntegrationTest extends ServiceIntegrationTest {

    @Inject
    private TastingService tastingService;

    @Test
    public void validatedMethod_WhenInvalidObject_ThenThrowsException(){
        VoegTastingToeCommand invalidCommand = eenVoegTastingToeCommand().build();
        field("naam").ofType(String.class).in(invalidCommand).set(null);

        expectException(ConstraintViolationException.class);
        tastingService.create(invalidCommand);
    }
}
