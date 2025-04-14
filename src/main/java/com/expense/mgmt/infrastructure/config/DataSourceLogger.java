package com.expense.mgmt.infrastructure.config;


import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
@Component
public class DataSourceLogger {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void logDataSource() throws SQLException {
        log.info("DataSource URL: {}", dataSource.getConnection().getMetaData().getURL());
        log.info("DataSource URL {}", dataSource.getConnection().getMetaData());
    }


    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void logHikariDataSource() {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDS = (HikariDataSource) dataSource;
            log.debug("HikariCP - Active Connections: {}", hikariDS.getHikariPoolMXBean().getActiveConnections());
            log.debug("HikariCP - Idle Connections: {}", hikariDS.getHikariPoolMXBean().getIdleConnections());
            log.debug("HikariCP - Total Connections: {}", hikariDS.getHikariPoolMXBean().getTotalConnections());
            log.debug("HikariCP - Threads Awaiting Connections: {}",
                    hikariDS.getHikariPoolMXBean().getThreadsAwaitingConnection());
            log.debug("Configured Max Pool Size: {}", hikariDS.getMaximumPoolSize());
            log.debug("Configured Min Idle Connections: {}", hikariDS.getMinimumIdle());
        }
    }
}
