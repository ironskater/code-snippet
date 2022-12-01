- mysql
mvn -f liquibase/mysql/ liquibase:rollback -Dliquibase.rollbackTag=0
mvn -f liquibase/mysql/ liquibase:update

- postgresql
mvn -f liquibase/postgresql/ liquibase:rollback -Dliquibase.rollbackTag=0
mvn -f liquibase/postgresql/ liquibase:update