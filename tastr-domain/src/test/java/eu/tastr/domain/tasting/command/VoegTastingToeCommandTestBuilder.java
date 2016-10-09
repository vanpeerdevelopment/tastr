package eu.tastr.domain.tasting.command;

import eu.tastr.infrastructure.test.builder.TestBuilder;

public class VoegTastingToeCommandTestBuilder extends TestBuilder<VoegTastingToeCommand> {

    private VoegTastingToeCommand.VoegTastingToeCommandBuilder builder;

    private VoegTastingToeCommandTestBuilder() {
        this.builder = VoegTastingToeCommand.VoegTastingToeCommandBuilder.voegTastingToeCommand()
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
