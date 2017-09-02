/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc;

import desafioinfisc.Entities.Atendimento;
import desafioinfisc.Entities.Cliente;
import desafioinfisc.Service.AtendimentoService;
import desafioinfisc.Service.ClienteService;
import desafioinfisc.Service.FuncionarioService;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Arthur
 */
public class DesafioInfisc {
    
    public static void main(String[] args) {
        /* // Teste Funcionarios
        FuncionarioService funcionarioService = new FuncionarioService(getFileLines());
        List<Funcionario> funcionarios = funcionarioService.getFuncionarios();
       
        funcionarios.forEach(System.out::println);
        System.out.println("QUANTIDADE DE FUNCONARIOS: " + funcionarioService.getQuantidadeFuncionarios());
        System.out.println("FUNCIONARIO PESQUISADO: " + funcionarioService.findFuncionarioById(1));
        
        // Teste Clientes*/
        ClienteService clienteService = new ClienteService(getFileLines());
        /*List<Cliente> clientes = clienteService.getClientes();
        
        clientes.forEach(System.out::println);
        System.out.println("QUANTIDADE DE CLIENTES: " + clienteService.getQuantidadeFuncionarios());
        System.out.println("CLIENTE PESQUISADO: " + clienteService.findClienteById(1));
        */
        
        AtendimentoService atendimentoService = new AtendimentoService(getFileLines());
        List<Atendimento> atendimentos = atendimentoService.getAtendimentos();
        atendimentos.forEach(System.out::println);
        System.out.println("TOTAL HORAS ATENDIMENTO: " + atendimentoService.getTotalHorasAtendimento() + " hrs");
        
        Cliente cliente = clienteService.findClienteById(1);
        System.out.println("CLIENTE: " + cliente);
        System.out.println("TOTAL DE SUAS HORAS: " + atendimentoService.getTotalHorasRealizadasByCliente(cliente));
    } 
    
    public static List<String> getFileLines(){
        List<String> lines = new ArrayList<>();    
        try{
            Stream<String> stream = Files.lines(Paths.get("C:/Users/Arthur/Downloads/dados.txt"));
            stream.forEach(line -> lines.add(line));
        } catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }
    
    
    
}
