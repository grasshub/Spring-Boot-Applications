package com.greenfield.springintegrationgateways.gateways

import com.greenfield.springintegrationgateways.SpringIntegrationGatewaysApplication
import com.greenfield.springintegrationgateways.configuration.IntegrationConfig
import com.greenfield.springintegrationgateways.gateway.ProductGateway
import com.greenfield.springintegrationgateways.model.Product

import spock.lang.Specification;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration

//@ContextConfiguration(locations="classpath:/config/test-config.xml")
@ContextConfiguration(classes=SpringIntegrationGatewaysApplication.class)
public class ProductGatewayTests extends Specification {
	
	@Autowired
    ProductGateway productGateway

    def "Test get product gateway"() {
        given:
        def productId = '122222'

        when:
        Product product = productGateway.getProduct('122222')

        println product?.productId
        println product?.description

        then:
        product
        product.productId == productId
    }

}