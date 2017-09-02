/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Service;

import desafioinfisc.Entities.Funcionario;
import desafioinfisc.Enumerations.IdentificacaoDaLinha;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Arthur
 */
public class FuncionarioService {
    
    private List<Funcionario> funcionarios;
    
    public FuncionarioService(List<String> fileLines){
        this.funcionarios = getAllFuncionarios(fileLines);
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public Funcionario findFuncionarioById(long id){
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().get();
    }
    
    public Integer getQuantidadeFuncionarios(){
        return this.funcionarios.size();
    }
    
    // ---- Private methods ----
    private List<Funcionario> getAllFuncionarios(List<String> fileLines){
        List<Funcionario> funcionarios = new ArrayList<>();
        getFuncionariosString(fileLines).forEach(line -> funcionarios.add(new Funcionario(line.split("\\|"))));
        return funcionarios;
    }
    
    private List<String> getFuncionariosString(List<String> fileLines){
        return fileLines.stream().filter(line -> Character.getNumericValue(line.charAt(0)) == IdentificacaoDaLinha.FUNCIONARIO.getValor())
                .collect(Collectors.toList());
    }
}
