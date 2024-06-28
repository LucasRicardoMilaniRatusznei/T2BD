public class Nota {
    private int idNota;
    private double nota;
    private int idAluno; // Referência ao id_aluno da tabela aluno

    public Nota(int idNota, double nota, int idAluno) {
        this.idNota = idNota;
        this.nota = nota;
        this.idAluno = idAluno;
    }

    public int getIdNota() {
        return idNota;
    }

    public double getNota() {
        return nota;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
}
