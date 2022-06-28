package br.com.aenweb.portalstudiocorpoealma.model;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="Usuarios")
@EqualsAndHashCode(of = {"id"})
public class UsuarioModel {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(value = AccessLevel.PUBLIC)
    @Getter(value = AccessLevel.PUBLIC)
    protected Long id;
	
	@Column(unique = true, name = "login")
	private String login;
	
	@ManyToOne
	private PessoaModel pessoa;
	
	@Column
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column
	private boolean ativo;
	
	private String usuarioCadastro;
	
    @Column(nullable = false, updatable = false)
    @Getter(value = AccessLevel.PUBLIC)
    @CreatedDate
	private LocalDateTime dataCadastro;
	
    @Column(nullable = false)
    @Getter(value = AccessLevel.PUBLIC)
	private LocalDateTime dataUltimaAlteracao;	
	
	@ManyToMany
	@JoinColumn(name="usuarios_id")
	private List<RoleModel> roles;
	
    @PrePersist
    public void prePersist() {
        this.dataCadastro= LocalDateTime.now();
        this.dataUltimaAlteracao= LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataUltimaAlteracao= LocalDateTime.now();
    }
}
