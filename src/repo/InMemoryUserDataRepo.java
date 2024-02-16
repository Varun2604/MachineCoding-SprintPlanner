package repo;

import models.Sprint;
import models.Task;
import models.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InMemoryUserDataRepo implements UserDataRepo {

    private final HashMap<Integer, User> usersById = new HashMap<>();

    private final HashMap<Integer, HashMap<Integer, Set<Task>>> userToSprintTasks = new HashMap<>();
    @Override
    public List<Task> getAllTasks(User user, Sprint sprint) {
        var sprintTasks = userToSprintTasks.get(user.getId());
        if(sprintTasks == null){
            System.out.println("no tasks for user in any sprint");
//            throw new RuntimeException("no tasks for user in any sprint");
            return List.of();
        }
        var tasks = sprintTasks.get(sprint.getId());
        if(tasks.size() == 0){
            System.out.println("no tasks for user in sprint");
            //            throw new RuntimeException("no tasks for user in sprint");
            return List.of();
        }
        return tasks.stream().toList();
    }

    @Override
    public boolean assignTask(User user, Sprint sprint, Task task){
        var sprintTasks = userToSprintTasks.get(user.getId());
        if(sprintTasks == null){
            System.out.println("no tasks for user in any sprint");
//            throw new RuntimeException("no tasks for user in any sprint");
            sprintTasks = new HashMap<>();
        }
        var existingTasks = sprintTasks.get(sprint.getId());
        if(existingTasks == null){
            existingTasks = new HashSet<>();
        }
        existingTasks.add(task);    // reassigning does not do any bad, so it is fine.
        sprintTasks.put(sprint.getId(), existingTasks);
        userToSprintTasks.put(user.getId(), sprintTasks);
        return true;
    }

    @Override
    public boolean removeTask(User user, Sprint sprint, Task task){
        var sprintTasks = userToSprintTasks.get(user.getId());
        if(sprintTasks == null){
            System.out.println("no tasks for user in any sprint");
//           throw new RuntimeException("no tasks for user in any sprint");
            return false;
        }
        var existingTasks = sprintTasks.get(sprint.getId());
        if(existingTasks != null){
            existingTasks.remove(task);
            existingTasks.add(task);    // reassigning does not do any bad, so it is fine.
            sprintTasks.put(sprint.getId(), existingTasks);
            userToSprintTasks.put(user.getId(), sprintTasks);
            return true;
        }
        System.out.println("no tasks for user in any sprint");
//      throw new RuntimeException("no tasks for user in any sprint");
        return false;
    }
}
