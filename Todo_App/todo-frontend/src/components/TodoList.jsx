
function TodoList({ todos, onEdit, onDelete }) {

    if (todos.length === 0) {
        return (
            <ul className="todo-list">
                <li className="state-msg empty">
                    <div className="empty-icon">✓</div>

                    <h3>All caught up!</h3>

                    <p>
                        No workspace items found.
                    </p>
                </li>
            </ul>
        );
    }

    return (
        <ul className="todo-list">

            {todos.map(todo => {

                const statusClass =
                    todo.status === "COMPLETED" ? "badge-completed" : "badge-pending";

                return (
                    <li key={todo.id} className={`todo-item ${todo.status === "COMPLETED" ? "completed-row" : ""}`}>

                        <div className="todo-content">
                            <div className="title-row">

                                <span className="todo-title">
                                    {todo.title}
                                </span>

                                <span className={`status-badge ${statusClass}`}>
                                    {todo.status}
                                </span>

                            </div>

                            <p className="todo-description">
                                {todo.description ||
                                    "No description provided."}
                            </p>
                        </div>


                        <div className="todo-item-actions">
                            <button className="action-btn edit-btn" onClick={() => onEdit(todo)} >
                                Edit
                            </button>

                            <button className="action-btn delete-btn" onClick={() => onDelete(todo.id)} >
                                Delete
                            </button>
                        </div>

                    </li>
                );
            })}

        </ul>
    );
}

export default TodoList;