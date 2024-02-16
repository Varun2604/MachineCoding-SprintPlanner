import repo.*;

public class Main {
    public static void main(String[] args) {
        SprintRepo sprintRepo = new InMemorySprintRepo();
        UserDataRepo userDataRepo = new InMemoryUserDataRepo();
        TaskRepo taskRepo = new InMemoryTaskRepo();


//        SprintService sprintSvc = new SprintServiceImpl()
    }
}

// Sprint planner
/**
 POST /sprints
 POST /sprints/<id>/tasks {type: "STORY|FEATURE|BUG", status : "TODO", userId : <user_id>}  [1. max of 20 tasks ]
 DELETE /sprints/<id>/tasks/<id> // this will remove & not delete
 PATCH /sprints/<id>/tasks/<id> {status: "INPROGRESS|DONE"}  [1. only 2 tasks in INPROGRESS at a time, 2. status transaction ]
 GET /users/<id>/tasks?sprint=<sprint_id>
 GET /users/<id>/delayedTasks?sprint=<sprint_id>   // this will get all delayed tasks



 **/