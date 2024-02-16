package repo;

import models.Task;

public interface TaskRepo {
    Task createTask(Task task);

    Task getTask(int id);

    Task updateTask(Task task);
}
