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
import modelo.Series;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class SeriesDAO {
    public void cadastrarSeries(Series s) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;

        try {
            String sql = "";

            sql = "insert into series(nome, cod, descricao, ano, duracao, numEps)"
                    + "values (?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,s.getNome());
            pstmt.setLong(2,s.getCod());
            pstmt.setString(3,s.getDescricao());
            pstmt.setInt(4, s.getAno());
            pstmt.setDouble(5,s.getDuracao());
            pstmt.setInt(6, s.getNumEps());
            
            pstmt.execute();

        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar Serie!" + se.getMessage());
        } finally {
            con.close();
            pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Series> buscarSeries() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Series> series = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from series ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                
                Series s = new Series();
                
                s.setNome(rs.getString("nome"));
                s.setCod(rs.getLong("cod"));
                s.setDescricao(rs.getString("descricao"));
                s.setAno(rs.getInt("ano"));
                s.setDuracao(rs.getDouble("duracao"));
                s.setNumEps(rs.getInt("numEps"));
                
                series.add(s);
            }

            return series;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarSerie (String nome) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from series where nome='"+nome+"'";

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Serie! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
    
    public void alterarSerie(Series s) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            
            sql = "update serie set"
                 +" nome='"+s.getNome()+"', cod="+s.getCod()+", descricao='"+s.getDescricao()+"', ano="+s.getAno()+", duracao="+s.getDuracao()+", numEps="+s.getNumEps()+""
                 +" where nome='"+s.getNome()+"'";

            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar Serie!"+e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }    
}
