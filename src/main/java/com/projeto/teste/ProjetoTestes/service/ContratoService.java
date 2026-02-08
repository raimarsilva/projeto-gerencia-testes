package com.projeto.teste.ProjetoTestes.service;

import static com.projeto.teste.ProjetoTestes.utils.DinheiroPorExtenso.paraRealHumano;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.teste.ProjetoTestes.model.Contrato;
import com.projeto.teste.ProjetoTestes.repository.ContratoRepository;

@Service
public class ContratoService {
  private static final DateTimeFormatter FORMATTER =
    DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));

  @Autowired
  ContratoRepository contratoRepository;

  public Contrato salvar(Contrato contrato){
    Contrato contratoProcessado = calculaDerivados(contrato);
    if (contratoProcessado != null) {
      return contratoRepository.save(contratoProcessado);
    }
    return null;
  }

  public List<Contrato> listarTodos() {
    return contratoRepository.findAll();
  }
  
  /* Realiza conversão da data não nula numerica em por extenso antes de salvar no banco. */
  private Contrato calculaDerivados(Contrato contrato) {
    LocalDate dataAssinatura = contrato.getDataAssinatura();
    BigDecimal remuneracao = contrato.getRemuneracao();
    if (contrato.getDataAssinatura() != null) {
        DateTimeFormatter formatter = FORMATTER;
        String dataFormatada = dataAssinatura.format(formatter);
        contrato.setDataAssExtenso(dataFormatada);
        
        contrato.setDataLimite(dataAssinatura.plusYears(2));
    }

    if(contrato.getRemuneracao() != null) {
      String valorExtenso = paraRealHumano(remuneracao);
      contrato.setRemuneracaoExtenso(valorExtenso);
    }

    return contrato;
  }
    
}
