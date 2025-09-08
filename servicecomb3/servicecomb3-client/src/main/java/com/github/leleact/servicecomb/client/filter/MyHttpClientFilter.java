package com.github.leleact.servicecomb.client.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.servicecomb.common.rest.filter.HttpClientFilter;
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
public class MyHttpClientFilter implements HttpClientFilter {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public CompletableFuture<Void> beforeSendRequestAsync(Invocation invocation, HttpServletRequestEx requestEx) {
        log.info("MyHttpClientFilter#beforeSendRequestAsync");
        return HttpClientFilter.super.beforeSendRequestAsync(invocation, requestEx);
    }

    @Override
    public Response afterReceiveResponse(Invocation invocation, HttpServletResponseEx responseEx) {
        log.info("MyHttpClientFilter#afterReceiveResponse");
        return null;
    }
}
