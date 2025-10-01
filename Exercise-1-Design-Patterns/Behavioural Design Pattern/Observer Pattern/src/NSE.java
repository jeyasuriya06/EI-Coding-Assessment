import java.util.ArrayList;

public class NSE implements StockExchange{
    private ArrayList<Trader> traders = new ArrayList<>();
    private String stock;
    private double price;

    public void setStockPrice(String stock, double price) {
        this.stock = stock;
        this.price = price;
        notifyTrader();
    }

    public void addTrader(Trader t) {
        traders.add(t);
    }

    public void removeTrader(Trader t) {
        traders.remove(t);
    }

    public void notifyTrader() {
        for (Trader t: traders) {
            t.getUpdate(stock, price);
        }
    }
}
