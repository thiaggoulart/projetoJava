/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;

/**
 *
 * @author Thiago Goulart
 */
public class Usuario extends Pessoa{
    private long codUsuario;
    private String email;
    private String senha;
    private String resenha;


    public Usuario() {
    }

    public Usuario(long codUsuario, String email, String senha, String resenha, Endereco endereco, Telefone telefone, Cartao cartao) {
        this.codUsuario = codUsuario;
        this.email = email;
        this.senha = senha;
        this.resenha = resenha;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cartao = cartao;
    }

    public Usuario(long codUsuario, String email, String senha, String resenha) {
        this.codUsuario = codUsuario;
        this.email = email;
        this.senha = senha;
        this.resenha = resenha;
    }

    
    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
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
  
    
    private boolean validaSenha(String senha, String resenha){
        
        if(senha.equals(resenha)){
            return true;
        }
        else{ 
            return false;
        }
    }//VerificaSenha

    @Override
    public String toString() {
        return "Usuario{" + "Email: " + email + 
                "\nSenha: " + senha + 
                "\nResenha: " + resenha + '}';
    }
    
}
