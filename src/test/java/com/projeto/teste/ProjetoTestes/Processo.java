/**
 * 
 */
package com.projeto.teste.ProjetoTestes;

/**
 * @author Raimar Silva de Lima
 *
 */
final class Processo {
	private String numProces = "";
	private String orgaoJudic = "";
	private String relator="";
	private Advogado advogado;
	
	protected Processo(String numProces, String orgaoJudic, String relator, Advogado advogado) {
		super();
		this.numProces = numProces;
		this.orgaoJudic = orgaoJudic;
		this.relator = relator;
	}
	
	protected Boolean associarAdvogado(Advogado advog) {
		this.advogado = advog;
		return true;
	}
	
	protected Boolean dissociarAdvogado(Advogado advog) {
		// fazer logica.
		return true;
	}

	protected String getNumProces() {
		return numProces;
	}

	protected void setNumProces(String numProces) {
		this.numProces = numProces;
	}

	protected String getOrgaoJudic() {
		return orgaoJudic;
	}

	protected void setOrgaoJudic(String orgaoJudic) {
		this.orgaoJudic = orgaoJudic;
	}

	protected String getRelator() {
		return relator;
	}

	protected void setRelator(String relator) {
		this.relator = relator;
	}

	protected Advogado getAdvogado() {
		return advogado;
	}

	protected void setAdvogado(Advogado advogado) {
		this.advogado = advogado;
	}

	@Override
	public String toString() {
		return "Processo [numProces=" + numProces + ", orgaoJudic=" + orgaoJudic + ", relator=" + relator
				+ ", advogado=" + advogado + "]";
	}

	

}
