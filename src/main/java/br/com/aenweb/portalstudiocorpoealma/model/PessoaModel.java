package br.com.aenweb.portalstudiocorpoealma.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity(name="Pessoas")
@EqualsAndHashCode(of = {"id"})
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(value = AccessLevel.PUBLIC)
    @Getter(value = AccessLevel.PUBLIC)
    protected Long id;
	
    private String cpf;
    private String nome;
    private String sobrenome;
    private String sexo;
    private LocalDate dataNascimento;
    private String estadoCivil;
    private String profissao;
    private String email;
    
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    
    private String foto;
    
    private String tipoMatricula;
    
    @ManyToMany
    private List<TelefoneModel> telefones;

    private String usuarioCadastro;
    
    @Column(nullable = false, updatable = false)
    @Getter(value = AccessLevel.PUBLIC)
    @CreatedDate
	private LocalDateTime dataCadastro;
	
    
    @Column(nullable = false)
    @Getter(value = AccessLevel.PUBLIC)
	private LocalDateTime dataUltimaAlteracao;	
    
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
