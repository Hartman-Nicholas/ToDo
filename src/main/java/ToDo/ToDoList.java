package ToDo;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;


public class ToDoList implements Serializable {

    @Serial
    private final static long serialVersionUID = 2L;
    private ArrayList <ToDo> todoList = new ArrayList<>();

    public void addToDo (ToDo todo) {
        todoList.add(todo);
    }

    private ArrayList<Integer> findToDoInd (String title) {
        ArrayList<Integer> findToDoInd = new ArrayList<>();
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getTitle().equals(title)) {
                findToDoInd.add(i);
            }
        }
        return findToDoInd;
    }

    public void modifyTitle (String title, int ind, String newTitle) {
        if (findToDoInd(title).size()>0) {
            todoList.get(findToDoInd(title).get(ind)).setTitle(newTitle);
        } else {
            System.out.println("Title not found");
        };
    }

    public void modifyBody (String title, int ind, String newBody) {
        if (findToDoInd(title).size()>0) {
            todoList.get(findToDoInd(title).get(ind)).setBody(newBody);
        } else {
            System.out.println("Title not found");
        };
    }

    public void modifyDueDate (String title, int ind, GregorianCalendar dueDate) {
        if (findToDoInd(title).size()>0) {
            todoList.get(findToDoInd(title).get(ind)).setDueDate(dueDate);
        } else {
            System.out.println("Title not found");
        };
    }

    public void modifyProject (String title, int ind, String newProject) {
        if (findToDoInd(title).size()>0) {
            todoList.get(findToDoInd(title).get(ind)).setProject(newProject);
        } else {
            System.out.println("Title not found");
        };
    }

    public void modifyStatus (String title, int ind, boolean completed) {
        if (findToDoInd(title).size()>0) {
            todoList.get(findToDoInd(title).get(ind)).setStatus(completed);
        } else {
            System.out.println("Title not found");
        };
    }

    public ArrayList<ToDo> getTodoList() {
        return todoList;
    }

    public void sortByDueDate () {
        todoList.stream().sorted(Comparator.comparing(ToDo::getDueDate))
                .forEach(x-> System.out.println(x.viewDueDate()));

    }

    public void sortByProject () {
        todoList.stream().sorted(Comparator.comparing(ToDo::getProject))
                .forEach(x-> System.out.println(x.getProject()));
    }

    public void removeToDo (int ind) {
        todoList.remove(ind);
    }
}
