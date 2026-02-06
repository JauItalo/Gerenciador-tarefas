import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class GerenciadorTarefas {

    private final List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();

    }

    public void adicionarTarefa(Tarefa novaTarefa) {
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getDescricao().equalsIgnoreCase(novaTarefa.getDescricao())) {
                javax.swing.JOptionPane.showMessageDialog(null, "Tarefa já existe.");
                return;
            }
        }
        tarefas.add(novaTarefa);
        System.out.println("Tarefa adicionada com sucesso");
    }
    
    public void listarTarefasEmTabela() {
        if (tarefas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Nenhuma tarefa cadastrada.");
            return;
        }

        String[] colunas = {"#", "Descrição", "Data", "Prioridade", "Status"};
        Object[][] dados = new Object[tarefas.size()][colunas.length];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = 0; i < tarefas.size(); i++) {
            Tarefa tarefa = tarefas.get(i);
            dados[i][0] = i + 1;
            dados[i][1] = tarefa.getDescricao();
            dados[i][2] = tarefa.getData().format(formatter);
            dados[i][3] = tarefa.getPrioridade();
            dados[i][4] = tarefa.isConcluida() ? "Concluída" : "Pendente";
        }

        JTable tabela = new JTable(dados, colunas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scrollPane = new JScrollPane(tabela);
        tabela.setFillsViewportHeight(true);
        javax.swing.JOptionPane.showMessageDialog(null, scrollPane, "Tarefas em Tabela", JOptionPane.PLAIN_MESSAGE);      
        }

        public void pesquisarTarefas(String termo) {
            StringBuilder resultado = new StringBuilder("Tarefas encontradas:\n");
            boolean encontrou = false;
            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa =  tarefas.get(i);
                if (tarefa.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
                    encontrou = true;
                    resultado.append((i + 1)).append(".")
                    .append(tarefa.getDescricao())
                    .append(" | Data: ").append(tarefa.getData())
                    .append(" | Prioridade: ").append(tarefa.getPrioridade())
                    .append(" | Status: ").append(tarefa.isConcluida() ? "Concluída" : "Pendente")
                    .append("\n");
                }
            }
            if (encontrou) {
                javax.swing.JOptionPane.showMessageDialog(null, resultado.toString());
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "Nenhuma tarefa encontrada.");
            }
        }

    public void marcarTarefaConcluida(int indice) {
       try {
        int idx = indice - 1;
        if (idx >= 0 && idx < tarefas.size()) {
            Tarefa tarefa = tarefas.get(idx);
            tarefa.marcarConcluida();
            javax.swing.JOptionPane.showMessageDialog(null, "Tarefa marcada como concluída com sucesso.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa marcada como concluída.");
        }
    } catch (IndexOutOfBoundsException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa marcada como concluída.");
       }
    }
    
    public void desmarcarTarefaConcluida(int indice) {
        int idx = indice - 1;
        if (idx >= 0 && idx < tarefas.size()) {
            Tarefa tarefa = tarefas.get(idx);
            tarefa.setConcluida(false);
            javax.swing.JOptionPane.showMessageDialog(null, "Tarefa desmarcada com sucesso.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa desmarcada.");
        }
    }

    public void removerTarefa(int indice) {
       try {
        int idx = indice - 1;
        if (idx >= 0 && idx < tarefas.size()) {
            Tarefa removida = tarefas.remove(idx);
            javax.swing.JOptionPane.showMessageDialog(null, "Tarefa removida:" + removida.getDescricao());
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa removida.");
        }
       } catch (IndexOutOfBoundsException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa removida.");
       }
    }

    public void editarTarefa(int indice, String novaDescricao, LocalDate novaData, Prioridade novaPrioridade) {
    int idx = indice - 1;
    if (idx >= 0 && idx < tarefas.size()) {
        Tarefa tarefa = tarefas.get(idx);
        tarefa.setDescricao(novaDescricao);
        tarefa.setData(novaData);
        tarefa.setPrioridade(novaPrioridade);
        javax.swing.JOptionPane.showMessageDialog(null, "Tarefa editada com sucesso.");
    } else {
        javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa editada.");
    }
}

}