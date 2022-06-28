package br.com.aenweb.portalstudiocorpoealma.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aenweb.portalstudiocorpoealma.model.UsuarioModel;
import br.com.aenweb.portalstudiocorpoealma.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/listAll")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<UsuarioModel>> listAll(){		
		
		return ResponseEntity.ok(usuarioService.findAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<UsuarioModel> save(@RequestBody UsuarioModel usuario){
		
		return ResponseEntity.ok(usuarioService.addUsuario(usuario));
		
	}
	
	@GetMapping("/findByEmail")
	public ResponseEntity<UsuarioModel> validatePassword(@RequestParam String email){
				
		return ResponseEntity.ok(usuarioService.findByEmail(email));
	}
	
	@GetMapping("/listaAlunos")
	public ResponseEntity<List<UsuarioModel>> listaAlunos(@RequestParam String roleName){
				
		return ResponseEntity.ok(usuarioService.findAllByRole(roleName));
	}
	
}
