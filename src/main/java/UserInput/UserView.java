package UserInput;

import ToDo.ToDo;
import java.util.ArrayList;

public class UserView {


    public static void printViewList () {
        System.out.println("\nView List");
        System.out.println("\t 0 - View ToDoList.");
        System.out.println("\t 1 - View sorted by dueDate.");
        System.out.println("\t 2 - View sorted by Projects.");
        System.out.println("\t 3 - View Incomplete ToDo's.");
        System.out.println("\t 4 - View Complete ToDo's.");
        System.out.println("\t 5 - To Leave view List.");
    }

    public static void viewToDoList () {

        boolean quit = false;
        int choice;

        printViewList();
        do {
            choice = UserInput.enterChoice();

            switch (choice) {
                case 0 -> viewBy(UserInput.getTodoList().getTodoList());
                case 1 -> viewBy(UserInput.getTodoList().sortByDueDate());
                case 2 -> viewBy(UserInput.getTodoList().sortByProject());
                case 3 -> viewBy(UserInput.getTodoList().filterByIncomplete());
                case 4 -> viewBy(UserInput.getTodoList().filterByComplete());
                case 5 -> {quit = true; UserInput.printInstructions();}
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
    }



    public static void viewBy (ArrayList<ToDo> toDoList) {
        int maxSize = 20;
        int count = 1;
        System.out.printf("%-10s%-22s%-22s%-22s%-22s%-22s\n","Index:","Title:","Body:", "Project:", "Due Date:", "Status:");
        for (ToDo toDo : toDoList) {
            System.out.printf("%-10s","" + count );
            if (toDo.getTitle().length() > maxSize) {
                System.out.printf("%-22s",toDo.getTitle().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",toDo.getTitle());
            }
            if (toDo.getBody().length() > maxSize) {
                System.out.printf("%-22s",toDo.getBody().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",toDo.getBody());
            }
            if (toDo.getProject().length() > maxSize) {
                System.out.printf("%-22s",toDo.getProject().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",toDo.getProject());
            }
            System.out.printf("%-22s",toDo.getDueDate());
            if (toDo.isStatus()) {
                System.out.printf("%-22s\n", "Completed");
            } else {
                System.out.printf("%-22s\n", "Incomplete");
            }
            count++;
        }

    }




}
