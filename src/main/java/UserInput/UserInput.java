package UserInput;

import ToDo.ToDo;
import ToDo.ToDoList;
import utils.ASCIIArt;
import utils.QuitException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class UserInput {
    private static final String NOT_A_VALID_CHOICE = "\"%s\" is not a valid choice.%n";
    private static final String ENTER_THE_INDEX = "Please enter your choice:  ";
    public static final String OPERATION_ABORTED = "Operation aborted";

    private static final ASCIIArt todoArt = new ASCIIArt();
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static ToDoList todoList = new ToDoList();


    /**********************************************  OPTION SECTION  **************************************************/


    public static void homeScreen() {
        boolean quit = false;

        todoArt.drawArt("TODO");

        do {
            Printer.printHomeScreen();
            int choice;
            try { choice = setUserIntInput(4); }
            catch (QuitException e) { return; }

            switch (choice) {
                case 0 -> ifArrayLoadedCheckBeforeOverwriting();
                case 1 -> optionsScreen();
                case 2 -> saveToDoList();
                case 3 -> { viewSavedToDoFiles();
                    if (loadToDoList()) {
                        optionsScreen();
                    }
                }
                case 4 -> quit = true;
            }
        } while (!quit);
        scanner.close();
    }

    private static void optionsScreen () {
        boolean quit = false;

        do {
            Printer.printInstructions();
            int choice;
            try { choice = setUserIntInput(5); }
            catch (QuitException e) { return; }

            switch (choice) {
                case 0 -> addTodo();
                case 1 -> viewToDoList();
                case 2 -> chooseToDoToModify();
                case 3 -> searchForToDo();
                case 4 -> removeToDo();
                case 5 -> quit = true;
            }
        } while (!quit);
    }

    private static void modifyToDoList (ToDo todo)  {
        boolean quit = false;

        do {
            Printer.printModifyList();

            int choice;
            try { choice = setUserIntInput(6); }
            catch (QuitException e) { return; }

            if (choice <= 4 && choice>=0) {
                modifyToDo(todo, choice);
            } else if (choice == 5) {
                todo.viewTodo();
            } else {
                quit = true;
            }
        } while (!quit);
    }

    private static void searchForToDo() {

        do {
            Printer.printFindOptions();

            int choice;
            String data;
            try {
                choice = setUserIntInput(2);
                if (choice == 2) { return; }

                System.out.println("Please enter the search criteria:");
                data = setUserStringInput();

            } catch (QuitException e) { return; }

            if (choice == 1 || choice==0) {
                Printer.viewBy(todoList.findBy(choice, data));
            }

        } while (true);
    }


    /***********************************************  ADD SECTION  ****************************************************/


    private static void addTodo() {
        String title;
        String body;
        String project;
        LocalDate dueDate;

        try {
            System.out.println("Please Enter a Title: ");
            title = setUserStringInput();

            boolean flag = true;
            if (todoList.findBy(0, title).size() > 0) {
                System.out.println("Please note you have the following ToDo's with the same name already loaded: ");
                Printer.viewBy(todoList.findBy(0,title));
                System.out.println("Please advise if you would like to continue adding this ToDo");
                flag = continueCheck();
            }

            if (!flag) { return; }

            System.out.println("Please Enter the Description: ");
            body = setUserStringInput();

            System.out.println("Please Enter a Project reference: ");
            project = setUserStringInput();

            dueDate = setDueDate();

        } catch (QuitException e) { return; }

        todoList.addToDo(new ToDo(title, body, dueDate, project));
        System.out.printf("ToDo \"%s\" Successfully added.%n", title);
    }


    /***********************************************  VIEW SECTION  ***************************************************/


    private static void viewToDoList () {
        boolean quit = false;

        if (todoList.getSize() == 0) {
            System.out.println("You have no ToDo's to view");
            return;
        }

        do {
            Printer.printViewList();

            int choice;
            try { choice = setUserIntInput(5); }
            catch (QuitException e) { return; }

            switch (choice) {

                // toDoList no sorting, if sorting applied will show last sorted
                // option
                case 0 -> {
                    Printer.viewBy(todoList.getTodoList());
                    viewDetailedToDo(todoList.getTodoList());
                }
                // [0] toDoList sorted by Due Date.
                case 1 -> {
                    Printer.viewBy(todoList.sortBy(0));
                    viewDetailedToDo(todoList.getTodoList());
                }
                // [1] toDoList sorted by Project.
                case 2 -> {
                    Printer.viewBy(todoList.sortBy(1));
                    viewDetailedToDo(todoList.sortBy(1));
                }
                // [0] toDoList filtered by Complete.
                case 3 -> {
                    Printer.viewBy(todoList.filterBy(0));
                    viewDetailedToDo(todoList.filterBy(0));
                }
                // [1] toDoList filtered by Incomplete.
                case 4 -> {
                    Printer.viewBy(todoList.filterBy(1));
                    viewDetailedToDo(todoList.filterBy(1));
                }
                case 5,-1 -> quit = true;
            }
        } while (!quit);
    }

    /**
     * Shows the user a ToDo List from which they can select one
     * of the ToDo objects so they can get a detailed view of that object.
     * @param toDoArray takes an Array type of List that is needed so
     *                 the user can select the ToDo they wish to view
     */

    private static void viewDetailedToDo(List<ToDo> toDoArray) {
        int index;
        if (toDoArray.size() == 0) { return; }

        System.out.println("Please select a ToDo from the list or quit to exit.");

        try { index = setUserIntInput(toDoArray.size()); }
        catch (QuitException e) { return; }

        if (index < 0) { return; }

        toDoArray.get(index).viewTodo();
    }


    /***********************************************  MODIFY SECTION  *************************************************/


    private static void chooseToDoToModify() {
        int arrayIndex;

        if (todoList.getSize() == 0) {
            System.out.println("You have no ToDo's to Modify");
            return;
        }

        Printer.viewBy(todoList.getTodoList());
        try {
            arrayIndex = setUserIntInput(todoList.getSize()-1);
        } catch (QuitException e) { return; }

        if(arrayIndex < 0) { return; }

        modifyToDoList(todoList.getToDo(arrayIndex));
    }

    private static void modifyToDo(ToDo todo, int index) {
        Object data = "";
        System.out.println("Please enter updated information: ");

        try {
            if (index >=0 && index <=2) {
                data = setUserStringInput();
            }
            else if (index == 3) {
                data = setDueDate();
            }
            else if (index == 4) {
                System.out.println("1. for Complete: ");
                System.out.println("2. for InComplete: ");

                data = setStatus();
            }
        } catch (QuitException e) { return; }

        Object oldValue = todoList.modify(todo, index, data);
        System.out.printf("Todo \"%s\" Successfully updated to \"%s\" .%n", oldValue, data);
    }


    /***********************************************  REMOVE SECTION  *************************************************/


    private static void removeToDo () {
        String title;
        int index;

        if (todoList.getSize() == 0) {
            System.out.println("You have no ToDo's to remove");
            return;
        }

        Printer.viewBy(todoList.getTodoList());

        try { index = setUserIntInput(todoList.getSize()-1); }
        catch (QuitException e) { return; }

        title = todoList.getTodoList().get(index).getTitle();
        System.out.printf("You are about to remove the following Todo \"%s\" .%n", title);

        if (!continueCheck()) {return;}

        todoList.removeToDo(index);
        System.out.printf("Todo \"%s\" Successfully removed .%n", title);
    }


    /******************************************  SAVE AND LOAD SECTION ************************************************/


    private static void saveToDoList ()  {
        boolean flag = true;

        if (todoList.getSize() == 0) {
            System.out.println("Please note your ToDo List is empty");
        }

        do {
            System.out.println("Please enter your file name or quit to exit: ");

            String filePath;
            try { filePath = setUserStringInput(); }
            catch (QuitException e) { return; }

            String appendedFile = Validators.checkFilePathEndsWithToDo(filePath) ? filePath : filePath + ".toDo";

            File checkFile = new File (appendedFile);

            if (checkFile.exists()) {
                System.out.printf("Please note if you continue you will be overwriting this \"%s\" file .%n", filePath);
                flag = continueCheck();
            }

            if (!flag) { return; }

            try {
                UserSaveAndLoad.saveToDoList(appendedFile, todoList);
                System.out.println("Your planner has been saved successfully.");
                break;
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file name.");
            } catch (IOException e) {
                System.out.println("Input output exception:" + e);
            }
        } while (true);
    }

    private static boolean loadToDoList () {
        System.out.println("Please enter the file name to load or quit to exit: ");

        do {
            String filePath;
            try { filePath = setUserStringInput(); }
            catch (QuitException e) { return false; }


            String appendedFile = Validators.checkFilePathEndsWithToDo(filePath) ? filePath : filePath + ".toDo";
            try {
                todoList = UserSaveAndLoad.loadToDoList(appendedFile);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error: " + e);
            } catch (ClassNotFoundException e) {
                System.out.println("Error: Class not found");
            } catch (Exception e) {
                System.out.println("Incorrect file format please load a ToDoList");
            }
        } while (true);
    }

    /**
     * Checks the project directory to see if there are any existing toDoLists saved.
     * If there are no previous toDoLists prints a message to the console informing the user.
     * If there are previous toDoLists prints a list of these on the console window.
     */

    private static void viewSavedToDoFiles () {
        String [] pathNames;

        FilenameFilter filter = (f, name) -> name.endsWith(".toDo");
        File current = new File(System.getProperty("user.dir"));
        pathNames = current.list(filter);

        if (pathNames != null) {
            System.out.println("You have the following ToDo Lists saved:");

            for (String pathname : pathNames) {
                System.out.println(pathname);
            }
        } else {
            System.out.println("No saved files found");
        }
    }




    /*********************************************  HELPER SECTIONS ***************************************************/


    private static String setUserStringInput() throws QuitException {
        String userStringInput;
        userStringInput = scanner.nextLine();
        if (userStringInput.equalsIgnoreCase("QUIT")) {
            System.out.println(OPERATION_ABORTED);
            throw new QuitException();
        }
        return userStringInput;

    }



    private static int setUserIntInput(int maxSize) throws QuitException {
        int userIntInput;
        do{
            System.out.println(ENTER_THE_INDEX);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                if (input.equalsIgnoreCase("QUIT")) {
                    System.out.println(OPERATION_ABORTED);
                    throw new QuitException();
                }
                System.out.printf(NOT_A_VALID_CHOICE, input);
                System.out.println(ENTER_THE_INDEX);
            }
            userIntInput = scanner.nextInt();

            scanner.nextLine();
            if (userIntInput < 0 || userIntInput > maxSize ) {
                System.out.printf(NOT_A_VALID_CHOICE, userIntInput);
            }
        } while(userIntInput < 0 || userIntInput > maxSize);
        return userIntInput;
    }

    private static LocalDate setDueDate() throws QuitException {
        String dueDate;

        do
        {
            String ENTER_THE_DAY = "Please enter the Due date Format yyyy/mm/dd: ";
            System.out.println(ENTER_THE_DAY);
            dueDate = setUserStringInput();
            if(dueDate.equalsIgnoreCase("QUIT")) {
                throw new QuitException();
            } else if (!Validators.isDateValid(dueDate)) {
                System.out.println("Invalid date or format");
            }
        } while (!Validators.isDateValid(dueDate));

        return LocalDate.parse(dueDate, formatter);
    }

    private static boolean setStatus () throws QuitException {
        int index;
        do{
            System.out.println(ENTER_THE_INDEX);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf(NOT_A_VALID_CHOICE, input);
                System.out.println(ENTER_THE_INDEX);
                if (input.equalsIgnoreCase("QUIT")) {
                    throw new QuitException();
                }
            }
            index = scanner.nextInt();

            scanner.nextLine();
            if (index < 1 || index > 2 ) {
                System.out.printf(NOT_A_VALID_CHOICE, index);
            }
        } while(index < 1 || index > 2);

        return index == 1;
    }


    /**
     * Helper method to check if a user would like to continue with
     * the operation or exit.
     * @return boolean true if they want to continue and false if
     * they do not.
     */

    private static boolean continueCheck() {
        String flag;
        do {
            System.out.println("Would you like to continue? Yes or No");
            flag = scanner.nextLine();
            if(flag.equalsIgnoreCase("YES")) {
                return true;
            } else if (flag.equalsIgnoreCase("NO")) {
                System.out.println(OPERATION_ABORTED);
                return false;
            } else {
                System.out.println("Please enter Yes or NO");
            }
        } while (true);
    }

    /**
     * If user has an toDoList with objects loaded and selects new ToDo list
     * This first checks if they want to continue and notifies them that if they continue
     * any unsaved changes to the toDoList will be lost.
     */

    private static void ifArrayLoadedCheckBeforeOverwriting() {
        if (todoList.getSize() > 0) {
            System.out.println("Please note if you continue this list will be lost if not saved.");
            Printer.viewBy(todoList.getTodoList());
            if(continueCheck()) {
                todoList.getTodoList().clear();
                optionsScreen();
            }
        }
        optionsScreen();
    }
}
