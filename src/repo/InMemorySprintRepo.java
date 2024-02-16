package repo;

import models.Sprint;
import models.Task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class InMemorySprintRepo implements SprintRepo{

    private final HashMap<Integer, Sprint> sprintById = new HashMap<>();

    private final HashMap<Integer, Set<Task>> sprintToTasks = new HashMap<>();
    @Override
    public Sprint createSprint(Sprint sprint) {
        if(this.sprintById.containsKey(sprint.getId())){
            // throw new RunTimeException("already exists");
            return null;
        }
        return this.sprintById.put(sprint.getId(), sprint);
    }

    @Override
    public Sprint getSprint(int id) {
        return this.sprintById.get(id);
    }

    @Override
    public boolean addToSprint(Sprint sprint, Task task) {
        var tasks = sprintToTasks.get(sprint.getId());
        if(tasks == null){
            tasks = new HashSet<>();
        }
        if(tasks.contains(task)){
//            throw new RuntimeException("task already added to sptint");
            return false;
        }
        tasks.add(task);
        sprintToTasks.put(sprint.getId(), tasks);
        return true;
    }

    @Override
    public int countOfTasks(Sprint sprint) {
        var tasks = sprintToTasks.get(sprint.getId());
        if(tasks == null){
            return 0;
        }
        return tasks.size();
    }

    @Override
    public boolean removeFromSprint(Sprint sprint, Task task) {
        var tasks = sprintToTasks.get(sprint.getId());
        if(tasks != null){
            tasks.remove(task);
            sprintToTasks.put(sprint.getId(), tasks);
            return true;
        }
        // throw new RuntimeException("task not in sprint");
        System.out.println("task not in sprint");
        return false;
    }
}
