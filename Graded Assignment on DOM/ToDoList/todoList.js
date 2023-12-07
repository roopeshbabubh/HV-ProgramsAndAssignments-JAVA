let taskIdCounter = 0;

function addTask() {
  const taskInput = document.getElementById('taskInput');
  const taskList = document.getElementById('taskList');

  if (taskInput.value.trim() !== '') {
    const taskId = 'task_' + taskIdCounter++;
    const taskText = taskInput.value;

    const listItem = document.createElement('li');
    listItem.setAttribute('id', taskId);
    listItem.innerHTML = `
      <span>${taskText}</span>
      <div>
      <button class="taskButton" onclick="removeTask('${taskId}')">✘</button>
      <button class="taskButton" onclick="markAsDone('${taskId}')">✔</button>
      </div>
    `;

    taskList.appendChild(listItem);
    updateTaskCount();
    taskInput.value = '';
  }
}

function removeTask(taskId) {
  const taskToRemove = document.getElementById(taskId);
  taskToRemove.remove();
  updateTaskCount();
}

function markAsDone(taskId) {
  const task = document.getElementById(taskId);
  task.classList.toggle('completed');
  updateTaskCount();
}

function updateTaskCount() {
  const totalTasks = document.getElementById('totalTasks');
  const completedTasks = document.getElementById('completedTasks');
  const tasks = document.querySelectorAll('#taskList li');

  totalTasks.textContent = tasks.length;

  const completedTasksCount = Array.from(tasks).filter(task => task.classList.contains('completed')).length;
  completedTasks.textContent = completedTasksCount;
}