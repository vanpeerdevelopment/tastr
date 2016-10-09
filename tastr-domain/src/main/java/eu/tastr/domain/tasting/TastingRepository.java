package eu.tastr.domain.tasting;

import eu.tastr.infrastructure.ddd.Repository;
import com.codahale.metrics.annotation.Timed;

@Timed
public interface TastingRepository extends Repository<Tasting, TastingId> {
}
