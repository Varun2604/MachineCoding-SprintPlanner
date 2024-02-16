package service;

import models.Sprint;
import models.Task;
import models.User;
import repo.TaskRepo;

import java.util.HashMap;
import java.util.Set;

public class TaskServiceImpl implements TaskService{

    TaskRepo repo;

    public TaskServiceImpl(TaskRepo repo){
        this.repo = repo;
    }

    private static final HashMap<Task.Status, Set<Task.Status>> statusTransitionMap = new HashMap<>(){
        {
            put(Task.Status.TODO, Set.of(Task.Status.INPROGRESS));
            put(Task.Status.INPROGRESS, Set.of(Task.Status.DONE));
        }
    };

    @Override
    public Task createTask(Task task) {
        Task createdTask = repo.createTask(task);
        if(createdTask == null){
            System.out.println("cannot create existing task");
//            throw new RuntimeException("cannot create existing task");
        }
        return createdTask;
    }

    @Override
    public boolean transitionTask(Task task) {
        var id = task.getId();
        var finalStatus = task.getStatus();
        Task currentTask = repo.getTask(id);
        if(currentTask == null){
            System.out.println("task does not exist");
            //throw new RuntimeException("invalid status transition");
            return false;
        }
        var currentStatus = currentTask.getStatus();
        if(statusTransitionMap.containsKey(currentStatus)) {
            var transitionableStatuses = statusTransitionMap.get(currentStatus);
            if(transitionableStatuses.contains(finalStatus)){
                currentTask.setStatus(finalStatus);
                repo.updateTask(currentTask);
                return true;
            }
            //throw new RuntimeException("invalid status transition");
            System.out.println("invalid status transition");
        }
        //throw new RuntimeException("cannot transition from current status");
        System.out.println("cannot transition from current status");
        return false;
    }
}
