import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {
    private Connection conexao;

    public NotaDAO() throws SQLException {
        conexao = ConexaoBD.getConnection();
    }

    public void fechar() throws SQLException {
        ConexaoBD.fecharConexao();
    }

    public void criarNota(Nota nota) throws SQLException {
        String sql = "INSERT INTO nota (id_nota, nota, id_aluno) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, nota.getIdNota());
            stmt.setDouble(2, nota.getNota());
            stmt.setInt(3, nota.getIdAluno());
            stmt.executeUpdate();
        }
    }

    public Nota buscarNotaPorId(int idNota) throws SQLException {
        String sql = "SELECT * FROM nota WHERE id_nota = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idNota);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                return new Nota(resultado.getInt("id_nota"),
                        resultado.getDouble("nota"),
                        resultado.getInt("id_aluno"));
            }
            return null;
        }
    }

    public List<Nota> buscarTodasNotas() throws SQLException {
        List<Nota> notas = new ArrayList<>();
        String sql = "SELECT * FROM nota ORDER BY id_nota";
        try (Statement stmt = conexao.createStatement();
             ResultSet resultado = stmt.executeQuery(sql)) {
            while (resultado.next()) {
                notas.add(new Nota(resultado.getInt("id_nota"),
                        resultado.getDouble("nota"),
                        resultado.getInt("id_aluno")));
            }
        }
        return notas;
    }

    public void atualizarNota(Nota nota) throws SQLException {
        String sql = "UPDATE nota SET nota = ?, id_aluno = ? WHERE id_nota = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, nota.getNota());
            stmt.setInt(2, nota.getIdAluno());
            stmt.setInt(3, nota.getIdNota());
            stmt.executeUpdate();
        }
    }

    public void deletarNota(int idNota) throws SQLException {
        String sql = "DELETE FROM nota WHERE id_nota = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idNota);
            stmt.executeUpdate();
        }
    }
}
