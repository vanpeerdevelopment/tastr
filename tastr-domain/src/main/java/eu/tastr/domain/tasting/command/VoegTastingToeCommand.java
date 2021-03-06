package eu.tastr.domain.tasting.command;

import eu.tastr.domain.tasting.TastingId;
import eu.tastr.infrastructure.ddd.Builder;
import eu.tastr.infrastructure.messaging.Command;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import static eu.tastr.domain.tasting.TastingId.tastingId;

public final class VoegTastingToeCommand extends Command<TastingId>{

    @NotEmpty(message = "Naam moet ingevuld zijn.")
    @Length(max = 200, message = "Naam kan maximaal 200 letters zijn.")
    private final String naam;

    public VoegTastingToeCommand(VoegTastingToeCommandBuilder builder) {
        super(tastingId(), 0);
        this.naam = builder.naam;
    }

    public String getNaam() {
        return naam;
    }

    public static class VoegTastingToeCommandBuilder extends Builder<VoegTastingToeCommand> {

        private String naam;

        private VoegTastingToeCommandBuilder() {
        }

        public static VoegTastingToeCommandBuilder voegTastingToeCommand() {
            return new VoegTastingToeCommandBuilder();
        }

        @Override
        protected VoegTastingToeCommand buildInternal() {
            return new VoegTastingToeCommand(this);
        }

        public VoegTastingToeCommandBuilder withNaam(String naam) {
            this.naam = naam;
            return this;
        }
    }
}
