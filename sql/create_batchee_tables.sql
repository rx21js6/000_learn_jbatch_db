CREATE SCHEMA AUTHORIZATION batchee;
SELECT set_config('search_path', 'batchee', false);

CREATE TABLE IF NOT EXISTS BATCH_CHECKPOINT(id bigserial , data bytea, stepName varchar(255), type varchar(20), INSTANCE_JOBINSTANCEID bigint, primary key(id));
CREATE TABLE IF NOT EXISTS BATCH_JOBEXECUTION(executionId bigserial, batchStatus varchar(20), createTime timestamp, endTime timestamp, exitStatus varchar(255), jobProperties bytea, startTime timestamp, updateTime timestamp, INSTANCE_JOBINSTANCEID bigint);
CREATE TABLE IF NOT EXISTS BATCH_JOBINSTANCE(jobInstanceId bigserial, batchStatus varchar(20), exitStatus varchar(255), jobName varchar(255), jobXml bytea, latestExecution bigint, restartOn varchar(255), step varchar(255), PRIMARY KEY (jobInstanceId));
CREATE TABLE IF NOT EXISTS BATCH_STEPEXECUTION(id bigserial, batchStatus varchar(20), exec_commit bigint, endTime timestamp, exitStatus varchar(255), exec_filter bigint, lastRunStepExecutionId bigint, numPartitions integer, persistentData bytea, exec_processskip bigint, exec_read bigint, exec_readskip bigint, exec_rollback bigint, startCount integer, startTime timestamp, stepName varchar(255), exec_write bigint, exec_writeskip bigint, EXECUTION_EXECUTIONID bigint, PRIMARY KEY (id));
ALTER TABLE BATCH_CHECKPOINT OWNER TO batchee;
ALTER TABLE BATCH_JOBEXECUTION OWNER TO batchee;
ALTER TABLE BATCH_JOBINSTANCE OWNER TO batchee;
ALTER TABLE BATCH_STEPEXECUTION OWNER TO batchee;

