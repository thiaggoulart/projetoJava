/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Aluno
 */
public class Ator extends Pessoa{
    private int numAtuacao;

    public Ator() {
    }

    public Ator(int numAtuacao) {
        this.numAtuacao = numAtuacao;
    }

    public Ator(int numAtuacao, long codPessoa, String nome, long CPF, int RG, Date dataNasc, Telefone telefone, Endereco endereco, Cartao cartao) {
        super(codPessoa, nome, CPF, RG, dataNasc, telefone, endereco, cartao);
        this.numAtuacao = numAtuacao;
    }

    public int getNumAtuacao() {
        return numAtuacao;
    }

    public void setNumAtuacao(int numAtuacao) {
        this.numAtuacao = numAtuacao;
    }

    public long getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(long codPessoa) {
        this.codPessoa = codPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public int getRG() {
        return RG;
    }

    public void setRG(int RG) {
        this.RG = RG;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    


    @Override
    public String toString() {
        return "Ator{" + "\nNúmero de atuações: " + numAtuacao + '}';
    }
    
    
}
