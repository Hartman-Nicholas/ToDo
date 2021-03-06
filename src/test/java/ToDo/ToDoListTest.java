package ToDo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


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

        List <ToDo> sorted = testList.sortByDueDate();

        assertEquals("Test 2", sorted.get(0).getTitle());

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

        List<ToDo> sorted = testList.sortByProject();
        assertEquals("Test 3", sorted.get(0).getTitle());
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
        assertEquals("Test 2", testList.getTodoList().get(0).getTitle());

    }

    @Test
    void findToDoInd() {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", "2021-1-06",  "Nicholas" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test 3", "2021-5-07",  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        List<Integer> findDuplicateValuesInd = testList.findToDoInd("Test");

        assertEquals(2, findDuplicateValuesInd.size());
        assertEquals(2, findDuplicateValuesInd.get(1));
        assertEquals(0, findDuplicateValuesInd.get(0));
    }


    @Test
    void filterByComplete() {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",true,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", "2021-1-06", false, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", "2021-5-07", true, "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        List <ToDo> filterByComplete = testList.filterByComplete();

        assertEquals(2, filterByComplete.size());
        assertEquals("Test", filterByComplete.get(0).getTitle());
        assertEquals("Test 3", filterByComplete.get(1).getTitle());

    }

    @Test
    void filterByIncomplete() {
        ToDo test = new ToDo("Test", "This is a test", "2021-12-02",true,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", "2021-1-06", false, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", "2021-5-07", true, "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        List <ToDo> filterByIncomplete = testList.filterByIncomplete();

        assertEquals(1, filterByIncomplete.size());
        assertEquals("Test 2", filterByIncomplete.get(0).getTitle());


    }


}