package se.pensionsmyndigheten.icc.test.orderprocessor.route;

import org.apache.camel.builder.RouteBuilder;

public class Route extends RouteBuilder {

    private static final String ROUTE_ID = "OrderProcessorRoute";

    @Override
    public void configure() throws Exception {

        from("properties:source.uri").id("input")
                .routeId(ROUTE_ID)
                .streamCaching()
                //.split().xtokenize(path, mode, namespaces, group)
                .to("properties:validation.uri").id("validate-input")
                .to("properties:target.uri").id("output");
    }

}
