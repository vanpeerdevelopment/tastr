package eu.tastr.infrastructure.migration;

import org.flywaydb.core.Flyway;

import java.util.Properties;

import static eu.tastr.infrastructure.migration.Migration.MigrationBuilder.migration;
import static org.apache.commons.lang3.Validate.notNull;

public class Migration {

    private Flyway flyway;

    private Migration(MigrationBuilder builder) {
        this.flyway = createFlyway(builder);
    }

    private Flyway createFlyway(MigrationBuilder builder) {
        Flyway newFlyway = new Flyway();
        Properties properties = new Properties();
        properties.put("flyway.url", builder.url);
        properties.put("flyway.user", builder.username);
        properties.put("flyway.password", builder.password);
        properties.put("flyway.schemas", builder.schema);
        newFlyway.configure(properties);
        return newFlyway;
    }

    private void migrate() {
        flyway.clean();
        flyway.migrate();
    }

    public static class MigrationBuilder {

        private String url;
        private String username;
        private String password;
        private String schema;

        private MigrationBuilder() {
        }

        public static MigrationBuilder migration() {
            return new MigrationBuilder();
        }

        public Migration build() {
            notNull(this.url);
            notNull(this.username);
            notNull(this.password);
            notNull(this.schema);
            return new Migration(this);
        }

        public MigrationBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public MigrationBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public MigrationBuilder withPassword(String password) {
            this.password = password;
            return this;
        }
        public MigrationBuilder withSchema(String schema) {
            this.schema = schema;
            return this;
        }

    }

    public static void main(String[] args) {
        System.out.println("Starting flyway migration");
        validateArgs(args);
        Migration migration = migration()
            .withUrl(args[0])
            .withUsername(args[1])
            .withPassword(args[2])
            .withSchema(args[3])
            .build();
        migration.migrate();
        System.out.println("Finished flyway migration");
    }

    private static void validateArgs(String[] args) {
        if(args.length != 4) {
            throw new IllegalArgumentException("Migration needs 4 arguments: url, username, password and schema");
        }
    }
}
