package eu.tastr.api.tasting;

import eu.tastr.domain.tasting.Tasting;
import eu.tastr.domain.tasting.TastingId;
import eu.tastr.service.tasting.TastingService;
import com.codahale.metrics.annotation.Timed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(TastingResource.TASTING_URL)
public class TastingResource {

    public static final String TASTING_URL = "/api/tasting";

    @Inject
    private TastingMapper mapper;
    @Inject
    private TastingService service;

    @Timed
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE)
    public List<TastingDto> findAll() {
        return service.findAll().stream()
            .map(tasting -> mapper.naarTastingDto(tasting))
            .collect(toList());
    }

    @Timed
    @RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, path = "/{id}")
    public TastingDto findById(@PathVariable String id) {
        Tasting tasting = service.findById(TastingId.fromUUID(id));
        return mapper.naarTastingDto(tasting);
    }

    @Timed
    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public TastingDto createTasting(@RequestBody TastingDto tastingDto) {
        Tasting tasting = service.create(mapper.naarVoegTastingToeCommand(tastingDto));
        return mapper.naarTastingDto(tasting);
    }
}
