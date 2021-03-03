package ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class ToDoListTest {

    @Test
    void addToDo() {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",  "David" );
        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        assertEquals(1, testList.getTodoList().size());
    }


    @Test
    void sortByDueDate () {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", "2021-1-06",  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", "2021-5-07",  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortByDueDate();

    }

    @Test
    void sortByProject() {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", "2021-1-06",  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", "2021-5-07",  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortByProject();
    }

    @Test
    void removeToDo() {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", "2021-1-06",  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", "2021-5-07",  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.removeToDo(0);

        assertEquals(2, testList.getTodoList().size());

    }
}