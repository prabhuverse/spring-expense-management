package com.example.demo_mvn.infrastructure.config;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class DataSourceLogger {

	@Autowired
	private DataSource dataSource;

	@PostConstruct
	public void logDataSource() throws SQLException {
		System.out.println("DataSource URL: " + dataSource.getConnection().getMetaData().getURL());
	}
}
