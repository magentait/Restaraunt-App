package authentication.processing;

import business.menu.UIMenuEntity;
import business.mode.UserMode;

import java.util.Scanner;

public class AuthHandler implements UIMenuEntity {
    private AuthService authService;
    private Scanner scanner;

    public AuthHandler(AuthService authService) {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        displayMenu();
        handleMenuInput();
    }

    public void displayMenu() {
        System.out.println("_____ АВТОРИЗАЦИЯ _____");
        System.out.println("(1)  Войти \n(2)  Регистрация Посетителя" +
                "\n(3)  Регистрация Администратора\n(0)  Выход ");
    }

    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 3: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    UIMenuEntity menu = authService.authenticateUser();
                    if (menu != null) {
                        menu.run();
                    } else {
                        System.out.println("Ошибка при авторизации");
                    }
                    break;
                case "2":
                    if (authService.registerUser(UserMode.VISITOR)) {
                        run();
                    } else {
                        System.out.println("Ошибка при регистрации");
                    }
                    break;
                case "3":
                    if (authService.registerUser(UserMode.ADMIN)) {
                        run();
                    } else {
                        System.out.println("Ошибка при регистрации");
                    }
                    break;
                case "0":
                    System.exit(0);
                    return;
                default:
                    System.out.println("Некорректный ввод, используйте число 0-3.");
            }
        }
    }
}
