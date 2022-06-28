package br.com.aenweb.portalstudiocorpoealma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.aenweb.portalstudiocorpoealma.service.impl.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

    private final CustomUserDetailsService usuarioService;
    private final PasswordEncoder passwordEncoder;
    
   public SecurityConfig(CustomUserDetailsService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{

		//http.authorizeRequests().antMatchers("/api/login" , "/api/login/**").permitAll().and().httpBasic();
		
        http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTValidateFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
