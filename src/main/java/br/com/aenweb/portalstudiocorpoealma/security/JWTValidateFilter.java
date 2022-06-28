package br.com.aenweb.portalstudiocorpoealma.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;
import br.com.aenweb.portalstudiocorpoealma.service.UsuarioService;

public class JWTValidateFilter extends BasicAuthenticationFilter{

	public static final String HEADER_ATTRIBUTE = "Authorization";
	public static final String ATTRIBUTE_PREFIX = "Bearer ";

	
	public JWTValidateFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String attribute = request.getHeader(HEADER_ATTRIBUTE);
		
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Access-Control-Allow-Headers, Content-Type, Authorization, Origin, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        
		if(attribute == null || !attribute.startsWith(ATTRIBUTE_PREFIX)) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			chain.doFilter(request, response);
			return;
		}
		
		String token = attribute.replace(ATTRIBUTE_PREFIX, "");
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		response.setStatus(HttpServletResponse.SC_OK);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		String user = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.TOKEN_PASSWORD))
				.build().verify(token).getSubject();
		
		if(user == null) {
			return null;
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

//		authorities = usuario.getRoles().stream().map(role -> {
//			return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
//		}).collect(Collectors.toList());
		
		return new UsernamePasswordAuthenticationToken(user, null, authorities);
	}
}
