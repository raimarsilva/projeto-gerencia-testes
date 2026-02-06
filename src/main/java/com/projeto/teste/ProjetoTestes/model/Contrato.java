package com.projeto.teste.ProjetoTestes.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.projeto.teste.ProjetoTestes.security.CriptoConverter;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_processo")
@Getter
@Setter
public class Contrato {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // ############### dados publicos ###############
  @NotBlank
  @Column(nullable = false)
  private String numProc;
  @NotNull
  @Column(nullable = false)
  private Integer anoContrato;

  @NotBlank
  @Column(nullable = false)
  private String nome;

  @NotBlank
  @Column(nullable = false)
  private String edital;
  @NotBlank
  @Column(nullable = false)
  private String douEdital;
  @NotBlank
  @Column(nullable = false)
  private LocalDate dataDouEdital;

  @NotBlank
  @Column(nullable = false)
  private String assinante;

  // ############### Dados privados do contratado ###############
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String estCivil;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String nacionalidade;

  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String rg;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String emissorRg;

  @Convert(converter = CriptoConverter.class)
  private String passaporte;
  @Convert(converter = CriptoConverter.class)
  private String emissorPassaporte;

  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String cpf;

  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String logradouro;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String endereco;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String enderecoNumero;
  @Convert(converter = CriptoConverter.class)
  private String enderecoCompl;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String enderecoBairro;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String enderecoCidade;
  @NotBlank
  @Column(nullable = false)
  @Convert(converter = CriptoConverter.class)
  private String enderecoEstado;

  // ############### dados contratuais ###############
  @NotBlank
  @Column(nullable = false)
  private String cargo;
  @NotBlank
  @Column(nullable = false)
  private String unidade;
  @NotBlank
  @Column(nullable = false)
  private String area;

  @NotNull
  @Column(nullable = false)
  private Integer cargaHoraria;

  @NotNull
  @Column(nullable = false)
  private BigDecimal remuneracao;

  @NotBlank
  @Column(nullable = false, length = 512)
  private String remuneracaoExtenso;

  @NotBlank
  @Column(nullable = false)
  private String classe;
  @NotBlank
  @Column(nullable = false)
  private String titulacao;

  @NotNull
  @Column(nullable = false)
  private LocalDate dataAssinatura;
  @NotNull
  @Column(nullable = false)
  private LocalDate dataVigencia;
  @NotNull
  @Column(nullable = false)
  private LocalDate dataProrrogacao;

  @NotBlank
  @Column(nullable = false, length = 512)
  private String dataAssExtenso;

  private String contratoNumero;
}
