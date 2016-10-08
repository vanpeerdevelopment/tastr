package be.vdab.vrijstellingenbeleid.domain;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingAssert;
import org.assertj.core.api.Assertions;

public class TastrAssertions extends Assertions {

    public static TastingAssert assertThat(Tasting tasting) {
        return new TastingAssert(tasting);
    }
}
