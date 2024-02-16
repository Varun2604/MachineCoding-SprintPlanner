package repo;

import models.Sprint;
import models.Task;

public interface SprintRepo {

    Sprint createSprint(Sprint sprint);

    Sprint getSprint(int id);

    boolean addToSprint(Sprint sprint, Task task);

    int countOfTasks(Sprint sprint);

    boolean removeFromSprint(Sprint sprint, Task task);
}
