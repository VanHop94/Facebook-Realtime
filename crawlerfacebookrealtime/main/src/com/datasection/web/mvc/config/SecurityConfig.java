package com.datasection.web.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.csrf().and().authorizeRequests().antMatchers("/resources/**").permitAll()
		// .antMatchers("/fb/**").permitAll().antMatchers("/admin/**")
		// .access("hasRole('ADMIN')").anyRequest().authenticated().and().formLogin()
		// .loginPage("/login").permitAll().failureUrl("/login?error=1")
		// .defaultSuccessUrl("/").and().logout().logoutUrl("/logout")
		// .logoutSuccessUrl("/login").permitAll();
		// AccessDeniedHandler han = new AppAccessDeniedHandler();
		// http.exceptionHandling().accessDeniedHandler(han);

		http.authorizeRequests().antMatchers("/**").permitAll();
		http.csrf().disable();
	}
}