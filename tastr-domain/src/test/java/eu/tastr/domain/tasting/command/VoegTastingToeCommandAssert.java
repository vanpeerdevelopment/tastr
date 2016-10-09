package eu.tastr.domain.tasting.command;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import static eu.tastr.domain.TastrAssertions.assertThat;

public class VoegTastingToeCommandAssert extends AbstractAssert<VoegTastingToeCommandAssert, VoegTastingToeCommand> {

    public VoegTastingToeCommandAssert(VoegTastingToeCommand actual) {
        super(actual, VoegTastingToeCommandAssert.class);
    }

    public VoegTastingToeCommandAssert heeftGegenereerdeAggregateId() {
        assertThat(actual.getAggregateId()).isNotNull();
        return this;
    }

    public VoegTastingToeCommandAssert heeftVersie(int versie) {
        Assertions.assertThat(actual.getVersie()).isEqualTo(versie);
        return this;
    }

    public VoegTastingToeCommandAssert heeftNaam(String naam) {
        Assertions.assertThat(actual.getNaam()).isEqualTo(naam);
        return this;
    }
}
