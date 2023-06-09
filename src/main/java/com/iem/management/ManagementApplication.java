package com.iem.management;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.iem.management.models.Funcionario;

public class ManagementApplication {

  static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public static void main(String[] args) {
	  // 3.1
	  insereFuncionariosEmOrdem();
	  // 3.2
	  removeJoao();
	  // 3.3
	  formatarFuncionarios();
	  // 3.4
	  aumentoSalario();
	  // 3.5
	  agrupaPorFuncao();
	  // 3.6
	  funcionariosPorFuncao();
	  // 3.7
	  funcionariosMesDezeDose();
	  // 3.8
	  nomeIdadeFuncionarioMaisVelho();
	  // 3.9
	  funcionarioEmOrdemAlfabetica();
	  // 3.10
	  geraSalarios();
	  // 3.11
	  geraSalariosMinimosPorFuncionario();
	}

	public static ArrayList<Funcionario> insereFuncionariosEmOrdem() {
	  funcionarios
	  .add(new Funcionario("Maria", LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"));
	  funcionarios
	  .add(new Funcionario("João", LocalDate.of(1990,05,12), new BigDecimal("2284.38"), "Operador"));
	  funcionarios
	  .add(new Funcionario("Caio", LocalDate.of(1961,05,02), new BigDecimal("9836.14"), "Coordenador"));
	  funcionarios
	  .add(new Funcionario("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.98"), "Diretor"));
	  funcionarios
	  .add(new Funcionario("Alice", LocalDate.of(1995,01,05), new BigDecimal("2234.68"), "Recepsionista"));
	  funcionarios
	  .add(new Funcionario("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"));
	  funcionarios
	  .add(new Funcionario("Arthur", LocalDate.of(1993,03,31), new BigDecimal("4071.84"), "Contador"));
	  funcionarios
	  .add(new Funcionario("Laura", LocalDate.of(1994,07,8), new BigDecimal("3017.45"), "Gerente"));
	  funcionarios
	  .add(new Funcionario("Heloísa", LocalDate.of(2003,05,24), new BigDecimal("1606.85"),
	      "Eletricista"));
	  funcionarios
	  .add(new Funcionario("Helena", LocalDate.of(1996,9,02), new BigDecimal("2799.93"), "Gerente"));
	  System.out.println("3.1: " + funcionarios);
	  return funcionarios;
	}

	public static List<Funcionario> removeJoao() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	  List<Funcionario> semJoao = funcionarios.stream()
	  .filter(funcionario -> !funcionario.getNome().equals("João"))
	  .collect(Collectors.toList());
	  System.out.println(semJoao);
	  return semJoao;
	}
	   
	public static void formatarFuncionarios() {
      funcionarios.clear();
      insereFuncionariosEmOrdem();

	  NumberFormat numberFormatter = NumberFormat.getNumberInstance();
      numberFormatter.setGroupingUsed(true);
      numberFormatter.setMinimumFractionDigits(2);
      numberFormatter.setMaximumFractionDigits(2);

      funcionarios.stream().forEach(f ->
        System.out.printf("%s - %s - %s - %s%n",
            f.getNome(),
            f.getFuncao(),
            f.getDataNascimentoFormatada(),
            numberFormatter.format(f.getSalario())));
      }

	public static ArrayList<Funcionario> aumentoSalario() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	      
	  funcionarios.stream()
	  .forEach(f -> f.setSalario(
	      ((new BigDecimal("0.10").multiply(f.getSalario())).add(f.getSalario()))
	      ));
	  System.out.println(funcionarios);
	  return funcionarios;
	}

	public static Map<String, List<Funcionario>> agrupaPorFuncao() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	  
	  Map<String, List<Funcionario>> funcionarioPorFuncao = new HashMap<>();
	  
	  for (Funcionario f : funcionarios) {	   
	    
	    if (funcionarioPorFuncao.containsKey(f.getFuncao())) {
	      List<Funcionario> listaFuncionarios = funcionarioPorFuncao
	          .get(f.getFuncao());
	      listaFuncionarios.add(f);

	    } else {
	      List<Funcionario> listaFuncionarios = new ArrayList<>();
	      listaFuncionarios.add(f);
	      funcionarioPorFuncao.put(f.getFuncao(), listaFuncionarios);
	    }
	  }
	  System.out.println(funcionarioPorFuncao);
	  return funcionarioPorFuncao;
	}
	
	public static Map<String, List<Funcionario>> funcionariosPorFuncao() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	  
	  Map<String, List<Funcionario>> funcionarioPorFuncao = agrupaPorFuncao();
	  
	  funcionarioPorFuncao.values().stream()
	  .flatMap(listaFuncao -> listaFuncao.stream())
	  .forEach(f -> System.out.println(f));
	  return funcionarioPorFuncao;
	}
	
	public static List<Funcionario> funcionariosMesDezeDose() {
      funcionarios.clear();
      insereFuncionariosEmOrdem();
      
      List<Funcionario> funcionariosMeses = funcionarios.stream()
      .filter(f -> f.getDataNascimento().getMonthValue() == 10
      || f.getDataNascimento().getMonthValue() == 12)
      .collect(Collectors.toList());
      System.out.println(funcionariosMeses);
      return funcionariosMeses;
	}
	
	public static void nomeIdadeFuncionarioMaisVelho() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	  
	  LocalDate hoje = LocalDate.now();
	  
	  int idadeMaisVelho = funcionarios.stream()
	      .mapToInt(f -> Period.between(f.getDataNascimento(), hoje).getYears())
	      .max().getAsInt();
	  
	   Funcionario maisVelho = funcionarios.stream()
	  .filter(f -> Period.between(f.getDataNascimento(), hoje).getYears() == idadeMaisVelho)
	  .findFirst()
	  .orElse(null);
	  
	  System.out.println("Funcionário mais velho é o "
	  + maisVelho.getNome() + " com "
	      + idadeMaisVelho + " anos de idade.");
	}
	
	public static void funcionarioEmOrdemAlfabetica() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	  
	  List<Funcionario> organizadorDeOrdemAlfabetica = funcionarios.stream()
	  .sorted(Comparator.comparing(Funcionario::getNome))
	  .collect(Collectors.toList());
	  
	  System.out.println(organizadorDeOrdemAlfabetica);
	}
	
	public static void geraSalarios() {
      funcionarios.clear();
      insereFuncionariosEmOrdem();
      
      funcionarios.stream()
      .forEach(f -> System.out.println(f.getSalario()));
	}
	
	public static void geraSalariosMinimosPorFuncionario() {
	  funcionarios.clear();
	  insereFuncionariosEmOrdem();
	  
	  BigDecimal salarioMinimo = new BigDecimal("1212.00");
	  
	  funcionarios.stream()
	  .forEach(f -> System.out.println("Quantidade de salários que " + f.getNome()
	      + " ganha: "
	      + f.getSalario().divide(salarioMinimo, 0, RoundingMode.HALF_DOWN)));
	  
	}
}
