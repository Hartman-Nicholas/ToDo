package ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void getTitle() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        assertEquals("Test", test.getTitle());
    }

    @Test
    void setTitle() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        test.setTitle("Setting title test");
        assertEquals("Setting title test", test.getTitle());
    }

    @Test
    void getBody() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        assertEquals("This is a test", test.getBody());
    }

    @Test
    void setBody() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        test.setBody("Setting body test");
        assertEquals("Setting body test", test.getBody());
    }

    @Test
    void getDueDate() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        assertEquals("2021-02-12", test.getDueDate());
    }

    @Test
    void setDueDate() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        test.setDueDate("2021-03-14");
        assertEquals("2021-03-14", test.getDueDate());
    }

    @Test
    void isStatus() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        assertTrue(test.isStatus());
    }

    @Test
    void setStatus() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        test.setStatus(false);
        assertFalse(test.isStatus());
    }

    @Test
    void getProject() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        assertEquals("David", test.getProject());
    }

    @Test
    void setProject() {
        ToDo test = new ToDo("Test", "This is a test", "2021-02-12",  "David" );
        test.setProject("Setting project test");
        assertEquals("Setting project test", test.getProject());
    }
}