package codesnippet.spring.security.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:persistence.properties")
@EnableTransactionManagement // To allow us to use @Transactional in spring
public class PersistenceConfig {

    private static final Logger LOGGER = LogManager.getLogger();

    @Bean
    public DataSource dataSource(Environment env) {

        DataSourceBuilder<HikariDataSource> dataSourceBuilder = DataSourceBuilder.create().type(HikariDataSource.class);

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

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, Environment env) {

        Properties hibernateProperties = new Properties();

        /**
         * dialect: 讓hibernate針對特定資料庫sql作優化
         */
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty(
            "spring.jpa.properties.hibernate.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
        hibernateProperties.setProperty("hibernate.format_sql", env.getProperty(
            "spring.jpa.properties.hibernate.format_sql"));

        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // Scan for hibernate @Entity class
        sessionFactory.setPackagesToScan("codesnippet.spring.security.persistence.entity");
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    @Bean
    // Note that when we write hibernate code, we always start a transaction
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }
}
