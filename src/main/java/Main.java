import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = null;
        NotaDAO notaDAO = null;

        try {
            alunoDAO = new AlunoDAO();
            notaDAO = new NotaDAO();

            int opcao;

            do {
                System.out.println("### MENU ###");
                System.out.println("1. Menu Aluno");
                System.out.println("2. Menu Nota");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()

                switch (opcao) {
                    case 1:
                        menuAluno(scanner, alunoDAO);
                        break;
                    case 2:
                        menuNota(scanner, notaDAO, alunoDAO);
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }

                System.out.println(); // Adiciona uma linha em branco para melhorar a legibilidade
            } while (opcao != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (alunoDAO != null) {
                try {
                    alunoDAO.fechar();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (notaDAO != null) {
                try {
                    notaDAO.fechar();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    private static void menuAluno(Scanner scanner, AlunoDAO alunoDAO) throws SQLException {
        int opcao;

        do {
            System.out.println("### MENU ALUNO ###");
            System.out.println("1. Inserir aluno");
            System.out.println("2. Buscar aluno por ID");
            System.out.println("3. Listar todos os alunos");
            System.out.println("4. Atualizar aluno");
            System.out.println("5. Deletar aluno");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do aluno: ");
                    int idAlunoInserir = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                    System.out.print("Digite o nome do aluno: ");
                    String nomeAlunoInserir = scanner.nextLine();
                    Aluno novoAluno = new Aluno(idAlunoInserir, nomeAlunoInserir);
                    alunoDAO.criarAluno(novoAluno);
                    System.out.println("Aluno inserido com sucesso.");
                    break;
                case 2:
                    System.out.print("Digite o ID do aluno: ");
                    int idAlunoBuscar = scanner.nextInt();
                    Aluno alunoEncontrado = alunoDAO.buscarAlunoPorId(idAlunoBuscar);
                    if (alunoEncontrado != null) {
                        System.out.println("Aluno encontrado:");
                        System.out.println("ID: " + alunoEncontrado.getIdAluno() + ", Nome: " + alunoEncontrado.getNome());
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
                    break;
                case 3:
                    List<Aluno> alunos = alunoDAO.buscarTodosAlunos();
                    System.out.println("Todos os alunos:");
                    for (Aluno aluno : alunos) {
                        System.out.println("ID: " + aluno.getIdAluno() + ", Nome: " + aluno.getNome());
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do aluno a ser atualizado: ");
                    int idAlunoAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                    System.out.print("Digite o novo nome do aluno: ");
                    String novoNomeAluno = scanner.nextLine();
                    Aluno alunoAtualizado = new Aluno(idAlunoAtualizar, novoNomeAluno);
                    alunoDAO.atualizarAluno(alunoAtualizado);
                    System.out.println("Aluno atualizado com sucesso.");
                    break;
                case 5:
                    System.out.print("Digite o ID do aluno a ser deletado: ");
                    int idAlunoDeletar = scanner.nextInt();
                    alunoDAO.deletarAluno(idAlunoDeletar);
                    System.out.println("Aluno deletado com sucesso.");
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            System.out.println(); // Adiciona uma linha em branco para melhorar a legibilidade
        } while (opcao != 0);
    }

    private static void menuNota(Scanner scanner, NotaDAO notaDAO, AlunoDAO alunoDAO) throws SQLException {
        int opcao;

        do {
            System.out.println("### MENU NOTA ###");
            System.out.println("1. Inserir nota");
            System.out.println("2. Buscar nota por ID");
            System.out.println("3. Listar todas as notas");
            System.out.println("4. Atualizar nota");
            System.out.println("5. Deletar nota");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID da nota: ");
                    int idNotaInserir = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                    System.out.print("Digite a nota: ");
                    double notaInserir = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline left by nextDouble()
                    System.out.print("Digite o ID do aluno: ");
                    int idAlunoNota = scanner.nextInt();
                    Nota novaNota = new Nota(idNotaInserir, notaInserir, idAlunoNota);
                    notaDAO.criarNota(novaNota);
                    System.out.println("Nota inserida com sucesso.");
                    break;
                case 2:
                    System.out.print("Digite o ID da nota: ");
                    int idNotaBuscar = scanner.nextInt();
                    Nota notaEncontrada = notaDAO.buscarNotaPorId(idNotaBuscar);
                    if (notaEncontrada != null) {
                        System.out.println("Nota encontrada:");
                        System.out.println("ID: " + notaEncontrada.getIdNota() + ", Nota: " + notaEncontrada.getNota() + ", Aluno ID: " + notaEncontrada.getIdAluno());
                    } else {
                        System.out.println("Nota não encontrada.");
                    }
                    break;
                case 3:
                    List<Nota> notas = notaDAO.buscarTodasNotas();
                    System.out.println("Todas as notas:");
                    for (Nota nota : notas) {
                        System.out.println("ID: " + nota.getIdNota() + ", Nota: " + nota.getNota() + ", Aluno ID: " + nota.getIdAluno());
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID da nota a ser atualizada: ");
                    int idNotaAtualizar = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                    System.out.print("Digite a nova nota: ");
                    double notaNova = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline left by nextDouble()
                    System.out.print("Digite o novo ID do aluno: ");
                    int novoIdAlunoNota = scanner.nextInt();
                    Nota notaAtualizada = new Nota(idNotaAtualizar, notaNova, novoIdAlunoNota);
                    notaDAO.atualizarNota(notaAtualizada);
                    System.out.println("Nota atualizada com sucesso.");
                    break;
                case 5:
                    System.out.print("Digite o ID da nota a ser deletada: ");
                    int idNotaDeletar = scanner.nextInt();
                    notaDAO.deletarNota(idNotaDeletar);
                    System.out.println("Nota deletada com sucesso.");
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }

            System.out.println(); // Adiciona uma linha em branco para melhorar a legibilidade
        } while (opcao != 0);
    }
}
