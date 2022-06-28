package br.com.aenweb.portalstudiocorpoealma.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;
import br.com.aenweb.portalstudiocorpoealma.repository.UsuarioRepository;
import br.com.aenweb.portalstudiocorpoealma.security.UserPrincipal;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioModel> usuario = usuarioRepository.findByUserFetchRoles(username);
		if(usuario.isEmpty())
			throw new UsernameNotFoundException("Erro ao realizar login!");
		
		return new UserPrincipal(usuario);
	}

}
