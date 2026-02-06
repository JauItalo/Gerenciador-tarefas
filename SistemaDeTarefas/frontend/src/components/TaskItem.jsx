import React from "react";


function TaskItem({ task, onEdit, onDelete, onToggle}) {
    return (
        <div className="task-item">
            <span
                style={{
                    textDecoration: task.concluida ? 'line-through' : 'none',
                    color: task.concluida ? 'gray' : 'black'
                }}

            >
                {task.descricao} | {task.data} | Prioridade: {task.prioridade}
                </span>
                <button onClick={() => onToggle(task.id)}>
                    {task.concluida ? 'Desmarcar' : 'Concluir'}
                </button>
                <button onClick={() => onEdit(task.id)}>Editar</button>
                <button onClick={() => onDelete(task.id)}>Excluir</button>
        </div>
    );
}

export default TaskItem;