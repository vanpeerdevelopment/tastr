package be.vdab.vrijstellingenbeleid.domain.tasting;

import be.vdab.vrijstellingenbeleid.infrastructure.ddd.Repository;
import com.codahale.metrics.annotation.Timed;

@Timed
public interface TastingRepository extends Repository<Tasting, TastingId> {
}
