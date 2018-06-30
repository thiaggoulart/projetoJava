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
import modelo.Endereco;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class EnderecoDAO {
    public long cadastrarEndereco(Endereco e) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;

        try {
            String sql = "";
            
            sql = "insert into endereco(codEndereco,rua, numero, CEP, cidade, bairro, complemento)"
                    + "values (codEndereco.nextval,?,?,?,?,?,?)";
             
            String generatedColumns[] = { "codEndereco" };
            pstmt = con.prepareStatement(sql, generatedColumns);

            pstmt.setString(1,e.getRua());
            pstmt.setInt(2,e.getNumero());
            pstmt.setInt(3,e.getCEP());
            pstmt.setString(4, e.getCidade());
            pstmt.setString(5,e.getBairro());
            pstmt.setString(6, e.getComplemento());
            
            pstmt.execute();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            int chaveGerada=0;
            
            while(rs.next()) {
                chaveGerada = rs.getInt(1);
                System.out.println("id gerado: " + chaveGerada);   
            }
            return chaveGerada;

        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar Endereço!" + se.getMessage());
        } finally {
            con.close();
            pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Endereco> buscarEndereco() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Endereco> endereco = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from endereco ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {

                Endereco e = new Endereco();
                
                e.setRua(rs.getString("rua"));
                e.setNumero(rs.getInt("numero"));
                e.setCEP(rs.getInt("CEP"));
                e.setCidade(rs.getString("cidade"));
                e.setBairro(rs.getString("bairro"));
                e.setComplemento(rs.getString("complmento"));
                
                endereco.add(e);
            }

            return endereco;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarEndereco (int CEP) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from endereco where CEP="+CEP;

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Endereco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
    
    public void alterarEndereco(Endereco end) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            
            sql = "update enderecoOO set"
                 +" rua='"+end.getRua()+"', numero="+end.getNumero()+", CEP="+end.getCEP()+", cidade="+end.getCidade()+", bairro="+end.getBairro()+", complemento='"+end.getComplemento()+"'"
                 +" where CEP="+end.getCEP();

            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar Endereco!"+e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }    
}
