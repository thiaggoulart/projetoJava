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
public class Cartao {
    long codCartao;
    long numero;
    int numSeguranca;

    public Cartao() {
    }

    public Cartao(long codCartao, long numero, int numSeguranca) {
        this.codCartao = codCartao;
        this.numero = numero;
        this.numSeguranca = numSeguranca;
    }

    public long getCodCartao() {
        return codCartao;
    }

    public void setCodCartao(long codCartao) {
        this.codCartao = codCartao;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public int getNumSeguranca() {
        return numSeguranca;
    }

    public void setNumSeguranca(int numSeguranca) {
        this.numSeguranca = numSeguranca;
    }


    @Override
    public String toString() {
        return "Cartao{" + "\nCódigo do Cartão: " +
                "\nNúmero: " + numero +
               "\nNúmero de Seguranca: " + numSeguranca + '}';
    }
    
    
}
