package se.pensionsmyndigheten.icc.test.orderprocessor.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

/**
 * 
 * Splits input message with orders/order structure into separate order in output
 *
 */
public class Route2 extends RouteBuilder {

    private static final String ROUTE_ID = "OrderProcessorRoute";

    @Override
    public void configure() throws Exception {

        from("properties:source.uri").id("input")
                .routeId(ROUTE_ID)
                .to("properties:validation.uri").id("validate-input")
                .split().tokenizeXML("order").streaming()
                .to("properties:target.uri").id("output");
    }
}