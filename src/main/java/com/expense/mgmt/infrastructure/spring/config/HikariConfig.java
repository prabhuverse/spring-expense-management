package com.expense.mgmt.infrastructure.spring.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HikariConfig {

    private String poolName;

    private int minimumIdle;

    private int maximumPoolSize;

    private String schema;
}
