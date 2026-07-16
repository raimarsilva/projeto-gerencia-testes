package com.projeto.teste.projetotestes.service;

import static com.projeto.teste.projetotestes.utils.DinheiroPorExtenso.paraRealHumano;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.projeto.teste.projetotestes.model.Contrato;
import com.projeto.teste.projetotestes.repository.ContratoRepository;

@Service
public class ContratoService {
  private static final Logger logger = LoggerFactory.getLogger(ContratoService.class);
  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy",
      new Locale("pt", "BR"));

  private final ContratoRepository contratoRepository;

  public ContratoService(ContratoRepository contratoRepository) {
    this.contratoRepository = contratoRepository;
  }

  public Contrato salvar(Contrato contrato) {
    if (contrato == null) {
      throw new IllegalArgumentException("Contrato não pode ser nulo");
    }
    logger.debug("CONTRATO VINDO DO HTML: {}", contrato);
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
    LocalDate dataAssinatura = contrato.getDataAssinatura();
    BigDecimal remuneracao = contrato.getRemuneracao();
    if (contrato.getDataAssinatura() != null) {
      DateTimeFormatter formatter = FORMATTER;
      String dataFormatada = dataAssinatura.format(formatter);
      contrato.setDataAssExtenso(dataFormatada);

      contrato.setDataLimite(dataAssinatura.plusYears(2));
    }

    if (contrato.getRemuneracao() != null) {
      String valorExtenso = paraRealHumano(remuneracao);
      contrato.setRemuneracaoExtenso(valorExtenso);
    }

    return contrato;
  }

}
