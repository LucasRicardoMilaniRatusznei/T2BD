import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
