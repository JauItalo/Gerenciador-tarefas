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
            System.out.println("Nenhuma tarefa cadastrada.");
        } else {
            for (int i = 0; i < tarefas.size(); i++) {
                Tarefa tarefa = tarefas.get(i);
                String status = tarefa.isConcluida() ? "Concluída" : "Pendente";
                System.out.println(i + ". " + tarefa.getDescricao() + " - " + status);
            }
        }
    }

    public void marcarTarefaConcluida(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            Tarefa tarefa = tarefas.get(indice);
            tarefa.setConcluida(true);
            System.out.println("Tarefa marcada como concluída com sucesso.");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa marcada como concluída.");
        }
    }

    public void removerTarefa(int indice) {
        if (indice >= 0 && indice < tarefas.size()) {
            tarefas.remove(indice);
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa removida.");
        }
    }

}