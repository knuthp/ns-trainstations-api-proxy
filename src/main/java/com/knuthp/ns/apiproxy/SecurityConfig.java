package com.knuthp.ns.apiproxy;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.authorizeRequests()
		  .antMatchers(HttpMethod.POST, "/Place").authenticated()
		  .antMatchers(HttpMethod.DELETE, "/Place/**").authenticated()
		  .anyRequest().permitAll()
		.and().httpBasic()
		.and().csrf().disable();
		// @formatter:on
	}
}
