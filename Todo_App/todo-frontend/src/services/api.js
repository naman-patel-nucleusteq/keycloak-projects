import keycloak from "./keycloak";

const API_BASE_URL = "http://localhost:9000/todos";

async function getAuthHeaders() {
    try {
        await keycloak.updateToken(30);

        return {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${keycloak.token}`
        };
    } catch (error) {
        console.error("Failed to refresh Keycloak token:", error);

        await keycloak.login();

        throw error;
    }
}


export async function getTodos() {

    const response = await fetch(API_BASE_URL, {
        method: "GET",
        headers: await getAuthHeaders()
    });

    if (!response.ok) {
        throw new Error(`Failed to fetch todos: ${response.status}`);
    }

    return await response.json();
}


export async function createTodo(todo) {

    const response = await fetch(API_BASE_URL, {
        method: "POST",
        headers: await getAuthHeaders(),
        body: JSON.stringify(todo)
    });

    if (!response.ok) {
        throw new Error(`Failed to create todo: ${response.status}`);
    }

    return await response.json();
}


export async function updateTodo(id, todo) {

    const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: "PUT",
        headers: await getAuthHeaders(),
        body: JSON.stringify(todo)
    });

    if (!response.ok) {
        throw new Error(`Failed to update todo: ${response.status}`);
    }

    return await response.json();
}


export async function deleteTodo(id) {

    const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: "DELETE",
        headers: await getAuthHeaders()
    });

    if (!response.ok) {
        throw new Error(`Failed to delete todo: ${response.status}`)
    }
}