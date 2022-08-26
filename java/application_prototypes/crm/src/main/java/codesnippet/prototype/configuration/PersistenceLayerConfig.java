package codesnippet.prototype.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

/**
 * REF
 * https://www.baeldung.com/hibernate-5-spring
 *
 * (Hibernate 中配置属性详解) https://blog.csdn.net/qq_25827845/article/details/53242219
 */
@Configuration

// <bean id="myTransactionManager"
//         class="org.springframework.orm.hibernate5.HibernateTransactionManager">
//     <property name="sessionFactory" ref="sessionFactory"/>
// </bean>

// <!-- Enable configuration of transactional behavior based on annotations -->
// <tx:annotation-driven transaction-manager="myTransactionManager" />
@EnableTransactionManagement // To allow us to use @Transactional in spring
public class PersistenceLayerConfig
{
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<HikariDataSource> dataSourceBuilder =
            DataSourceBuilder.create().type(HikariDataSource.class);

        HikariDataSource source = dataSourceBuilder
                                        .driverClassName("org.postgresql.Driver")
                                        .url("jdbc:postgresql://localhost:5432/crm")
                                        .username("hyde")
                                        .password("hyde")
                                        .build();

        source.addDataSourceProperty("cachePrepStmts", "true");
        source.addDataSourceProperty("prepStmtCacheSize", "250");
        source.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        return source;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        // Scan for hibernate @Entity class
        sessionFactory.setPackagesToScan("codesnippet.prototype.persistence.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    // Note that when we write hibernate code, we always start a transaction
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();

        /**
         * dialect: 讓hibernate針對特定資料庫sql作優化
         */
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        hibernateProperties.setProperty("hibernate.format_sql", "true");
        // hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
        // hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");

        // For EhCache 3 (or Hibernate >=5.3) this region factory should be used:
        // hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.jcache.JCacheRegionFactory");

        return hibernateProperties;
    }
}
