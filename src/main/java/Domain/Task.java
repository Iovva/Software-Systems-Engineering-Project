package Domain;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private int id;
    private String taskName;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private TaskStatus status;

    public Task(int id, String taskName,
                String description, String startTime,
                String endTime, String status) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        setStartTime(startTime);
        setEndTime(endTime);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String name) {
        this.taskName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public String getStartTimeAsString(){
        return startTime.toString();
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setStartTime (String startTime){
        this.startTime = LocalDateTime.parse(startTime);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getEndTimeAsString(){
        return endTime.toString();
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(String endTime){
        this.endTime = LocalDateTime.parse(endTime);
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getStatusAsString(){
        return status.toString();
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        if (Objects.equals(status, "pending"))
            this.status = Domain.TaskStatus.PENDING;
        else if ((Objects.equals(status, "finished")))
            this.status = TaskStatus.FINISHED;
        else
            this.status = TaskStatus.INVALID;
    }
}
