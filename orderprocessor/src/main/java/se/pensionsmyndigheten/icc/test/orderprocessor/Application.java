package se.pensionsmyndigheten.icc.test.orderprocessor;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.JndiRegistry;
import org.apache.camel.impl.PropertyPlaceholderDelegateRegistry;
import org.apache.camel.main.Main;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;

import se.pensionsmyndigheten.icc.test.orderprocessor.bean.DatabaseUtilBean;
import se.pensionsmyndigheten.icc.test.orderprocessor.bean.FileNameBean;
import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route;
import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route2;
import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route3;
import se.pensionsmyndigheten.icc.test.orderprocessor.route.Route4;

public class Application {

    private static final String DEFAULT_PROPERTIES_LOCATION = "classpath:config/application.properties";
    private static final String DEFAULT_ROUTE = "Route";

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);
    //private static final Logger LOG = Logger.getLogger(Application.class);

    public static void main(String... args) {
    	String selectedRoute = DEFAULT_ROUTE;
        LOG.debug("Starting application...");
        String location = System.getProperty("properties.location",DEFAULT_PROPERTIES_LOCATION);
        LOG.debug("Loading properties from: " + location);
        
        if(args.length > 0) {
        	selectedRoute = args[1];
        }
        LOG.debug("Selected Route:" + selectedRoute);
        
        Main camel = new Main();
        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation(location);
        camel.bind("properties",pc);        
        camel.bind("filenamebean", new FileNameBean());
        camel.bind("databaseutilbean", new DatabaseUtilBean());
        
        camel.addRouteBuilder(new Route3());
        
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
