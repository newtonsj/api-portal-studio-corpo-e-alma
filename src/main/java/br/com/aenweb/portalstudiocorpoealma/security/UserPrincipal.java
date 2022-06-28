package br.com.aenweb.portalstudiocorpoealma.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;

public class UserPrincipal implements UserDetails{
	
	private final Optional<UsuarioModel> user;
	
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(Optional<UsuarioModel> user) {
		this.user = user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		authorities = this.user.get().getRoles().stream().map(role -> {
			return new SimpleGrantedAuthority("ROLE_".concat(role.getName()));
		}).collect(Collectors.toList());
		return authorities;

	}

	@Override
	public String getPassword() {
		return user.orElse(new UsuarioModel()).getPassword();
	}

	@Override
	public String getUsername() {
		return user.orElse(new UsuarioModel()).getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.orElse(new UsuarioModel()).isAtivo();
	}

}
