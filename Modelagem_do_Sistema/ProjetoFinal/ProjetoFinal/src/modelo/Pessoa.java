/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Thiago Goulart
 */
public abstract class Pessoa {
    protected long codPessoa;
    protected String nome;
    protected long CPF;
    protected int RG;
    protected Date dataNasc;
    protected Telefone telefone;
    protected Endereco endereco;
    protected Cartao cartao;

    public Pessoa() {
    }

    public Pessoa(long codPessoa, String nome, long CPF, int RG, Date dataNasc, Telefone telefone, Endereco endereco, Cartao cartao) {
        this.codPessoa = codPessoa;
        this.nome = nome;
        this.CPF = CPF;
        this.RG = RG;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cartao = cartao;
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

  
    public int calculaIdade(){
        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(dataNasc);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);

        if (today.before(dateOfBirth)){

            age--;

        }
        return age;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "\nNome: " + nome + 
                "\nCPF: " + CPF + 
                "\nRG: " + RG +
                "\nTelfone: "+ telefone + 
                "\nIdade: " + calculaIdade() + '}';
    }

    
    
}
