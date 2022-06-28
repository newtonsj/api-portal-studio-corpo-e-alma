package br.com.aenweb.portalstudiocorpoealma.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO implements Serializable{

	private String user;
	private String token;
	private Date expiresAt;
}
