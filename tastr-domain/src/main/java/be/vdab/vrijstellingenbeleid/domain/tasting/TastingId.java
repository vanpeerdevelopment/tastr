package be.vdab.vrijstellingenbeleid.domain.tasting;

import be.vdab.vrijstellingenbeleid.infrastructure.ddd.Id;

import javax.persistence.Embeddable;

import static java.util.UUID.randomUUID;

@Embeddable
public final class TastingId extends Id {

    private TastingId() {
        this(randomUUID().toString());
    }

    private TastingId(String value) {
        super(value);
    }

    public static TastingId tastingId() {
        return new TastingId();
    }

    public static TastingId fromUUID(String value) {
        return new TastingId(value);
    }
}
