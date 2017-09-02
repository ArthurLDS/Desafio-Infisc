/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Entities;

/**
 *
 * @author Arthur
 */
public class Cliente {

    public Cliente(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Cliente(String[] propriedades) {
        this.id = Long.parseLong(propriedades[1]);
        this.nome = propriedades[2];
    }
    
    private long id;
    
    private String nome;

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString(){
        return "ID: " + this.id + " | Nome: " + this.nome;
    }
}