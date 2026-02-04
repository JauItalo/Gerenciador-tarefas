import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


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
    
    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Nenhuma tarefa cadastrada.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            StringBuilder lista = new StringBuilder("Tarefas cadastradas:\n\n");
            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa = tarefas.get(i);
                String status = tarefa.isConcluida() ? "✔️ Concluída" : "⏳ Pendente";
                lista.append((i + 1)).append(". ")
                    .append(tarefa.getDescricao())
                    .append("\n     Data:").append(tarefa.getData())
                    .append("\n     Prioridade:").append(tarefa.getPrioridade())
                    .append("\n     Status:").append(status)
                    .append("\n-----------------------------\n");
                }
                javax.swing.JOptionPane.showMessageDialog(null, lista.toString());      
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