package com.iem.management;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.iem.management.models.Funcionario;

public class ManagementApplication {

  static ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

	public static void main(String[] args) {
	  // 3.1
	  insereFuncionariosEmOrdem();
	  // 3.2
	  removeJoao();
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
}
