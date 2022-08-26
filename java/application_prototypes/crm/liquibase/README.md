mvn -f liquibase/pom.xml liquibase:rollback -Dliquibase.rollbackTag=0
mvn -f liquibase/pom.xml liquibase:update