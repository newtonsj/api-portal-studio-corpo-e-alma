package br.com.aenweb.portalstudiocorpoealma.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
@Entity(name="Roles")
@EqualsAndHashCode(of = {"id"})
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(value = AccessLevel.PUBLIC)
    @Getter(value = AccessLevel.PUBLIC)
    protected Long id;
	
	@Column(unique = true)
	private String name;
	
	public RoleModel(Long id) {
		this.id = id;
	}
}
