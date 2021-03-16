package ToDo;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.parse("1986/12/20", formatter);

    @Test
    void getTitle() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        assertEquals("Test", test.getTitle());
    }

    @Test
    void setTitle() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        test.setTitle("Setting title test");
        assertEquals("Setting title test", test.getTitle());
    }

    @Test
    void getBody() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        assertEquals("This is a test", test.getBody());
    }

    @Test
    void setBody() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        test.setBody("Setting body test");
        assertEquals("Setting body test", test.getBody());
    }

    @Test
    void getDueDate() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        assertEquals(localDate, test.getDueDate());
    }

    @Test
    void setDueDate() throws ParseException {
        ToDo test = new ToDo("Test", "This is a test",localDate ,  "David" );

        LocalDate updateDate = LocalDate.parse("1988/12/20", formatter);
        test.setDueDate(updateDate);
        assertEquals(updateDate, test.getDueDate());
    }

    @Test
    void getStatus() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        assertFalse(test.getStatus());
    }

    @Test
    void setStatus() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        test.setStatus(false);
        assertFalse(test.getStatus());
    }

    @Test
    void getProject() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        assertEquals("David", test.getProject());
    }

    @Test
    void setProject() {
        ToDo test = new ToDo("Test", "This is a test", localDate,  "David" );
        test.setProject("Setting project test");
        assertEquals("Setting project test", test.getProject());
    }
}