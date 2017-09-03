/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc;

import desafioinfisc.Entities.Atendimento;
import desafioinfisc.Entities.Cliente;
import desafioinfisc.Entities.Funcionario;
import desafioinfisc.Service.AtendimentoService;
import desafioinfisc.Service.ClienteService;
import desafioinfisc.Service.FuncionarioService;
import static desafioinfisc.Utils.FileUtils.getFileLines;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * @author Arthur
 */
public class DesafioInfisc {
    
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        
        System.out.println("Digite o caminho de seu arquivo: (Ex: 'C:/Users/Arthur/Downloads/dados.txt')");
        String caminho = ler.nextLine();
        List<String> fileLines = getFileLines(caminho);
        
        FuncionarioService funcionarioService = new FuncionarioService(fileLines);
        ClienteService clienteService = new ClienteService(fileLines);
        AtendimentoService atendimentoService = new AtendimentoService(fileLines);
        
        // Exercício A
        System.out.println("QUANTIDADE DE FUNCONARIOS: " + funcionarioService.getQuantidadeFuncionarios());
        
        // Exercício B
        System.out.println("QUANTIDADE DE CLIENTES: " + clienteService.getQuantidadeClientes());
        
        // Exercício C
        System.out.println("QUANTIDADE DE ATENDIMENTOS: " + atendimentoService.getQuantidadeAtendimentos());
        
        // Exercício D
        System.out.println("TOTAL HORAS REALIZADAS DE ATENDIMENTO: " + atendimentoService.getTotalHorasAtendimento() + " hrs");
        
        // Exercicio E
        System.out.println("LISTA DE CLIENTES COM TOTAL DE HORAS REALIZADAS EM ATENDIMENTO: ");
        atendimentoService.getListaClientesComTotalHorasAtendimento().forEach(System.out::println);
        
        // Exercício F
        System.out.println("LISTA DE FUNCIONÁRIOS COM TOTAL DE HORAS REALIZADAS EM ATENDIMENTO: ");
        atendimentoService.getListaFuncionariosComTotalHorasAtendimento().forEach(System.out::println);
        
        // Exercício G
        System.out.println("ID DO FUNCIONARIO COM MAIOR QUANTIDADE DE HORAS DE ATENDIMENTO: " + atendimentoService.getFuncionarioComMaiorQntHorasAtendimento().getId());
        
        // Exercício H
        System.out.println("NOME DO CLIENTE COM MAIOR QUANTIDADE DE HORAS DE ATENDIMENTO: " + atendimentoService.getClienteComMaiorQntHorasAtendimento().getNome());
        
        // Exercício I
        System.out.println("LISTA DE FORMA ORDENADA PELO NOME DOS FUNCIONÁRIOS QUE MAIS REALIZARAM ATENDIMENTOS: ");
        atendimentoService.getFuncionariosOrdenadosPorMaisAtendimentos().forEach(System.out::println);  
    }     
}
