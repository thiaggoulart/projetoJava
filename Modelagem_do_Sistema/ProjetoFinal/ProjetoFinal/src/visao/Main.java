package visao;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Thiago Goulart
 */
public class Main extends Application {
    private static Stage stage;
    
    private static Scene login;
    private static Scene cadastro;
    private static Scene Midia;
    private static Scene listaFilmes;
    private static Scene listaSeries;
    private static Scene cadastroFilme;   
    private static Scene cadastroSerie;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        
        Parent loginFxml = FXMLLoader.load(getClass().getResource("Login.fxml")); 
        login = new Scene(loginFxml);
        Parent cadastroFxml = FXMLLoader.load(getClass().getResource("Cadastro.fxml"));   
        cadastro = new Scene(cadastroFxml);
        Parent MidiaFxml = FXMLLoader.load(getClass().getResource("Midia.fxml"));   
        Midia = new Scene(MidiaFxml);
        Parent listaFilmesFxml = FXMLLoader.load(getClass().getResource("ListarFilme.fxml"));   
        listaFilmes = new Scene(listaFilmesFxml);
        Parent listaSeriesFxml = FXMLLoader.load(getClass().getResource("ListarSerie.fxml"));   
        listaSeries = new Scene(listaSeriesFxml);
        Parent cadastroFilmeFxml = FXMLLoader.load(getClass().getResource("CadastroFilme.fxml"));   
        cadastroFilme = new Scene(cadastroFilmeFxml);
        Parent cadastroSerieFxml = FXMLLoader.load(getClass().getResource("CadastroSerie.fxml"));
        cadastroSerie = new Scene(cadastroSerieFxml);

        primaryStage.setScene(login);
        primaryStage.setTitle("Locadora");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void mudaTela(int scene){
        switch(scene){
            case 1:
                stage.setScene(login);
            break;
            case 2:
                stage.setScene(cadastro);
            break;
            case 3:
                stage.setScene(Midia);
            break;
            case 4:
                stage.setScene(listaFilmes);
            break;
            case 5:
                stage.setScene(listaSeries);
            break;
            case 6:
                stage.setScene(cadastroFilme);
            break;
            case 7:
                stage.setScene(cadastroSerie);
            break;
        }//switch
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
