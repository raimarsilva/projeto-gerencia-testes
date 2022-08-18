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
final class Processo {
	private String numProces = "";
	private String orgaoJudic = "";
	private String relator="";
	private Advogado advogado;
	
	protected Boolean associarAdvogado(Advogado advog) {
		this.advogado = advog;
		return true;
	}
	
	protected Boolean dissociarAdvogado(Advogado advog) {
		// fazer logica.
		return true;
	}

	@Override
	public String toString() {
		return "Processo [numProces=" + numProces + ", orgaoJudic=" + orgaoJudic + ", relator=" + relator
				+ ", advogado=" + advogado + "]";
	}

	

}
