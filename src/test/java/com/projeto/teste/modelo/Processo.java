/**
 * 
 */
package com.projeto.teste.modelo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Iramar Jetterson Ramon Raimar
 *
 */
@Data
public final class Processo {
	
	private String idProcesso;
	private String numProces;
	private String orgaoJudic;
	private String relator;
	private Advogado advogado;
	
	public Processo(String idProcesso, String numProces, String orgaoJudic, String relator, Advogado advogado) {
		super();
		this.idProcesso = idProcesso;
		this.numProces = numProces;
		this.orgaoJudic = orgaoJudic;
		this.relator = relator;
		this.advogado = advogado;
	}

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
