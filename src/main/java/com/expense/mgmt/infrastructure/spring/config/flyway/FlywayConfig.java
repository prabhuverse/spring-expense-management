package com.expense.mgmt.infrastructure.spring.config.flyway;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.flyway")
    public FlywayProperties flywayProperties() {
        return new FlywayProperties();
    }

    @Bean
    public Flyway flyway(FlywayProperties properties, DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .locations("classpath:db/migration")
                .dataSource(dataSource)
                //.schemas(properties.getSchemas().toArray(new String[0]))
                .baselineOnMigrate(properties.isBaselineOnMigrate())
                .sqlMigrationPrefix(properties.getSqlMigrationPrefix())
                .sqlMigrationSuffixes(properties.getSqlMigrationSuffixes().toArray(new String[0]))
                .load();
        flyway.migrate();
        return flyway;
    }
}
