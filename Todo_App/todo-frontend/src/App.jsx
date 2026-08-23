import { useEffect, useState } from "react";
import TodoForm from "./components/TodoForm";
import TodoList from "./components/TodoList";
import { getTodos, createTodo, updateTodo, deleteTodo } from "./services/api";
import keycloak from "./services/keycloak";


function App() {

  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [editingId, setEditingId] = useState(null);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    status: "PENDING"
  });


  const handleLogout = () => {
    keycloak.logout({
      redirectUri: window.location.origin
    });
  };

  const loadTodos = async () => {

    try {
      setLoading(true);
      setError("");
      const data = await getTodos();
      setTodos(data);

    }
    catch (err) {
      console.error(err);
      setError("Could not fetch tasks. Ensure the Spring Boot service is running.");
    }
    finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadTodos();
  }, []);

  const handleSubmit = async () => {

    try {

      const payload = {
        title: formData.title.trim(),
        description: formData.description.trim(),
        status: formData.status
      };

      if (editingId) {
        await updateTodo(editingId, payload);
      }
      else {
        await createTodo(payload);
      }

      resetForm();
      await loadTodos();
    }
    catch (err) {
      console.error(err);
      alert("Operation failed. Please check the backend.");
    }
  };

  const handleEdit = (todo) => {

    setEditingId(todo.id);

    setFormData({
      title: todo.title || "",
      description: todo.description || "",
      status: todo.status || "PENDING"
    });

  };

  const handleDelete = async (id) => {

    const confirmed =
      window.confirm("Are you sure you want to delete this task?");

    if (!confirmed) {
      return;
    }

    try {
      await deleteTodo(id);
      await loadTodos();

    } catch (err) {
      console.error(err);
      alert("Could not delete the task.");
    }
  };

  const resetForm = () => {

    setEditingId(null);

    setFormData({
      title: "",
      description: "",
      status: "PENDING"
    });

  };

  const taskText =
    todos.length === 1
      ? "1 active task pending"
      : `${todos.length} active tasks pending`;

  return (
    <div className="app-container">

      <header className="app-header">
        <div className="header-content">

          <div>
            <h1>Workspace Tasks</h1>

            <p id="task-counter">
              {loading
                ? "Syncing dashboard tracking status..."
                : taskText}
            </p>
          </div>

          <button onClick={handleLogout} className="btn btn-secondary logout-btn">
            Logout
          </button>

        </div>
      </header>


      <TodoForm
        formData={formData}
        setFormData={setFormData}
        editingId={editingId}
        onSubmit={handleSubmit}
        onCancel={resetForm}
      />

      <main className="todo-card">

        {loading ? (

          <ul className="todo-list">

            <li className="state-msg loading">
              <div className="spinner"></div>
              <span>
                Loading active database tasks...
              </span>
            </li>

          </ul>

        ) : error ? (

          <ul className="todo-list">

            <li className="state-msg error">
              <h3>
                Connection Offline
              </h3>
              <p>
                {error}
              </p>
            </li>

          </ul>

        ) : (

          <TodoList
            todos={todos}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />

        )}

      </main>

    </div>
  );
}

export default App;