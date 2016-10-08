package be.vdab.vrijstellingenbeleid.domain;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingAssert;
import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand;
import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommandAssert;
import org.assertj.core.api.Assertions;

public class TastrAssertions extends Assertions {

    public static TastingAssert assertThat(Tasting tasting) {
        return new TastingAssert(tasting);
    }

    public static VoegTastingToeCommandAssert assertThat(VoegTastingToeCommand command) {
        return new VoegTastingToeCommandAssert(command);
    }
}
