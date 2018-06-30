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
public class Filmes extends Midia{
    private int numOscar;

    public Filmes() {
    }

    public Filmes(String nome, long cod, String descricao, int ano, double duracao, int numOscar) {
        this.nome = nome;
        this.cod = cod;
        this.descricao = descricao;
        this.ano = ano;
        this.duracao = duracao;
        this.numOscar = numOscar;
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

    public int getNumOscar() {
        return numOscar;
    }

    public void setNumOscar(int numOscar) {
        this.numOscar = numOscar;
    }

    @Override
    public String toString() {
        return "Filmes{" + "\nNome: " + nome + 
                "\nCódigo: " + cod + 
                "\nDescrição: " + descricao + 
                "\nAno: " + ano + 
                "\nDuração: " + duracao + 
                "\nNúmero de Oscar: " + numOscar + '}';
    }
    
}
