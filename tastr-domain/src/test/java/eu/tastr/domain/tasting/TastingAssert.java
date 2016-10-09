package eu.tastr.domain.tasting;

import org.assertj.core.api.AbstractAssert;

import static eu.tastr.domain.TastrAssertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class TastingAssert extends AbstractAssert<TastingAssert, Tasting> {

    public TastingAssert(Tasting actual) {
        super(actual, TastingAssert.class);
    }

    public TastingAssert heeftGegenereerdeId() {
        assertThat(actual.getId()).isNotNull();
        return this;
    }

    public TastingAssert heeftId(TastingId id) {
        assertThat(actual.getId()).isEqualTo(id);
        return this;
    }

    public TastingAssert heeftVersie(int versie) {
        assertThat(actual.getVersie()).isEqualTo(versie);
        return this;
    }

    public TastingAssert heeftVersieGroterDan(int versie) {
        assertThat(actual.getVersie()).isGreaterThan(versie);
        return this;
    }

    public TastingAssert heeftNaam(String naam) {
        assertThat(actual.getNaam()).isEqualTo(naam);
        return this;
    }
}
