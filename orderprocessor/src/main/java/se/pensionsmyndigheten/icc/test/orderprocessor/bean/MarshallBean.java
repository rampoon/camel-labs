package se.pensionsmyndigheten.icc.test.orderprocessor.bean;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import se.pensionsmyndigheten.icc.test.order.OrderType;
import se.pensionsmyndigheten.icc.test.order.Orders;

public class MarshallBean {

	private JAXBContext contextOrder;
	private Marshaller marshallerOrder;
	private Unmarshaller unmarshallerOrder;
	private final String contextPathOrder = "se.pensionsmyndigheten.icc.test.order";
	
	synchronized public String marshal(Orders orders) throws JAXBException {
		System.out.println("Start marshal");
		contextOrder = JAXBContext.newInstance(contextPathOrder);
		marshallerOrder = contextOrder.createMarshaller();
		marshallerOrder.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		marshallerOrder.setProperty( "jaxb.encoding", "UTF-8");
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter( byteArrayOutputStream, StandardCharsets.UTF_8);
		System.out.println("Before marshal");
		marshallerOrder.marshal(orders, outputStreamWriter);
		System.out.println("After marshal");
		return byteArrayOutputStream.toString();
	}
	
	synchronized public Orders unmarshal(String message) throws JAXBException {
		System.out.println("Start unMarshal");
		contextOrder = JAXBContext.newInstance(contextPathOrder);
		unmarshallerOrder = contextOrder.createUnmarshaller();
		
		System.out.println("Before unmarshal message=" + message);
		Orders orders = (Orders)unmarshallerOrder.unmarshal(new StringReader(message));
		System.out.println("After unmarshal");
		return orders;
	}
}
