import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AlunoDAO alunoDAO = null;
        NotaDAO notaDAO = null;

        try {
            alunoDAO = new AlunoDAO();
            notaDAO = new NotaDAO();

            int opcao = -1; // Initialize opcao with a default value

            do {
                clearScreen();
                System.out.println("### MENU ###");
                System.out.println("1. Menu Aluno");
                System.out.println("2. Menu Nota");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");
                try {
                    opcao = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline left by nextInt()
                } catch (InputMismatchException e) {
                    System.out.println("Opção inválida. Por favor, insira um número.");
                    scanner.next(); // Consume the invalid input
                    continue;
                }

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
        int opcao = -1; // Initialize opcao with a default value

        do {
            clearScreen();
            System.out.println("### MENU ALUNO ###");
            System.out.println("1. Inserir aluno");
            System.out.println("2. Buscar aluno por ID");
            System.out.println("3. Listar todos os alunos");
            System.out.println("4. Atualizar aluno");
            System.out.println("5. Deletar aluno");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do aluno: ");
                    String nomeAlunoInserir = scanner.nextLine();
                    if (nomeAlunoInserir.length() > 20) {
                        System.out.println("Nome do aluno não pode ter mais que 20 caracteres.");
                        break;
                    }
                    Aluno novoAluno = new Aluno(0, nomeAlunoInserir);
                    alunoDAO.criarAluno(novoAluno);
                    System.out.println("Aluno inserido com sucesso.");
                    break;
                case 2:
                    int idAlunoBuscar = getValidIntInput(scanner, "Digite o ID do aluno: ");
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
                    int idAlunoAtualizar = getValidIntInput(scanner, "Digite o ID do aluno a ser atualizado: ");
                    if (alunoDAO.buscarAlunoPorId(idAlunoAtualizar) == null) {
                        System.out.println("ID do aluno não existe.");
                        break;
                    }
                    System.out.print("Digite o novo nome do aluno: ");
                    String novoNomeAluno = scanner.nextLine();
                    if (novoNomeAluno.length() > 20) {
                        System.out.println("Nome do aluno não pode ter mais que 20 caracteres.");
                        break;
                    }
                    Aluno alunoAtualizado = new Aluno(idAlunoAtualizar, novoNomeAluno);
                    alunoDAO.atualizarAluno(alunoAtualizado);
                    System.out.println("Aluno atualizado com sucesso.");
                    break;
                case 5:
                    int idAlunoDeletar = getValidIntInput(scanner, "Digite o ID do aluno a ser deletado: ");
                    if (alunoDAO.buscarAlunoPorId(idAlunoDeletar) == null) {
                        System.out.println("ID do aluno não existe.");
                        break;
                    }
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
        int opcao = -1; // Initialize opcao with a default value

        do {
            clearScreen();
            System.out.println("### MENU NOTA ###");
            System.out.println("1. Inserir nota");
            System.out.println("2. Buscar nota por ID");
            System.out.println("3. Listar todas as notas");
            System.out.println("4. Atualizar nota");
            System.out.println("5. Deletar nota");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida. Por favor, insira um número.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Digite a nota (format: xx.xx): ");
                    String notaInserirStr = scanner.nextLine();
                    try {
                        double notaInserir = Double.parseDouble(notaInserirStr);
                        if (!notaInserirStr.matches("\\d{2}\\.\\d{2}") || notaInserir < 0.00 || notaInserir > 10.00) {
                            System.out.println("Nota deve ser entre 00.00 e 10.00 e no formato xx.xx.");
                            break;
                        }
                        int idAlunoNota = getValidIntInput(scanner, "Digite o ID do aluno: ");
                        if (alunoDAO.buscarAlunoPorId(idAlunoNota) == null) {
                            System.out.println("ID do aluno não existe.");
                            break;
                        }
                        Nota novaNota = new Nota(0, notaInserir, idAlunoNota); // ID set to 0 for auto-increment
                        notaDAO.criarNota(novaNota);
                        System.out.println("Nota inserida com sucesso.");
                    } catch (NumberFormatException e) {
                        System.out.println("Formato de nota inválido. Deve ser no formato xx.xx.");
                    }
                    break;
                case 2:
                    int idNotaBuscar = getValidIntInput(scanner, "Digite o ID da nota: ");
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
                    int idNotaAtualizar = getValidIntInput(scanner, "Digite o ID da nota a ser atualizada: ");
                    if (notaDAO.buscarNotaPorId(idNotaAtualizar) == null) {
                        System.out.println("ID da nota não existe.");
                        break;
                    }
                    System.out.print("Digite a nova nota (format: xx.xx): ");
                    String notaNovaStr = scanner.nextLine();
                    try {
                        double notaNova = Double.parseDouble(notaNovaStr);
                        if (!notaNovaStr.matches("\\d{2}\\.\\d{2}") || notaNova < 0.00 || notaNova > 10.00) {
                            System.out.println("Nota deve ser entre 00.00 e 10.00 e no formato xx.xx.");
                            break;
                        }
                        int novoIdAlunoNota = getValidIntInput(scanner, "Digite o novo ID do aluno: ");
                        Nota notaAtualizada = new Nota(idNotaAtualizar, notaNova, novoIdAlunoNota);
                        notaDAO.atualizarNota(notaAtualizada);
                        System.out.println("Nota atualizada com sucesso.");
                    } catch (NumberFormatException e) {
                        System.out.println("Formato de nota inválido. Deve ser no formato xx.xx.");
                    }
                    break;
                case 5:
                    int idNotaDeletar = getValidIntInput(scanner, "Digite o ID da nota a ser deletada: ");
                    if (notaDAO.buscarNotaPorId(idNotaDeletar) == null) {
                        System.out.println("ID da nota não existe.");
                        break;
                    }
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

    private static int getValidIntInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine(); // Consume the newline left by nextInt()
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next(); // Consume the invalid input
            }
        }
        return value;
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
