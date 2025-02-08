package com.example.demo_mvn.infrastructure.config;

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
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(sourceProperties.getUrl());
		dataSourceBuilder.driverClassName(sourceProperties.getDriverClassName());
		dataSourceBuilder.username(sourceProperties.getUsername());
		dataSourceBuilder.password(sourceProperties.getPassword());
		return dataSourceBuilder.build();
	}

	@Bean
	@Profile("!dev")
	@Primary
	public DataSource defaultDataSource(
			@Qualifier("defaultDataSourceProperties") DataSourceProperties sourceProperties) {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(sourceProperties.getUrl());
		dataSourceBuilder.driverClassName(sourceProperties.getDriverClassName());
		dataSourceBuilder.username(sourceProperties.getUsername());
		dataSourceBuilder.password(sourceProperties.getPassword());
		// HikariDataSource dataSource = (HikariDataSource) dataSourceBuilder.build();
		// dataSource.setPoolName("Demo-PoolDB");
		// dataSource.setReadOnly(true);
		return dataSourceBuilder.build();
	}
}
