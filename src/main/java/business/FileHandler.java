package business;

import authentication.UserDatabase;
import authentication.guest.Admin;
import authentication.guest.Visitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import business.product.Dish;
import business.product.FoodMenu;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public class FileHandler {

    private static String dataFolderPath = "Data";

    public static <T> void save(List<T> data, String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), data);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void saveStats(String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();
        RestaurantStateTemplate restaurantStats = new RestaurantStateTemplate();

        try {
            objectMapper.writeValue(new File(filePath), restaurantStats);
        } catch (IOException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadStats() {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, "stats.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            RestaurantStateTemplate stats = objectMapper.readValue(new File(filePath), RestaurantStateTemplate.class);

            RestaurantStats.setTotalRevenue(stats.getTotalRevenue());
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadAdmins() {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, "admins.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Admin> users = Arrays.asList(objectMapper.readValue(new File(filePath), Admin[].class));

            for (Admin admin : users) {
                UserDatabase.addUser(admin);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadVisitors() {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, "visitors.json");
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Visitor> users = Arrays.asList(objectMapper.readValue(new File(filePath), Visitor[].class));

            for (Visitor visitor : users) {
                UserDatabase.addUser(visitor);
            }

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static void uploadDishes(String path) {
        String filePath = MessageFormat.format("{0}/{1}", dataFolderPath, path);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Dish> dishes = Arrays.asList(objectMapper.readValue(new File(filePath), Dish[].class));

            for (Dish dish : dishes) {
                FoodMenu.add(dish);
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
