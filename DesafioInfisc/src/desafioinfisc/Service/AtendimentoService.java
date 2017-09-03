/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Service;

import desafioinfisc.Entities.Atendimento;
import desafioinfisc.Entities.Cliente;
import desafioinfisc.Entities.Funcionario;
import desafioinfisc.Enumerations.IdentificacaoDaLinha;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta classe é um "Serviço" do objeto Atendimento, responsável por buscasr os dados 
 * referentes aos atendimentos a partir da leitura de um arquivo. Também é responsável
 * por manipular os dados referentes à mesma.
 *
 * @author Arthur
 *
 * @see FuncionarioService
 * @see ClienteService
 */
public class AtendimentoService {

    private List<Atendimento> atendimentos;

    private FuncionarioService funcionarioService;

    private ClienteService clienteService;

    public AtendimentoService(List<String> fileLines) {
        this.funcionarioService = new FuncionarioService(fileLines);
        this.clienteService = new ClienteService(fileLines);
        this.atendimentos = getAllAtendimentos(fileLines);
        setAllTotalHorasAtendimentoFuncionarios();
        setAllTotalHorasAtendimentoClientes();
        setAllNumeroAtendimentosFuncionarios();
    }
    
    /**
     * Método que retorna todos atendimentos encontrados.
     * @return List 
     */
    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }
    
    /**
     * Método que retorna a quantidade de atendimentos encontrados.
     * @return Integer
     */
    public Integer getQuantidadeAtendimentos() {
        return this.atendimentos.size();
    }
    
    /**
     * Método que retorna numero total de horas de todos atendimentos existentes.
     * @return Integer 
     */
    public Integer getTotalHorasAtendimento() {
        return this.atendimentos.stream().mapToInt(a -> a.getDuracaoHoras()).sum();
    }
    
    /**
     * Método que retorna o total de horas realizadas de atendimento por Cliente.
     * @param cliente
     * @return Integer 
     */
    public Integer getTotalHorasRealizadasByCliente(Cliente cliente) {
        return this.atendimentos.stream().filter(a -> a.getCliente().getId() == cliente.getId()).mapToInt(a -> a.getDuracaoHoras()).sum();
    }
    
    /**
     * Método que retorna o total de horas realizadas de atendimento por Funcionário.
     * @param funcionario
     * @return Integer
     */
    public Integer getTotalHorasRealizadasByFuncionario(Funcionario funcionario) {
        return this.atendimentos.stream().filter(a -> a.getFuncionario().getId() == funcionario.getId()).mapToInt(a -> a.getDuracaoHoras()).sum();
    }
    
    /**
     * Método que retorna o Funcionario que realizou a maior quantidade de horas em atendimento.
     * @return Funcionario
     */
    public Funcionario getFuncionarioComMaiorQntHorasAtendimento() {
        return funcionarioService.getFuncionarios().stream()
                .max((f1, f2) -> Integer.compare(f1.getTotalHorasAtendimento(), f2.getTotalHorasAtendimento())).get();
    }
    
    /**
     * Método que retorna Cliente que consumiu a maior quantidade de hors em atendimento.
     * @return Cliente
     */
    public Cliente getClienteComMaiorQntHorasAtendimento() {
        return clienteService.getClientes().stream()
                .max((c1, c2) -> Integer.compare(c1.getTotalHorasAtendimento(), c2.getTotalHorasAtendimento())).get();
    }
    
    /**
     * Método que retorna de forma ordenada os Funcionarios que mais realizaram atendimentos.
     * @return List
     */
    public List<Funcionario> getFuncionariosOrdenadosPorMaisAtendimentos() {
        return funcionarioService.getFuncionarios().stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .sorted((f1, f2) -> Integer.compare(f2.getNumeroAtendimentos(), f1.getNumeroAtendimentos()))
                .collect(Collectors.toList());
    }
    
    /**
     * Método que retorna os Clientes com a quantidade de horas que eles se ocuparam em atendimento.
     * @return List
     */
    public List<Cliente> getListaClientesComTotalHorasAtendimento() {
        return clienteService.getClientes().stream()
                .sorted((c1, c2) -> Long.compare(c1.getId(), c2.getId())).collect(Collectors.toList());
    }
    
    /**
     * Método que retorna os Funcionários com a quantidade de horas que eles se ocuparam em atendimento.
     * @return List
     */
    public List<Funcionario> getListaFuncionariosComTotalHorasAtendimento() {
        return funcionarioService.getFuncionarios();
    }

    // ---- Private methods ----
    
    /**
     * Método que insere o numero de atendimentos realizados por cada Funcionario existente.
     */
    private void setAllNumeroAtendimentosFuncionarios() {
        funcionarioService.getFuncionarios().stream().forEach(f -> f.setNumeroAtendimentos(
                (int) this.atendimentos.stream().filter(a -> a.getFuncionario().getId() == f.getId()).count()));
    }
    
    /**
     * Método que insere o numero Total de Horas de Atendimentos realizados por cada Funcionário existente.
     */
    private void setAllTotalHorasAtendimentoFuncionarios() {
        funcionarioService.getFuncionarios().stream().forEach(f -> f.setTotalHorasAtendimento(
                this.atendimentos.stream().filter(a -> a.getFuncionario().getId() == f.getId()).mapToInt(a -> a.getDuracaoHoras()).sum()));
    }
    
    /**
     * Método que insere o numero Total de Horas de Atendimentos realizados por cada Cliente existente.
     */
    private void setAllTotalHorasAtendimentoClientes() {
        clienteService.getClientes().stream().forEach(c -> c.setTotalHorasAtendimento(
                this.atendimentos.stream().filter(a -> a.getCliente().getId() == c.getId()).mapToInt(a -> a.getDuracaoHoras()).sum()));
    }
    
    /**
     * Método que retorna todos os Atendimentos (Em formato de objeto Atendimento) existentes, a partir da 
     * leitura da lista das linhas do arquivo que foi recebida.
     * @param fileLines - Lista de linhas do arquivo
     * @return List
     */
    private List<Atendimento> getAllAtendimentos(List<String> fileLines) {
        List<Atendimento> atendimentos = new ArrayList<>();
        getAtendimentosString(fileLines).forEach(line
                -> atendimentos.add(
                        new Atendimento(
                                Long.parseLong(line.split("\\|")[1]),
                                funcionarioService.findFuncionarioById(Long.parseLong(line.split("\\|")[2])),
                                clienteService.findClienteById(Long.parseLong(line.split("\\|")[3])),
                                Integer.parseInt(line.split("\\|")[4])
                        )
                )
        );
        return atendimentos;
    }
    
    /**
     * Método que retorna todos os Atendimentos (Em formato do tipo String) existentes, a partir da 
     * leitura da lista das linhas do arquivo que foi recebida.
     * @param fileLines
     * @return List
     */
    private List<String> getAtendimentosString(List<String> fileLines) {
        return fileLines.stream().filter(line -> Character.getNumericValue(line.charAt(0)) == IdentificacaoDaLinha.ATENDIMENTO.getValor())
                .collect(Collectors.toList());
    }
}
