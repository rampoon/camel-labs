package se.pensionsmyndigheten.icc.test.orderprocessor.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

/**
 * 
 * Splits input message with orders/order structure into separate order in output
 *
 */
public class Route3 extends RouteBuilder {

    private static final String ROUTE_ID = "OrderProcessorRoute";

    @Override
    public void configure() throws Exception {

        from("properties:source.uri").id("input")
        .routeId(ROUTE_ID)
        .log("Start splitting order message" + getRouteCollection().getShortName())
        //.streamCaching()
        //.split().xtokenize(path, mode, namespaces, group)
        // "http://test.icc.pensionsmyndigheten.se/order"
        //.split().xtokenize(path, mode, namespaces, group)
        //.split().xtokenize("//orders/order", ns)
        //.split(xpath("/order"))
        .to("properties:validation.uri").id("validate-input")
       // .split().tokenizeXML("order").streaming() works
        .split().tokenizeXML("order","orders").streaming()
        //.split(xpath("//order"))
        //.convertBodyTo(String.class)
        //.end()
        .to("properties:target.uri").id("output")
        .log("Done splitting order message");
        
    }
}