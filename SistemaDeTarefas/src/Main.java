
import java.awt.Component;
import java.time.LocalDate;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GerenciadorTarefas gerenciador = new GerenciadorTarefas();
   
        String[] opcoes = {
            "Adcionar Tarefa\n",
            "Listar Tarefas",
            "Remover Tarefa",
            "Editar Tarefa",
            "Pesquisar Tarefas",
            "Marcar Tarefa como Concluída",
            "Desmarcar Tarefa como Concluída",
            "Sair"
        };
            
            while (true) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.add(new JLabel("<html><h2>📝 <span style='color:#1976D2;'>Sistema de Tarefas</span> 📝</h2></html>"));        
                panel.add(Box.createVerticalStrut(10));

                JButton[] botoes = new JButton[opcoes.length];
                for (int i = 0; i < opcoes.length; i++) {
                    botoes[i] = new JButton(opcoes[i]);
                    botoes[i].setAlignmentX(Component.CENTER_ALIGNMENT);
                    panel.add(botoes[i]);
                    panel.add(Box.createVerticalStrut(5));
                }

                JDialog dialog = new JDialog();
                dialog.setTitle("Menu Principal");
                dialog.setModal(true);
                dialog.setContentPane(panel);
                dialog.pack();
                dialog.setLocationRelativeTo(null);

                final int[] escolha = {-1};
                for (int i = 0; i < botoes.length; i++) {
                    int idx = i;
                    botoes[i].addActionListener(evt -> {
                        escolha[0] = idx;
                        dialog.dispose();
                    });
                }

                dialog.setVisible(true);

                // Após fechar o dialog, execute a ação escolhida:
                if (escolha[0] == -1 || escolha[0] == 7) { // Sair ou fechar janela
                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    break;
                }

                switch (escolha[0]) {
                    case 0 -> {
                        String descricao = JOptionPane.showInputDialog(null, "Digite a descrição da tarefa:", "Adicionar Tarefa", JOptionPane.PLAIN_MESSAGE);
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
                            } catch (NumberFormatException | java.time.DateTimeException ex) {
                                JOptionPane.showMessageDialog(null, "Data inválida.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Descrição inválida. Tarefa não adicionada.");
                        }
                    }
                    case 1 -> gerenciador.listarTarefasEmTabela();
                    case 2 -> {
                        String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa a remover:");
                        if (idxStr != null) {
                            try {
                                int indiceRemover = Integer.parseInt(idxStr);
                                gerenciador.removerTarefa(indiceRemover);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Índice inválido!");
                            }
                        }
                    }
                    case 3 -> {
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
                                        String[] opcoesPrioridade = {"BAIXA", "MEDIA", "ALTA"};
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
                                    } catch (NumberFormatException | java.time.DateTimeException ex) {
                                        JOptionPane.showMessageDialog(null, "Data inválida. Nenhuma tarefa editada.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Descrição inválida. Nenhuma tarefa editada.");
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Índice inválido!");
                            }
                        }
                    }
                    case 4 -> {
                        String termo = JOptionPane.showInputDialog("Digite a palavra-chave para pesquisar:");
                        if (termo != null && !termo.isBlank()){
                            gerenciador.pesquisarTarefas(termo);
                        } else {
                            JOptionPane.showMessageDialog(null, "Palavra-chave inválida!");
                        }
                    }
                    case 5 -> {
                        String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa concluída:");
                        if (idxStr != null) {
                            try {
                                int indice = Integer.parseInt(idxStr);
                                gerenciador.marcarTarefaConcluida(indice);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Índice inválido!");
                            }
                        }
                    }
                    case 6 -> {
                        String idxStr = JOptionPane.showInputDialog("Digite o índice da tarefa a desmarcar como concluída:");
                        if (idxStr != null) {
                            try {
                                int indice = Integer.parseInt(idxStr);
                                gerenciador.desmarcarTarefaConcluida(indice);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Índice inválido!");
                            }
                        }
                    }
                }
            }
        }
    }
