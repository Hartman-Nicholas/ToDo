package ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;


public class ToDoList implements Serializable {

    @Serial
    private final static long serialVersionUID = 2L;
    private ArrayList <ToDo> todoList = new ArrayList<>();

    public void addToDo (ToDo todo) {
        todoList.add(todo);
    }


    public ArrayList<Integer> findToDoInd (String title) {
        ArrayList<Integer> findToDoInd = new ArrayList<>();
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).getTitle().equals(title)) {
                findToDoInd.add(i);
            }
        }
        return findToDoInd;
    }

    public ArrayList<ToDo> getTodoList() {
        return todoList;
    }

    public ArrayList<ToDo> sortByDueDate () {
        ArrayList<ToDo> sortByDueDate = new ArrayList<>();
        todoList.stream().sorted(Comparator.comparing(ToDo::getDueDate))
                .forEach(sortByDueDate::add);
        return sortByDueDate;
    }

    public ArrayList<ToDo> sortByProject () {
        ArrayList<ToDo> sortByProject = new ArrayList<>();
        todoList.stream().sorted(Comparator.comparing(ToDo::getProject))
                .forEach(sortByProject::add);
        return sortByProject;
    }

    public ArrayList<ToDo> filterByComplete () {
        ArrayList<ToDo> filterByComplete = new ArrayList<>();
        todoList.stream().filter(x->x.isStatus())
                .forEach(filterByComplete::add);
        return filterByComplete;
    }

    public ArrayList<ToDo> filterByIncomplete () {
        ArrayList<ToDo> filterByIncomplete = new ArrayList<>();
        todoList.stream().filter(x-> !x.isStatus())
                .forEach(filterByIncomplete::add);
        return filterByIncomplete;
    }


    public void removeToDo (int ind) {
        todoList.remove(ind);
    }

    public void saveToDoList (String filePath, ToDoList toDoList) throws IOException {

        File saveState = new File(filePath);
        FileOutputStream fos = new FileOutputStream(saveState);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(toDoList);

        oos.close();

    }

    public ToDoList loadToDoList (String filePath) throws IOException, ClassNotFoundException {

        File saveState = new File(filePath);
        FileInputStream fis = new FileInputStream(saveState);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ToDoList toDoList = (ToDoList) ois.readObject();

        ois.close();

        return toDoList;



    }





}
