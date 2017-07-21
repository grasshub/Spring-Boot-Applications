package com.greenfield.springintegrationactivemq.services

import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import  com.greenfield.springintegrationactivemq.SpringIntegrationActiveMqApplication
import com.greenfield.springintegrationactivemq.configuration.ActiveMQConfig

import javax.jms.Connection
import javax.jms.ConnectionFactory
import javax.jms.DeliveryMode
import javax.jms.Destination
import javax.jms.MessageProducer
import javax.jms.Session
import javax.jms.TextMessage

@ContextConfiguration(classes=[SpringIntegrationActiveMqApplication.class])
class HelloServiceJmsIT extends Specification {
	
	@Autowired
	@Qualifier("jmsConnectionFactory")
	ConnectionFactory jmsConnectionFactory
	
	String queueName = ActiveMQConfig.HELLO_QUEUE
	Session session
	Destination destination
	MessageProducer producer
	
	private static final String MESSAGE = "let's go to the WEST WORLD!"
	private static final boolean FALSE = false
	private static final int THREE_THOUSAND = 3000;
	
	def setup(){
		Connection connection = jmsConnectionFactory.createConnection()
		
		connection.start()
		session = connection.createSession(FALSE, Session.AUTO_ACKNOWLEDGE)
		destination = session.createQueue(queueName)
		producer = session.createProducer(destination)
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT)
	}
 
	def "Test Send and Receive of Message"() {
		given:
		TextMessage textMessage = session.createTextMessage()
		textMessage.setText(MESSAGE)
		
		when:
		producer.send(destination, textMessage)
		
		sleep(THREE_THOUSAND)
		
		then:
		true		
	}
	
}
