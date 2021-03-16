package ToDo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ToDoList implements Serializable {

    @Serial
    private final static long serialVersionUID = 2L;
    private final ArrayList <ToDo> todoList = new ArrayList<>();

    public void addToDo (ToDo todo) {
        todoList.add(todo);
    }

    public ArrayList<ToDo> getTodoList() {
        return todoList;
    }

    /**
     * Sort the todoList ArrayList in ascending order.
     * @param index parameter for setting the type of sort
     *              [0] sort by Due Date.
     *              [1] sort by Project.
     * @return the sorted todoList.
     */


    public List<ToDo> sortBy (int index) {

        switch (index) {
            case 0 -> todoList.sort(Comparator.comparing(ToDo::getDueDate));
            case 1 -> todoList.sort(Comparator.comparing(ToDo::getProject));
            }
            return todoList;
    }

    /**
     * Filters the todoList ArrayList by either complete or incomplete tasks
     * @param index parameter for setting the filter type.
     *              [0] Filter by completed tasks.
     *              [1] Filter by incompleted tasks.
     * @return a new List that contains the filtered items.
     */

    public List<ToDo> filterBy (int index) {
        List<ToDo> filterList = new ArrayList<>();

        switch (index) {
            case 0 -> filterList = todoList.stream()
                    .filter(todo->todo.getStatus())
                    .collect(Collectors.toList());
            case 1 -> filterList = todoList.stream()
                    .filter(todo->!todo.getStatus())
                    .collect(Collectors.toList());
        }
        return filterList;
    }

    /**
     * Finds ToDo Tasks either by project[0] or Title[1]
     * @param index [0] searches for all elements by Title.
     *              [1] searches for all elements by Project.
     * @param data value to search by.
     * @return List of found results.
     */

    public List<ToDo> findBy (int index, String data) {


        List<ToDo> foundItems = new ArrayList<>();

        switch (index) {
            case 0 -> foundItems = todoList.stream()
                    .filter(x-> x.getTitle().equalsIgnoreCase(data))
                    .collect(Collectors.toList());
            case 1 -> foundItems = todoList.stream()
                    .filter(x->x.getProject().equalsIgnoreCase(data))
                    .collect(Collectors.toList());

        }
        return foundItems;
    }

    public void removeToDo (int index) {
        todoList.remove(index);
    }

    /**
     * Takes a ToDo object and allows the user to modify selected fields.
     * @param todo object to be modified.
     * @param index Index number determines which field will be modified
     *              [0] modify Title
     *              [1] modify Body
     *              [2] modify Project
     *              [3] modify DueDate
     *              [4] modify Status
     *
     * @param data information to set the new field to.
     * @return the old data that was replaced is returned for display purposes.
     */

    public Object modify (ToDo todo, int index, Object data) {
        String oldTitle = todo.getTitle();
        String oldBody = todo.getBody();
        String oldProject = todo.getProject();
        LocalDate oldDate = todo.getDueDate();
        boolean oldStatus = todo.getStatus();

        switch (index) {
            case 0 -> {
                todo.setTitle((String) data);
                return oldTitle;
            }
            case 1 -> {
                todo.setBody((String) data);
                return oldBody;
            }
            case 2 -> {
                todo.setProject((String) data);
                return oldProject;
            }
            case 3 -> {
                todo.setDueDate((LocalDate) data);
                return oldDate;
            }
            case 4 -> {
                todo.setStatus((boolean) data);
                return oldStatus;
            }

        }
        return "";
    }

    public ToDo getToDo (int index) {
        return todoList.get(index);
    }

    public int getSize () {
        return todoList.size();
    }








}
