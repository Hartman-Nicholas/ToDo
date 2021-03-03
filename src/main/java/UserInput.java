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
    private static final String ENTER_THE_INDEX = "Please enter the Index number of the task you want to modify:  ";
    private static final String ENTER_THE_DAY = "Please enter the Due date Format yyyy-mm-dd: ";
    private static final String OPERATION_ABORTED = "Operation aborted returning to main menu...";
    private static final Scanner scanner = new Scanner(System.in);
    private static ToDoList todoList = new ToDoList();
    private static final ASCIIArt todoArt = new ASCIIArt();


    public static void printWelcome () {
        System.out.println("\nPress");
        System.out.println("\t 0 - To create new ToDo List.");
        System.out.println("\t 1 - To Load an existing ToDo List.");
        System.out.println("\t 2 - To quit the application");

    }

    public static void printInstructions() {
        System.out.println("\nPress");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - Add ToDo");
        System.out.println("\t 2 - View ToDo List");
        System.out.println("\t 3 - Modify ToDo");
        System.out.println("\t 4 - To remove a ToDo");
        System.out.println("\t 5 - To Save your ToDo List");
        System.out.println("\t 6 - To return to home Screen");
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

    public static void printModifyList () {
        System.out.println("\nPress");
        System.out.println("\t 0 - Modify Title.");
        System.out.println("\t 1 - Modify Body.");
        System.out.println("\t 2 - Modify Due Date.");
        System.out.println("\t 3 - Modify Project.");
        System.out.println("\t 4 - Modify Status.");
        System.out.println("\t 5 - To Leave Modify List.");
    }


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
                case 0 -> optionsScreen();
                case 1 -> {
//                    UserInput.load();
                    optionsScreen();
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
                case 0 -> printInstructions();
                case 1 -> addTodo();
                case 2 -> viewToDoList();
                case 3 -> modifyItem();
                case 4 -> removeToDo();
                case 5 -> {quit = true; printWelcome();}
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
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
        System.out.printf("ToDo \"%s\" Successfully added.%n", title);
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
                case 0 -> viewBy(todoList.getTodoList());
                case 1 -> viewBy(todoList.sortByDueDate());
                case 2 -> viewBy(todoList.sortByProject());
                case 3 -> viewBy(todoList.filterByIncomplete());
                case 4 -> viewBy(todoList.filterByComplete());
                case 5 -> {quit = true; printInstructions();}
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
    }


    private static void modifyToDoList (int index) {

        boolean quit = false;
        int choice;

        printModifyList();
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
                case 0 -> modifyTitle(index);
                case 1 -> modifyBody(index);
                case 2 -> modifyProject(index);
                case 3 -> modifyDueDate(index);
                case 4 -> modifyStatus(index);
                case 5 -> quit = true;
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
    }

    public static void modifyTitle (int index) {
        String oldTitle = todoList.getTodoList().get(index).getTitle();
        String title;
        System.out.println("Please enter new title: ");
        title = setString();
        if (title.equalsIgnoreCase("QUIT")) {return; }
        todoList.getTodoList().get(index).setTitle(title);
        System.out.printf("Todo \"%s\" Successfully modified to \"%s\" .%n", oldTitle, title);
        printModifyList();
    }


    public static void modifyBody (int index) {
        String oldBody = todoList.getTodoList().get(index).getBody();
        String body;
        System.out.println("Please enter new body: ");
        body = setString();
        if (body.equalsIgnoreCase("QUIT")) {return; }
        todoList.getTodoList().get(index).setBody(body);
        System.out.printf("Todo \"%s\" Successfully modified to \"%s\" .%n", oldBody, body);
        printModifyList();
    }

    public static void modifyProject (int index) {
        String oldProject = todoList.getTodoList().get(index).getProject();
        String project;
        System.out.println("Please enter new Project: ");
        project = setString();
        if (project.equalsIgnoreCase("QUIT")) {return; }
        todoList.getTodoList().get(index).setBody(project);
        System.out.printf("Todo \"%s\" Successfully modified to \"%s\" .%n", oldProject, project);
        printModifyList();
    }

    public static void modifyDueDate (int index) {
        String oldDueDate = todoList.getTodoList().get(index).getDueDate();
        String dueDate;
        System.out.println("Please enter new Due Date: ");
        dueDate = setDueDate();
        todoList.getTodoList().get(index).setDueDate(dueDate);
        System.out.printf("Todo \"%s\" Successfully modified to \"%s\" .%n", oldDueDate, dueDate);
        printModifyList();
    }

    public static void modifyStatus (int index) {
        boolean oldStatus = todoList.getTodoList().get(index).isStatus();
        boolean status;
        System.out.println("1. for Complete: ");
        System.out.println("2. for InComplete: ");

        status = setStatus();
        todoList.getTodoList().get(index).setStatus(status);
        System.out.printf("Todo \"%s\" Successfully modified to \"%s\" .%n", oldStatus, status);
        printModifyList();
    }

    private static boolean setStatus () {
        int index;
        do{
            System.out.println(ENTER_THE_INDEX);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf(NOT_A_VALID_NUMBER, input);
                System.out.println(ENTER_THE_INDEX);
            }
            index = scanner.nextInt();

            scanner.nextLine();
            if (index < 1 || index > 2 ) {
                System.out.printf(NOT_A_VALID_CHOICE, index);
            }
        } while(index < 1 || index > 2);

        return index == 1;

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

    public static int setArrayInteger () {
        int index;
        do{
            System.out.println(ENTER_THE_INDEX);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("QUIT")) {
                    System.out.println(OPERATION_ABORTED);
                    printInstructions();
                    return -1;
                }
                System.out.printf(NOT_A_VALID_NUMBER, input);
                System.out.println(ENTER_THE_INDEX);
            }
            index = scanner.nextInt()-1;

            scanner.nextLine();
            if (index < 0 || index >= todoList.getTodoList().size() ) {
                System.out.printf(NOT_A_VALID_CHOICE, index);
            }
        } while(index < 0 || index >= todoList.getTodoList().size());
        return index;
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

    private static void viewBy (ArrayList<ToDo> toDoList) {
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



    public static void modifyItem() {
        int index;

        viewBy(todoList.getTodoList());
        index = setArrayInteger();
        if(index < 0) { return; }
        modifyToDoList(index);
        printInstructions();
    }



    public static void removeToDo () {
        String title;
        int index;

        viewBy(todoList.getTodoList());
        index = setArrayInteger();
        if(index < 0) { return; }


        title = todoList.getTodoList().get(index).getTitle();
        if (!continueCheck()) {return;}
        todoList.removeToDo(index);
        System.out.printf("Todo \"%s\" Successfully removed .%n", title);
        printInstructions();


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
