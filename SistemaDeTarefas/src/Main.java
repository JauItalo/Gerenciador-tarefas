import javax.swing.JOptionPane;


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
                    5. Editar Tarefa
                    6. Sair
                    """;

            String input = JOptionPane.showInputDialog(menu);
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                break;
            }
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
                        String dataStr = JOptionPane.showInputDialog("Digite a data da tarefa (DD-MM-AAAA):");
                        try {
                            java.time.LocalDate data = java.time.LocalDate.parse(dataStr);
                            Tarefa tarefa = new Tarefa(descricao, data);
                            gerenciador.adicionarTarefa(tarefa);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Data inválida.");
                        }
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
                case 4 -> {
                    String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa a remover:");
                    if (idxStr != null) {
                        try {
                            int indiceRemover = Integer.parseInt(idxStr);
                            gerenciador.removerTarefa(indiceRemover);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Índice inválido!");
                        }
                    }
                }
                case 5 -> {
                    String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa a editar:");
                    if (idxStr != null) {
                        try {
                            int indiceEditar = Integer.parseInt(idxStr);
                            String novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição:");
                            if (novaDescricao != null && !novaDescricao.isBlank()) {
                                gerenciador.editarTarefa(indiceEditar, novaDescricao);
                            } else {
                                JOptionPane.showMessageDialog(null, "Descrição inválida. Nenhuma tarefa editar.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Índice inválido!");
                        }
                    }
                }
                case 6 -> JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                default ->{
                    if (opcao < 1 || opcao > 6)
                    JOptionPane.showMessageDialog(null, "Opção Inválida. Tente Outra Opção.");
                 }

                }
        }while (true);
    }
}