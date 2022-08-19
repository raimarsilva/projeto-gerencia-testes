package com.projeto.teste.ProjetoTestes;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.projeto.teste.modelo.*;

class Main {
	
	public List<Processo> processos = new ArrayList<Processo>();
	public List<Advogado> advogados = new ArrayList<Advogado>();
	
	//inicio do CRUD de processos.
	@Test
	public boolean cadastrarProcesso(Processo processo) {
		return processos.add(processo);
	}	
	
	@Test
	public String verProcesso(String idProc) {
		for (Processo p : processos) {
			if (p.idProcesso == idProc) {
				return p.toString();
			}
		}
		return null;
	}
	
	@Test
	public boolean atualizarProcesso(Processo processo, Advogado advogado) {
		Processo proc = new Processo();
		for (Processo p : processos) {
			if (p.idProcesso == processo.idProcesso) {
				proc = p;
			}
			break;
		}
		proc.advogado = advogado;
		return true;
	}
	
	@Test
	public boolean removerProcesso(Processo processo) {
		int i = 0;
		for (Processo p : processos) {
			if (p.idProcesso == processo.idProcesso) {
				i = processos.indexOf(p);
			}
			break;
		}
		processos.remove(i);
		return true;
	}
	//fim do CRUD de processos.
	
	
	//inicio do CRUD de advogados.
	@Test
	public boolean cadastrarAdvogado(Advogado advogado) {
		return advogados.add(advogado);
	}	
	
	@Test
	public String verAdvogado(String idAdvog) {
		for (Advogado a : advogados) {
			if (a.idAdv == idAdvog) {
				return a.toString();
			}
		}
		return null;
	}
	
	@Test
	public boolean atualizarAdvogado(Advogado advogado, String nome) {
		Advogado adv = new Advogado();
		for (Advogado a : advogados) {
			if (a.idAdv == advogado.idAdv) {
				adv = a;
			}
			break;
		}
		adv.nomeAdv = nome;
		return true;
	}
	
	@Test
	public boolean removerAdvogado(Advogado advogado) {
		int i = 0;
		for (Advogado a : advogados) {
			if (a.idAdv == advogado.idAdv) {
				i = advogados.indexOf(a);
			}
			break;
		}
		advogados.remove(i);
		return true;
	}
	//fim do CRUD de advogados.

	public static void main(String[] args) {
		
		//criando um advogado.
		Advogado advog = new Advogado();
		advog.idAdv = "ADV1001";
		advog.nomeAdv = "Renata Medeiros";
		advog.registroOAB = "F59.445";
		
		//criando um processo, sem advogado associado.
		Processo proc = new Processo("PJT1001","F-AIRR-1235-06.2010.5.02.0443","5a Turma","João Sicrano");
		proc.set = ;
		proc.numProces = "F-AIRR-1235-06.2010.5.02.0443";
		proc.orgaoJudic = "5a Turma";
		proc.relator = "João Sicrano";
		
		
		
		//associando advogado criado ao processo criado.
		proc.associarAdvogado(advog);
		
		
		//armazenando o processo na lista de dados.
		
		processos.add(proc);
		System.out.println(processos.toString());

	}
	
	

}
