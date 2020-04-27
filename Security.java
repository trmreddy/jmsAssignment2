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

public class Security {

	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		InitialContext context = new InitialContext();
		Topic topic = (Topic) context.lookup("topic/cardTopic");
		try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
				JMSContext jmxContext = cf.createContext();) {
			jmxContext.setClientID("Security");
			JMSConsumer firstConsumer = jmxContext.createSharedConsumer(topic, "SharedSecurity");
			JMSConsumer secondConsumer = jmxContext.createSharedConsumer(topic, "SharedSecurity");
			Message firstConsumerMessage = null;
			Message secondConsumerMessage = null;
			for (int i = 0; i < 10; i += 2) {
				firstConsumerMessage = firstConsumer.receive();
				Customer customer = (Customer) firstConsumerMessage.getBody(Customer.class);
				System.out.println(customer.getCustomerName() + " -- " + customer.getLocationUsed());

				secondConsumerMessage = secondConsumer.receive();
				Customer secondCustomer = (Customer) secondConsumerMessage.getBody(Customer.class);
				System.out.println(secondCustomer.getCustomerName() + " -- " + secondCustomer.getLocationUsed());
			}
		}
	}
}
