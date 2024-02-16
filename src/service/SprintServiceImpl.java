package service;

import models.Sprint;
import models.Task;
import models.User;
import repo.SprintRepo;

public class SprintServiceImpl implements SprintService{

    TaskService taskService;

    UserDataMapperService userDataMapperService;

    UserService userService;

    SprintRepo sprintRepo;

    private final int MAX_TASKS_IN_SPRINT = 20;

    private final int MAX_PROGRESS_USER_SPRINT_TASKS = 2;

    public SprintServiceImpl(TaskService taskService, UserService userService, UserDataMapperService userDataMapperService, SprintRepo sprintRepo) {
        this.sprintRepo = sprintRepo;
        this.taskService = taskService;
        this.userDataMapperService = userDataMapperService;
        this.userService = userService;
    }

    @Override
    public Sprint createSprint(Sprint sprint) {
        return sprintRepo.createSprint(sprint);     // no uniqueness on names, so no worries on thread safety
    }

    @Override
    public boolean addTask(Sprint sprint, Task task, User user) {
        Sprint s = sprintRepo.getSprint(sprint.getId());
        if(s == null){
            System.out.println("invalid sprint");
//             throw new RuntimeException("invalid sprint");
            return false;
        }
        synchronized (s) {       // ensure that no two threads can add tasks for the same sprint
            var tasksInSprint = sprintRepo.countOfTasks(sprint);
            if(tasksInSprint >= MAX_TASKS_IN_SPRINT){
                System.out.println("too many tasks in sprint");
//                throw new RuntimeException("too many tasks in sprint");
                return false;
            }
            var createdTask = taskService.createTask(task);
            if(createdTask == null){
                System.out.println("unable to create task");
//                throw new RuntimeException("unable to create task"); // && propagate exception
                return false;
            }
            userDataMapperService.assignTask(user, sprint, createdTask);
            sprintRepo.addToSprint(sprint, createdTask);
            return true;
        }
    }

    @Override
    public boolean removeTask(Sprint sprint, Task task, User user) {
        // bug: cannot be able to unmap task from user's sprint tasks
        var removed = sprintRepo.removeFromSprint(sprint, task);
        if(removed){
            return userDataMapperService.removeTask(null, sprint, task);
        }
        return false;
    }

    @Override
    public boolean transitionTask(Sprint sprint, Task task, User user) {
        // BUG: check if user can have this task transitioned.
        User u = userService.getUser(user.getId());
        Sprint s = sprintRepo.getSprint(sprint.getId());
        if(u == null || s == null){
            System.out.println("invalid data");
//                throw new RuntimeException("invalid data"); // && propagate exception
            return false;
        }
        var pendingTasks = userDataMapperService.getAllPendingTasks(user, sprint);
        if(pendingTasks.size() >= MAX_PROGRESS_USER_SPRINT_TASKS){
            System.out.println("cannot have more than 2 pending tasks");
//                throw new RuntimeException("cannot have more than 2 pending tasks"); // && propagate exception
            return false;
        }
        synchronized (u) {
            synchronized (s) {
                return taskService.transitionTask(task);    // since we have the same reference in task in both the repos, transitioning the state in the reference will update both repos.
            }
        }
    }
}
