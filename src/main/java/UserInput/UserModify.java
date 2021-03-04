package UserInput;

import java.util.Scanner;

public class UserModify {


    private static final String NOT_A_VALID_CHOICE = "\"%s\" is not a valid choice.%n";
    private static final String NOT_A_VALID_NUMBER = "\"%s\" is not a valid number.%n";
    private static final String ENTER_THE_INDEX = "Please enter 1 or 2 to update accordingly:  ";

    private static final Scanner scanner = new Scanner(System.in);



    public static void printModifyList () {
        System.out.println("\nPress");
        System.out.println("\t 0 - Modify Title.");
        System.out.println("\t 1 - Modify Body.");
        System.out.println("\t 2 - Modify Project.");
        System.out.println("\t 3 - Modify Due Date.");
        System.out.println("\t 4 - Modify Status.");
        System.out.println("\t 5 - To Leave Modify List.");
    }

    private static void modifyToDoList (int index) {

        boolean quit = false;
        int choice;

        printModifyList();
        do {
            choice = UserInput.enterChoice();
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
        String oldTitle = UserInput.getTodoList().getTodoList().get(index).getTitle();
        String title;

        System.out.println("Please enter new title: ");

        title = UserInput.setString();
        if (title.equalsIgnoreCase("QUIT")) {return; }
        UserInput.getTodoList().getTodoList().get(index).setTitle(title);

        System.out.printf("Todo Title \"%s\" Successfully modified to \"%s\" .%n", oldTitle, title);
        printModifyList();
    }


    public static void modifyBody (int index) {
        String oldBody = UserInput.getTodoList().getTodoList().get(index).getBody();
        String body;

        System.out.println("Please enter new body: ");
        body = UserInput.setString();
        if (body.equalsIgnoreCase("QUIT")) {return; }

        UserInput.getTodoList().getTodoList().get(index).setBody(body);
        System.out.printf("Todo Body \"%s\" Successfully modified to \"%s\" .%n", oldBody, body);
        printModifyList();
    }

    public static void modifyProject (int index) {
        String oldProject = UserInput.getTodoList().getTodoList().get(index).getProject();
        String project;

        System.out.println("Please enter new Project: ");
        project = UserInput.setString();
        if (project.equalsIgnoreCase("QUIT")) {return; }

        UserInput.getTodoList().getTodoList().get(index).setBody(project);
        System.out.printf("Todo Project \"%s\" Successfully modified to \"%s\" .%n", oldProject, project);
        printModifyList();
    }

    public static void modifyDueDate (int index) {
        String oldDueDate = UserInput.getTodoList().getTodoList().get(index).getDueDate();
        String dueDate;

        System.out.println("Please enter new Due Date: ");
        dueDate = UserInput.setDueDate();

        UserInput.getTodoList().getTodoList().get(index).setDueDate(dueDate);
        System.out.printf("Todo Due Date \"%s\" Successfully modified to \"%s\" .%n", oldDueDate, dueDate);
        printModifyList();
    }

    public static void modifyStatus (int index) {
        boolean oldStatus = UserInput.getTodoList().getTodoList().get(index).isStatus();
        boolean status;

        System.out.println("1. for Complete: ");
        System.out.println("2. for InComplete: ");
        status = setStatus();

        UserInput.getTodoList().getTodoList().get(index).setStatus(status);
        System.out.printf("Todo Status \"%s\" Successfully updated to \"%s\" .%n", oldStatus ? "Complete" : "Incomplete", status ? "Complete" : "Incomplete");
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

    public static void modifyItem() {
        int index;

        UserView.viewBy(UserInput.getTodoList().getTodoList());
        index = UserInput.setArrayInteger();
        if(index < 0) { return; }

        modifyToDoList(index);
        UserInput.printInstructions();
    }
}
