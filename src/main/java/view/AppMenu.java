package view;

import java.util.Scanner;

public abstract class AppMenu {
    /**
     * This method reads a single line from user input and parses it.
     */
    public abstract boolean check(String input, Scanner scanner);
}