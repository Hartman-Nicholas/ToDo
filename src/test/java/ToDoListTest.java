import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    void addToDo() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        assertEquals(1, testList.getTodoList().size());
    }

    @Test
    void modifyTitle() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,02, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,02, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.modifyTitle("Test", 1, "Dog");
        assertEquals("Test", testList.getTodoList().get(0).getTitle());
        assertEquals("Dog", testList.getTodoList().get(2).getTitle());
    }
}