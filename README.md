
# README - TODO Application

### Introduction

This Java application was developed during the Software Development Academy (SDA) to practise core Java concepts such as high cohesion, low coupling, method naming, file organisation, abstraction, Java documentation and unit testing. We also practised our useage of GIT and Github.

### App description

A Command line interface (CLI)  application that allows the user to create a ToDo tasks in a list. 

The ToDo task consists of a Title, Description, Project, Due Date and Status.

The user can then view, modify, and delete a ToDo, they are also able to mark the ToDo as complete or incomplete. In the view list menu they will have the option to sort by due date, sort by project type, filter by complete and incomplete. Once the user has finished entering their tasks they are able to save their ToDo list, they will also be able to load a previously saved ToDo list and adjust accordingly.



## Running Application

##### Requirements:

- Java 15
- GIT (optional)
- IDE able to build Maven projects if you plan to run from an IDE.
- Maven Installed if you plan to run from the command line. Maven installation guide can be found here. [Maven Installation](https://www.vogella.com/tutorials/ApacheMaven/article.html#maven_installation)



##### Using an IDE and GIT

1. In your IDE navigate to the directory where you would like to store the application.

2. Clone the project repo and pull a copy of the repo to your local machine using the following command.

   `$ git clone https://github.com/Hartman-Nicholas/ToDo.git`

3. Navigate to project folder, src, main, java and Main.java as per the tree below

![FolderTree](/img/FolderTree.png)

4. Run the Main.java file using the IDE's run command.

   

##### Using the Command Line

1. Make sure you have Maven installed as per the installation guide above, you can check if Maven is installed by running the following from the command line.

   `$ mvn -v`

2. Navigate to the project directory from the terminal and run the following command 

   ```
   $ mvn compile
   $ mvn exeC:java -Dexec.mainClass=main.java
   ```

   More information on running a Maven project from the command line can be found [here.](https://metamug.com/article/java/build-run-java-maven-project-command-line.html)



## Basic Usage

On succesfully running the application the user is greeted with the following Home Screen.

#### Home Screen

![HomeScreen](/img/HomeScreen.png)

0. Create a new ToDo List if there is currently a ToDo List loaded it will create a new list and all inputs from the old list will be lost if not saved. If there is no list loaded makes a new list.

1. Returns back to the options screen.

2. Saves a ToDo List

3. Will generate a List of previously saved ToDo's user can then type in the ToDo List they wish to load.

4. Quit.

   

#### Options Screen

![OptionsMenu](/img/OptionsMenu.png)



The option page is loaded from selecting either 0 or 1 from the home page. The option menu gives you access to the main functionality of the ToDo App. 

#### Adding a Todo

![AddingTodo](/img/AddingTodo.png)

From the options menu select zero. You will then be prompted for a serious of inputs in order to create a new ToDo task.

#### View a Todo

![ViewTodo](/img/ViewTodo.png)

From the options menu select one. 

You will then be presented with the view options list which provides you with various options to view your list. 

On selecting one of the options you are then presented with the appropriate ToDo List. If you would like to view a detailed description of the ToDo you then enter the index number of the ToDo you would like to view or quit to exit.

#### Modify a Todo

![modifyToDo](/img/modifyToDo.png)

From the options menu select 2. 

You are then presented with a list of your current ToDo's that you can select from to Modify.

Upon selecting a ToDo to modify you are then present with the modification List which allows you to choose which property of the ToDo you would like to update.

Finally before you leave the modify list you are able to view your updated ToDo to view the changes you have made.

#### Find ToDo

![findToDo](/img/findToDo.png)

From the options menu select 3.

You then choose if you would like to search by title or project.

Then enter your search criteria and a list of ToDo's that match the criteria is returned.

#### Remove ToDo

![RemoveToDo](/img/RemoveToDo.png)

From the options menu select 4

A list of your current ToDo's will be generated select the Index number of the ToDo you would like to Delete.

You are then prompted to confirm if you would like to remove the ToDo. 



## App Developer

Find out more about the Author Nicholas Hartman by going to my personal website, following me on twitter or connecting over LinkedIn.

[HartOfCode](https://www.hartofcode.com/) 

[LinkedIn](https://www.linkedin.com/in/nicholas-hartman-86201050/)

[Twitter](https://twitter.com/nich_hart)



