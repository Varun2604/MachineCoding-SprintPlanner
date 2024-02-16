package repo;

import models.Sprint;
import models.Task;
import models.User;

import java.util.List;

public interface UserDataRepo {

    List<Task> getAllTasks(User user, Sprint sprint);

//    List<Task> getAllDelayedTasks(User user, Sprint sprint); /// ???????

    boolean assignTask(User user, Sprint sprint, Task task);

    boolean removeTask(User user, Sprint sprint, Task task);
}
