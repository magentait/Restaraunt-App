package business.menu;

import business.product.DishService;
import business.product.FoodMenu;
import business.order.OrderDatabase;
import business.order.OrderService;

import java.util.Scanner;

public class VisitorUIMenu implements UIMenuEntity {
    private final Scanner scanner;
    private final OrderService orderService;
    private final DishService dishService;

    public VisitorUIMenu() {
        scanner = new Scanner(System.in);
        orderService = new OrderService();
        dishService = new DishService();
    }

    @Override
    public void run() {
        displayMenu();
        handleMenuInput();
    }

    @Override
    public void displayMenu() {
        System.out.println("________________ ГЛАВНОЕ МЕНЮ ____________________");
        System.out.println("                 [Посетитель]");
        System.out.println("(1)  Открыть меню");
        System.out.println("(2)  Оформить заказ");
        System.out.println("(3)  Оплатить заказ");
        System.out.println("(4)  Добавить в заказ");
        System.out.println("(5)  Удалить блюдо из заказа");
        System.out.println("(6)  Отменить заказ");
        System.out.println("(7)  Активные заказы");
        System.out.println("(8)  Добавить отзыв");
        System.out.println("(0)  Выход");
    }

    @Override
    public void handleMenuInput() {
        while (true) {
            System.out.print("Введите число от 0 до 8: ");
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    FoodMenu.display();
                    break;
                case "2":
                    FoodMenu.display();
                    orderService.create();
                    break;
                case "3":
                    OrderDatabase.display();
                    orderService.pay();
                    break;
                case "4":
                    OrderDatabase.display();
                    orderService.addDishToOrder();
                    break;
                case "5":
                    OrderDatabase.display();
                    orderService.deleteDishFromOrder();
                    break;
                case "6":
                    OrderDatabase.display();
                    orderService.cancel();
                    break;
                case "7":
                    OrderDatabase.display();
                    break;
                case "8":
                    FoodMenu.display();
                    dishService.addFeedBack();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Некорректный ввод! (Используйте 0-8)");
            }

            run();
        }
    }
}
