package eu.tastr.domain.tasting;

import eu.tastr.infrastructure.test.UnitTest;
import org.junit.Test;

import static eu.tastr.domain.TastrAssertions.assertThat;
import static eu.tastr.domain.tasting.TastingId.tastingId;
import static org.assertj.core.api.Assertions.assertThat;

public class TastingIdTest extends UnitTest {

    @Test
    public void tastingId_ThenGeeftRandomId() {
        TastingId tastingId = tastingId();

        assertThat(tastingId.getValue()).isNotNull();
    }

    @Test
    public void fromUUID_GivenValidTastingUUID_ThenReturnNewTastingId() {
        TastingId tastingId = TastingId.fromUUID("3d9f2df0-758c-427e-81b0-36d025e9370e");
        assertThat(tastingId.getValue()).isNotNull();
    }
}
