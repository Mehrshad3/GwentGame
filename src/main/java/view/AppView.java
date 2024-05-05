package view;

import enums.Menu;
import model.App;

import java.io.PrintStream;
import java.util.Scanner;

public class AppView {
    private static AppView appViewObject = null;
    public final Scanner scanner = new Scanner(System.in);
    public final PrintStream soutPrintStream = System.out;
    public final PrintStream serrPrintStream = System.err;

    private AppView() {
    }

    public static AppView getAppViewObject() {
        if (appViewObject == null) appViewObject = new AppView();
        return appViewObject;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.Exit);
    }
}
