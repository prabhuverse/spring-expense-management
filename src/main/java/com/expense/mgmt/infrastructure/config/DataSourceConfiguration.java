package com.expense.mgmt.infrastructure.config;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataSourceConfiguration {

    /*
    @Bean
    @ConfigurationProperties(prefix = "spring.r2dbc.postgresql")
    public PostgresqlConnectionConfiguration connectionConfiguration() {
        return PostgresqlConnectionConfiguration.builder()
                .host("localhost")  // Default host, replace with your actual DB host
                .database("your_database_name")  // Replace with your DB name
                .username("your_username")  // Replace with your DB username
                .password("your_password")  // Replace with your DB password
                .build();
    }

    @Bean
    public ConnectionFactory connectionFactory(PostgresqlConnectionConfiguration connectionConfiguration) {
        return new PostgresqlConnectionFactory(connectionConfiguration);
    }
     */
}
