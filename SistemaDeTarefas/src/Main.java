import java.time.LocalDate;
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
                    3. Remover Tarefa
                    4. Editar Tarefa
                    5. Pesquisar Tarefa
                    6. Marcar tarefa como concluída
                    7. Desmarcar tarefa como concluída
                    8. Sair
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
                    String[] opcoesPrioridade = {"BAIXA", "MEDIA", "ALTA"};
                   
                    if (descricao != null && !descricao.isBlank()) {
                        String dataStr = JOptionPane.showInputDialog("Digite a data da tarefa (ddMMyyyy):");
                        LocalDate data = null;
                        try {
                            if (dataStr != null && dataStr.matches("\\d{8}")) {
                                int dia = Integer.parseInt(dataStr.substring(0, 2));
                                int mes = Integer.parseInt(dataStr.substring(2, 4));
                                int ano = Integer.parseInt(dataStr.substring(4, 8));
                                data = LocalDate.of(ano, mes, dia);
                            } else {
                                JOptionPane.showMessageDialog(null, "Formato inválido! Use ddMMyyyy.");
                            }
                            String prioridadeStr = (String) JOptionPane.showInputDialog(
                        null,
                        "Escolha a prioridade da tarefa:",
                        "Prioridade",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        opcoesPrioridade,
                        opcoesPrioridade[0]
                    );
                    if (prioridadeStr != null) {
                        Prioridade prioridade = Prioridade.valueOf(prioridadeStr);
                        Tarefa tarefa = new Tarefa(descricao, data, prioridade);
                        gerenciador.adicionarTarefa(tarefa);
                    } else {
                        JOptionPane.showMessageDialog(null, "Prioridade não selecionada.");
                    }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Data inválida.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Descrição inválida. Tarefa não adicionada.");
                    }
                }

                case 2 -> gerenciador.listarTarefasEmTabela();


                case 3 -> {
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
                case 4 -> {
                    String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa a editar:");
                    if (idxStr != null) {
                        try {
                            int indiceEditar = Integer.parseInt(idxStr);
                            String novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição:");
                            if (novaDescricao != null && !novaDescricao.isBlank()) {
                                String dataStr = JOptionPane.showInputDialog("Digite a nova data da tarefa ddMMyyyy:");
                                LocalDate data = null;
                                try {
                                    if (dataStr != null && dataStr.matches("\\d{8}")) {
                                        int dia = Integer.parseInt(dataStr.substring(0, 2));
                                        int mes = Integer.parseInt(dataStr.substring(2, 4));
                                        int ano = Integer.parseInt(dataStr.substring(4, 8));
                                        data = LocalDate.of(ano, mes, dia);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Formato inválido! Use ddMMyyyy.");
                                    }
                                    String [] opcoesPrioridade = {"BAIXA", "MEDIA", "ALTA"};
                                    String prioridadeStr = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Escolha a nova prioridade da tarefa",
                                        "Prioridade",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        opcoesPrioridade,
                                        opcoesPrioridade[0]
                                    );
                                    if (prioridadeStr != null) {
                                        Prioridade novaPrioridade = Prioridade.valueOf(prioridadeStr);
                                        gerenciador.editarTarefa(indiceEditar, novaDescricao, data, novaPrioridade);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Prioridade não selecionada. Nenhuma tarefa editada.");
                                    }
                                    
                                } catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, "Data inválida. Nenhuma tarefa editada.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Descrição inválida. Nenhuma tarefa editar.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Índice inválido!");
                        }
                    }
                }

                case 5 -> {
                    String termo = JOptionPane.showInputDialog("Digite a palavra-chave para pesquisar:");
                    if (termo != null && !termo.isBlank()){
                        gerenciador.pesquisarTarefas(termo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Palavra-chave inválida!");
                    }
                }

                case 6 -> {
                    String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa concluída:");
                    if (idxStr != null) {
                        try {
                            int indice = Integer.parseInt(idxStr);
                            gerenciador.marcarTarefaConcluida(indice);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Índice inválido!");
                        }
                    }
                }
                case 7 -> {
                    String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa a desmarcar como concluída:");
                    if (idxStr != null) {
                        try {
                            int indice = Integer.parseInt(idxStr);
                            gerenciador.desmarcarTarefaConcluida(indice);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Índice inválido!");
                        }
                    }
                }
                case 8 -> JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                default ->{
                    if (opcao < 1 || opcao > 8)
                    JOptionPane.showMessageDialog(null, "Opção Inválida. Tente Outra Opção.");
                 }

                }
        }while (true);
    }
}