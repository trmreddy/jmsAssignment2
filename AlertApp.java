package com.ram.pubsub.assignment;

import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.Topic;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class AlertApp {

	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/cardTopic");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmxContext = cf.createContext();) {
			jmxContext.setClientID("AlertApp");
			JMSConsumer consumer = jmxContext.createDurableConsumer(topic, "AlertappConsumer");
			consumer.close();
			Thread.sleep(10000);
			consumer = jmxContext.createDurableConsumer(topic, "AlertappConsumer");
			Message message = null;
			for (int i = 0; i < 10; i++) {
				message = consumer.receive();
				Customer customer = (Customer) message.getBody(Customer.class);
				System.out.println(customer.getCustomerName() + " -- " + customer.getLocationUsed());
			}
		}
	}
}
