package business.order.status;

import business.order.Order;

public class InProcessState extends OrderState {
    public InProcessState(Order order) {
        super(order);
    }

    @Override
    public void display() {
        System.out.println("\n- ЗАКАЗ: ID " + order.getId());
        System.out.println("Статус готовности заказа: \uD83D\uDFE1 (Готовится)");

        super.display();
    }

    @Override
    public String getReadyState() {
        return "INPROCESS";
    }

    @Override
    public void getReady() {
        order.changeState(new ReadyState(order));
    }

    @Override
    public void getAccepted() {
        order.changeState(new AcceptedState(order));
    }
    @Override
    public void getProcessed() { }
}
