package se.pensionsmyndigheten.icc.test.orderprocessor;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.main.Main;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;

import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route;
import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route2;
import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route3;

public class Application {

    private static final String DEFAULT_PROPERTIES_LOCATION = "classpath:config/application.properties";

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    //private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String... args){
        LOG.info("Starting application...");
        String location = System.getProperty("properties.location",DEFAULT_PROPERTIES_LOCATION);
        LOG.info("Loading properties from: " + location);
        
        Main camel = new Main();
        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation(location);
        camel.bind("properties",pc);
        
        while(camel.getCamelContexts().iterator().hasNext() ) {
        	CamelContext camelContext = camel.getCamelContexts().iterator().next();
        	LOG.debug("camelContext.getName()=" + camelContext.getName());
        }

       camel.addRouteBuilder(new Route3());
      // camel.addRouteBuilder(new Route());
LOG.debug("Used Route:" + camel.getRouteBuilders().get(0).getRouteCollection().getDescriptionText());
        
        try {
        	LOG.debug("camel.getVersion()=" + camel.getVersion() );
            camel.run();
        }
        catch(Exception e){
            LOG.error("Failed to start application.",e);
        }
        finally {
        	LOG.debug("finally: camel.getVersion()=" + camel.getVersion() );
        }
    }

}
