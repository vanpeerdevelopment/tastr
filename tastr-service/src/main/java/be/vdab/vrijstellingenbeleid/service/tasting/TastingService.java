package be.vdab.vrijstellingenbeleid.service.tasting;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingId;
import be.vdab.vrijstellingenbeleid.domain.tasting.TastingRepository;
import be.vdab.vrijstellingenbeleid.domain.tasting.command.VoegTastingToeCommand;
import com.codahale.metrics.annotation.Timed;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static be.vdab.vrijstellingenbeleid.domain.tasting.Tasting.TastingBuilder.tasting;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Named
@Validated
@Transactional
public class TastingService {

    @Inject
    private TastingRepository repository;

    @Timed
    @Transactional(SUPPORTS)
    public List<Tasting> findAll() {
        return repository.findAll();
    }

    @Timed
    @Transactional(SUPPORTS)
    public Tasting findById(TastingId id) {
        return repository.findOneExisting(id);
    }

    @Timed
    public Tasting create(@Valid VoegTastingToeCommand command) {
        Tasting tasting = tasting()
            .withId(command.getAggregateId())
            .withNaam(command.getNaam())
            .build();
        return validateAndSave(tasting);
    }

    private Tasting validateAndSave(Tasting tasting) {
        return repository.save(tasting);
    }
}
