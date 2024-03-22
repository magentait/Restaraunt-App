package business.product;

import business.mode.UpdateMode;
import business.util.DishUtil;

import java.util.Objects;
import java.util.Scanner;

public class DishService {
    public void create() {
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) != null) {
            System.out.println("Блюдо \'" + name + "\' уже существует!");
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == 0) {
            return;
        }

        int prepareTime = DishUtil.inputInteger("Укажите время приготовления: ");
        if (prepareTime == 0) {
            return;
        }

        Dish dish = new Dish(name, price, prepareTime);

        System.out.println("Блюдо \'" + name + "\' добавлено");
        System.out.println();
        dish.display();

        FoodMenu.add(dish);
    }

    public void delete() {
        if (FoodMenu.getAll().isEmpty()) {
            return;
        }

        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        FoodMenu.delete(name);

        System.out.println("Блюдо \'" + name + "\' удалено");
        System.out.println();
    }

    public void updatePrice() {
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        double price = DishUtil.inputPrice();
        if (price == -1) {
            return;
        }

        FoodMenu.update(name, price);

        System.out.println("Блюдо \'" + name + "\' обновилось");
        System.out.println();
    }

    public void update(UpdateMode mode) {
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        int data = DishUtil.inputInteger((mode == UpdateMode.COUNT) ? "Укажите количество: " : "Укажите время приготовления: ");
        if (data == -1) {
            return;
        }

        if (mode == UpdateMode.COUNT) {
            FoodMenu.update(name, data, UpdateMode.COUNT);
        } else {
            FoodMenu.update(name, data, UpdateMode.TIME);
        }

        System.out.println("Блюдо \'" + name + "\' обновилось");
        System.out.println();
    }

    public void addFeedBack() {
        String name = DishUtil.InputDishName();
        if (name == null || name.isEmpty()) {
            return;
        }

        Dish dish = FoodMenu.getDishByName(name);
        if (dish == null) {
            System.out.println("Блюдо отсутствует в меню");
            return;
        }

        System.out.println("Напишите отзыв:");
        String text;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            text = scanner.nextLine().trim().toLowerCase();

            if (text.equals("0")) return;
            if (text.length() < 10) {
                System.out.println("Используйте больше 10 символов!\n0 - выход в меню!");
                continue;
            }
            break;
        }

        int mark;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print("Оцените блюдо (1 - 5): ");

                mark = scanner.nextInt();

                if (mark == -1) {
                    return;
                } else if (mark < 1 || mark > 5) {
                    System.out.println("Некорректная оценка!");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Некорректная оценка!" + "\n \'-1\' - выход.");
                scanner.nextLine();
            }
        }

        FeedBack feedBack = new FeedBack(mark, text);
        dish.addFeedBack(feedBack);
        System.out.println("Отзыв добавлен!");
    }
}
