/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.SeriesDAO;
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
import modelo.Series;

/**
 * FXML Controller class
 *
 * @author lehmi
 */
public class CadastroSerieController implements Initializable {

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
    private TextField numEps;
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
                cadastrarSerie();
                limpar();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Main.mudaTela(5);
            }
        });
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(5);
            }
        });   // TODO
    }    
    public void cadastrarSerie() throws SQLException{
        Series ser = new Series();
        SeriesDAO serDAO = new SeriesDAO();
     
        ser.setCod(Long.valueOf(codigo.getText()));
        ser.setNome(nome.getText());
        ser.setAno(Integer.valueOf(ano.getText()));
        ser.setDuracao(Double.valueOf(duracao.getText()));
        ser.setNumEps(Integer.valueOf(numEps.getText()));
        ser.setDescricao(descricao.getText());
    
        serDAO.cadastrarSeries(ser);
    }
    
    public void limpar() {
        codigo.setText(null);
        nome.setText(null);
        ano.setText(null);
        duracao.setText(null);
        numEps.setText(null);
        descricao.setText(null);
    }
}
