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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe principal da aplicação.
 *
 * @author Arthur
 */
public class DesafioInfisc {

    /**
     * Método principal da aplicação, responsável por executar e exibir a
     * solução de todos os exercícios propostos.
     *
     * @param args
     */
    public static void main(String[] args) {

        String caminho = lerCaminhoArquivo();
        List<String> fileLines = getFileLines(caminho);

        FuncionarioService funcionarioService = new FuncionarioService(fileLines);
        ClienteService clienteService = new ClienteService(fileLines);
        AtendimentoService atendimentoService = new AtendimentoService(fileLines);

        // Exercício a) - Quantidade de funcionários
        System.out.println("- QUANTIDADE DE FUNCONARIOS: " + funcionarioService.getQuantidadeFuncionarios());

        // Exercício b) - Quantidade de clientes
        System.out.println("- QUANTIDADE DE CLIENTES: " + clienteService.getQuantidadeClientes());

        // Exercício c) - Quantidade de atendimentos
        System.out.println("- QUANTIDADE DE ATENDIMENTOS: " + atendimentoService.getQuantidadeAtendimentos());

        // Exercício d) - Total de horas realizadas de atendimento
        System.out.println("- TOTAL HORAS REALIZADAS DE ATENDIMENTO: " + atendimentoService.getTotalHorasAtendimento() + " hrs");

        // Exercicio e) - Total de horas realizadas de atendimento por cliente (listar cada cliente e a quantidade de horas que ele se ocupou em atendimento)
        System.out.println("- LISTA DE CLIENTES COM TOTAL DE HORAS REALIZADAS EM ATENDIMENTO: ");
        atendimentoService.getListaClientesComTotalHorasAtendimento().forEach(System.out::println);

        // Exercício f) - Total de horas realizadas de atendimento por funcionário (listar cada funcionário e a quantidade de horas que ele se ocupou em atendimento);
        System.out.println("- LISTA DE FUNCIONÁRIOS COM TOTAL DE HORAS REALIZADAS EM ATENDIMENTO: ");
        atendimentoService.getListaFuncionariosComTotalHorasAtendimento().forEach(System.out::println);

        // Exercício g) - Qual o ID do funcionário que realizou a maior quantidade de horas em atendimento
        System.out.println("- ID DO FUNCIONARIO COM MAIOR QUANTIDADE DE HORAS DE ATENDIMENTO: " + atendimentoService.getFuncionarioComMaiorQntHorasAtendimento().getId());

        // Exercício h) - Qual o NOME do cliente que consumiu a maior a quantidade de horas em atendimento
        System.out.println("- NOME DO CLIENTE COM MAIOR QUANTIDADE DE HORAS DE ATENDIMENTO: " + atendimentoService.getClienteComMaiorQntHorasAtendimento().getNome());

        // Exercício i) - Liste de forma ordenada o nome dos funcionários que mais realizaram atendimentos.
        System.out.println("- LISTA DE FORMA ORDENADA PELO NOME DOS FUNCIONÁRIOS QUE MAIS REALIZARAM ATENDIMENTOS (NUMERO ATENDIMENTOS): ");
        atendimentoService.getFuncionariosOrdenadosPorMaisAtendimentos().forEach(System.out::println);
    }

    private static String lerCaminhoArquivo() {
        Scanner ler = new Scanner(System.in);
        File file;
        String caminho = "";

        do {
            System.out.println("Digite o caminho de seu arquivo (Exemplo: 'C:/AlgumaPasta/AlgumaPasta/dados.txt'): ");
            caminho = ler.nextLine();
            file = new File(caminho);
            if (!file.exists()) {
                System.out.println("Arquivo inexistente! Tente novamente.");
            }
        } while (!file.exists());

        return caminho;
    }
}
