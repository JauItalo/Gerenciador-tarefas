
import java.time.LocalDate;

public class Tarefa {
    private String descricao;
    private boolean concluida;
    private LocalDate data;
    private Prioridade prioridade;

    public Tarefa(String descricao, LocalDate data, Prioridade prioridade) {
        this.descricao = descricao;
        this.concluida = false;
        this.data = data;
        this.prioridade = prioridade;
    }

    public void marcarConcluida() {
        this.concluida = true;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isConcluida() {
        return concluida;
    }

    public void setDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Prioridade getPrioridade() {
        return prioridade;
    }
    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}