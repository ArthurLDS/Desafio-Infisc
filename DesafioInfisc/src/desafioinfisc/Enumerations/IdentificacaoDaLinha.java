/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Enumerations;

/**
 * Enum usada para identificar o tipo de dado lido do arquivo.
 * Ex: Se na linha constar : "0|1|Arthur" isso quer dizer que 0 identifica um Funcionario.
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
