/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Aluno
 */
public abstract class Midia {
    protected String nome;
    protected long cod;
    protected String descricao;
    protected int ano;
    protected double duracao;
    protected String genero;

    public Midia() {
    }

    public Midia(String nome, long cod, String descricao, int ano, double duracao, String genero) {
        this.nome = nome;
        this.cod = cod;
        this.descricao = descricao;
        this.ano = ano;
        this.duracao = duracao;
        this.genero = genero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCod() {
        return cod;
    }

    public void setCod(long cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getDuracao() {
        return duracao;
    }

    public void setDuracao(double duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
 
    @Override
    public String toString() {
        return "Midia{" + "\nNome: " + nome + 
                "\nCódigo: " + cod + 
                "\nDescrição: " + descricao + 
                "\nAno: " + ano + 
                "\nDuração: " + duracao + 
                "\nGênero: " + genero + '}';
    }
    
   
}
