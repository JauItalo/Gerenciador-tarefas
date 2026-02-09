import axios from 'axios';
import { useEffect, useState } from 'react';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';

const API_URL = 'http://localhost:8080/api/tarefas';

function App() {
  const [tasks, setTasks] = useState([]);
  const [editingTask, setEditingTask] = useState(null);


  useEffect(() => {
    axios.get(API_URL)
      .then(response => setTasks(response.data))
      .catch(() => alert('Erro ao carregar tarefas'));
  }, []);

  function handleAddTask(data) {
    axios.post(API_URL, { ...data, concluida: false })
      .then(response => setTasks([...tasks, response.data]))
      .catch(() => alert('Erro ao adicionar tarefa'))
  }

  function handleEditTask(id, data) {
    axios.put(`${API_URL}/${id}`, { ...data, concluida: editingTask.concluida })
      .then(response => {
        setTasks(tasks.map(t => t.id === id ? response.data : t));
        setEditingTask(null);
      })
      .catch(() => alert('Erro ao editar tarefa'));
  }

  function handleDeleteTask(id) {
    axios.delete(`${API_URL}/${id}`)
      .then(() => setTasks(tasks.filter(t => t.id !== id)))
      .catch(() => alert('Erro ao excluir a tarefa'));
  }

  function handleToggleTask(id) {
    const task = tasks.find(t => t.id === id);
    axios.put(`${API_URL}/${id}`, { ...task, concluida: !task.concluida })
      .then(response => setTasks(tasks.map(t => t.id === id ? response.data : t)))
      .catch(() => alert('Erro ao atualizar status da tarefa'));
  }

  return (
      <div>
        <h1>Sistema de tarefas</h1>
        <TaskForm
          onSubmit={editingTask
            ? data => handleEditTask(editingTask.id, data)
            : handleAddTask}
          initialData={editingTask}
          />
          <TaskList
            tasks={tasks}
            onEdit={id => setEditingTask(tasks.find(t => t.id === id))}
            onDelete={handleDeleteTask}
            onToggle={handleToggleTask}
          />      
          </div>
  );
}

export default App
