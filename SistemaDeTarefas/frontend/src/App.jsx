import axios from 'axios';
import { useEffect, useState } from 'react';
import TaskForm from './components/TaskForm';
import TaskList from './components/TaskList';
import FeedbackPopup from './components/FeedbackPopup';
import './App.css';


const API_URL = 'http://localhost:8080/api/tarefas';

function App() {
  const [tasks, setTasks] = useState([]);
  const [editingTask, setEditingTask] = useState(null);
  const [message, setMessage] = useState('');
  const [busca, setBusca] = useState('');
  const [filtroPrioridade, setFiltroPrioridade] = useState('TODAS');
  const [filtroStatus, setFiltroStatus] = useState('TODAS');

  useEffect(() => {
    axios.get(API_URL)
      .then(response => setTasks(response.data))
      .catch(() => alert('Erro ao carregar tarefas'));
  }, []);


  useEffect(() => {
    if (message) {
      const timer = setTimeout(() => setMessage(''), 3000);
      return () => clearTimeout(timer); 
    }
  }, [message]);

  function handleAddTask(data) {
    axios.post(API_URL, { ...data, concluida: false })
      .then(response => {
      setTasks([...tasks, response.data]);
      setMessage('Tarefa adicionada com sucesso!');
  })
  .catch(() => setMessage('Erro ao adicionar tarefa'));
  }

  function handleEditTask(id, data) {
    axios.put(`${API_URL}/${id}`, { ...data, concluida: editingTask.concluida })
      .then(response => {
        setTasks(tasks.map(t => t.id === id ? response.data : t));
        setEditingTask(null);
        setMessage('Tarefa editada com sucesso!');
      })
      .catch(() => setMessage('Erro ao editar tarefa'));
  }

  function handleDeleteTask(id) {
    if (!window.confirm('Tem certeza que deseja excluir esta tarefa?')){
      return;
    }
    axios.delete(`${API_URL}/${id}`)
      .then(() => {
        setTasks(tasks.filter(t => t.id !== id));
        setMessage('Tarefa excluída com sucesso!');
      })
      .catch(() => setMessage('Erro ao excluir a tarefa'));
  }

  function handleToggleTask(id) {
    const task = tasks.find(t => t.id === id);
    axios.put(`${API_URL}/${id}`, { ...task, concluida: !task.concluida })
      .then(response => {
        setTasks(tasks.map(t => t.id === id ? response.data : t));
        setMessage('Status da tarefa atualizado com sucesso!');
      })
      .catch(() => setMessage('Erro ao atualizar status da tarefa'));
  }


  const tarefasFiltradas = tasks.filter(t =>
  (filtroPrioridade === 'TODAS' || t.prioridade === filtroPrioridade) &&
  (filtroStatus === 'TODAS' ||
    (filtroStatus === 'CONCLUIDA' && t.concluida) ||
    (filtroStatus === 'PENDENTE' && !t.concluida)
  ) &&
  (typeof t.descricao === 'string' ? t.descricao.toLowerCase().includes(busca.toLowerCase()) : false)
);

  return (
      <div>
        <h1>Sistema de tarefas</h1>
        {message && (
          <FeedbackPopup message={message} onClose={() => setMessage('')} />
        )}

        <div className='filtros' style={{marginBottom: '20px'}}>
          <input
            type='text'
            placeholder='Buscar por descrição...'
            value={busca}
            onChange={e => setBusca(e.target.value)}
            style={{marginRight: '10px'}}
            />
        


            <select value={filtroPrioridade} onChange={e => setFiltroPrioridade(e.target.value)} style={{marginRight: '10px'}}>
              <option value='TODAS'>Todas prioridades</option>
              <option value='BAIXA'>Baixa</option>
              <option value='MEDIA'>Media</option>
              <option value='ALTA'>Alta</option>
            </select>
            <select value={filtroStatus} onChange={e => setFiltroStatus(e.target.value)}>
              <option value='TODAS'>Todos status</option>
              <option value='CONCLUIDA'>Concluída</option>
              <option value='PENDENTE'>Pendente</option>
            </select>


            <button
              style={{ marginLeft: '10px', height: '40px' }}
              onClick={e => {
                e.preventDefault();
                setBusca(buscaTemp);
           }}
        >
            Buscar
          </button>
        </div>
            
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
