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
        String sql = "INSERT INTO nota (nota, id_aluno) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDouble(1, nota.getNota());
            stmt.setInt(2, nota.getIdAluno());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    System.out.println("ID da nova nota: " + generatedKeys.getInt(1));
                }
            }
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
        if (buscarNotaPorId(nota.getIdNota()) == null) {
            System.out.println("ID da nota não existe.");
            return;
        }
        String sql = "UPDATE nota SET nota = ?, id_aluno = ? WHERE id_nota = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, nota.getNota());
            stmt.setInt(2, nota.getIdAluno());
            stmt.setInt(3, nota.getIdNota());
            stmt.executeUpdate();
        }
    }

    public void deletarNota(int idNota) throws SQLException {
        if (buscarNotaPorId(idNota) == null) {
            System.out.println("ID da nota não existe.");
            return;
        }
        String sql = "DELETE FROM nota WHERE id_nota = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idNota);
            stmt.executeUpdate();
        }
    }
}
