package business.order;

import business.FileHandler;
import business.product.Dish;

import java.util.ArrayList;
import java.util.List;

public class OrderDatabase {
    private static List<Order> orders;
    private final static String filePath = "orders.json";

    public static List<Order> getAll() {
        if (orders == null) {
            orders = new ArrayList<Order>();
        }

        return orders;
    }

    public static void add(Order order) {
        orders = getAll();
        orders.add(order);

        Thread orderThread = new Thread(order);
        orderThread.start();

        FileHandler.save(orders, filePath);
    }

    public static void addDish(Order order, Dish dish) {
        order.addDish(dish);

        FileHandler.save(orders, filePath);
    }

    public static void deleteDish(Order order, Dish dish) {
        order.deleteDish(dish);

        FileHandler.save(orders, filePath);
    }

    public static void delete(Order order) {
        orders = getAll();
        orders.remove(order);

        FileHandler.save(orders, filePath);
    }

    public static Order getOrderById(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }

        return null;
    }

    public static void display() {
        if (orders == null || orders.isEmpty()) {
            System.out.println("Активных заказов нет!");
            return;
        }

        for (var order : orders) {
            order.getReadyState().display();
        }
    }
}
