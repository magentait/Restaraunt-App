package business.util;

import business.product.Dish;
import business.product.FoodMenu;

import java.util.Objects;
import java.util.Scanner;

public class DishUtil {
    public static String inputName() {
        Scanner scanner = new Scanner(System.in);
        String name;

        while (true) {
            System.out.print("Укажите название блюда: ");
            name = scanner.nextLine().trim().toLowerCase();

            if (name.equals("0")) {
                return "";
            }

            if (!isValidDishName(name))  {
                System.out.println("Некорректное название блюда" + "\n0 - выход.");
            } else {
                break;
            }
        }

        return name;
    }

    public static double inputPrice() {
        Scanner scanner = new Scanner(System.in);
        double price;

        while (true) {
            try {
                System.out.print("Укажите цену блюда: ");
                price = scanner.nextDouble();

                if (price == -1) {
                    return 0.0;
                } else if (price <= 0) {
                    System.out.println("Некорректная цена!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Некорректная цена" + "\n\'-1\' - выход");
                scanner.nextLine();
            }
        }

        return price;
    }

    public static int inputInteger(String message) {
        Scanner scanner = new Scanner(System.in);
        int data;

        while (true) {
            try {
                System.out.print(message);
                data = scanner.nextInt();

                if (data == -1) {
                    return 0;
                } else if (data < 0) {
                    System.out.println("Введите положительное число!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Некорректное число." + "\n\'-1\' - выход");
                scanner.nextLine();
            }
        }

        return data;
    }

    public static String InputDishName() {
        String name = DishUtil.inputName();
        Dish dish = FoodMenu.getDishByName(name);
        if (Objects.equals(name, "")) {
            return null;
        } else if (dish == null) {
            System.out.println("Блюда с таким названием не существует!");
            return "";
        }

        return name;
    }

    private static boolean isValidDishName(String name) { return name.matches("^[a-zA-Z0-9\\\\s]+$"); }
}
