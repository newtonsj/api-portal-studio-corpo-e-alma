package br.com.aenweb.portalstudiocorpoealma.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
@Entity(name="Telefones")
@EqualsAndHashCode(of = {"id"})
public class TelefoneModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(value = AccessLevel.PUBLIC)
    @Getter(value = AccessLevel.PUBLIC)
    protected Long id;
    
    private String ddi;
    private String ddd;
    private String telefone;
    private String tipo;
    private boolean sms;
    private boolean whatsapp;
    
	public TelefoneModel(Long id) {
		this.id = id;
	}
}
