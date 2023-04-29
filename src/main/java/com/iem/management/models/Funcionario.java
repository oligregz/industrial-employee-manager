package com.iem.management.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {

  private BigDecimal salario;
  
  private String funcao;

  public Funcionario() {}

  public Funcionario(BigDecimal salario, String funcao) {
    this.salario = salario;
    this.funcao = funcao;
  }

  public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }

  public String getDataNascimentoFormatada() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return getDataNascimento().format(formatter);
  }

  @Override
  public String toString() {
    return "Funcionario [nome= " + nome
        + ", dataNascimento=" + getDataNascimentoFormatada()
        + ", salario=" + salario
        + ", funcao=" + funcao + "]";
  }

  
}
