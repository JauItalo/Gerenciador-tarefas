import React from 'react';
import TaskItem from './TaskItem';


function TaskList({ tasks, onEdit, onDelete, onToggle}) {
    if (!tasks.length) {
        return <p>Nenhuma tarefa cadastrada</p>;
    }


    return (
        <div>
            <h2>Lista de Tarefas</h2>
            {tasks.map(task => (
                <TaskItem
                key={task.id}
                task={task}
                onEdit={onEdit}
                onDelete={onDelete}
                onToggle={onToggle}
                />
            ))}
        </div>
    );
}

export default TaskList;