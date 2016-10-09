package eu.tastr.domain.tasting;

import eu.tastr.infrastructure.ddd.AggregateRoot;
import eu.tastr.infrastructure.ddd.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.google.common.collect.Lists.newArrayList;

@Entity
@Table(name = Tasting.TABLE_NAME)
public class Tasting extends AggregateRoot<TastingId> {

    public static final String TABLE_NAME = "TASTING";
    public static final String TABLE_ID = "ID";
    public static final String COLUMN_NAAM = "NAAM";

    @Id
    @NotNull
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = TABLE_ID, unique = true, nullable = false, updatable = false, length = 36))
    private TastingId id;

    @NotEmpty(message = "Naam moet ingevuld zijn.")
    @Length(max = 200, message = "Naam kan maximaal 200 letters zijn.")
    @Column(name = COLUMN_NAAM, nullable = false, length = 200)
    private String naam;

    private Tasting() {
    }

    private Tasting(TastingBuilder builder) {
        this.id = builder.id;
        this.naam = builder.naam;
    }

    @Override
    public TastingId getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public static class TastingBuilder extends Builder<Tasting> {

        private TastingId id;
        private String naam;

        private TastingBuilder() {
        }

        public static TastingBuilder tasting() {
            return new TastingBuilder();
        }

        @Override
        protected Tasting buildInternal() {
            return new Tasting(this);
        }

        public TastingBuilder withId(TastingId id) {
            this.id = id;
            return this;
        }

        public TastingBuilder withNaam(String naam) {
            this.naam = naam;
            return this;
        }
    }
}
