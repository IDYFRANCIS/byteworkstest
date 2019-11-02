package com.francis.byteworkstest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


@EnableAutoConfiguration(exclude = { FreeMarkerAutoConfiguration.class })
@SpringBootApplication
public class ByteworkstestApplication {
	
	
	@Value("${security.signing-key}")
    private String SIGNING_KEY;

	
	@Bean
	  public JwtAccessTokenConverter accessTokenConverter() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(SIGNING_KEY);
	      return converter;
	  }

	  @Bean
	  public TokenStore tokenStore() {
	      return new JwtTokenStore(accessTokenConverter());
	  }

	  @Primary
	  @Bean
	  public DefaultTokenServices tokenServices() {
	      DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	      defaultTokenServices.setTokenStore(tokenStore());
	      defaultTokenServices.setSupportRefreshToken(true);
	      return defaultTokenServices;
	  }
	

	public static void main(String[] args) {
		SpringApplication.run(ByteworkstestApplication.class, args);
	}

}
