package eu.tastr.domain;

import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.TastingAssert;
import eu.tastr.domain.tasting.command.VoegTastingToeCommand;
import eu.tastr.domain.tasting.command.VoegTastingToeCommandAssert;
import org.assertj.core.api.Assertions;

public class TastrAssertions extends Assertions {

    public static TastingAssert assertThat(Tasting tasting) {
        return new TastingAssert(tasting);
    }

    public static VoegTastingToeCommandAssert assertThat(VoegTastingToeCommand command) {
        return new VoegTastingToeCommandAssert(command);
    }
}
