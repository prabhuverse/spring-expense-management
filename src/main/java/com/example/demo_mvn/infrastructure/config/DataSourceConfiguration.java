package com.example.demo_mvn.infrastructure.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(value = "spring.datasource.dev")
    public DataSourceProperties devDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(value = "spring.datasource")
    public DataSourceProperties defaultDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Profile("dev")
    public DataSource devDataSource(@Qualifier("devDataSourceProperties") DataSourceProperties sourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(sourceProperties.getUrl());
        config.setDriverClassName(sourceProperties.getDriverClassName());
        config.setUsername(sourceProperties.getUsername());
        config.setPassword(sourceProperties.getPassword());
        config.setPoolName(sourceProperties.getHikari().getPoolName());
        config.setMinimumIdle(sourceProperties.getHikari().getMinimumIdle());
        config.setMaximumPoolSize(sourceProperties.getHikari().getMaximumPoolSize());
        return new HikariDataSource(config);
    }

    @Bean
    @Profile("!dev")
    @Primary
    public DataSource defaultDataSource(
            @Qualifier("defaultDataSourceProperties") DataSourceProperties sourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(sourceProperties.getUrl());
        config.setDriverClassName(sourceProperties.getDriverClassName());
        config.setUsername(sourceProperties.getUsername());
        config.setPassword(sourceProperties.getPassword());
        config.setPoolName(sourceProperties.getHikari().getPoolName());
        config.setMinimumIdle(sourceProperties.getHikari().getMinimumIdle());
        config.setMaximumPoolSize(sourceProperties.getHikari().getMaximumPoolSize());
        return new HikariDataSource(config);
    }
}
