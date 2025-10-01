public class InstitutionalTrader implements Trader {
    private String name;

    public InstitutionalTrader(String name) {
        this.name = name;
    }

    public void getUpdate(String stock, double price) {
        System.out.println(name + " is notified that " + stock + " price is now $" + price);
    }
}