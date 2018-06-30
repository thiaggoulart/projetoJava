/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao;

import dao.CartaoDAO;
import dao.EnderecoDAO;
import dao.PessoaDAO;
import dao.TelefoneDAO;
import dao.UsuarioDAO;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Cartao;
import modelo.Endereco;
import modelo.Pessoa;
import modelo.Telefone;
import modelo.Usuario;
/**
 * FXML Controller class
 *
 * @author lehmi
 */
public class CadastroController implements Initializable {

    @FXML
    private TextField telefone;
    @FXML
    private TextField nomeCompleto;
    @FXML
    private TextField CPF;
    @FXML
    private TextField RG;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField email;
    @FXML
    private TextField senha;
    @FXML
    private TextField resenha;
    @FXML
    private TextField numCartao;
    @FXML
    private TextField numSeg;
    @FXML
    private DatePicker dataNascDT;
    @FXML
    private TextField telefone1;
    @FXML
    private TextField rua;
    @FXML
    private TextField Numero;
    @FXML
    private TextField CEP;
    @FXML
    private TextField cidade;
    @FXML
    private TextField bairro;
    @FXML
    private TextField complemento;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
                    
                   
                    
        btnCadastrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    
                cadastrarUsu();
                
                limpar();
                } catch (SQLException ex) {
                    Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
                }
                Main.mudaTela(1);
            }
        });
        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.mudaTela(1);
            }
        });   // TODO
    }    
    
    public void cadastrarTel(Pessoa p) throws SQLException{
        Telefone tel = new Telefone();
      
        tel.setCelular(telefone.getText());
        tel.setFixo(telefone1.getText());
        p.setTelefone(tel);
         
    }
    
    public void cadastrarUsu() throws SQLException{
       
        Pessoa usu = new Usuario();
        UsuarioDAO usuDAO = new UsuarioDAO();
                
        usu.setNome(nomeCompleto.getText());
        usu.setCPF(Long.parseLong(CPF.getText()));
        usu.setRG(Integer.parseInt(RG.getText()));
        LocalDate dt = dataNascDT.getValue();
        usu.setDataNasc(Date.valueOf(dt));
        cadastrarEnd(usu);
        cadastrarTel(usu);
        cadastrarCar(usu);
        PessoaDAO pessoa = new PessoaDAO();
        pessoa.cadastrarPessoa(usu);
        Usuario usu2 = (Usuario) usu;
        usu2.setEmail(email.getText());
        usu2.setSenha(senha.getText());
        usu2.setResenha(resenha.getText());
    
        usuDAO.cadastrarUsuario(usu2);
    }
   
   public void cadastrarCar(Pessoa p) throws SQLException{
    Cartao car = new Cartao();
    
     car.setNumero(Long.parseLong(numCartao.getText()));
     car.setNumSeguranca(Integer.parseInt(numSeg.getText()));

     p.setCartao(car);
   }
   
   public void cadastrarEnd(Pessoa u) throws SQLException{
       Endereco end = new Endereco();
        
        end.setRua(rua.getText());
        end.setNumero(Integer.parseInt(Numero.getText()));
        end.setCEP(Integer.parseInt(telefone1.getText()));
        end.setCidade(cidade.getText());
        end.setBairro(bairro.getText());
        end.setComplemento(complemento.getText());
        u.setEndereco(end);
    }
   
    public void limpar() {
        nomeCompleto.setText(null);
        telefone.setText(null);
        telefone1.setText(null);
        CPF.setText(null);
        RG.setText(null);
        email.setText(null);
        senha.setText(null);
        resenha.setText(null);
        numCartao.setText(null);
        numSeg.setText(null);
    }
}
