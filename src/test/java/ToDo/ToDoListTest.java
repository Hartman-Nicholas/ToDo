package ToDo;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ToDoListTest {

    @Test
    void addToDo() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021, Calendar.FEBRUARY, 27), true, "David" );
        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        assertEquals(1, testList.getTodoList().size());
    }

    @Test
    void modifyTitle() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.modifyTitle("Test", 1, "Dog");
        assertEquals("Test", testList.getTodoList().get(0).getTitle());
        assertEquals("Dog", testList.getTodoList().get(2).getTitle());
    }

    @Test
    void modifyBody() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.modifyBody("Test", 1, "This is a modification Test");
        assertEquals("This is a test", testList.getTodoList().get(0).getBody());
        assertEquals("This is a modification Test", testList.getTodoList().get(2).getBody());
    }


    @Test
    void modifyDueDate() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.modifyDueDate("Test", 1, new GregorianCalendar(2022, Calendar.MARCH, 27));
        assertEquals(new GregorianCalendar(2021,Calendar.FEBRUARY,27), testList.getTodoList().get(0).getDueDate());
        assertEquals(new GregorianCalendar(2022,Calendar.MARCH,27), testList.getTodoList().get(2).getDueDate());
    }

    @Test
    void modifyProject() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.modifyProject("Test", 1, "Peter");
        assertEquals("David", testList.getTodoList().get(0).getProject());
        assertEquals("Peter", testList.getTodoList().get(2).getProject());
    }

    @Test
    void modifyStatus() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.modifyStatus("Test", 1, false);
        assertTrue(testList.getTodoList().get(0).isStatus());
        assertFalse(testList.getTodoList().get(2).isStatus());
    }

    @Test
    void sortByDueDate () {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Test2", "This is a duplicate test", new GregorianCalendar(2021,Calendar.MARCH, 27), true, "David" );
        ToDo test3 = new ToDo("Test3", "This is a duplicate test", new GregorianCalendar(2021,Calendar.JANUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortByDueDate();

    }

    @Test
    void sortByProject() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortByProject();
    }

    @Test
    void removeToDo() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,Calendar.FEBRUARY, 27), true, "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.removeToDo(0);

        assertEquals(2, testList.getTodoList().size());

    }
}