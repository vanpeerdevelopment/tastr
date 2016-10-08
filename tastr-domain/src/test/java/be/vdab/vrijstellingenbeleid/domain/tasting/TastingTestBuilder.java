package be.vdab.vrijstellingenbeleid.domain.tasting;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting.TastingBuilder;
import be.vdab.vrijstellingenbeleid.infrastructure.test.builder.PersistableTestBuilder;
import org.springframework.context.ApplicationContext;

import static be.vdab.vrijstellingenbeleid.domain.tasting.Tasting.TastingBuilder.tasting;
import static be.vdab.vrijstellingenbeleid.domain.tasting.TastingId.tastingId;

public class TastingTestBuilder extends PersistableTestBuilder<Tasting> {

    private TastingBuilder builder;

    private TastingTestBuilder() {
        builder = tasting()
            .withId(tastingId())
            .withNaam("Tasting naam");
    }

    public static TastingTestBuilder eenTasting() {
        return new TastingTestBuilder();
    }

    @Override
    protected Tasting buildObject() {
        return builder.build();
    }

    @Override
    protected Tasting persistObject(Tasting tasting, ApplicationContext context) {
        return context.getBean(TastingRepository.class).save(tasting);
    }

    public TastingTestBuilder withoutId() {
        return withId(null);
    }

    public TastingTestBuilder withId(TastingId id) {
        builder.withId(id);
        return this;
    }

    public TastingTestBuilder withNaam(String naam) {
        builder.withNaam(naam);
        return this;
    }
}
