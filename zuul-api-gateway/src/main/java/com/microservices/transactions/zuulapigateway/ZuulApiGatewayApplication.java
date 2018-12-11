package com.microservices.transactions.zuulapigateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableZuulProxy
@EnableResourceServer
@SpringBootApplication
@EnableSwagger2
public class ZuulApiGatewayApplication extends ResourceServerConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiGatewayApplication.class, args);
	}


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/uaa/oauth/**","/swagger-ui.html").permitAll()
				.and()
				.csrf().disable();
	}

}
