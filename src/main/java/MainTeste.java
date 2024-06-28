import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainTeste {
    public static void main(String[] args) throws SQLException {
        Connection conexao;
        conexao = ConexaoBD.getConnection();

        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Statement stmt = conexao.createStatement();
             ResultSet resultado = stmt.executeQuery(sql)) {
            while (resultado.next()) {
                alunos.add(new Aluno(resultado.getInt("id_aluno"),
                        resultado.getString("nome")));
            }
        }
        for (Aluno aluno : alunos) {
            System.out.println("ID: " + aluno.getIdAluno() + ", Nome: " + aluno.getNome());
        }
    }
}
