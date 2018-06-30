/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Pessoa;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class PessoaDAO {
    public long cadastrarPessoa(Pessoa p) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;
   
        try {
            String sql = "";

            sql = "insert into pessoa(codPessoa,nome, CPF, RG, dataNasc)"
                    + "values (codPessoa.nextval,?,?,?,?)";

            String generatedColumns[] = { "codPessoa" };
            pstmt = con.prepareStatement(sql, generatedColumns);

            pstmt.setString(1,p.getNome());
            pstmt.setLong(2,p.getCPF());
            pstmt.setInt(3, p.getRG());
            pstmt.setDate(4,p.getDataNasc());
            
            pstmt.execute();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            int chaveGerada=0;
            
            while(rs.next()) {
                chaveGerada = rs.getInt(1);
                System.out.println("id gerado: " + chaveGerada);   
            }
            return chaveGerada;

        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar Pessoa!" + se.getMessage());
        } finally {
            con.close();
            pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Pessoa> buscarPessoa() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Pessoa> pessoa = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from pessoa ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                
                Pessoa p = new Pessoa() {};
                
                p.setNome(rs.getString("nome"));
                p.setCPF(rs.getLong("CPF"));
                p.setRG(rs.getInt("RG"));
                p.setDataNasc(rs.getDate("dataNasc"));
                p.setCodPessoa(rs.getInt("codPessoa"));
                pessoa.add(p);
            }

            return pessoa;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarPessoa (int CPF) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from pessoa where CPF="+CPF;

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Pessoa! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
    
    public void alterarPessoa(Pessoa p) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            
            sql = "update pessoa set"
                 +" nome='"+p.getNome()+"', CPF="+p.getCPF()+", RG="+p.getRG()+", dataNasc="+p.getDataNasc()
                 +" where CPF"+p.getCPF();

            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar Pessoa!"+e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }    
}
