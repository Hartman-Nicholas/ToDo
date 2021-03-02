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

    public void modifyDueDate (String title, int ind, String dueDate) {
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

    public ArrayList<ToDo> sortByDueDate () {
        ArrayList<ToDo> sortByDueDate = new ArrayList<ToDo>();
        todoList.stream().sorted(Comparator.comparing(ToDo::getDueDate))
                .forEach(sortByDueDate::add);
        return sortByDueDate;
    }

    public void sortByProject () {
        todoList.stream().sorted(Comparator.comparing(ToDo::getProject))
                .forEach(x-> System.out.println(x.getProject()));
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
