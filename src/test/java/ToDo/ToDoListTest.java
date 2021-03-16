package ToDo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
    void sortBy_DueDate () {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        //Index 0 is sortBy due date.
        testList.sortBy(0);

        assertEquals("Test 2", testList.getTodoList().get(0).getTitle());

    }

    @Test
    void sortBy_Project() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1,  "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        //Index 1 is sortBy project.
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

        //Index 1 in filterBy checks for Incomplete (false);
        List <ToDo> filterByIncomplete = testList.filterBy(1);

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

        //Index 0 in filterBy checks for Complete (True);
        List <ToDo> filterByIncomplete = testList.filterBy(0);

        assertEquals(2, filterByIncomplete.size());
        assertEquals("Test", filterByIncomplete.get(0).getTitle());
    }

    @Test
    void findBy_Title() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );
        ToDo test4 = new ToDo("Test", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.addToDo(test4);

        //Index 0 in findBy method searches by Title and the information contained in data.
        List <ToDo> findByTitle = testList.findBy(0, "Test");

        assertEquals(2, findByTitle.size());
        assertEquals("Test", findByTitle.get(0).getTitle());

    }

    @Test
    void findBy_Project() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        ToDo test2 = new ToDo("Test 2", "This is a duplicate test 2", localDate1, "Nicholas" );
        ToDo test3 = new ToDo("Test 3", "This is a duplicate test 3", localDate2,  "Andrew" );
        ToDo test4 = new ToDo("Test", "This is a duplicate test 3", localDate2,  "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);
        testList.addToDo(test4);

        //Index 1 in findBy method searches by Project and the information contained in data.
        List <ToDo> findByProject = testList.findBy(1, "Andrew");

        assertEquals(2, findByProject.size());
        assertEquals("Andrew", findByProject.get(0).getProject());

    }

    @Test
    void modify_Title() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);

        //Index 0 in modify method modifies by Title and the information contained in data.
        testList.modify(test, 0, "UpdatedData");

        assertEquals("UpdatedData", test.getTitle());
    }

    @Test
    void modify_body() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);

        //Index 1 in modify method modifies by body and the information contained in data.
        testList.modify(test, 1, "UpdatedData");

        assertEquals("UpdatedData", test.getBody());
    }

    @Test
    void modify_Project() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);

        //Index 2 in modify method modifies by Project and the information contained in data.
        testList.modify(test, 2, "UpdatedData");

        assertEquals("UpdatedData", test.getProject());
    }

    @Test
    void modify_dueDate() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);

        //Index 3 in modify method modifies by Due Date and the information contained in data.
        testList.modify(test, 3, localDate1);

        assertEquals(localDate1, test.getDueDate());
    }

    @Test
    void modify_status() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);

        //Index 4 in modify method modifies by Status and the information contained in data.
        testList.modify(test, 4, true);

        assertTrue(test.getStatus());
    }

}