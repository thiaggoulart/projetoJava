/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.FilmesDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Filmes;

/**
 * FXML Controller class
 *
 * @author lehmi
 */
public class CadastroFilmeController implements Initializable {

    @FXML
    private TextField nome;
    @FXML
    private TextField codigo;
    @FXML
    private TextField ano;
    @FXML
    private TextField duracao;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField numOscar;
    @FXML
    private TextField descricao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                cadastrarFilme();
                limpar();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Main.mudaTela(4);
            }
        });
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(4);
            }
        });   // TODO
    }    
    
    public void cadastrarFilme() throws SQLException{
        Filmes fil = new Filmes();
        FilmesDAO filDAO = new FilmesDAO();
     
        fil.setCod(Long.valueOf(codigo.getText()));
        fil.setNome(nome.getText());
        fil.setAno(Integer.valueOf(ano.getText()));
        fil.setDuracao(Double.valueOf(duracao.getText()));
        fil.setNumOscar(Integer.valueOf(numOscar.getText()));
        fil.setDescricao(descricao.getText());
    
        filDAO.cadastrarFilmes(fil);
    }
    
    public void limpar() {
        codigo.setText(null);
        nome.setText(null);
        ano.setText(null);
        duracao.setText(null);
        numOscar.setText(null);
        descricao.setText(null);
    }
}
