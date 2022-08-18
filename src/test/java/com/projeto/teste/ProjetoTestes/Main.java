package com.projeto.teste.ProjetoTestes;

import java.util.ArrayList;

import com.projeto.teste.modelo.*;

class Main {

	public static void main(String[] args) {
		
		ArrayList<Processo> listaProces = new ArrayList<Processo>();
		
		//criando um processo, sem advogado associado.
		Processo proc = new Processo();
		proc.idProcesso = "PJT1001";
		proc.numProces = "F-AIRR-1235-06.2010.5.02.0443";
		proc.orgaoJudic = "5a Turma";
		proc.relator = "João Sicrano";
		
		//criando um advogado.
		Advogado advog = new Advogado();
		advog.idAdv = "ADV1001";
		advog.nomeAdv = "Renata Medeiros";
		advog.registroOAB = "F59.445";
		
		//associando advogado criado ao processo criado.
		proc.associarAdvogado(advog);
		
		//armazenando o processo na lista de dados.
		listaProces.add(proc);
		

	}

}
