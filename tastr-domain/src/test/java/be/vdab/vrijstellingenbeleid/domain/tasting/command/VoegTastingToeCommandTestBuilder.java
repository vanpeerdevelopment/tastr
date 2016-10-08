package be.vdab.vrijstellingenbeleid.domain.tasting.command;

import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand.VoegTastingToeCommandBuilder;
import be.vdab.vrijstellingenbeleid.infrastructure.test.builder.TestBuilder;

import static be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand.VoegTastingToeCommandBuilder.voegTastingToeCommand;

public class VoegTastingToeCommandTestBuilder extends TestBuilder<VoegTastingToeCommand> {

    private VoegTastingToeCommandBuilder builder;

    private VoegTastingToeCommandTestBuilder() {
        this.builder = voegTastingToeCommand()
            .withNaam("Tasting naam");
    }

    public static VoegTastingToeCommandTestBuilder eenVoegTastingToeCommand() {
        return new VoegTastingToeCommandTestBuilder();
    }

    @Override
    protected VoegTastingToeCommand buildObject() {
        return builder.build();
    }

    public VoegTastingToeCommandTestBuilder withNaam(String naam) {
        builder.withNaam(naam);
        return this;
    }
}
