public interface StockExchange { // Subject
    void addTrader(Trader t);
    void removeTrader(Trader t);
    void notifyTrader();
}