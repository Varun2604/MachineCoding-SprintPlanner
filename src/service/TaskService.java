package service;

import models.Sprint;
import models.Task;
import models.User;

public interface TaskService {

    Task createTask(Task task);

    boolean transitionTask(Task task);
}
