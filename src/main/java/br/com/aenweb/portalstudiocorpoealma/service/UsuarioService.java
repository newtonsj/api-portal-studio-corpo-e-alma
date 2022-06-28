package br.com.aenweb.portalstudiocorpoealma.service;

import java.util.List;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;

public interface UsuarioService {
	public UsuarioModel addUsuario(UsuarioModel usuario);

	public UsuarioModel findByEmail(String email);

	public List<UsuarioModel> findAll();
	
	public List<UsuarioModel> findAllByRole(String roleName);
}
