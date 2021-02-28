import java.util.Calendar;
import java.util.GregorianCalendar;

public class ToDo {

    private String title;
    private String body;
    private GregorianCalendar dueDate;
    private boolean status;
    private String project;


    public ToDo(String title, String body, GregorianCalendar dueDate, boolean status, String project) {
        this.title = title;
        this.body = body;
        this.dueDate = dueDate;
        this.status = status;
        this.project = project;
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
        this.body = body;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setDueDate(GregorianCalendar dueDate) {
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
