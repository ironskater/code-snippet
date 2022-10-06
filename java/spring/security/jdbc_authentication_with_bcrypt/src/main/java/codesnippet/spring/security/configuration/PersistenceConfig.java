package codesnippet.spring.security.configuration;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:persistence.properties")
public class PersistenceConfig {

    private static final Logger LOGGER = LogManager.getLogger();

    @Bean
    public DataSource dataSource(Environment env) {

        DataSourceBuilder<HikariDataSource> dataSourceBuilder =
            DataSourceBuilder.create().type(HikariDataSource.class);

        LOGGER.info(">>> Start constructing datasource............");

        LOGGER.info(">>> jdbc.driver = {}", env.getProperty("jdbc.driver"));
        LOGGER.info(">>> jdbc.url = {}", env.getProperty("jdbc.url"));
        LOGGER.info(">>> jdbc.user = {}", env.getProperty("jdbc.user"));
        LOGGER.info(">>> jdbc.password = {}", env.getProperty("jdbc.password"));

        HikariDataSource source = dataSourceBuilder
            .driverClassName(env.getProperty("jdbc.driver"))
            .url(env.getProperty("jdbc.url"))
            .username(env.getProperty("jdbc.user"))
            .password(env.getProperty("jdbc.password"))
            .build();

        source.addDataSourceProperty("cachePrepStmts", "true");
        source.addDataSourceProperty("prepStmtCacheSize", "250");
        source.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        LOGGER.info(">>> End constructing datasource............");

        return source;
    }
}
