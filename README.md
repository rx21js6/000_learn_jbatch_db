# 000_learn_jbatch_db

(Pardon my broken English.😅)

## What's this?

Example of running jBatch(BatchEE), and recoding execution logs in PostgreSQL.

## Referenced libraries

- JDK 1.8
- Apache Batchee 1.0.2
- Weld (weld-servlet-shaded) 3.1.9
- PostgreSQL JDBC Driver 42
- Jersey 2.37 + Jackson (for batch execution)
- etc.(`build.gradle` also contains unused libraries. 😅)


## Requirements

1. PostgreSQL-Server(12+)  - Recommended installing at same host (localhost).
1. Gradle(6.9+)
1. (If creating war) Jetty9+

## Usage

<ol>
<li>Create role and database. Use sql/create_batchee_role.sql and sql/create_batchee_db.sql. (via psql command)
<li>When DB is running on another host, change src/main/resources/batchee.properties 's parameter 'persistence.database.url'.

    persistence.database.url=jdbc:postgresql://localhost:5432/batchee
    ↓
    persistence.database.url=jdbc:postgresql://your-db-server:5432/batchee

<li>Run gretty(task: appRunWar etc.) or create war and deploy to server.
<li>Access the url below using browser (or Curl etc.):

    http://localhost:8080/000_learn_jbatch_db/rest/dummyBatchEndpoint/dummy01

Tables are automatically created, and batch job will run.
When succeeded, returns executionId, otherwise returns 500(Internal Server Error).

<li>When checking execution result, access the url below: (Replace "executionId" to above's result.)

```
http://localhost:8080/000_learn_jbatch_db/rest/dummyBatchEndpoint/execution?id=executionId
```

</ol>

## License

Apache License Version 2.0

©2022 nauplius.jp

