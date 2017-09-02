/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Service;

import desafioinfisc.Cliente;
import desafioinfisc.IdentificacaoDaLinha;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Arthur
 */
public class ClienteService {
    
    private List<Cliente> clientes;

    public ClienteService(List<String> fileLines) {
        this.clientes = getAllClientes(fileLines);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public Cliente findClienteById(long id){
        return this.clientes.stream().filter(c -> c.getId() == id).findFirst().get();
    }
    
    public Integer getQuantidadeFuncionarios(){
        return this.clientes.size();
    }
    
    // ---- Private methods ----
    private  List<Cliente> getAllClientes(List<String> fileLines){
        List<Cliente> clientes = new ArrayList<>();
        getClientesString(fileLines).forEach(line -> clientes.add(new Cliente(line.split("\\|"))));
        return clientes;
    }
    
    private List<String> getClientesString(List<String> fileLines){
        List<String> clientesString = fileLines.stream().filter(line -> Character.getNumericValue(line.charAt(0)) == IdentificacaoDaLinha.CLIENTE.getValor())
                .collect(Collectors.toList());
        return clientesString;
    }
}
