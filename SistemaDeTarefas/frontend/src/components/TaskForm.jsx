import { useState } from "react";


function TaskForm({ onSubmit, initialData}) {
    const [descricao, setDescricao] = useState(initialData ?.descricao || '');
    const [data, setData] = useState(initialData ?.data || '');
    const [prioridade, setPrioridade] = useState(initialData ?.prioridade || 'MEDIA');

    function handleSubmit(e) {
        e.preventDefault();

        if (descricao.trim().length < 3) {
            alert('A descrição deve conter pelo menos 3 caracteres.');
            return;
        }

        const hoje = new Date();
        const dalaSelecionada = new Date(data);
        hoje.setHours(0, 0, 0, 0);
        if (dalaSelecionada < hoje) {
            alert('A data deve ser igual ou posterior a hoje.');
            return;
        }
        onSubmit({
            descricao,
            data,
            prioridade
        });
        setDescricao('');
        setData('');
        setPrioridade('MEDIA');
    }
    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                placeholder="Descrição"
                value={descricao}
                onChange={e => setDescricao(e.target.value)}
                required
            />
            <input
                type="date"
                value={data}
                onChange={e => setData(e.target.value)}
                required
                />
            <select
                value={prioridade}
                onChange={e => setPrioridade(e.target.value)}
                >
                <option value="BAIXA">Baixa</option>
                <option value="MEDIA">Media</option>
                <option value="ALTA">Alta</option>
                </select>
                <button type="submit">Salvar</button>
        </form>
    );
}

export default TaskForm;