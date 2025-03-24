package com.expense.mgmt.infrastructure.spring;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.http.async.SdkAsyncHttpClient;
import software.amazon.awssdk.http.crt.AwsCrtAsyncHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class AwsSpringConfig {

    @Bean
    public S3AsyncClient s3AsyncClient() {
        return S3AsyncClient.builder()
                .multipartEnabled(true)
                .region(Region.US_WEST_1)
                .credentialsProvider(DefaultCredentialsProvider.builder().profileName("uis").build())
                .httpClient(clientBuilder())
                .overrideConfiguration(clientOverRideConfiguration())
                .build();
    }

    private ClientOverrideConfiguration clientOverRideConfiguration() {
        return ClientOverrideConfiguration.builder()
                .apiCallTimeout(Duration.ofSeconds(5))
                .apiCallAttemptTimeout(Duration.ofSeconds(5))
                .build();
    }

    private SdkAsyncHttpClient clientBuilder() {
        return AwsCrtAsyncHttpClient.builder()
                .maxConcurrency(100)
                .connectionTimeout(Duration.ofSeconds(5))
                .build();
    }
}
