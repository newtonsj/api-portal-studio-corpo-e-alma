package br.com.aenweb.portalstudiocorpoealma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer>{
	
	public Optional<UsuarioModel> findByLogin(String email);
	
	@Query("SELECT u FROM Usuarios u JOIN FETCH u.roles where u.login =:email")
	public Optional<UsuarioModel> findByUserFetchRoles(@Param("email") String email);
	
	@Query("FROM Usuarios u LEFT JOIN u.roles ur where ur.name =:name")
	public List<UsuarioModel> listAllByRole(@Param("name") String name);
}
