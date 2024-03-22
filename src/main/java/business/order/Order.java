package business.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import business.RestaurantStats;
import business.product.Dish;
import business.modes.PaymentStatusMode;
import business.order.status.AcceptedState;
import business.order.status.OrderState;

import java.util.ArrayList;
import java.util.List;

import static business.modes.PaymentStatusMode.NOTPAID;
import static business.modes.PaymentStatusMode.PAID;


public class Order implements Runnable {
    @JsonProperty("Id")
    private int id;
    @JsonProperty("dishes")
    private List<Dish> dishes;
    @JsonProperty("totalPrice")
    private double totalPrice;
    @JsonProperty("totalPrepareTime")
    private int totalPrepareTime;
    private PaymentStatusMode paymentStatus;
    private OrderState readyState;

    public Order() {
        id = OrderDatabase.getAll().size() + 1;
        dishes = new ArrayList<Dish>();
        totalPrice = 0;
        totalPrepareTime = 0;
        paymentStatus = NOTPAID;
        readyState = new AcceptedState(this);
    }

    public void changeState(OrderState state) {
        this.readyState = state;
    }

    public List<Dish> getDishes() { return dishes; }

    public int getId() { return id; }

    public void addDish(Dish dish) {
        dishes.add(dish);

        computeTotalPrepareTime();
        computeTotalPrice();

        readyState.getAccepted();
    }

    public void deleteDish(Dish dish) {
        dishes.remove(dish);

        computeTotalPrepareTime();
        computeTotalPrice();

        readyState.getAccepted();
    }

    public void computeTotalPrice() {
        totalPrice = 0;
        for (Dish item : dishes) {
            totalPrice += item.getPrice();
        }
    }

    public void computeTotalPrepareTime() {
        totalPrepareTime = 0;
        for (Dish item : dishes) {
            totalPrepareTime += item.getPrepareTime();
        }
    }

    public PaymentStatusMode getPaymentStatus() {
        return paymentStatus;
    }

    public OrderState getReadyState() {
        return readyState;
    }

    @JsonProperty("readyState")
    public String getReadyStateString() { return readyState.getReadyState(); }

    public double getTotalPrice() { return totalPrice; }

    public int getTotalPrepareTime() { return totalPrepareTime; }

    public void pay() {
        paymentStatus = PAID;
        RestaurantStats.updateTotalRevenue(totalPrice);
    }

    public void add(Dish dish) {
        dishes.add(dish);
        computeTotalPrice();
        computeTotalPrepareTime();
    }

    @Override
    public void run() {
        try {
            long halfTime = 1000L * (totalPrepareTime / 3);

            Thread.sleep(halfTime);
            readyState.getProcessed();

            Thread.sleep(halfTime);
            readyState.getReady();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
