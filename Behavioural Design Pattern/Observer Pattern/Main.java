public class Main {
    public static void main(String[] args) {
        Trader t1 = new RetailTrader("Suriya");
        Trader t2 = new InstitutionalTrader("ADIA");

        NSE nse = new NSE();

        nse.addTrader(t1);
        nse.addTrader(t2);

        nse.setStockPrice("Nvidia", 500);
        nse.setStockPrice("Bitcoin", 10000);
    }
}
