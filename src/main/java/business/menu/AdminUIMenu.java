package business.menu;

import authentication.UserDatabase;
import business.RestStatistics;
import business.product.DishService;
import business.product.FoodMenu;
import business.mode.UpdateMode;

import java.util.Scanner;

public class AdminUIMenu implements UIMenuEntity {
    private final Scanner scanner = new Scanner(System.in);
    private DishService dishService = new DishService();

    @Override
    public void run() {
        displayMenu();
        handleMenuInput();
    }

    @Override
    public void displayMenu() {
        System.out.println("________________ ГЛАВНОЕ МЕНЮ ____________________");
        System.out.println("                [Администратор]");
        System.out.println("(1) Открыть меню");
        System.out.println("(2) Добавить позицию");
        System.out.println("(3) Удалить позицию");
        System.out.println("(4) Обновить цену позиции");
        System.out.println("(5) Обновить количество позиции");
        System.out.println("(6) Изменить время приготовления");
        System.out.println("(7) Отобразить прибыль и самое популярное блюдо");
        System.out.println("(8) Отобразить список администраторов ");
        System.out.println("(9) Отобразить список посетителей");
        System.out.println("(0) Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 9: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    FoodMenu.display();

                    break;
                case "2":
                    FoodMenu.display();
                    dishService.create();

                    break;
                case "3":
                    FoodMenu.display();
                    dishService.delete();

                    break;
                case "4":
                    FoodMenu.display();
                    dishService.updatePrice();

                    break;
                case "5":
                    FoodMenu.display();
                    dishService.update(UpdateMode.COUNT);

                    break;
                case "6":
                    FoodMenu.display();
                    dishService.update(UpdateMode.TIME);

                    break;
                case "7":
                    RestStatistics.display();

                    FoodMenu.displayPopular();

                    break;
                case "8":
                    UserDatabase.displayAdmins();

                    break;
                case "9":
                    System.out.println("\nСписок зарегистрированных посетителей:");
                    UserDatabase.displayVisitors();
                    break;
                case "0":
                    System.exit(0);

                    break;
                default:
                    System.out.println("Некорректный ввод! (Используйте 0-9)");
            }

            run();
        }
    }
}
