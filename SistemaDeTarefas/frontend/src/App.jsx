import React, { useState, useEffect } from 'react'
import axios from 'axios';
import TaskList from './components/TaskList';
import TaskForm from './components/TaskForm';

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

  

  return (
    <>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
