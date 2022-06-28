package br.com.aenweb.portalstudiocorpoealma.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;
import br.com.aenweb.portalstudiocorpoealma.repository.UsuarioRepository;
import br.com.aenweb.portalstudiocorpoealma.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@Override
	public UsuarioModel addUsuario(UsuarioModel usuario) {
		
		usuario.setDataCadastro(LocalDateTime.now());
		usuario.setDataUltimaAlteracao(LocalDateTime.now());
		usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public UsuarioModel findByEmail(String email) {
		
		Optional<UsuarioModel> optUsuario = Optional.ofNullable(usuarioRepository.findByLogin(email).orElseThrow(() -> new UsernameNotFoundException(
                "Usuário não encontrado!")));
		
		if(optUsuario.isEmpty())
			return null;
		
		return optUsuario.get();
	}
	
	public Boolean validaSenha(String password, String email) {
		boolean valid = false;
		Optional<UsuarioModel> optUsuario = usuarioRepository.findByLogin(email);
		if(optUsuario.isEmpty())
			return null;
		valid = passwordEncoder().matches(password, optUsuario.get().getPassword());
		return valid;
	}

	@Override
	public List<UsuarioModel> findAll() {
		return usuarioRepository.findAll();
	}

	private BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public List<UsuarioModel> findAllByRole(String roleName) {
		return usuarioRepository.listAllByRole(roleName);
	}
	
}
