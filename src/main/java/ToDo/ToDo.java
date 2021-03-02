package ToDo;

import utils.WordWrap;

import java.io.Serial;
import java.io.Serializable;

public class ToDo implements Serializable {

    private static final WordWrap wordWrap = new WordWrap();
    private String title;
    private String body;
    private String dueDate;
    private boolean status;
    private String project;

    @Serial
    private final static long serialVersionUID = 1L;


    public ToDo(String title, String body, String dueDate, String project) {
        this.title = title;
        this.body = wordWrap.wrap(body);
        this.dueDate = dueDate;
        this.status = false;
        this.project = project;
    }

    public void viewTodo () {
        System.out.println("Title: " + title);
        System.out.println("Task: " + "\n" + body);
        System.out.println("Project: " + project);
        System.out.println("Due Date: " + dueDate);
        if (status) {
            System.out.println("Status: Completed");
        } else {
            System.out.println("Status: Incomplete");
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = wordWrap.wrap(body);
    }

    public String getDueDate() {
        return dueDate;
    }


    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
