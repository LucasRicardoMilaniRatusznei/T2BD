import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
JDBC é um conjunto de classes e interfaces (API) escritas em Java que fazem o envio de instruções SQL para qualquer
 banco de dados relacional
O driver JDBC fornece a conexão ao banco de dados e implementa o protocolo para transferir a consulta e o resultado
entre cliente e banco de dados.
 */

public class ConexaoBD {
    private static Connection conexao;

    public static Connection getConnection() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            // Estabelece uma nova conexão se nenhuma existe ou está fechada
            conexao = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Treinamento",
                    "guia",
                    "natavalu65");
        }
        return conexao;
    }

    public static void fecharConexao() throws SQLException {
        if (conexao != null && !conexao.isClosed()) {
            conexao.close();
        }
    }
}