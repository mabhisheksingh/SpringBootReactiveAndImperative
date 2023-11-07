package com.abhishek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * The answer depends on whether you are using Spring Boot or not.
 * If you are using Spring and Spring Security WITHOUT Spring Boot, The @EnableWebSecurity does just you described in your original question:
 * it will automatically define and configure several beans, the filter chain, etc. to enable basic security for a web application.
 * If you are using Spring Boot, autoconfiguration classes
 * (See https://github.com/spring-projects/spring-boot/tree/main/spring-boot-project/spring-boot-autoconfigure/src/main/java/org/springframework/boot/autoconfigure/security)
 * will autoconfigure security beans for you. There is no need for @EnableWebSecurity. In fact with Boot,
 * there is generally no need for any of Spring's @Enable* annotations.
 */
@Configuration
//@EnableWebSecurity //use only for Imperative Spring boot
//@EnableWebFluxSecurity
public class SpringSecurityConfig {

	@Bean
	SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
		/*
		 * Imperative Style of Security http.authorizeHttpRequests( authz ->
		 * authz.requestMatchers("/api/v1/testcontroller",
		 * "api/v1/testcontroller").permitAll() // .hasRole("ROLE_PUBLIC").anyRequest()
		 * // .permitAll() .anyRequest().authenticated());
		 */

		http.authorizeExchange(exchange -> exchange.pathMatchers("/api/v1/test**/**", "/actuator/**")
				.permitAll()
				.anyExchange()
				.authenticated())
				.oauth2Login()
				.and()
				.formLogin()
				.and()
				.logout()
				.and().csrf().disable();
//				.oauth2ResourceServer(server -> server.jwt())
//				.formLogin();

        return http.build();
	}

	@Bean
	MapReactiveUserDetailsService userDetailsService() {
		UserDetails admin = User.builder().password(passwordEncoder().encode("admin")).username("admin")
				.roles("ADMIN","USER").build();
		UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user"))
				.roles( "USER").build();
		return new MapReactiveUserDetailsService( admin ,user);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
