package ToDo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class ToDoListTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.parse("1986/12/20", formatter);
    LocalDate localDate1 = LocalDate.parse("1980/12/20", formatter);
    LocalDate localDate2 = LocalDate.parse("1990/12/20", formatter);

    @Test
    void addToDo() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        assertEquals(1, testList.getTodoList().size());
    }


    @Test
    void sortByDueDate () {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortBy(0);

        assertEquals("Test 2", testList.getTodoList().get(0).getTitle());

    }

    @Test
    void sortByProject() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortBy(1);
        assertEquals("Test 3", testList.getTodoList().get(0).getTitle());
    }

    @Test
    void removeToDo() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.removeToDo(0);

        assertEquals(2, testList.getTodoList().size());
        assertEquals("Test 2", testList.getTodoList().get(0).getTitle());

    }


    @Test
    void sortBy() {
    }

    @Test
    void filterBy_Incomplete() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2, "Andrew" );

        test.setStatus(true);
        test2.setStatus(false);
        test3.setStatus(true);

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        List <ToDo> filterByIncomplete = testList.filterBy(0);

        assertEquals(1, filterByIncomplete.size());
        assertEquals("Test 2", filterByIncomplete.get(0).getTitle());
    }

    @Test
    void filterBy_Complete() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        test.setStatus(true);
        test2.setStatus(false);
        test3.setStatus(true);

        List <ToDo> filterByIncomplete = testList.filterBy(0);

        assertEquals(2, filterByIncomplete.size());
        assertEquals("Test", filterByIncomplete.get(0).getTitle());
    }

    @Test
    void findBy() {
    }
}