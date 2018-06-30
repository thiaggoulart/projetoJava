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
import modelo.Filmes;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class FilmesDAO {
    public void cadastrarFilmes(Filmes f) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;

        try {
            String sql = "";

            sql = "insert into filmes(cod,nome,descricao, ano, duracao, numOscar,genero)"
                    + "values (cod.nextval,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,f.getNome());
            pstmt.setString(2,f.getDescricao());
            pstmt.setInt(3, f.getAno());
            pstmt.setDouble(4,f.getDuracao());
            pstmt.setInt(5, f.getNumOscar());
            pstmt.setString(6, f.getGenero());
            
            pstmt.execute();

        } catch (SQLException se) {
            throw new SQLException("Erro ao cadastrar Filme!" + se.getMessage());
        } finally {
            con.close();
            pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Filmes> buscarFilmes() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Filmes> filmes = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from filmes ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                
                Filmes f = new Filmes();
                
                f.setNome(rs.getString("nome"));
                f.setCod(rs.getLong("cod"));
                f.setDescricao(rs.getString("descricao"));
                f.setAno(rs.getInt("ano"));
                f.setDuracao(rs.getDouble("duracao"));
                f.setNumOscar(rs.getInt("numOscar"));
                f.setGenero(rs.getString("genero"));
                
                filmes.add(f);
            }

            return filmes;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarFilme (String nome) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from filmes where nome='"+nome+"'";

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Filme! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
    
    public void alterarFilme(Filmes f) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            
            sql = "update filmes set "+ 
                   "nome= '"+f.getNome()+"'"+
                   ",descricao='"+f.getDescricao()+"'"+
                   ",ano="+f.getAno()+
                   ",duracao="+f.getDuracao()+
                   ",numOscar="+f.getNumOscar()+
                   ",genero='"+f.getGenero()+"'"+
                   "where cod="+f.getCod();

            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar Filme!"+e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }    
}
