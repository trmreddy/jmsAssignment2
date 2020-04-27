package com.ram.pubsub.assignment;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import com.ram.pubsub.Employee;

public class CardApp {

	public static void main(String[] args) throws NamingException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/cardTopic");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(); 
				JMSContext jmxContext = cf.createContext();) {
			JMSProducer createProducer = jmxContext.createProducer();
			Customer customer = new Customer();
			customer.setCardNumber(4703382494L);
			customer.setCustomerName("RamMohan Reddy");
			customer.setLocationUsed("Marietta");
			for(int i=0;i<10;i++) {
				customer.setCustomerName(customer.getCustomerName()+i);
				customer.setLocationUsed(customer.getLocationUsed()+i);
				createProducer.send(topic, customer);
			}
			System.out.println("Object sent");
		}
	}

}
