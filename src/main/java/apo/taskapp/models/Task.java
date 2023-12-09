package apo.taskapp.models;

import lombok.Getter;
import lombok.Setter;

public class Task {

    @Getter
    @Setter
    int taskID;

    @Getter
    @Setter
    String title;

    @Getter
    @Setter
    String content;

    @Getter
    @Setter
    String creationDate;

    @Getter
    @Setter
    String deletedDate;

    @Getter
    @Setter
    String updatedDate;

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Task(int taskID, String title, String content, String creationDate, String deletedDate, String updatedDate) {
        this.taskID = taskID;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.deletedDate = deletedDate;
        this.updatedDate = updatedDate;
    }

    public Task(int taskID, String title, String content, String creationDate, String updatedDate) {
        this.taskID = taskID;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.updatedDate = updatedDate;
    }

}
