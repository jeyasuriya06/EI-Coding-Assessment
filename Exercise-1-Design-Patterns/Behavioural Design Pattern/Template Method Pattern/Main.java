public class Main {
    public static void main(String[] args) {
        OrderProcessTemplate order = new OnlineOrder();
        order.processOrder();

        System.out.println();

        OrderProcessTemplate order2 = new CODOrder();
        order2.processOrder();
    }
}
