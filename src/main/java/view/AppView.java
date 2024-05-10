package view;

import enums.Menu;
import model.App;

import java.util.Scanner;

public class AppView {
    private static AppView appViewObject = null;
    public final Scanner scanner = new Scanner(System.in);

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
