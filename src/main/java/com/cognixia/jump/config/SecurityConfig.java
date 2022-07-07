package com.cognixia.jump.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filter.JwtFilter;
import com.cognixia.jump.util.CustomPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtFilter jwtFilter;

	@Autowired
	private UserDetailsService userDetailsService;

//	@Autowired
//	private CustomPasswordEncoder customPasswordEncoder;

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.csrf().disable()
		 .authorizeRequests()
		 .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
		 .antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
		 .antMatchers(HttpMethod.POST, "/api/user").permitAll()
		 .antMatchers(HttpMethod.POST, "/api/product").permitAll()
		 .antMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
		 .antMatchers(HttpMethod.GET, "/api/product").permitAll()
		 .antMatchers("/api/admin").hasRole("ADMIN")
		 .anyRequest().authenticated().and()
		 .sessionManagement()
		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		 			.exceptionHandling()
//					.authenticationEntryPoint((request, response, ex) -> {
//						response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//					}).and()
		 			
			
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		}
	
	

}
