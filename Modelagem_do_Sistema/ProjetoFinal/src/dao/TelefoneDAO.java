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
import modelo.Telefone;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class TelefoneDAO {
    public long cadastrarTelefone(Telefone t) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;

        try {
            String sql = "";

            sql = "insert into Telefone(codTelefone,celular, fixo)"
                    + "values (codTelefone.nextval,?,?)";
            
            String generatedColumns[] = { "codTelefone" };
            pstmt = con.prepareStatement(sql, generatedColumns);

            pstmt.setString(1,t.getCelular());
            pstmt.setString(2,t.getFixo());
            
            pstmt.execute();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            int chaveGerada=0;
            
            while(rs.next()) {
                chaveGerada = rs.getInt(1);
                System.out.println("id gerado: " + chaveGerada);   
            }
            return chaveGerada;

        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar Telefone!" + se.getMessage());
        } finally {
            con.close();
            pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Telefone> buscarTelefone() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Telefone> telefone = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from telefone ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                
                Telefone t = new Telefone();
                
                t.setCelular(rs.getString("celular"));
                t.setFixo(rs.getString("fixo"));
                
                telefone.add(t);
            }

            return telefone;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarTelefone (String codTelefone) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from telefone where codtelefone="+codTelefone;

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Telefone! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
    
    public void alterarTelefone(Telefone t) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            
            sql = "update telefone set"
                 +" Celular="+t.getCelular()+", fixo="+t.getFixo()
                 +" where codTelefone="+ t.getCodTelefone();

            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar Telefone!"+e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }    
}
