package be.vdab.vrijstellingenbeleid.api.tasting;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand;

import javax.inject.Named;

import static be.vdab.vrijstellingenbeleid.api.tasting.TastingDto.tastingDto;
import static be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand.VoegTastingToeCommandBuilder.voegTastingToeCommand;

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
