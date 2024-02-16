package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    private static int idCounter = 0;

    private String summary;

    private String description;

    private final int id;

    private final LocalDateTime startTime; //TODO: is this the correct class to use ?

    private LocalDateTime endTime; //TODO: is this the correct class to use ?

    private Status status;

    private final Type type;

    public Task(String summary, String description, LocalDateTime startTime, LocalDateTime endTime, Type type) {
        this.summary = summary;
        this.description = description;
        this.id = idCounter;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.status = Status.TODO;
        idCounter++;
    }

    public Task(LocalDateTime startTime, LocalDateTime endTime, Type type) {
        this.id = idCounter;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.status = Status.TODO;
        idCounter++;
    }

//    private User user;

    public enum Status {
        TODO,
        INPROGRESS,
        DONE
    }

    public enum Type {
        FEATURE,
        BUG,
        STORY
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}
