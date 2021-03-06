package se.pensionsmyndigheten.icc.test.orderprocessor.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;

/**
 * 
 * Splits input message with orders/order structure into separate order files in output 
 *
 */
public class Route4 extends RouteBuilder {

    private static final String ROUTE_ID = "OrderProcessorRoute";

    @Override
    public void configure() throws Exception {

        from("properties:source.uri").id("input")
        .routeId(ROUTE_ID)
        .log("Start splitting order message")
        .to("properties:validation.uri").id("validate-input")
      //  .split().tokenizeXML("order","orders").streaming()
       // .split().tokenizeXML("order").streaming()
        .bean("marshallbean","unmarshal")
        .bean("databaseutilbean","save")
       // .to("file:./output") 		
        .log("Done splitting order message");
        
    }
    
}