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
public class Telefone {
    private long codTelefone;
    private String fixo;
    private String celular;

    public Telefone() {
    }

    public Telefone(long codTelefone, String fixo, String celular) {
        this.codTelefone = codTelefone;
        this.fixo = fixo;
        this.celular = celular;
    }

    public long getCodTelefone() {
        return codTelefone;
    }

    public void setCodTelefone(long codTelefone) {
        this.codTelefone = codTelefone;
    }


    public String getFixo() {
        return fixo;
    }

    public void setFixo(String fixo) {
        this.fixo = fixo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Telefone{" + "\nSeu númerio de telefone fixo é: " + fixo + 
               "\nSeu número de tefelefone celular é: " + celular + '}';
    }
    
}
