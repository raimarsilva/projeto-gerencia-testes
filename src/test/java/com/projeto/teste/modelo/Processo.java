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
public final class Processo {
	public String idProcesso;
	public String numProces = "";
	public String orgaoJudic = "";
	public String relator="";
	public Advogado advogado;
	
	public Boolean associarAdvogado(Advogado advog) {
		this.advogado = advog;
		return true;
	}
	
	public Boolean dissociarAdvogado(Advogado advog) {
		// fazer logica.
		return true;
	}

	@Override
	public String toString() {
		return "Processo [numProces=" + numProces + ", orgaoJudic=" + orgaoJudic + ", relator=" + relator
				+ ", advogado=" + advogado + "]";
	}

	

}
