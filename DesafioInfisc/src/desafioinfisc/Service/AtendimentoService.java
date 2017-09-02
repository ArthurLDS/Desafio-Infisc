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
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
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
    
    // ---- Private methods ----
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
