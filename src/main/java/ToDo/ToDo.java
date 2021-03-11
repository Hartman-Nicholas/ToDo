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

    /**
     * Creates a ToDo object for the user that stores the Task information
     * @param title sets the title of the ToDo task.
     * @param body sets the body of the ToDo task with word wrap formatting set to
     *             limit the output to 80 Characters per line.
     * @param dueDate sets the dueDate of the ToDo task.
     * @param status sets the status of the Todo.
     * @param project sets the project of the ToDo task.
     */


    public ToDo(String title, String body, String dueDate, boolean status, String project) {
        this.title = title;
        this.body = wordWrap.wrap(body);
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
    }

    public ToDo(String title, String body, String dueDate, String project) {
        this.title = title;
        this.body = wordWrap.wrap(body);
        this.dueDate = dueDate;
        this.status = false;
        this.project = project;
    }

    /**
     * Displays the information of the ToDo in the terminal,
     * window.
     */

    public void viewTodo () {
        System.out.println("Title: " + title);
        System.out.println("ToDo: " + "\n" + body);
        System.out.println("Project: " + project);
        System.out.println("Due Date: " + dueDate);
        if (status) {
            System.out.println("Status: Completed");
        } else {
            System.out.println("Status: Incomplete");
        }

    }

    /**
     * Gets the title from the ToDo object.
     * @return the String value of the title from the ToDo object.
     */

    public String getTitle() {
        return title;
    }

    /**
     * Sets the title on the ToDo object.
     * @param title String value to change the new title to.
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the body from the ToDo object.
     * @return String value of the body on the ToDo object.
     */

    public String getBody() {
        return body;
    }

    /**
     * Sets the body on the ToDo object with wordWrap formatting
     * set to a maximum of 80 characters per line.
     * @param body String value to change the new body to.
     */

    public void setBody(String body) {
        this.body = wordWrap.wrap(body);
    }

    /**
     * Gets the due date from the ToDo object.
     * @return The due date
     */

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
