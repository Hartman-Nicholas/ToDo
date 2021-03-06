package UserInput;

import ToDo.ToDoList;

import java.io.*;

public class UserSaveAndLoad {

    public static void saveToDoList (String filePath, ToDoList toDoList) throws IOException {


        File saveState = new File(filePath);
        FileOutputStream fos = new FileOutputStream(saveState);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(toDoList);

        oos.close();

    }

    public static ToDoList loadToDoList (String filePath) throws IOException, ClassNotFoundException, Exception {

        File saveState = new File(filePath);
        FileInputStream fis = new FileInputStream(saveState);
        ObjectInputStream ois = new ObjectInputStream(fis);

        ToDoList toDoList = (ToDoList) ois.readObject();

        ois.close();

        return toDoList;

    }





}
