package repo;

import models.Task;

import java.util.HashMap;
import java.util.HashSet;

public class InMemoryTaskRepo implements TaskRepo{

    private final HashMap<Integer, Task> tasksById = new HashMap<>();

    @Override
    public Task createTask(Task task) {
        if(this.tasksById.containsKey(task.getId())){
            // throw new RunTimeException("already exists");
            return null;
        }
        return this.tasksById.put(task.getId(), task);
    }

    @Override
    public Task getTask(int id) {
        if(!this.tasksById.containsKey(id)){
            // throw new RunTimeException("already exists");
            return null;
        }
        return this.tasksById.get(id);
    }

    @Override
    public Task updateTask(Task task) {
        if(!this.tasksById.containsKey(task.getId())){
            // throw new RunTimeException("already exists");
            return null;
        }
        return this.tasksById.put(task.getId(), task);
    }
}
