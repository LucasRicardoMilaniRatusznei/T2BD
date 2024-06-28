import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection conexao;

    public AlunoDAO() throws SQLException {
        conexao = ConexaoBD.getConnection();
    }

    public void fechar() throws SQLException {
        ConexaoBD.fecharConexao();
    }

    public void criarAluno(Aluno aluno) throws SQLException {
        String sql = "INSERT INTO aluno (id_aluno, nome) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, aluno.getIdAluno());
            stmt.setString(2, aluno.getNome());
            stmt.executeUpdate();
        }
    }

    public Aluno buscarAlunoPorId(int idAluno) throws SQLException {
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return new Aluno(resultado.getInt("id_aluno"),
                        resultado.getString("nome"));
            }
            return null;
        }
    }

    public List<Aluno> buscarTodosAlunos() throws SQLException {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno ORDER BY id_aluno";
        try (Statement stmt = conexao.createStatement();
             ResultSet resultado = stmt.executeQuery(sql)) {
            while (resultado.next()) {
                alunos.add(new Aluno(resultado.getInt("id_aluno"),
                        resultado.getString("nome")));
            }
        }
        return alunos;
    }

    public void atualizarAluno(Aluno aluno) throws SQLException {
        String sql = "UPDATE aluno SET nome = ? WHERE id_aluno = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getIdAluno());
            stmt.executeUpdate();
        }
    }

    public void deletarAluno(int idAluno) throws SQLException {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAluno);
            stmt.executeUpdate();
        }
    }
}