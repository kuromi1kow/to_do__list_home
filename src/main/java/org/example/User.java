package org.example;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    ArrayList <User> users=new ArrayList<User>();
    Scanner scanner=new Scanner(System.in);
    public String name;
    public String surname;
    public String password;
    public User(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password=password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSurname() {
        return surname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
