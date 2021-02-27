import org.junit.jupiter.api.Test;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    void getTitle() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        assertEquals("Test", test.getTitle());
    }

    @Test
    void setTitle() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        test.setTitle("Setting title test");
        assertEquals("Setting title test", test.getTitle());
    }

    @Test
    void getBody() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        assertEquals("This is a test", test.getBody());
    }

    @Test
    void setBody() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        test.setBody("Setting body test");
        assertEquals("Setting body test", test.getBody());
    }

    @Test
    void getDueDate() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        assertEquals(new GregorianCalendar(2021, 02, 27), test.getDueDate());
    }

    @Test
    void setDueDate() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        test.setDueDate(new GregorianCalendar(2022, 02, 27));
        assertEquals(new GregorianCalendar(2022,02, 27), test.getDueDate());
    }

    @Test
    void isStatus() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        assertTrue(test.isStatus());
    }

    @Test
    void setStatus() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        test.setStatus(false);
        assertFalse(test.isStatus());
    }

    @Test
    void getProject() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        assertEquals("David", test.getProject());
    }

    @Test
    void setProject() {
        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        test.setProject("Setting project test");
        assertEquals("Setting project test", test.getProject());
    }
}