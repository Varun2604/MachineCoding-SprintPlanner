package service;

import models.Sprint;
import models.Task;
import models.User;

public interface SprintService {
    Sprint createSprint(Sprint sprint);

    boolean addTask(Sprint sprint, Task task, User user);

    boolean removeTask(Sprint sprint, Task task, User user);

    boolean transitionTask(Sprint sprint, Task task, User user);
}
