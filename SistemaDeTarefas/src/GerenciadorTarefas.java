import java.util.ArrayList;
import java.util.List;


public class GerenciadorTarefas {

    private final List<Tarefa> tarefas;

    public GerenciadorTarefas() {
        tarefas = new ArrayList<>();

    }

    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        System.out.println("Tarefa adicionada com sucesso");
    }
    
    public void listarTarefas() {
        if (tarefas.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, "Nenhuma tarefa cadastrada.");
        } else {
            StringBuilder lista = new StringBuilder("Tarefas cadastradas:\n");
            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa = tarefas.get(i);
                lista.append((i + 1)).append(". ")
                    .append(tarefa.getDescricao());
                    if (tarefa.isConcluida()) {
                        lista.append("(Concluída)");
                    }
                    lista.append("\n");
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
            javax.swing.JOptionPane.showMessageDialog(null, "Tarefa removida com sucesso.");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa removida.");
        }
       } catch (IndexOutOfBoundsException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa removida.");
       }
    }

    public void editarTarefa(int indice, String novaDescricao) {
    int idx = indice - 1;
    if (idx >= 0 && idx < tarefas.size()) {
        Tarefa tarefa = tarefas.get(idx);
        tarefa.setDescricao(novaDescricao);
        javax.swing.JOptionPane.showMessageDialog(null, "Tarefa editada com sucesso.");
    } else {
        javax.swing.JOptionPane.showMessageDialog(null, "Índice inválido. Nenhuma tarefa editada.");
    }
}

}