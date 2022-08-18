/**
 * 
 */
package com.projeto.teste.ProjetoTestes;

/**
 * @author Raimar Silva
 *
 */
final class Advogado {
	private int idAdv=0;
	private String nomeAdv="";
	private String registroOAB="";
	private Processo processo;
	
	protected Advogado(int idAdv, String nomeAdv, String registroOAB, Processo processo) {
		super();
		this.idAdv = idAdv;
		this.nomeAdv = nomeAdv;
		this.registroOAB = registroOAB;
		this.processo = processo;
	}
	
	

	protected int getIdAdv() {
		return idAdv;
	}

	protected void setIdAdv(int idAdv) {
		this.idAdv = idAdv;
	}

	protected String getNomeAdv() {
		return nomeAdv;
	}

	protected void setNomeAdv(String nomeAdv) {
		this.nomeAdv = nomeAdv;
	}

	protected String getRegistroOAB() {
		return registroOAB;
	}

	protected void setRegistroOAB(String registroOAB) {
		this.registroOAB = registroOAB;
	}

	protected Processo getProcesso() {
		return processo;
	}

	protected void setProcesso(Processo processo) {
		this.processo = processo;
	}
	
	@Override
	public String toString() {
		return "Advogado [idAdv=" + idAdv + ", nomeAdv=" + nomeAdv + ", registroOAB=" + registroOAB + ", processo="
				+ processo + "]";
	}
	
	

}
