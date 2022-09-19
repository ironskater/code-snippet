```bash
mvn -f liquibase/ liquibase:rollback -Dliquibase.rollbackTag=0
mvn -f liquibase/ liquibase:update
mvn -f liquibase/ liquibase:clearCheckSums
```