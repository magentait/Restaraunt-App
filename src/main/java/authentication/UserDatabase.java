package authentication;

import authentication.guest.Admin;
import authentication.guest.User;
import authentication.guest.Visitor;
import business.FileHandler;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {
    private static List<User> users;
    private static List<Admin> admins;
    private static List<Visitor> visitors;
    private static final String filePath = "users.json";

    public static List<User> getAll() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    public static void addUser(User user) {
        if (users == null) users = new ArrayList<>();
        if (admins == null) admins = new ArrayList<>();
        if (visitors == null) visitors = new ArrayList<>();

        users.add(user);
        if (user.getUserType().equals("ADMIN"))
        {
            admins.add(new Admin(user.getUserName(), user.getPassword()));
            FileHandler.save(admins, "admins.json");
        } else {
            visitors.add(new Visitor(user.getUserName(), user.getPassword()));
            FileHandler.save(visitors, "visitors.json");
        }

        FileHandler.save(users, filePath);
    }

    public static void displayAdmins() {
        System.out.println();
        if (admins == null) admins = new ArrayList<>();
        for (Admin admin : admins) {
            admin.displayInfo();
        }
        System.out.println();
    }

    public static void displayVisitors() {
        System.out.println();
        if (visitors == null) visitors = new ArrayList<>();
        for (Visitor visitor : visitors) {
            visitor.displayInfo();
        }
        System.out.println();
    }
}
