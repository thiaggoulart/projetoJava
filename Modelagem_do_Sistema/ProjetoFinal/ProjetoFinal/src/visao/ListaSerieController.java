/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.SeriesDAO;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modelo.Series;

/**
 * FXML Controller class
 *
 * @author lehmi
 */
public class ListaSerieController implements Initializable {

    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Series> tableView;
    @FXML
    private TableColumn<Series, Long> codigo;
    @FXML
    private TableColumn<Series, String> nome;
    @FXML
    private TableColumn<Series, String> descricao;
    @FXML
    private TableColumn<Series, Integer> ano;
    @FXML
    private TableColumn<Series, Double> duracao;
    @FXML
    private TableColumn<Series, Integer> numEps;
    @FXML
    private Button btnAlterar;
    @FXML
    private TextField nome1;
    @FXML
    private TextField descricao1;
    @FXML
    private TextField ano1;
    @FXML
    private TextField duracao1;
    @FXML
    private TextField numEps1;

    private ObservableList<Series> series;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            atualizar();
        } catch (SQLException ex) {
            Logger.getLogger(ListaSerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(7);
            }
        });
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            try {
                alterar(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ListaSerieController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
  
        
        btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    atualizar();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaSerieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
           
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    excluir();
                } catch (SQLException ex) {
                    Logger.getLogger(ListaSerieController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        btnVoltar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(3);
            }
        });
    }
    
    public void atualizar() throws SQLException{
        tableView.getItems().clear();
        ArrayList<Series> serie = new ArrayList<>();
        SeriesDAO sd = new SeriesDAO();
        serie = sd.buscarSeries();
        series = FXCollections.observableArrayList(serie);
        
        for (int i = 0; i < series.size(); i++) {
            nome.setCellValueFactory(new PropertyValueFactory<>("nome"));      
            descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));  
            ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
            duracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
            numEps.setCellValueFactory(new PropertyValueFactory<>("numEps"));
            tableView.setItems(series);
        }          
    }
    
    public void excluir() throws SQLException{
        Series serie = tableView.getSelectionModel().getSelectedItem();
        SeriesDAO fd = new SeriesDAO();

        if(serie != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja excluir a Série??");
            alert.setContentText(serie.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get().equals(ButtonType.OK)){
                fd.deletarSerie(serie.getNome());
                tableView.getItems().remove(serie);
                atualizar();
            }
        }
    }    
    
    public void alterar(Series serie) throws SQLException{
        SeriesDAO fd = new SeriesDAO();
        
        if(serie != null){
            nome1.setText(serie.getNome());
            descricao1.setText(serie.getDescricao());
            ano1.setText(String.valueOf(serie.getAno()));
            duracao1.setText(String.valueOf(serie.getDuracao()));
            numEps1.setText(String.valueOf(serie.getNumEps()));            
            
            btnAlterar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    serie.setNome(nome1.getText());
                    serie.setDescricao(descricao1.getText());
                    serie.setAno(Integer.parseInt(ano1.getText()));
                    serie.setDuracao(Double.parseDouble(duracao1.getText()));
                    serie.setNumEps(Integer.parseInt(numEps1.getText()));

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmação");
                    alert.setHeaderText("Deseja alterar a série?");
                    alert.setContentText(serie.getNome());

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get().equals(ButtonType.OK)){ 
                        try {
                            fd.alterarSerie(serie);
                            atualizar();
                            limpar();
                        } catch (SQLException ex) {
                            System.out.println(ex);
                        }
                    }
                }
            });
        }//if    
    }
    
    public void limpar() {
        
    nome1.setText(null);
    descricao1.setText(null);
    ano1.setText(null);
    duracao1.setText(null);
    numEps1.setText(null);
    }    
    
}
