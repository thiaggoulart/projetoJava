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
import modelo.Usuario;
import persistencia.ConexaoBanco;

/**
 *
 * @author Thiago Goulart
 */
public class UsuarioDAO {
    public void cadastrarUsuario(Usuario u) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;

        try {
            String sql = "";
            
            EnderecoDAO eDAO = new EnderecoDAO();
            long chaveGeradaEndereco = eDAO.cadastrarEndereco(u.getEndereco());
            
            TelefoneDAO tDAO = new TelefoneDAO();
            long chaveGeradaTelefone = tDAO.cadastrarTelefone(u.getTelefone());
            
            CartaoDAO cDAO = new CartaoDAO();
            long chaveGeradaCartao = cDAO.cadastrarCartao(u.getCartao());
            
            PessoaDAO pDAO = new PessoaDAO();
            long chaveGeradaPessoa = pDAO.cadastrarPessoa(u);

            sql = "insert into Usuario(codUsuario,email, senha,codPessoa,codCartao,codTelefone,codEndereco)"
                    + "values (codUsuario.nextval,?,?,?,?,?,?)";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1,u.getEmail());
            pstmt.setString(2,u.getSenha());
            pstmt.setLong(3, chaveGeradaPessoa);
            pstmt.setLong(4, chaveGeradaCartao);
            pstmt.setLong(5, chaveGeradaTelefone);
            pstmt.setLong(6, chaveGeradaEndereco);
            
            pstmt.execute();

        } catch (SQLException se) {
            System.out.println("Erro ao cadastrar Usuario!" + se.getMessage());
        } finally {
            con.close();
            if(pstmt != null)
                pstmt.close();
        }
    }//fecha cadastrar
    
    public ArrayList<Usuario> buscarUsuario() throws SQLException {   
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();
        ArrayList<Usuario> usuario = new ArrayList<>();
        
        try {
            String sql;
            sql = "select * from UsurarioOO ";

            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                
                Usuario u = new Usuario();
                
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                
                usuario.add(u);
            }

            return usuario;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }
    }//fecha buscar
    
    public void deletarUsuario (String email) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            sql = "delete from usuarioOO where email='"+email+"'";

            stat.execute(sql);
        } catch (SQLException se) {
            throw new SQLException("Erro ao deletar Usuario! " + se.getMessage());
        } finally {
            stat.close();
            con.close();
        }//fecha finally
    }//fecha método deletar 
    
    public void alterarUsuario(Usuario u) throws SQLException {
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        Statement stat = con.createStatement();

        try {
            String sql;
            
            sql = "update usurarioOO set"
                 +" email='"+u.getEmail()+"', senha='"+u.getSenha()+"'"
                 +" where email='"+u.getEmail()+"'";

            stat.execute(sql);

        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar Usuario!"+e.getMessage());
        } finally {
            con.close();
            stat.close();
        }
    }    
}
