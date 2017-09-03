/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafioinfisc.Entities;

/**
 * Classe para objetos do tipo <strong>Funcionário</strong>, onde serão contidos, valores e métodos para o mesmo.
 * 
 * @author Arthur
 */
public class Funcionario {
    
    public Funcionario(){
    }
    
    public Funcionario(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Funcionario(String[] propriedades) {
        this.id = Long.parseLong(propriedades[1]);
        this.nome = propriedades[2];
    }
    
    private long id;
    
    private String nome;
    
    private int totalHorasAtendimento;
    
    private int numeroAtendimentos;
    
    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getTotalHorasAtendimento() {
        return totalHorasAtendimento;
    }

    public int getNumeroAtendimentos() {
        return numeroAtendimentos;
    }

    public void setNumeroAtendimentos(int numeroAtendimentos) {
        this.numeroAtendimentos = numeroAtendimentos;
    }
    
    public void setTotalHorasAtendimento(int totalHorasAtendimento) {
        this.totalHorasAtendimento = totalHorasAtendimento;
    }
    
    @Override
    public String toString(){
        return "ID: " + this.id + " | Nome: " + this.nome + " | Total horas de atendimento: " + this.getTotalHorasAtendimento() + " hrs | Numero Atendimentos: " + this.getNumeroAtendimentos();
    }
}
