package be.vdab.vrijstellingenbeleid.domain.tasting.command;

import org.assertj.core.api.AbstractAssert;

import static be.vdab.vrijstellingenbeleid.domain.TastrAssertions.assertThat;

public class VoegTastingToeCommandAssert extends AbstractAssert<VoegTastingToeCommandAssert, VoegTastingToeCommand> {

    public VoegTastingToeCommandAssert(VoegTastingToeCommand actual) {
        super(actual, VoegTastingToeCommandAssert.class);
    }

    public VoegTastingToeCommandAssert heeftGegenereerdeAggregateId() {
        assertThat(actual.getAggregateId()).isNotNull();
        return this;
    }

    public VoegTastingToeCommandAssert heeftVersie(int versie) {
        assertThat(actual.getVersie()).isEqualTo(versie);
        return this;
    }

    public VoegTastingToeCommandAssert heeftNaam(String naam) {
        assertThat(actual.getNaam()).isEqualTo(naam);
        return this;
    }
}
