import ToDo.ToDo;
import ToDo.ToDoList;
import utils.ASCIIArt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInput {

    private static final String FOUND_TASK = "Found the following task: ";
    private static final String YES_NO_OR_QUIT = "Please enter Yes, No or Quit";
    private static final String NOT_A_VALID_CHOICE = "\"%s\" is not a valid choice.%n";
    private static final String NOT_A_VALID_NUMBER = "\"%s\" is not a valid number.%n";
    private static final String ENTER_THE_TIME = "Please enter the time between 0-23: ";
    private static final String ENTER_THE_DAY = "Please enter the Due date Format yyyy-mm-dd: ";
    private static final String OPERATION_ABORTED = "Operation aborted returning to main menu...";
    private static final Scanner scanner = new Scanner(System.in);
    private static ToDoList todoList = new ToDoList();
    private static final ASCIIArt todoArt = new ASCIIArt();
//
//
    public static void welcomeScreen() {
        boolean quit = false;
        int choice;

        todoArt.drawArt();
        UserInput.printWelcome();
        do {
            System.out.println("Enter your choice: " );
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf(NOT_A_VALID_NUMBER, input);
                System.out.println("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0 -> UserInput.optionsScreen();
                case 1 -> {
//                    UserInput.load();
                    UserInput.optionsScreen();
                }
                case 2 -> quit = true;
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
        scanner.close();
    }


    public static void optionsScreen () {
        boolean quit = false;
        int choice;

        UserInput.printInstructions();
        do {
            System.out.println("Enter your choice: " );
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf(NOT_A_VALID_NUMBER, input);
                System.out.println("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0 -> UserInput.printInstructions();
                case 1 -> UserInput.addTodo();
                case 2 -> UserInput.viewToDoList();
                case 3 -> UserInput.addTodo();
//                case 4 -> UserInput.viewToDoByProject();
//                case 5 -> UserInput.searchForItem();
//                case 6 -> UserInput.savePlanner();
//                case 7 -> quit = true;
                case 8 -> quit = true;
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
    }

    public static void printWelcome () {
        System.out.println("\nPress");
        System.out.println("\t 0 - To create new ToDo.");
        System.out.println("\t 1 - To Load an existing ToDo.");
        System.out.println("\t 2 - To quit the application");

    }

    public static void printInstructions() {
        System.out.println("\nPress");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - To view the Todo by Due Date");
        System.out.println("\t 2 - To view the Todo by Project");
        System.out.println("\t 3 - To add a ToDo to the ToDo List");
        System.out.println("\t 4 - To modify a ToDo");
        System.out.println("\t 5 - To remove a ToDo from the ToDo List");
        System.out.println("\t 6 - To search for a Todo");
        System.out.println("\t 7 - Save your ToDo List");
        System.out.println("\t 8 - To quit the application.");
    }

    public static void printViewList () {
        System.out.println("\nPress");
        System.out.println("\t 0 - View ToDoList.");
        System.out.println("\t 1 - View sorted by dueDate.");
        System.out.println("\t 2 - View sorted by Projects.");
        System.out.println("\t 3 - View Incomplete ToDo's.");
        System.out.println("\t 4 - View Complete ToDo's.");
        System.out.println("\t 5 - To Leave view List.");
    }

    public static void addTodo() {
        String title;
        String body;
        String dueDate;
        String project;
        boolean flag = true;

        System.out.println("Please Enter a Title: ");
        title = setString();
        if(title.equalsIgnoreCase("QUIT")) { return; }

        if (todoList.findToDoInd(title).size() > 0) {
            ArrayList<Integer> index = todoList.findToDoInd(title);
            System.out.println("Please note you have the following Tasks with the same name already loaded: ");
            viewSameToDo(index);
            System.out.println("Please advise if you would like to continue adding this task");
            flag = continueCheck();
        }
        if (!flag) { return; }

        System.out.println("Please Enter the Description: ");
        body = setString();
        if(body.equalsIgnoreCase("QUIT")) { return; }
        System.out.println("Please Enter a Project reference: ");
        project = setString();
        if(project.equalsIgnoreCase("QUIT")) { return; }
        dueDate = setDueDate();
        if(dueDate.equalsIgnoreCase("QUIT")) { return; }

        todoList.addToDo(new ToDo(title, body,dueDate,project));
        System.out.printf("Task \"%s\" Successfully added.%n", title);
        printInstructions();
    }

    private static void viewToDoList () {

        boolean quit = false;
        int choice;

        printViewList();
        do {
            System.out.println("Enter your choice: " );
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf(NOT_A_VALID_NUMBER, input);
                System.out.println("Enter your choice: ");
            }

            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0 -> viewSortedByDueDate(todoList.sortByDueDate());
                case 1 -> UserInput.addTodo();
                case 2 -> UserInput.viewToDoList();
//                case 4 -> UserInput.viewToDoByProject();
//                case 5 -> UserInput.searchForItem();
//                case 6 -> UserInput.savePlanner();
//                case 7 -> quit = true;
                case 8 -> quit = true;
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
    }


    public static void viewToDoByDate() {
        todoList.sortByDueDate();
    }

    public static void viewToDoByProject() {
        todoList.sortByProject();
    }

    public static String setString () {
        String string;
        string = scanner.nextLine();
        if (string.equalsIgnoreCase("QUIT")) {
            System.out.println(OPERATION_ABORTED);
            printInstructions();
            return string;
        }
        return string;
    }

    private static void viewSameToDo (ArrayList<Integer> index) {
        int maxSize = 20;
        int count = 1;
        System.out.printf("%-10s%-22s%-22s%-22s%-22s%-22s\n","Index:","Title:","Body:", "Project:", "Due Date:", "Status:");
        for (Integer integer : index) {
            System.out.printf("%-10s","" + count );
            if (todoList.getTodoList().get(integer).getTitle().length()> maxSize) {
                System.out.printf("%-22s",todoList.getTodoList().get(integer).getTitle().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",todoList.getTodoList().get(integer).getTitle());
            }
            if (todoList.getTodoList().get(integer).getBody().length()> maxSize) {
                System.out.printf("%-22s",todoList.getTodoList().get(integer).getBody().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",todoList.getTodoList().get(integer).getBody());
            }
            if (todoList.getTodoList().get(integer).getProject().length()> maxSize) {
                System.out.printf("%-22s",todoList.getTodoList().get(integer).getProject().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",todoList.getTodoList().get(integer).getProject());
            }
            System.out.printf("%-22s",todoList.getTodoList().get(integer).getDueDate());
            if (todoList.getTodoList().get(integer).isStatus()) {
                System.out.printf("%-22s\n", "Completed");
            } else {
                System.out.printf("%-22s\n", "Incomplete");
            }
            count++;
        }
    }

    private static void viewSortedByDueDate (ArrayList<ToDo> toDoList) {
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





//
//    public static void load () {
//
//        String filePath = "BobsPlan.txt";
//
//        try {
//            todoList = todoList.loadToDoList(filePath);
//        } catch (Exception e) {
//            System.out.println("Hi There " + e);
//        }
//    }


//
//    public static void modifyItem() {
//        String day;
//        int time;
//        String task;
//        boolean flag;
//        String oldTask;
//
//
//        day = setDay();
//        if(day.equals("QUIT")) { return; }
//        time = setTime();
//        if(time < 0) { return; }
//
//
//
//        if(planner.checkTask(time, day)) {
//            oldTask = planner.findTask(time,day);
//            System.out.println(FOUND_TASK + oldTask );
//
//            flag = continueCheck();
//            if (!flag) { return; }
//
//            System.out.println("Please enter new task name: ");
//            task = scanner.nextLine();
//            planner.addTask(time, day, task);
//            System.out.printf("Task \"%s\" Successfully updated.%n", oldTask);
//
//        } else {
//            System.out.println("Task not found");
//        }
//        printInstructions();
//
//    }
//
//    public static void removeItem () {
//
//        String day;
//        int time;
//        boolean flag;
//        String oldTask;
//
//        day = setDay();
//        if(day.equals("QUIT")) { return; }
//        time = setTime();
//        if(time < 0) { return; }
//
//
//        if(planner.checkTask(time, day)) {
//            oldTask = planner.findTask(time,day);
//            System.out.println(FOUND_TASK + oldTask );
//
//            flag = continueCheck();
//            if (!flag) { return; }
//
//            planner.removeTask(time, day);
//            System.out.printf("Task \"%s\" Successfully deleted.%n", oldTask);
//
//        } else {
//            System.out.println("You have no tasks to Delete please check your weekly planner.");
//        }
//        printInstructions();
//
//    }
//
//    public static void searchForItem () {
//        String day;
//        int time;
//        String oldTask;
//
//        day = setDay();
//        if(day.equals("QUIT")) { return; }
//        time = setTime();
//        if(time < 0) { return; }
//
//
//        if(planner.checkTask(time, day)) {
//            oldTask = planner.findTask(time,day);
//            System.out.println(FOUND_TASK + oldTask );
//
//        } else {
//            System.out.println("You have no tasks at this time.");
//        }
//        printInstructions();
//
//    }
//
//    public static void savePlanner ()  {
//
//        String filePath;
//        boolean flag = true;
//
//        do {
//            System.out.println("Please enter your file name: ");
//            filePath = scanner.nextLine();
//
//            String appendedFile = planner.checkFile(filePath) ? filePath : filePath + ".txt";
//
//            File checkFile = new File (appendedFile);
//
//            if (checkFile.exists()) {
//                System.out.printf("Please note if you continue you will be overwriting this \"%s\" file .%n", filePath);
//                flag = continueCheck();
//            }
//
//            if (!flag) { return; }
//
//            try {
//
//                todoList.savePlanner(appendedFile, planner);
//                System.out.println("Your planner has been saved successfully");
//                break;
//            } catch (FileNotFoundException e) {
//                System.out.println("Invalid file name.");
//            } catch (IOException e) {
//                System.out.println("Hi there" + e);
//            }
//        } while (true);
//    }

    private static boolean continueCheck() {
        String flag;
        do {
            System.out.println("Would you like to continue? Yes, No or Quit");
            flag = scanner.nextLine();
            if(flag.equalsIgnoreCase("YES")) {
                return true;
            } else if (flag.equalsIgnoreCase("NO") || flag.equalsIgnoreCase("QUIT")) {
                System.out.println(OPERATION_ABORTED);
                printInstructions();
                return false;
            } else {
                System.out.println(YES_NO_OR_QUIT);
            }
        } while (true);
    }

    private static String setDueDate() {
        String dueDate;
        do
        {
            System.out.println(ENTER_THE_DAY);
                dueDate = scanner.nextLine();
                if(dueDate.equalsIgnoreCase("QUIT")) {
                    System.out.println(OPERATION_ABORTED);
                    printInstructions();
                    return "QUIT";
                }
            System.out.println("Invalid date or format");
        } while (!isDateValid(dueDate));
        return dueDate;
    }

    private static boolean isDateValid (String date) {

        boolean valid;

        try {

            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );

            valid = true;

        } catch (DateTimeParseException e) {
            valid = false;
        }

        return valid;
    }
}
