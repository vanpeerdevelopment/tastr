package eu.tastr.api.tasting;

import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.command.VoegTastingToeCommand;

import javax.inject.Named;

import static eu.tastr.api.tasting.TastingDto.tastingDto;
import static eu.tastr.domain.tasting.command.VoegTastingToeCommand.VoegTastingToeCommandBuilder.voegTastingToeCommand;

@Named
public class TastingMapper {

    public TastingDto naarTastingDto(Tasting tasting) {
        return tastingDto()
            .withId(tasting.getId().getValue())
            .withVersie(tasting.getVersie())
            .withNaam(tasting.getNaam());
    }

    public VoegTastingToeCommand naarVoegTastingToeCommand(TastingDto tastingDto) {
        return voegTastingToeCommand()
            .withNaam(tastingDto.naam)
            .build();
    }
}
