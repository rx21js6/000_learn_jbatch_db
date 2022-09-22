package jp.nauplius.learn.learn_jbach_db.persistence;

import org.apache.batchee.container.services.persistence.jdbc.Dictionary;
import org.apache.batchee.container.services.persistence.jdbc.database.Database;

import lombok.Getter;

public class PgDictionary extends Dictionary {

    @Getter
    private String createCheckpointTable;

    @Getter
    private String createJobInstanceTable;

    @Getter
    private String createJobExecutionTable;

    @Getter
    private String createStepExecutionTable;;

    public PgDictionary(String checkpointTable, String jobInstanceTable, String jobExecutionTable,
            String stepExecutionTable, Database database) {
        super(checkpointTable, jobInstanceTable, jobExecutionTable, stepExecutionTable, database);

        this.createCheckpointTable = this.replaceSql(super.getCreateCheckpointTable(), database);
        this.createJobInstanceTable = this.replaceSql(super.getCreateJobInstanceTable(), database);
        this.createJobExecutionTable = this.replaceSql(super.getCreateJobExecutionTable(), database);
        this.createStepExecutionTable = this.replaceSql(super.getCreateStepExecutionTable(), database);
    }

    private String replaceSql(String sql, Database database) {
        String replacementCreateTable = SQL.CREATE_TABLE;
        String replaceToCreateTableIfNotExist = SQL.CREATE_TABLE + "if not exists ";
        String replacementTextSerial = String.format("%s %s", database.bigint(), database.autoIncrementId());

        String replacedSql = sql.replace(replacementCreateTable, replaceToCreateTableIfNotExist).replace(replacementTextSerial, PgDatabase.SERIAL);
        return replacedSql;
    }

}
