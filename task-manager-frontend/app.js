const API_URL = "http://localhost:8080/api/tasks";

function loadTasks() {
    const email = document.getElementById("email").value;
    if (!email) {
        alert("Please enter email");
        return;
    }

    fetch(`${API_URL}/${email}`)
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(data => {
            const list = document.getElementById("taskList");
            list.innerHTML = "";

            data.forEach(task => {
                const li = document.createElement("li");
                li.innerHTML = `
                    <span>${task.title} (${task.status})</span>
                    <button class="delete-btn" onclick="deleteTask(${task.id}, '${email}')">Delete</button>
                `;
                list.appendChild(li);
            });
        })
        .catch(() => alert("Error fetching tasks"));
}

function createTask() {
    const email = document.getElementById("email").value;
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;

    if (!email || !title) {
        alert("Email and title required");
        return;
    }

    fetch(`${API_URL}/${email}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ title, description })
    })
        .then(res => {
            if (!res.ok) throw new Error();
            return res.json();
        })
        .then(() => {
            document.getElementById("title").value = "";
            document.getElementById("description").value = "";
            loadTasks();
        })
        .catch(() => alert("Error creating task"));
}

function deleteTask(id, email) {
    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(() => loadTasks())
        .catch(() => alert("Error deleting task"));
}