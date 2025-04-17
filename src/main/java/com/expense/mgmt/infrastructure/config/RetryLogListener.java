package com.expense.mgmt.infrastructure.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;

@Slf4j
public class RetryLogListener implements RetryListener {

    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.info("Retry count {} #close ", context.getRetryCount());
    }

    public <T, E extends Throwable> void onSuccess(RetryContext context, RetryCallback<T, E> callback, T result) {
        log.info("Retry count {} #onSuccess ", context.getRetryCount());
    }

    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.info("Retry count {} #onError cause {}", context.getRetryCount(), throwable.getCause());
    }
}
