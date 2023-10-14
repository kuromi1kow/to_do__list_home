package org.example;
import Interface.finishAndStartProgram;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Date;
public class toDoList implements finishAndStartProgram {
    private List<String> notes;
    public toDoList() throws SQLException {
        notes=new ArrayList<>();
    }
    Scanner scanner = new Scanner(System.in);
    Date date=new Date();
    public void mainMenu(){
        System.out.println("[*] --- To Do List");
        System.out.println("[1] --> Register");
        System.out.println("[2] --> Login");
        System.out.println("[3] --> Exit");
    }
    @Override
    public void mainMenu(List <User> users) throws SQLException {
        mainMenu();
        String select = scanner.next();
        int num=Integer.parseInt(select);
        do{
            switch (num){
                case 1:
                    register(users);
                    break;
                case 2:
                    login(users);
                    break;
                case 3:
                    exit();
                    break;
                default:
                    break;
            }
        }while (num!=3);
    }
    public void register(List<User> users) throws SQLException {
        System.out.println("Enter your name below: ");
        String name = scanner.next();
        System.out.println("Enter your surname below: ");
        String surname = scanner.next();
        System.out.println("Create your password: ");
        String password = scanner.next();
        User user1 = new User(name, surname, password);
        users.add(user1);
        int index = users.size()-1;
        Connection connection = DriverManager.getConnection(DB_URL, "postgres", "asdfghjkl");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO  roles (name, surname, password) VALUES (?,?,?)");
        statement.setString(1, user1.getName());
        statement.setString(2, user1.getSurname());
        statement.setString(3, user1.getPassword());
        statement.executeUpdate();
        connection.close();
        System.out.println("Successfully registered! ");
        userMenu(users, index);
    }
    public void login(List <User> users){
        String name;
        String surname;
        String password;
        System.out.println("Enter your name: ");
        name = scanner.next();
        System.out.println("Enter your surname: ");
        surname=scanner.next();
        System.out.println("Enter your password: ");
        password= scanner.next();
        int index;
        for (int i = 0; i <users.size() ; i++) {
            if (name.equals(users.get(i).getName()) && surname.equals(users.get(i).getSurname()) && password.equals(users.get(i).getPassword())) {
                index = i;
                userMenu(users, index);
            }
            else
                System.out.println("Invalid name or surname");
        }
    }

    public void userMenu(List <User> users, int index) {
        System.out.println("[*]---- To Do List ----");
        System.out.println("[1] --> Add note");
        System.out.println("[2] --> Edit note");
        System.out.println("[3] --> Delete note");
        System.out.println("[4] --> Show note");
        System.out.println("[5] --> Exit");
        String num = scanner.next();
        int select = Integer.parseInt(num);
        do {
            switch (select) {
                case 1:
                    System.out.println(date);
                    System.out.println("Press enter when you finish writing a note");
                    addNote();
                    userMenu(users, index);
                    break;
                case 2:
                    editNote();
                    userMenu(users, index);
                    break;
                case 3:
                    deleteNote();
                    userMenu(users, index);
                    break;
                case 4:
                    showNote();
                    userMenu(users, index);
                    break;
                case 5:exit();
                    break;
                default:
                    break;
            }
        } while (select != 4);
    }
    public void addNote() {
        System.out.println("Enter your note: ");
        notes.add(scanner.next());
        System.out.println("Note successfully added!");
    }
    public void deleteNote() {
        System.out.println("Enter the index of the note you want to delete: ");
        int index = scanner.nextInt();
        if(notes.size()<index){
            System.out.println("Invalid number! ");
            return;
        }
        notes.remove(index);
        System.out.println("Note removed! ");
    }
    public void editNote() {
        System.out.println("Enter the index of the note you want to edit: ");
        int index = scanner.nextInt();
        if (index >= 0 && index < notes.size()) {
            System.out.println("Enter the new text for the note: ");
            String newText = scanner.next();
            notes.set(index, newText);
            System.out.println("Note successfully edited!");
        } else {
            System.out.println("Invalid index, please try again.");
        }
    }
    public void showNote() {
        System.out.println("Write the index of which notebook you want to see");
        int index=scanner.nextInt();
        if (notes.isEmpty()) {
            System.out.println("Notes absent! ");
        } else if (index >=0 && index < notes.size()){
            System.out.println("Note " + (index+1) + ": " + notes.get(index));
        } else {
            System.out.println("Invalid index, please try again.");
        }
    }
    public void exit(){
        System.exit(0);
    }
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
}
