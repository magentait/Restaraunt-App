package authentication.processing;

import authentication.UserDatabase;
import authentication.factory.AdminFactory;
import authentication.factory.UserFactory;
import authentication.factory.VisitorFactory;
import authentication.guest.User;
import business.menu.AdminUIMenu;
import business.menu.UIMenuEntity;
import business.menu.VisitorUIMenu;
import business.mode.InfoMode;
import business.mode.UserMode;
import business.util.UserUtil;

import java.util.Objects;

public class AuthService {
    private UserFactory userFactory;

    public boolean registerUser(UserMode mode) {
        System.out.println("Регистрация.");
        String username = UserUtil.handleInfoInput(
                "Введите имя пользователя: ",
                "Некорректное имя пользователя!\n" +
                        "Имя пользователя должно состоять из >= 4 символов(a-Z, 0-9)!",
                InfoMode.USERNAME
        );

        if (username.isEmpty()) {
            return false;
        }

        if (UserDatabase.getAll().stream().anyMatch(user -> user.getUserName().equals(username))) {
            System.out.println("Пользователь с именем \"" + username + "\" уже существует.");
            return false;
        }

        String password = UserUtil.handleInfoInput(
                "Придумайте пароль: ",
                "Некорректный пароль!\n" +
                        "Пароль должен состоять из >= 8 символов(a-Z, 0-9)!",
                InfoMode.PASSWORD
        );
        String hashedPassword = UserUtil.sha256(password);

        User newUser;
        if (mode == UserMode.VISITOR) {
            userFactory = new VisitorFactory();
        } else {
            userFactory = new AdminFactory();
        }

        newUser = userFactory.createUser(username, hashedPassword);
        UserDatabase.addUser(newUser);
        System.out.println("Пользователь \"" + username + "\" УСПЕШНО зарегистрирован.");

        return true;
    }

    public UIMenuEntity authenticateUser() {
        String username = UserUtil.handleInfoInput(
                "Введите имя пользователя: ",
                "Некорректное имя пользователя!\n" +
                        "Имя пользователя должно состоять из >= 4 символов(a-Z, 0-9)!",
                InfoMode.USERNAME
        );

        if (username.isEmpty()) {
            return null;
        }

        User user = UserDatabase.getAll().stream()
                .filter(u -> u.getUserName().equals(username))
                .findFirst()
                .orElse(null);

        String password = UserUtil.handleInfoInput(
                "Введите пароль: ",
                "Пароль введён некорректно!\n" +
                        "Пароль должен состоять из >= 4 символов(a-Z, 0-9)!",
                InfoMode.PASSWORD
        );
        String hashedPassword = UserUtil.sha256(password);

        if (user != null && user.getPassword().equals(hashedPassword)) {
            String userType = user.getUserType();
            System.out.println("Добро пожаловать, " + user.getUserName() + "!");

            if (Objects.equals(userType, "VISITOR")) {
                return new VisitorUIMenu();
            } else {
                return new AdminUIMenu();
            }
        } else {
            System.out.println("Неверный логин или пароль.");
            return null;
        }
    }
}
