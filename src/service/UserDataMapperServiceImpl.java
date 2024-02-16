package service;

import models.Sprint;
import models.Task;
import models.User;
import repo.UserDataRepo;

import java.time.LocalDateTime;
import java.util.List;

public class UserDataMapperServiceImpl implements UserDataMapperService {

    UserDataRepo repo;

    public UserDataMapperServiceImpl(UserDataRepo repo){
        this.repo = repo;
    }

    @Override
    public List<Task> getAllTasks(User user, Sprint sprint) {
        return repo.getAllTasks(user, sprint);
    }

    @Override
    public List<Task> getAllDelayedTasks(User user, Sprint sprint) {
        var tasks = repo.getAllTasks(user, sprint);     // use indexes for faster perfomance
        var currentTime = LocalDateTime.now();
        var delayedTasks = tasks.stream().filter(task -> !Task.Status.DONE.equals(task.getStatus()) && currentTime.isAfter(task.getEndTime()));
        return delayedTasks.toList();
    }

    @Override
    public List<Task> getAllPendingTasks(User user, Sprint sprint) {
        var tasks = repo.getAllTasks(user, sprint);     // use indexes for faster perfomance
        var pendingTasks = tasks.stream().filter(task -> Task.Status.INPROGRESS.equals(task.getStatus()));
        return pendingTasks.toList();
    }

    @Override
    public boolean assignTask(User user, Sprint sprint, Task task) {
        return repo.assignTask(user, sprint, task);
    }

    @Override
    public boolean removeTask(User user, Sprint sprint, Task task) {
        return repo.removeTask(user, sprint, task);
    }
}
