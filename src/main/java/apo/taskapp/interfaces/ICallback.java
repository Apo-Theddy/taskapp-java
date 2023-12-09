package apo.taskapp.interfaces;

import apo.taskapp.models.Task;

public interface ICallback {
    void execute(Task task);
}
