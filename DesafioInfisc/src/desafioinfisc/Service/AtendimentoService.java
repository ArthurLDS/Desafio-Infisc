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
 *
 * @author Arthur
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

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }
    
    public Integer getQuantidadeAtendimentos(){
        return this.atendimentos.size();
    }
    
    public Integer getTotalHorasAtendimento(){
        return this.atendimentos.stream().mapToInt(a -> a.getDuracaoHoras()).sum();
    }
    
    public Integer getTotalHorasRealizadasByCliente(Cliente cliente){
        return this.atendimentos.stream().filter(a -> a.getCliente().getId() == cliente.getId()).mapToInt(a -> a.getDuracaoHoras()).sum();
    }
    
    public Integer getTotalHorasRealizadasByFuncionario(Funcionario funcionario){
        return this.atendimentos.stream().filter(a -> a.getFuncionario().getId() == funcionario.getId()).mapToInt(a -> a.getDuracaoHoras()).sum();
    }
    
    public Funcionario getFuncionarioComMaiorQntHorasAtendimento(){
        return funcionarioService.getFuncionarios().stream()
                .max((f1, f2) -> Integer.compare(f1.getTotalHorasAtendimento(), f2.getTotalHorasAtendimento())).get();
    }
    
    public Cliente getClienteComMaiorQntHorasAtendimento(){
        return clienteService.getClientes().stream()
                .max((c1, c2) -> Integer.compare(c1.getTotalHorasAtendimento(), c2.getTotalHorasAtendimento())).get();
    }
    
    public List<Funcionario> getFuncionariosOrdenadosPorMaisAtendimentos(){
        return funcionarioService.getFuncionarios().stream()
                .sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
                .sorted((f1, f2) -> Integer.compare(f2.getNumeroAtendimentos(), f1.getNumeroAtendimentos()))
                .collect(Collectors.toList());
    } 
    
    public List<Cliente> getListaClientesComTotalHorasAtendimento(){
        return clienteService.getClientes().stream()
                .sorted((c1, c2) -> Long.compare(c1.getId(), c2.getId())).collect(Collectors.toList());
    }
    
    public List<Funcionario> getListaFuncionariosComTotalHorasAtendimento(){
        return funcionarioService.getFuncionarios();
    }
    
    // ---- Private methods ----
    private void setAllNumeroAtendimentosFuncionarios(){
        funcionarioService.getFuncionarios().stream().forEach(f -> f.setNumeroAtendimentos(
                (int) this.atendimentos.stream().filter(a -> a.getFuncionario().getId() == f.getId()).count()));
    }
    
    private void setAllTotalHorasAtendimentoFuncionarios(){
        funcionarioService.getFuncionarios().stream().forEach(f -> f.setTotalHorasAtendimento(
                this.atendimentos.stream().filter(a -> a.getFuncionario().getId() == f.getId()).mapToInt(a -> a.getDuracaoHoras()).sum()));
    }
    
    private void setAllTotalHorasAtendimentoClientes(){
        clienteService.getClientes().stream().forEach(c -> c.setTotalHorasAtendimento(
                this.atendimentos.stream().filter(a -> a.getCliente().getId() == c.getId()).mapToInt(a -> a.getDuracaoHoras()).sum()));
    }
    
    private List<Atendimento> getAllAtendimentos(List<String> fileLines){
        List<Atendimento> atendimentos = new ArrayList<>();
        getAtendimentosString(fileLines).forEach(line -> 
            atendimentos.add( 
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
    
    private List<String> getAtendimentosString(List<String> fileLines){
        return fileLines.stream().filter(line -> Character.getNumericValue(line.charAt(0)) == IdentificacaoDaLinha.ATENDIMENTO.getValor())
                .collect(Collectors.toList());
    }
    
    
}
