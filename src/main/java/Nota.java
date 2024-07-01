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
        String formattedNota = String.format("%.2f", nota);
        if (!formattedNota.matches("\\d{2}\\.\\d{2}") || nota < 0.00 || nota > 10.00) {
            throw new IllegalArgumentException("Nota deve ser entre 00.00 e 10.00 e no formato xx.xx.");
        }
        this.nota = nota;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
}
