package be.vdab.vrijstellingenbeleid.e2e.tests.db;

import be.vdab.vrijstellingenbeleid.domain.tasting.Tasting;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class DbCleaner {

    @Inject
    private JdbcTemplate jdbcTemplate;
    @Inject
    private Environment environment;

    public void clean() {
        truncateEntities();
    }

    private void truncateEntities() {
        deleteTable(Tasting.TABLE_NAME);
    }

    private void deleteTable(String tablename){
        jdbcTemplate.update("DELETE FROM " + dbSchema() + "." + tablename);
    }

    private String dbSchema() {
        return environment.getProperty("db.schema");
    }
}
