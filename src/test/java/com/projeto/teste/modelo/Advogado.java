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
final class Advogado {
	private int idAdv=0;
	private String nomeAdv="";
	private String registroOAB="";
	private Processo processo;
	
	@Override
	public String toString() {
		return "Advogado [idAdv=" + idAdv + ", nomeAdv=" + nomeAdv + ", registroOAB=" + registroOAB + ", processo="
				+ processo + "]";
	}
	
	

}
