package business.util;

import business.product.Dish;
import business.product.FoodMenu;
import business.order.Order;
import business.order.OrderDatabase;

import java.util.Objects;
import java.util.Scanner;

public class OrderUtil {
    public static Order inputOrder() {
        Order resultOrder = new Order();

        String dishName = "-1";
        while (!Objects.equals(dishName, null)) {
            dishName = DishUtil.InputDishName();
            Dish dish = FoodMenu.getDishByName(dishName);
            if (dish == null) {
                continue;
            }

            if (dishName != null) {
                if (dish.getCount() <= 0) {
                    System.out.println("Нельзя заказать");
                } else {
                    resultOrder.add(FoodMenu.getDishByName(dishName));
                    dish.setCount(dish.getCount() - 1);
                    System.out.println("Блюдо успешно добавлено в текущий заказ.");
                }
            }
        }

        return resultOrder;
    }

    public static int inputOrderById() {
        Scanner scanner = new Scanner(System.in);
        int id;

        while (true) {
            try {
                System.out.print("Введите ID заказа: ");
                id = scanner.nextInt();

                if (id == -1) {
                    return 0;
                } else if (id <= 0) {
                    System.out.println("Некорректное ID");
                } else if (id > OrderDatabase.getAll().size()) {
                    System.out.println("Заказа с таким ID не существует!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Некорректное ID" + "\n\'-1\' - выход");
                scanner.nextLine();
            }
        }

        return id;
    }
}
