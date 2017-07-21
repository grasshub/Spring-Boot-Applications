package com.greenfield.springintegrationgateways.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

// Need to define the message channel
@Configuration
public class IntegrationConfig {
	
	@Bean(name="getProductChannel")
	@Description("Entry to the message system through gateway.")
	public MessageChannel requestChannel() {
		return new DirectChannel();
	}
}
