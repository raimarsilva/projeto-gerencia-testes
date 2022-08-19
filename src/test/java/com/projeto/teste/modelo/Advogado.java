/**
 * 
 */
package com.projeto.teste.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Iramar Jetterson Ramon Raimar
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public final class Advogado {
	public String idAdv="";
	public String nomeAdv="";
	public String registroOAB="";
	public Processo processo;
	
	public Advogado(String idAdv, String nomeAdv, String registroOAB) {
		super();
		this.idAdv = idAdv;
		this.nomeAdv = nomeAdv;
		this.registroOAB = registroOAB;
	}

	@Override
	public String toString() {
		return "Advogado [idAdv=" + idAdv + ", nomeAdv=" + nomeAdv + ", registroOAB=" + registroOAB + ", processo="
				+ processo + "]";
	}
	
	

}
