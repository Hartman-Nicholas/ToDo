import ToDo.ToDo;
import ToDo.*;

import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {

        ToDo test = new ToDo("Test", "This is a test", new GregorianCalendar(2021,02, 27), true, "David" );
        ToDo test2 = new ToDo("Dummy Value", "This is a duplicate test", new GregorianCalendar(2021,05, 27), true, "Andrew" );
        ToDo test3 = new ToDo("Test", "This is a duplicate test", new GregorianCalendar(2021,01, 27), true, "Andrew" );

        ToDoList testList = new ToDoList();
        testList.addToDo(test);
        testList.addToDo(test2);
        testList.addToDo(test3);

        testList.sortByDueDate();
        testList.sortByProject();

    }
}
