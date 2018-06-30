/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Thiago Goulart
 */
public class Locadora {
    private String nome;
    private long CNPJ;

    public Locadora() {
    }

    public Locadora(String nome, long CNPJ) {
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
    }

    @Override
    public String toString() {
        return "Locadora{" + "\nNome: " + nome + 
                "\nCNPJ: " + CNPJ + '}';
    }
    
}
