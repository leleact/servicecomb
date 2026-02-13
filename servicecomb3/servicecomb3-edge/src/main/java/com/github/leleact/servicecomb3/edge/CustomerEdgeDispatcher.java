package com.github.leleact.servicecomb3.edge;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.apache.servicecomb.edge.core.AbstractEdgeDispatcher;
import org.apache.servicecomb.edge.core.EdgeInvocation;
import org.apache.servicecomb.edge.core.Utils;

/**
 * dispatcher.
 *
 * @author leleact
 * @since 2026-02-13
 */
public class CustomerEdgeDispatcher extends AbstractEdgeDispatcher {
    @Override
    public void init(Router router) {
        String regex = "/myapi/([^\\\\/]+)/(.*)";
        router.routeWithRegex(regex).handler(createBodyHandler());
        router.routeWithRegex(regex).failureHandler(this::onFailure).handler(this::onRequest);
    }

    private void onRequest(RoutingContext context) {
        EdgeInvocation edgeInvocation = new EdgeInvocation();
        edgeInvocation.setVersionRule("0.0.0.0+");
        edgeInvocation.init(extractMicroserviceName(context), context,
            Utils.findActualPath(context.request().path(), 2), httpServerFilters);
        edgeInvocation.edgeInvoke();
    }

    private String extractMicroserviceName(RoutingContext context) {
        return context.pathParam("param0");
    }
}
