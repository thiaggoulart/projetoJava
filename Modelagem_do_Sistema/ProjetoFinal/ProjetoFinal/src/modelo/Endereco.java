package modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thiago Goulart
 */
public class Endereco {
    private String rua;
    private int numero;
    private int CEP;
    private String cidade;
    private String bairro;
    private String complemento;
    private long codEndereco;

    public Endereco() {
    }

    public Endereco(String rua, int numero, int CEP, String cidade, String bairro, String complemento, long codEndereco) {
        this.rua = rua;
        this.numero = numero;
        this.CEP = CEP;
        this.cidade = cidade;
        this.bairro = bairro;
        this.complemento = complemento;
        this.codEndereco = codEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCEP() {
        return CEP;
    }

    public void setCEP(int CEP) {
        this.CEP = CEP;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public long getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(long codEndereco) {
        this.codEndereco = codEndereco;
    }

    

    @Override
    public String toString() {
        return "Endereco{" + "\nCódigo do Endereço:" + codEndereco+
                "\nRua: " + rua + 
                "\nNúmero: " + numero + 
                "\nCEP=" + CEP +
                "\nCidade: " + cidade +
                "\nBairro: " + bairro +
                "\nComplemento: " + complemento + '}';
    }

}
