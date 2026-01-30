import javax.swing.JOptionPane


public class Main {
    public static void main(String[] args) {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
        int opcao;

        do { 
            String menu = """
                    ==== Sistema de Tarefas ====
                    1. Adicionar Tarefa
                    2. Listar Tarefas
                    3. Marcar tarefa como concluída
                    4. Remover Tarefa
                    5. Sair
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) break;
            try {
                opcao = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção Inválida. Tente Outra Opção.");
                continue;
            }
            switch (opcao) {
                case 1 -> {
                    String descricao = JOptionPane.showInputDialog("Digite a descrição da tarefa:");
                    if (descricao != null && !descricao.isBlank()) {
                        Tarefa tarefa = new Tarefa(descricao);
                        gerenciador.adicionarTarefa(tarefa);
                    } else {
                        JOptionPane.showMessageDialog(null, "Descrição inválida. Tarefa não adicionada.");
                    }
                }
                case 2 -> gerenciador.listarTarefas();

                case 3 -> {
                    String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa concluída:");
                    if (idxStr != null) {
                        try {
                            int indiceConcluida = Integer.parseInt(idxStr);
                            gerenciador.marcarTarefaConcluida(indiceConcluida);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Índice inválido!");
                        }
                    }
                }
                case 4:
                    System.out.println("Digite o índice da tarefa a ser removida:");
                    int indiceRemover = scanner.nextInt();
                    gerenciador.removerTarefa(indiceRemover);
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção Inválida. Tente Outra Opção.");
            }    
        } while (opcao != 5);

        scanner.close();
    }
}
}