public abstract class OrderProcessTemplate {

    public final void processOrder() {
        selectItems();
        makePayment();
        deliver();
    }

    private void selectItems() {
        System.out.println("Items are being selected");
    }
                                                                //selectItems() and deliver() are common process in any order process
    private void deliver() {
        System.out.println("Items purchased are delivered");
    }

    protected abstract void makePayment();                      //makePayment() differs, it can be Cash On Delivery or Online Payment
}