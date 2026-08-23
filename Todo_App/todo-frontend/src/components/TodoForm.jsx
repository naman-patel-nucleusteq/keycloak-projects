
function TodoForm({ formData, setFormData, editingId, onSubmit, onCancel }) {

    const handleChange = (event) => {
        const { name, value } = event.target;

        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (formData.title.trim().length < 3) {
            alert("Title must have at least 3 characters!");
            return;
        }

        onSubmit();
    };


    return (
        <form className="todo-form" onSubmit={handleSubmit}>

            <div className="form-grid">

                <div className="input-block">
                    <label htmlFor="todo-input">
                        Task Title
                    </label>

                    <input
                        id="todo-input"
                        name="title"
                        type="text"
                        value={formData.title}
                        onChange={handleChange}
                        placeholder="What needs to be done? (min 3 chars)"
                        autoComplete="off"
                        required
                    />
                </div>

                <div className="input-block">
                    <label htmlFor="todo-desc">
                        Description
                    </label>

                    <input
                        id="todo-desc"
                        name="description"
                        type="text"
                        value={formData.description}
                        onChange={handleChange}
                        placeholder="Add additional task context or details..."
                        autoComplete="off"
                    />
                </div>

                <div className="input-block">
                    <label htmlFor="todo-status">
                        Status
                    </label>

                    <select
                        id="todo-status"
                        name="status"
                        value={formData.status}
                        onChange={handleChange}
                        className="status-select"
                    >
                        <option value="PENDING">
                            PENDING
                        </option>

                        <option value="COMPLETED">
                            COMPLETED
                        </option>
                    </select>
                </div>

            </div>

            <div className="form-actions">

                {editingId && (
                    <button
                        type="button"
                        className="btn btn-secondary"
                        onClick={onCancel}
                    >
                        Cancel
                    </button>
                )}

                <button
                    type="submit"
                    className="btn btn-primary"
                >
                    {editingId ? "Save Changes" : "Add Task"}
                </button>

            </div>

        </form>
    );
}

export default TodoForm;