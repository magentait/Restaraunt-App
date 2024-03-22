package business.order.status;

import business.order.Order;

public class AcceptedState extends OrderState {
    public AcceptedState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n- ЗАКАЗ: ID " + order.getId());
        System.out.println("Статус готовности заказа: \uD83D\uDD34 (Принят)");

        super.display();
    }

    @Override
    public String getReadyState() {
        return "ACCEPTED";
    }

    @Override
    public void getProcessed() {
        order.changeState(new InProcessState(order));
    }

    @Override
    public void getAccepted() {
        order.changeState(new AcceptedState(order));
    }

    @Override
    public void getReady() { }
}
