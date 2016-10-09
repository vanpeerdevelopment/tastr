package eu.tastr.service.validation;

import eu.tastr.domain.tasting.command.VoegTastingToeCommand;
import eu.tastr.service.ServiceIntegrationTest;
import eu.tastr.service.tasting.TastingService;
import org.junit.Test;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;

import static eu.tastr.domain.tasting.command.VoegTastingToeCommandTestBuilder.eenVoegTastingToeCommand;
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
