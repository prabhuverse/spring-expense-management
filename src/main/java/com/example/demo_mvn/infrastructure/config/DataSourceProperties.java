package com.example.demo_mvn.infrastructure.config;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSourceProperties {

    private String url;

    private String username;

    private String password;

    private String driverClassName;
}
