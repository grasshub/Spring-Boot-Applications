package com.greenfield.springintegrationactivemq.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.jms.JmsMessageDrivenChannelAdapter;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ActiveMQConfig {
	
	public static final String HELLO_QUEUE = "hello.queue";
	
	@Autowired
	@Qualifier("jmsConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Bean
	public Queue helloJMSQueue() {
		return new ActiveMQQueue(HELLO_QUEUE);
	}
	
	@Bean(name="helloChannel")
	@Description("Pick up message sends to ActiveMQ from producer to message service response handler which is HelloService")
	public MessageChannel requestChannel() {
		return new DirectChannel();
	}
	
	// This adapter contains JMSMessageDrivernEndPoint which is a message-driven endpoint that receive JMS messages (whenever message is delivered to ActiveMQ), 
	// converts them into Spring Integration Messages, and then sends the result to a channel.
	@Bean
	public JmsMessageDrivenChannelAdapter jmsMessageDrivenChannelAdapter() {
		
		AbstractMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
		// Set the destination ActiveMQ Queue
		messageListenerContainer.setDestination(helloJMSQueue());
		// Set the default jmsConnectionFactory created by Spring Boot (Spring Integration by default looking for connectionFactory Spring Bean.)
		messageListenerContainer.setConnectionFactory(connectionFactory);
		
		JmsMessageDrivenChannelAdapter jmsMessageChannelAdapter = new JmsMessageDrivenChannelAdapter(messageListenerContainer, new ChannelPublishingJmsMessageListener());
		// Set the output channel to receive the message delivered to ActiveMQ to message response handler (HelloService)
		jmsMessageChannelAdapter.setOutputChannel(requestChannel());
		
		return jmsMessageChannelAdapter;
	}
	
}
