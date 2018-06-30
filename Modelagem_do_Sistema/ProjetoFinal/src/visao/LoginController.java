/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.UsuarioDAO;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Usuario;
import persistencia.ConexaoBanco;

/**
 * FXML Controller class
 *
 * @author Thiago Goulart
 */
public class LoginController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField senha;
    @FXML
    private Button btnLogar;
    @FXML
    private Button btnCadastrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnLogar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    verificacao();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
        });
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(2);
            }
        });   // TODO
    }    
    public void verificacao() throws SQLException{
        ConexaoBanco c = new ConexaoBanco();
        Connection con = c.getConexao();
        PreparedStatement pstmt = null;
        
        String login = email.getText();
        String password = senha.getText();
 
        UsuarioDAO conexao = new UsuarioDAO();
        
        boolean resposta =  conexao.consultar(login, password);
 
        if (resposta == true){
            Main.mudaTela(3);
        }else {
            System.out.println("login não realizado!\n Favor conferir o email e senha digitado!");
        }
    }
}
