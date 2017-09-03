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
 * Esta classe é um "Serviço" do objeto Funcionario, responsável por buscasr os dados 
 * referentes aos funcionarios a partir da leitura de um arquivo. Também é responsável
 * por manipular os dados referentes ao mesmo. 
 * 
 * @author Arthur
 */
public class FuncionarioService {
    
    private List<Funcionario> funcionarios;
    
    public FuncionarioService(List<String> fileLines){
        this.funcionarios = getAllFuncionarios(fileLines);
    }
    
    /**
     * Método que retorna todos os Funcionários existentes.
     * @return List
     */
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    /**
     * Método que retorna um Funcionário a partir de uma busca pelo seu respectivo Id.
     * @param id
     * @return Funcionario
     */
    public Funcionario findFuncionarioById(long id){
        return funcionarios.stream().filter(f -> f.getId() == id).findFirst().get();
    }
    
    /**
     * Método que retorna a quantidade total de funcionarios existentes.
     * @return Integer
     */
    public Integer getQuantidadeFuncionarios(){
        return this.funcionarios.size();
    }
    
    // ---- Private methods ----
    
    /**
     * Método que retorna todos os Funcionários (Em formato de objeto Funcionario) existentes, a partir da 
     * leitura da lista das linhas do arquivo que foi recebida.
     * @param fileLines
     * @return List
     */
    private List<Funcionario> getAllFuncionarios(List<String> fileLines){
        List<Funcionario> funcionarios = new ArrayList<>();
        getFuncionariosString(fileLines).forEach(line -> funcionarios.add(new Funcionario(line.split("\\|"))));
        return funcionarios;
    }
    
    /**
     * Método que retorna todos os Funcionários (Em formato do tipo String) existentes, a partir da 
     * leitura da lista das linhas do arquivo que foi recebida.
     * @param fileLines
     * @return List
     */
    private List<String> getFuncionariosString(List<String> fileLines){
        return fileLines.stream().filter(line -> Character.getNumericValue(line.charAt(0)) == IdentificacaoDaLinha.FUNCIONARIO.getValor())
                .collect(Collectors.toList());
    }
}