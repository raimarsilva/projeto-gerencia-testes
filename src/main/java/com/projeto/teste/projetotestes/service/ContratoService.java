package com.projeto.teste.projetotestes.service;

import static com.projeto.teste.projetotestes.utils.DinheiroPorExtenso.paraRealHumano;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.teste.projetotestes.model.Contrato;
import com.projeto.teste.projetotestes.repository.ContratoRepository;
import com.projeto.teste.projetotestes.utils.DateFormatterPtBr;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContratoService {

  private final ContratoRepository contratoRepository;

  public ContratoService(ContratoRepository contratoRepository) {
    this.contratoRepository = contratoRepository;
  }

  @SuppressWarnings("null")
  public Contrato salvar(Contrato contrato) {
    if (contrato == null) {
      throw new IllegalArgumentException("Contrato não pode ser nulo");
    }
    log.debug("CONTRATO VINDO DO HTML: {}", contrato.toString());
    Contrato contratoProcessado = calculaDerivados(contrato);
    return contratoRepository.save(contratoProcessado);

  }

  public List<Contrato> listarTodos() {
    return contratoRepository.findAll();
  }

  /*
   * Realiza conversão da data não nula numerica em por extenso antes de salvar no
   * banco.
   */
  private Contrato calculaDerivados(Contrato contrato) {
    if (contrato == null) {
      return null;
    }

    dataPorExtenso(contrato);
    remuneracaoPorExtenso(contrato);

    contrato.setDataLimite(contrato.getDataAssinatura().plusYears(2));

    return contrato;
  }

  private void dataPorExtenso(Contrato c) {
    c.setDataAssExtenso(c.getDataAssinatura().format(DateFormatterPtBr.FORMATTER));
  }

  private void remuneracaoPorExtenso(Contrato c) {
    c.setRemuneracaoExtenso(paraRealHumano(c.getRemuneracao()));
  }

}
