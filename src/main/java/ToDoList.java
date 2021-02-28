import java.util.ArrayList;

public class ToDoList {

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

    public ArrayList<ToDo> getTodoList() {
        return todoList;
    }
}
