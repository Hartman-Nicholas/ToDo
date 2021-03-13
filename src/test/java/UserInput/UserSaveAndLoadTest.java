package UserInput;

import ToDo.ToDo;
import ToDo.ToDoList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class UserSaveAndLoadTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.parse("1986/12/20", formatter);
    LocalDate localDate1 = LocalDate.parse("1980/12/20", formatter);
    LocalDate localDate2 = LocalDate.parse("1990/12/20", formatter);

    @Test
    void saveToDoList() throws Exception {
        ToDo test = new ToDo("Test", "This is a test", localDate, "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        UserSaveAndLoad.saveToDoList("Test.txt", testList);

        ToDoList loadList = UserSaveAndLoad.loadToDoList("Test.txt");

        assertEquals(testList.getTodoList().get(0).getTitle(), loadList.getTodoList().get(0).getTitle());
    }

    @Test
    void saveToDoList_Exception() {
        ToDo test = new ToDo("Test", "This is a test", localDate, "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);


        Assertions.assertThrows(IOException.class, () -> UserSaveAndLoad.saveToDoList("T/tes.txt", testList));
    }

    @Test
    void loadToDoList() throws Exception {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2, "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        UserSaveAndLoad.saveToDoList("Test.txt", testList);

        ToDoList loadList = UserSaveAndLoad.loadToDoList("Test.txt");

        assertEquals(testList.getTodoList().get(0).getTitle(), loadList.getTodoList().get(0).getTitle());

    }

    @Test
    void loadToDoList_IOException() throws IOException, ClassNotFoundException {

        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        Assertions.assertThrows(IOException.class, () -> UserSaveAndLoad.loadToDoList("T/tes.txt"));
    }

    @Test
    void loadToDoList_ClassNotFoundException() throws Exception {

        String test = "Test";
        File saveState = new File("Test_Exception.txt");
        FileOutputStream fos = new FileOutputStream(saveState);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(test);

        oos.close();

        Assertions.assertThrows(Exception.class, () -> UserSaveAndLoad.loadToDoList("Test_Exception.txt"));
    }


}