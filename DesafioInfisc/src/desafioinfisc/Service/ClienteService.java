/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Service;

import desafioinfisc.Entities.Cliente;
import desafioinfisc.Enumerations.IdentificacaoDaLinha;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Esta classe é um "Serviço" do objeto Cliente, responsável por buscasr os dados 
 * referentes aos clientes a partir da leitura de um arquivo. Também é responsável
 * por manipular os dados referentes ao mesmo.
 * 
 * @author Arthur
 * 
 * @see Cliente
 */
public class ClienteService {
    
    private List<Cliente> clientes;

    public ClienteService(List<String> fileLines) {
        this.clientes = getAllClientes(fileLines);
    }
    
    /**
     * Método que retorna todos os clientes existentes.
     * @return List
     */
    public List<Cliente> getClientes() {
        return clientes;
    }
    
    /**
     * Método que retorna um Cliente a partir de uma busca por seu Id.
     * @param id
     * @return Cliente
     */
    public Cliente findClienteById(long id){
        return this.clientes.stream().filter(c -> c.getId() == id).findFirst().get();
    }
    
    /**
     * Método que retorna a quantidade de clientes existentes.
     * @return Integer
     */
    public Integer getQuantidadeClientes(){
        return this.clientes.size();
    }
    
    // ---- Private methods ----
    
    /**
     * Método que retorna todos os Clientes (Em formato de objeto Cliente) existentes, a partir da 
     * leitura da lista das linhas do arquivo que foi recebida.
     * @param fileLines - Lista de linhas do arquivo
     * @return List
     */
    private  List<Cliente> getAllClientes(List<String> fileLines){
        List<Cliente> clientes = new ArrayList<>();
        getClientesString(fileLines).forEach(line -> clientes.add(new Cliente(line.split("\\|"))));
        return clientes;
    }
    
    /**
     * Método que retorna todos os Clientes (Em formato de tipo String) existentes, a partir da 
     * leitura da lista das linhas do arquivo que foi recebida.
     * 
     * @param fileLines - Lista de linhas do arquivo
     * @return List
     */
    private List<String> getClientesString(List<String> fileLines){
        List<String> clientesString = fileLines.stream().filter(line -> Character.getNumericValue(line.charAt(0)) == IdentificacaoDaLinha.CLIENTE.getValor())
                .collect(Collectors.toList());
        return clientesString;
    }
}