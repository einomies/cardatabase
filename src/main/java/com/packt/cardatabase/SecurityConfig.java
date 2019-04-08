package com.packt.cardatabase;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.cardatabase.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	/*
	 * The POST method request to the /login endpoint is allowed without
	 * authentication and that requests to all other endpoints need authentication.
	 * The filters to be used in the /login and other endpoints are defined by using
	 * the addFilterBefore method.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Add this row to allow access to all endpoints
//		http.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll();

//		Tämä pois kommenteista, kun API:n käyttö vaatii taas autentikoitumisen
		http.csrf().disable().cors().and().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated().and()
				// Filter for the api/login requests
				.addFilterBefore(new LoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// Filter for other requests to check JWT in header
				.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	/*
	 * This is a CORS (Cross-Origin Resource Sharing) filter. This is needed for the
	 * frontend, that is sending requests from the other origin. The CORS filter
	 * intercepts requests, and if these are identified as cross origin, it adds
	 * proper headers to the request. For that, we use Spring Security's
	 * CorsConfigurationSource interface. In this example, we will allow all HTTP
	 * methods and headers. You can define the list of allowed origins, methods, and
	 * headers here, if you need more finely graded definition.
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

}
