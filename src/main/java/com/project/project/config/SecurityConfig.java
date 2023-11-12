package com.project.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.project.CustomerConfig.CustomerservConfig;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	    	
	    	@Bean
	    	protected BCryptPasswordEncoder passwordEncoder()
	    		{
	    			return new BCryptPasswordEncoder(); 
	    		}
	    	@Bean
	    	protected UserDetailsService uService()
	    	{
	    		return new CustomerservConfig();
	    	}
	    	
	    	@Bean
	    	public DaoAuthenticationProvider provider()
	    	{
	    		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
	    		daoAuthenticationProvider.setUserDetailsService(uService());
	    		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
	    		return daoAuthenticationProvider;
	    	}
	    	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
	    	{
	    		authenticationManagerBuilder.authenticationProvider(provider());
	    	}
	    
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf().disable()
	                .authorizeHttpRequests((authorize) ->
	                        authorize.requestMatchers("/check","/*").permitAll()
	                        .anyRequest().authenticated()
	                ).formLogin(
	                        form -> form
	                                .loginPage("/customerlogin")
	                                .loginProcessingUrl("/do-logins")
	                                .defaultSuccessUrl("/go")
	                                .permitAll()
	                ).logout(
	                        logout -> logout
	                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	                                .permitAll()
	                );
	        return http.build();
	    }
	    }

	  







