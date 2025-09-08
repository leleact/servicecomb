package com.github.leleact.servicecomb.client.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.servicecomb.common.rest.filter.HttpServerFilter;
import org.apache.servicecomb.core.Invocation;
import org.apache.servicecomb.foundation.vertx.http.HttpServletRequestEx;
import org.apache.servicecomb.foundation.vertx.http.HttpServletResponseEx;
import org.apache.servicecomb.swagger.invocation.Response;

import java.util.concurrent.CompletableFuture;

/**
 * xxx.
 *
 * @author leleact
 * @since 2025-09-07
 */
@Slf4j
public class MyHttpServerFilter implements HttpServerFilter {
    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public Response afterReceiveRequest(Invocation invocation, HttpServletRequestEx requestEx) {
        log.info("MyHttpServerFilter#afterReceiveRequest");
        return null;
    }

    @Override
    public CompletableFuture<Void> beforeSendResponseAsync(Invocation invocation, HttpServletResponseEx responseEx) {
        log.info("MyHttpServerFilter#beforeSendResponseAsync");
        return HttpServerFilter.super.beforeSendResponseAsync(invocation, responseEx);
    }
}
