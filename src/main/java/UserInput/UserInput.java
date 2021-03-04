package UserInput;

import ToDo.ToDoList;
import utils.ASCIIArt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class UserInput {

    private static final String YES_NO_OR_QUIT = "Please enter Yes, No or Quit";
    private static final String PLEASE_ENTER_FILE = "Please enter your file name or quit to exit: ";
    private static final String NOT_A_VALID_CHOICE = "\"%s\" is not a valid choice.%n";
    private static final String NOT_A_VALID_NUMBER = "\"%s\" is not a valid number.%n";
    private static final String ENTER_THE_INDEX = "Please enter the Index number of the task you want to modify:  ";
    private static final String ENTER_THE_DAY = "Please enter the Due date Format yyyy-mm-dd: ";
    private static final String OPERATION_ABORTED = "Operation aborted returning to main menu...";
    private static final Scanner scanner = new Scanner(System.in);
    private static ToDoList todoList = new ToDoList();
    private static final ASCIIArt todoArt = new ASCIIArt();

    public static ToDoList getTodoList() {
        return todoList;
    }

    public static void printWelcome () {
        System.out.println("\nHome Page");
        System.out.println("\t 0 - To create new ToDo List.");
        System.out.println("\t 1 - To Load an existing ToDo List.");
        System.out.println("\t 2 - To quit the application");

    }

    public static void printInstructions() {
        System.out.println("\nTodo Page");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - Add ToDo");
        System.out.println("\t 2 - View ToDo List");
        System.out.println("\t 3 - Modify ToDo");
        System.out.println("\t 4 - To remove a ToDo");
        System.out.println("\t 5 - To Save your ToDo List");
        System.out.println("\t 6 - To return to home Screen");
    }

    public static int enterChoice () {
        int choice;
        System.out.println("Enter your choice: " );
        while (!scanner.hasNextInt()) {
            String input = scanner.next();
            System.out.printf(NOT_A_VALID_NUMBER, input);
            System.out.println("Enter your choice: ");
        }

        choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    public static void welcomeScreen() {
        boolean quit = false;
        int choice;

        todoArt.drawArt();
        UserInput.printWelcome();
        do {
            choice = enterChoice();

            switch (choice) {
                case 0 -> checkLoseChanges();
                case 1 -> { loadToDoList(); optionsScreen(); }
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
            choice = enterChoice();

            switch (choice) {
                case 0 -> printInstructions();
                case 1 -> UserAddRemove.addTodo();
                case 2 -> UserView.viewToDoList();
                case 3 -> UserModify.modifyItem();
                case 4 -> UserAddRemove.removeToDo();
                case 5 -> saveToDoList();
                case 6 -> {printWelcome();quit = true;}
                default -> System.out.println("Invalid Selection");
            }
        } while (!quit);
    }


    public static void loadToDoList () {

        System.out.println("Please enter the file name to load: ");

        do {
            String filePath = setString();
            if (filePath.equalsIgnoreCase("QUIT")) {
                return;
            }
            String appendedFile = todoList.checkFile(filePath) ? filePath : filePath + ".txt";
            try {
                todoList = todoList.loadToDoList(appendedFile);
                return;
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            } catch (ClassNotFoundException e) {
                System.out.println("Error: Class not found");
            }
        } while (true);
    }

    public static void saveToDoList ()  {

        String filePath;
        boolean flag = true;

        do {
            System.out.println(PLEASE_ENTER_FILE);
            filePath = setString();
            if (filePath.equalsIgnoreCase("QUIT")) {
                return;
            }

            String appendedFile = todoList.checkFile(filePath) ? filePath : filePath + ".txt";

            File checkFile = new File (appendedFile);

            if (checkFile.exists()) {
                System.out.printf("Please note if you continue you will be overwriting this \"%s\" file .%n", filePath);
                flag = continueCheck();
            }

            if (!flag) { return; }

            try {

                todoList.saveToDoList(appendedFile, todoList);
                System.out.println("Your planner has been saved successfully");
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file name.");
            } catch (IOException e) {
                System.out.println("Hi there" + e);
            }
        } while (true);
    }


    private static boolean checkToDoLength () {
        return todoList.getTodoList().size() > 0;
    }


    private static void checkLoseChanges () {
        if (checkToDoLength()) {
            System.out.println("Please note if you continue your changes will be lost ");
            UserView.viewBy(todoList.getTodoList());
            if(continueCheck()) {
                todoList.getTodoList().clear();
                optionsScreen();
            }
        }
        optionsScreen();
    }


    public static boolean continueCheck() {
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


    public static String setDueDate() {
        String dueDate;

        do
        {
            System.out.println(ENTER_THE_DAY);
                dueDate = setString();
                if(dueDate.equalsIgnoreCase("QUIT")) {
                    return "QUIT";
                } else if (!isDateValid(dueDate)) {
                    System.out.println("Invalid date or format");
                }
        } while (!isDateValid(dueDate));
        return dueDate;
    }


    private static boolean isDateValid (String date) {
        boolean valid;

        try {

            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT));

            valid = true;

        } catch (DateTimeParseException e) {
            valid = false;
        }

        return valid;
    }


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

}
