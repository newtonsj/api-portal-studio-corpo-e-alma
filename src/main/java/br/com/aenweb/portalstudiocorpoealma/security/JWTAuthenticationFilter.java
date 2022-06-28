package br.com.aenweb.portalstudiocorpoealma.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	private static final int TOKEN_EXPIRES= 864_000_00;
	public static final String TOKEN_PASSWORD = "8fc21489-b9f4-4ea9-b382-6f7f3916ff6c";
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			
			UsuarioModel user = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);
			
//			authorities = user.getRoles().stream().map(role -> {
//				return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
//			}).collect(Collectors.toList());
			
			
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getLogin(), user.getPassword(), new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException("Falha na autenticação", e);
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		UserPrincipal userData = (UserPrincipal) authResult.getPrincipal();
		
		String token = JWT.create().withSubject(userData.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
				.sign(Algorithm.HMAC512(TOKEN_PASSWORD));
		
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Access-Control-Allow-Headers, Content-Type, Authorization, Origin, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setStatus(HttpServletResponse.SC_OK);
        
		response.getWriter().write(token);
		response.getWriter().flush();
	}
}
