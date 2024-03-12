package com.coderscampus.assignment3;

public class Main {
    public static void main(String[] args) {
        final int LINES_TO_READ = 4;
        final String DATA_FILEPATH = "src/com/coderscampus/assignment3/data.txt";
        User[] users = UserService.parseFileIntoUsers(DATA_FILEPATH, LINES_TO_READ);
    }
}