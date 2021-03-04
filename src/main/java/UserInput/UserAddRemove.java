package UserInput;
import ToDo.ToDo;
import java.util.ArrayList;

public class UserAddRemove {


    public static void addTodo() {
        String title;
        String body;
        String dueDate;
        String project;
        boolean flag = true;

        System.out.println("Please Enter a Title: ");
        title = UserInput.setString();
        if(title.equalsIgnoreCase("QUIT")) { return; }

        if (UserInput.getTodoList().findToDoInd(title).size() > 0) {
            ArrayList<Integer> index = UserInput.getTodoList().findToDoInd(title);
            System.out.println("Please note you have the following ToDo's with the same name already loaded: ");
            viewSameToDo(index);
            System.out.println("Please advise if you would like to continue adding this ToDo");
            flag = UserInput.continueCheck();
        }
        if (!flag) { return; }

        System.out.println("Please Enter the Description: ");
        body = UserInput.setString();
        if(body.equalsIgnoreCase("QUIT")) { return; }
        System.out.println("Please Enter a Project reference: ");
        project = UserInput.setString();
        if(project.equalsIgnoreCase("QUIT")) { return; }
        dueDate = UserInput.setDueDate();
        if(dueDate.equalsIgnoreCase("QUIT")) { return; }

        UserInput.getTodoList().addToDo(new ToDo(title, body,dueDate,project));
        System.out.printf("ToDo \"%s\" Successfully added.%n", title);
        UserInput.printInstructions();
    }


    public static void removeToDo () {
        String title;
        int index;

        UserView.viewBy(UserInput.getTodoList().getTodoList());
        index = UserInput.setArrayInteger();
        if(index < 0) { return; }


        title = UserInput.getTodoList().getTodoList().get(index).getTitle();
        if (!UserInput.continueCheck()) {return;}
        UserInput.getTodoList().removeToDo(index);
        System.out.printf("Todo \"%s\" Successfully removed .%n", title);
        UserInput.printInstructions();

    }

    public static void viewSameToDo (ArrayList<Integer> index) {
        int maxSize = 20;
        int count = 1;
        System.out.printf("%-10s%-22s%-22s%-22s%-22s%-22s\n","Index:","Title:","Body:", "Project:", "Due Date:", "Status:");
        for (Integer integer : index) {
            System.out.printf("%-10s","" + count );
            if (UserInput.getTodoList().getTodoList().get(integer).getTitle().length()> maxSize) {
                System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getTitle().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getTitle());
            }
            if (UserInput.getTodoList().getTodoList().get(integer).getBody().length()> maxSize) {
                System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getBody().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getBody());
            }
            if (UserInput.getTodoList().getTodoList().get(integer).getProject().length()> maxSize) {
                System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getProject().substring(0, 16) + "...");
            } else {
                System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getProject());
            }
            System.out.printf("%-22s",UserInput.getTodoList().getTodoList().get(integer).getDueDate());
            if (UserInput.getTodoList().getTodoList().get(integer).isStatus()) {
                System.out.printf("%-22s\n", "Completed");
            } else {
                System.out.printf("%-22s\n", "Incomplete");
            }
            count++;
        }
    }




}
