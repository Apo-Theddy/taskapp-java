package apo.taskapp.controllers;

import java.util.ArrayList;

import apo.taskapp.models.Task;
import apo.taskapp.models.User;
import apo.taskapp.services.TaskService;

public class TaskController {

    private final TaskService taskService;

    public TaskController() {
        this.taskService = new TaskService();
    }

    public ArrayList<Task> getTasks(User user) {
        return taskService.getTasks(user);
    }

    public boolean deleteTask(Task task) {
        return taskService.deleteTask(task);
    }

    public boolean createNewTask(Task task, User user) {
        return taskService.createNewTask(task, user);
    }

    public boolean updateTask(Task task) {
        return taskService.updateTask(task);
    }
}
