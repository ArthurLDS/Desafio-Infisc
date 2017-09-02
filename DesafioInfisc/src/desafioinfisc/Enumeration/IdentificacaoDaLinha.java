/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Enumerations;

/**
 *
 * @author Arthur
 */
public enum IdentificacaoDaLinha {
    
    FUNCIONARIO(0),
    CLIENTE(1),
    ATENDIMENTO(2);
    
    private int valor;
    
    IdentificacaoDaLinha(int numero){
        valor = numero;
    }

    public int getValor() {
        return valor;
    }
}
