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
import modelo.Cartao;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class CartaoDAO {
    public long cadastrarCartao(Cartao car) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;
   
        try {
            String sql = "";

            sql = "insert into cartao(codCartao,numero, numSeguranca)"
                    + "values (codCartao.nextval,?,?)";

            String generatedColumns[] = { "codCartao" };
            pstmt = con.prepareStatement(sql, generatedColumns);
            
            pstmt.setLong(1,car.getNumero());
            pstmt.setInt(2,car.getNumSeguranca());
            
            pstmt.execute();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            int chaveGerada=0;
            
            while(rs.next()) {
                chaveGerada = rs.getInt(1);
                System.out.println("id gerado: " + chaveGerada);   
            }
            return chaveGerada;

        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar Cartao!" + se.getMessage());
        } finally {
            con.close();
            pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Cartao> buscarCartao() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Cartao> cartao = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from cartao ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                
                Cartao car = new Cartao();
                
                car.setNumero(rs.getLong("numero"));
                car.setNumSeguranca(rs.getInt("numSeguranca"));
                
                cartao.add(car);
            }

            return cartao;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarCartap (int numSeguranca) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from cartao where numSeguranca="+numSeguranca;

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Cartao! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
        
}
