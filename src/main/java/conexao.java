import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
JDBC é um conjunto de classes e interfaces (API) escritas em Java que fazem o envio de instruções SQL para qualquer
 banco de dados relacional
O driver JDBC fornece a conexão ao banco de dados e implementa o protocolo para transferir a consulta e o resultado
entre cliente e banco de dados.
 */

public class conexao {
    public static void main(String[] args) throws SQLException {
        Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Treinamento",
                "guia", "natavalu65");
        if (conexao != null){
            System.out.println("Conectado com sucesso!");
        } else {
            System.out.println("Conexão falhou!");
        }
    }
}

teste