package eu.tastr.infrastructure.migration;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

import javax.inject.Named;

@Named
public class DbMigrationStrategy implements FlywayMigrationStrategy {

    @Override
    public void migrate(Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }
}
