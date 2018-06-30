/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.FilmesDAO;
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
import modelo.Filmes;

/**
 * FXML Controller class
 *
 * @author Thiago Goulart
 */
public class ListarFilmeController implements Initializable {

    @FXML
    private Button btnAtualizar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVoltar;
    @FXML
    private TableView<Filmes> tableView;
    @FXML
    private TableColumn<Filmes, Long> codigo;
    @FXML
    private TableColumn<Filmes, String> nome;
    @FXML
    private TableColumn<Filmes, String> descricao;
    @FXML
    private TableColumn<Filmes, Integer> ano;
    @FXML
    private TableColumn<Filmes, Double> duracao;
    @FXML
    private TableColumn<Filmes, Integer> numOscar;
    @FXML
    private TableColumn<Filmes, String> genero;
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
    private TextField numOscar1;
    @FXML
    private TextField genero1;

    private ObservableList<Filmes> filmes;



    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     try {
            atualizar();
        } catch (SQLException ex) {
            Logger.getLogger(ListarFilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(6);
            }
        });
        
        tableView.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
            try {
                alterar(newValue);
            } catch (SQLException ex) {
                Logger.getLogger(ListarFilmeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
  
        
        btnAtualizar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    atualizar();
                } catch (SQLException ex) {
                    Logger.getLogger(ListarFilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
           
        btnExcluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    excluir();
                } catch (SQLException ex) {
                    Logger.getLogger(ListarFilmeController.class.getName()).log(Level.SEVERE, null, ex);
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
        ArrayList<Filmes> filme = new ArrayList<>();
        FilmesDAO fd = new FilmesDAO();
        filme = fd.buscarFilmes();
        filmes = FXCollections.observableArrayList(filme);
        
        for (int i = 0; i < filmes.size(); i++) {
            codigo.setCellValueFactory(new PropertyValueFactory<>("cod"));
            nome.setCellValueFactory(new PropertyValueFactory<>("nome"));      
            descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));  
            ano.setCellValueFactory(new PropertyValueFactory<>("ano"));
            duracao.setCellValueFactory(new PropertyValueFactory<>("duracao"));
            numOscar.setCellValueFactory(new PropertyValueFactory<>("numOscar"));
            genero.setCellValueFactory(new PropertyValueFactory<>("genero"));
            tableView.setItems(filmes);
        }          
    }
    
    public void excluir() throws SQLException{
        Filmes filme = tableView.getSelectionModel().getSelectedItem();
        FilmesDAO fd = new FilmesDAO();

        if(filme != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Deseja excluir o filme?");
            alert.setContentText(filme.toString());

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get().equals(ButtonType.OK)){
                fd.deletarFilme(filme.getNome());
                tableView.getItems().remove(filme);
                atualizar();
            }
        }
    }    
    
    public void alterar(Filmes filme) throws SQLException{
        FilmesDAO fd = new FilmesDAO();
        
        if(filme != null){
            nome1.setText(filme.getNome());
            descricao1.setText(filme.getDescricao());
            ano1.setText(String.valueOf(filme.getAno()));
            duracao1.setText(String.valueOf(filme.getDuracao()));
            numOscar1.setText(String.valueOf(filme.getNumOscar()));
            genero1.setText(filme.getGenero());
            
            btnAlterar.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    filme.setNome(nome1.getText());
                    filme.setDescricao(descricao1.getText());
                    filme.setAno(Integer.parseInt(ano1.getText()));
                    filme.setDuracao(Double.parseDouble(duracao1.getText()));
                    filme.setNumOscar(Integer.parseInt(numOscar1.getText()));
                    filme.setGenero(genero1.getText());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmação");
                    alert.setHeaderText("Deseja alterar o filme?");
                    alert.setContentText(filme.getNome());

                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get().equals(ButtonType.OK)){ 
                        try {
                            fd.alterarFilme(filme);
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
    numOscar1.setText(null);
    genero1.setText(null);
    }    
    
}
