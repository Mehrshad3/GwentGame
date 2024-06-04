package controller;

import model.GsonReaderWriter;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class ProfileMenuController extends MenuController {
    public void changeUsername(String newUsername) {
        User user = User.getCurrentUser();
        File file = new File(".GWENT/Users/" + newUsername + ".txt");
        if (file.exists()) {
            System.out.println("This Username already exists!");
        } else {
            String oldUsername = user.getName();

            File file1 = new File(".GWENT/Users/" + oldUsername + ".txt");
            try {
                file1.delete();
                file.createNewFile();
                GsonReaderWriter.getGsonReaderWriter().saveUser(user);
                user.setName(newUsername);
            } catch (IOException e) {
                System.out.println("Can't Create New File");
                e.printStackTrace();

            }
        }

    }

    public void changeNickname(String newNickname, String oldNickname) {
        if (Objects.equals(oldNickname, newNickname)) {
            System.out.println("(NEW) Nickname please!!");
        } else {
            User user = User.getCurrentUser();
            File file = new File(".GWENT/Users/" + user.getName() + ".txt");
            user.setNickname(newNickname);
            GsonReaderWriter.getGsonReaderWriter().saveUser(user);
        }
    }

    public void changePassword(String newPassword, String oldPassword) {
        if (Objects.equals(oldPassword, newPassword)) {
            System.out.println("(NEW) Password please!!");
        } else {
            User user = User.getCurrentUser();
            File file = new File(".GWENT/Users/" + user.getName() + ".txt");
            user.setPassword(newPassword);
            GsonReaderWriter.getGsonReaderWriter().saveUser(user);
        }
    }

    public void changeEmail(String newEmail, String oldEmail) {
        if (Objects.equals(oldEmail, newEmail)) {
            System.out.println("(NEW) Email please!!");
        } else {
            User user = User.getCurrentUser();
            File file = new File(".GWENT/Users/" + user.getName() + ".txt");
            user.setEmail(newEmail);
            GsonReaderWriter.getGsonReaderWriter().saveUser(user);
        }
    }

    public void showUserInfo() {
        User user = User.getCurrentUser();
        System.out.println("Username:" + user.getName() + "\nNickname:" + user.getNickname() + "\nEmail:" + user.getEmail());
    }

    public void showUserGameHistory() {
        showUserGameHistory(5);
    }

    public void showUserGameHistory(int number) {
        // TODO
    }
}
