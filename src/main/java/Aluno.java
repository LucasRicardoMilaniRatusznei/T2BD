public class Aluno {
    private int idAluno;
    private String nome;

    public Aluno(int idAluno, String nome) {
        this.idAluno = idAluno;
        this.nome = nome;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > 20) {
            throw new IllegalArgumentException("Nome não pode ter mais que 20 caracteres.");
        }
        this.nome = nome;
    }
}
