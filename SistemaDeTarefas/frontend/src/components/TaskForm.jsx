import { useState } from "react";


function TaskForm({ onSubmit, initialData}) {
    const [descricao, setDescricao] = useState(initialData ?.descricao || '');
    const [data, setData] = useState(initialData ?.data || '');
    const [prioridade, setPrioridade] = useState(initialData ?.prioridade || 'MEDIA');

    function handleSubmit(e) {
        e.preventDefault();
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