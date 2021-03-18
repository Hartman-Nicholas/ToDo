package UserInput;

import ToDo.ToDo;

import java.util.List;

public class Printer {



    public static void printHomeScreen () {
        System.out.println("\nHome Page");
        System.out.println("\t 0 - To create new ToDo List.");
        System.out.println("\t 1 - Go to options page.");
        System.out.println("\t 2 - Save ToDo List.");
        System.out.println("\t 3 - To Load an existing ToDo List.");
        System.out.println("\t 4 - To quit the application");

    }

    public static void printInstructions() {
        System.out.println("\nOptions Menu");
        System.out.println("\t 0 - Add ToDo");
        System.out.println("\t 1 - View ToDo List");
        System.out.println("\t 2 - Modify ToDo");
        System.out.println("\t 3 - Search for ToDo");
        System.out.println("\t 4 - To remove a ToDo");
        System.out.println("\t 5 - To return to home Screen");
    }

    public static void printModifyList () {
        System.out.println("\nPress");
        System.out.println("\t 0 - Modify Title.");
        System.out.println("\t 1 - Modify Body.");
        System.out.println("\t 2 - Modify Project.");
        System.out.println("\t 3 - Modify Due Date.");
        System.out.println("\t 4 - Modify Status.");
        System.out.println("\t 5 - View ToDo.");
        System.out.println("\t 6 - To Leave Modify List.");
    }

    public static void printViewList () {
        System.out.println("\nView List");
        System.out.println("\t 0 - View ToDoList.");
        System.out.println("\t 1 - View sorted by dueDate.");
        System.out.println("\t 2 - View sorted by Projects.");
        System.out.println("\t 3 - View Complete ToDo's.");
        System.out.println("\t 4 - View Incomplete ToDo's.");
        System.out.println("\t 5 - To Leave view List.");
    }

    public static void printFindOptions() {
        System.out.println("\nSearch Options");
        System.out.println("\t 0 - Search By Title");
        System.out.println("\t 1 - Search By Project");
        System.out.println("\t 2 - To Leave Search List");
    }

    public static void viewBy (List<ToDo> toDoList) {

        int count = 0;
        System.out.printf("%-10s%-22s%-22s%-22s%-22s%-22s\n","Index:","Title:","Body:", "Project:", "Due Date:", "Status:");
        for (ToDo toDo : toDoList) {
            System.out.printf("%-10s","" + count );
            subStringBasedOnLength(toDo.getTitle());
            subStringBasedOnLength(toDo.getBody());
            subStringBasedOnLength(toDo.getProject());
            System.out.printf("%-22s",toDo.getDueDate());
            System.out.println(toDo.getStatus() ? "Complete" : "Incomplete");
            count++;
        }

    }

    private static void subStringBasedOnLength (String data) {
        int maxSize = 20;

        if (data.length() > maxSize) {
            System.out.printf("%-22s",data.substring(0, 16) + "...");
        } else {
            System.out.printf("%-22s",data);
        }
    }



}
