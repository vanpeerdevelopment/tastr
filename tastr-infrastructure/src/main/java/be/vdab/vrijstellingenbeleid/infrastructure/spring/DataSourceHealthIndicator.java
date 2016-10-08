package be.vdab.vrijstellingenbeleid.infrastructure.spring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.env.Environment;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.springframework.dao.support.DataAccessUtils.requiredSingleResult;

@Named
public class DataSourceHealthIndicator implements HealthIndicator {

    @Inject
    private Environment environment;
    @Inject
    private DataSource dataSource;

    @Override
    public Health health() {
        try {
            return Health.up()
                .withDetail("schema", dbSchema())
                .withDetail("test", String.format("aantal tastings is %s", aantalTastings()))
                .build();
        } catch (Exception e) {
            return Health.down()
                .withDetail("schema", dbSchema())
                .withException(e)
                .build();
        }
    }

    private BigDecimal aantalTastings() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<BigDecimal> aantalTastings = jdbcTemplate.query(
            String.format("SELECT count(*) FROM %s.%s",
                dbSchema(),
                "TASTING"),
            new SingleColumnRowMapper<BigDecimal>());
        return requiredSingleResult(aantalTastings);
    }

    private String dbSchema() {
        return environment.getProperty("db.schema");
    }
}
