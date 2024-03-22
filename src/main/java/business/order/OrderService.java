package business.order;

import business.product.FoodMenu;
import business.modes.PaymentStatusMode;
import business.order.status.ReadyState;
import business.util.DishUtil;
import business.util.OrderUtil;

import java.util.Objects;

public class OrderService {
    public void create() {
        System.out.println("(0 - оформить заказ и выйти в главное меню): ");

        Order order = OrderUtil.inputOrder();
        if (order.getDishes().isEmpty()) {
            return;
        }

        System.out.println();
        System.out.println("Ваш заказ успешно создан:");
        order.getReadyState().display();

        OrderDatabase.add(order);
    }

    public void addDishToOrder() {
        System.out.println("Введите информацию о заказе для добавления блюда: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("Ваш Заказ " + id + " уже готов, нельзя добавить в него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("Ваш Заказ " + id + " уже готовится, нельзя добавить в него блюда!");
            return;
        }

        FoodMenu.display();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("Такого блюда не сущесвтует!");
            return;
        }

        OrderDatabase.addDish(order, FoodMenu.getDishByName(name));
        System.out.println("Ваш заказ успешно обновлён:");
        order.getReadyState().display();
    }

    public void deleteDishFromOrder() {
        System.out.println("Введите информацию о заказе для удаления блюда: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("Ваш Заказ " + id + " уже готов, нельзя удалить из него блюда!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "INPROCESS")) {
            System.out.println("Ваш Заказ " + id + " уже готовится, нельзя удалить из него блюда!");
            return;
        }

        if (order.getDishes().size() <= 1) {
            System.out.println("В заказе только одно блюдо!\nВместо удаления блюда, можно удалить заказ!");
            return;
        }

        order.getReadyState().display();
        String name = DishUtil.inputName();
        if (Objects.equals(name, "")) {
            return;
        } else if (FoodMenu.getDishByName(name) == null) {
            System.out.println("Такого блюда не существует!");
            return;
        } else if (!order.getDishes().contains(FoodMenu.getDishByName(name))) {
            System.out.println("Такое блюдо отсуствует в Заказе " + id + "!");
            return;
        }

        OrderDatabase.deleteDish(order, FoodMenu.getDishByName(name));
        System.out.println("Ваш заказ успешно обновлён:");
        order.getReadyState().display();
    }

    public void cancel() {
        System.out.println("Введите информацию о заказе для отмены: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        } else if (Objects.equals(order.getReadyStateString(), "READY")) {
            System.out.println("Ваш Заказ " + id + " уже готов, его нельзя отменить!");
            return;
        }

        OrderDatabase.delete(order);
        System.out.println("Отмена заказа произошла успешно!");
    }

    public void pay() {
        System.out.println("Введите информацию о заказе для оплаты: ");
        int id = OrderUtil.inputOrderById();
        if (id == 0) {
            return;
        }

        Order order = OrderDatabase.getOrderById(id);
        if (order == null) {
            System.out.println("Произошла ошибка!");
            return;
        }

        if (!(order.getReadyState() instanceof ReadyState)) {
            System.out.println("Дождитесь завершения заказа");
            return;
        }

        if (order.getPaymentStatus() == PaymentStatusMode.PAID) {
            System.out.println("Ваш Заказ " + id + " уже оплачен!");
            return;
        }

        order.pay();

        System.out.println("Оплата произошла успешно!");
    }
}
