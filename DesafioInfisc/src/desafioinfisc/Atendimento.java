/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc;

/**
 *
 * @author Arthur
 */
public class Atendimento {
    
    private long id;
    
    private Funcionario funcionario;
    
    private Cliente cliente;
    
    private int duracaoHoras;

    public long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }
    
    
}
