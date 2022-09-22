package jp.nauplius.learn.learn_jbach_db.persistence;

import org.apache.batchee.container.services.persistence.jdbc.database.DerbyDatabase;

public class PgDatabase extends DerbyDatabase {
    public static final String SERIAL = "bigserial";

    @Override
    public String blob() {
        return "bytea";
    }
}
