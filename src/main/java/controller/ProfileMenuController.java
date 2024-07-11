package controller;

import model.GsonReaderWriter;
import model.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ProfileMenuController extends MenuController {
    public void changeUsername(String newUsername) {
        boolean shouldchange = Validator.getValidator().validateUsername(newUsername);
        if (shouldchange) {
            User currentuser = User.getCurrentUser();
            File oldparentfolder = new File(".GWENT/Users/" + currentuser.getName());
//            File olduserjson=new File(".GWENT/Users/"+currentuser.getName()+"/profile.json");
            try {
                Files.move(oldparentfolder.toPath(),Path.of(".GWENT/Users/"+newUsername));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            currentuser.setName(newUsername);
                GsonReaderWriter.getGsonReaderWriter().saveUser(currentuser);
                //TODO:Check decks exists?This is just for debugging and code is complete now.

        }else{
            System.out.println("NewUsername isn't Valid");
        }
    }

    public void changeNickname(String newNickname, String oldNickname) {
        if (Objects.equals(oldNickname, newNickname)) {
            System.out.println("(NEW) Nickname please!!");
        } else {
            boolean shouldchange = Validator.getValidator().validateNickname(newNickname);
            if (shouldchange) {
                User user = User.getCurrentUser();
                user.setNickname(newNickname);
                GsonReaderWriter.getGsonReaderWriter().saveUser(user);
            } else {
                System.out.println("NewNilname is not Valid !");
            }
        }
    }

    public void changePassword(String newPassword, String oldPassword) {
        if (Objects.equals(oldPassword, newPassword)) {
            System.out.println("(NEW) Password please!!");
        } else {
            boolean shouldchange = Validator.getValidator().validatePassword(newPassword);
            if (shouldchange) {
                User user = User.getCurrentUser();
                user.setPassword(newPassword);
                GsonReaderWriter.getGsonReaderWriter().saveUser(user);
            } else {
                System.out.println("newPassword is not Valid ! ");
            }
        }
    }

    public void changeEmail(String newEmail, String oldEmail) {
        if (Objects.equals(oldEmail, newEmail)) {
            System.out.println("(NEW) Email please!!");
        } else {
            boolean shouldchange=Validator.getValidator().validateEmail(newEmail);
            if (shouldchange) {
                User user = User.getCurrentUser();
                user.setEmail(newEmail);
                GsonReaderWriter.getGsonReaderWriter().saveUser(user);
            } else {
                System.out.println("NewEmail is not Valid!");
            }
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
