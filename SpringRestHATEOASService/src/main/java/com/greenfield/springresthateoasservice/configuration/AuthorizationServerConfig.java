package com.greenfield.springresthateoasservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private static final String APPLICATION_NAME = "bookmarks";
	
	@Autowired
	private ClientDetailsService clientDetailsService;
	
	// This is required for password grants, which we specify below as one of the authorizedGrantTypes.
	@Autowired
	AuthenticationManagerBuilder authenticationManager;
	
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}
	
	@Bean
	public TokenStoreUserApprovalHandler userApprovalHandler() {
		TokenStoreUserApprovalHandler userApprovalHandler = new TokenStoreUserApprovalHandler();
		userApprovalHandler.setTokenStore(tokenStore());
		userApprovalHandler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
		userApprovalHandler.setClientDetailsService(clientDetailsService);
		
		return userApprovalHandler;
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients.inMemory().withClient("android-" + APPLICATION_NAME).authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
			.authorities("ROLE_USER").scopes("read", "write").secret("123456").accessTokenValiditySeconds(60 * 60).refreshTokenValiditySeconds(3 * 60 * 60);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		// Workaround for https://github.com/spring-projects/spring-boot/issues/1801
		endpoints.tokenStore(tokenStore()).userApprovalHandler(userApprovalHandler()).authenticationManager( new AuthenticationManager() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				return authenticationManager.getOrBuild().authenticate(authentication);
			}
			
		});
		
	}

}
