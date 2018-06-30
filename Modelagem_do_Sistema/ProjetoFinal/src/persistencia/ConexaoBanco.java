package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Thiago Goulart
 */
public class ConexaoBanco {
    private String usuario = "ads_bd06";
    private String senha = "ads_bd06";
    private String servidor = "oracle.canoas.ifrs.edu.br";
    private int porta = 1521;

    private Connection conexao = null;

    public ConexaoBanco() {
    }

    public Connection getConexao() throws SQLException {
        if (conexao == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@" + this.servidor + ":" + this.porta + ":XE",
                        this.usuario,
                        this.senha);

            } catch (ClassNotFoundException e) {
                System.out.println("Senhor programador! Importe o pacote do DB antes de chingar o java");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return conexao;
    }
}
