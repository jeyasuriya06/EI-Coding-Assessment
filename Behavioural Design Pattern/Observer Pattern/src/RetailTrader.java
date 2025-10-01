public class RetailTrader implements Trader {
    private String name;
    
    public RetailTrader(String name) {
        this.name = name;
    }

    public void getUpdate(String stock, double price) {
        System.out.println(name + " is notified that " + stock + " price as $" + price);
    }
}
