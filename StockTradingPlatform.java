import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    String name;
    double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buyStock(String stockName, int quantity, double price) {
        double cost = quantity * price;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        holdings.put(stockName, holdings.getOrDefault(stockName, 0) + quantity);
        balance -= cost;
        System.out.println("Bought " + quantity + " shares of " + stockName);
    }

    public void sellStock(String stockName, int quantity, double price) {
        if (!holdings.containsKey(stockName) || holdings.get(stockName) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(stockName, holdings.get(stockName) - quantity);
        if (holdings.get(stockName) == 0) {
            holdings.remove(stockName);
        }
        balance += quantity * price;
        System.out.println("Sold " + quantity + " shares of " + stockName);
    }

    public void displayPortfolio() {
        System.out.println("\nPortfolio:");
        System.out.println("Balance: $" + balance);
        for (Map.Entry<String, Integer> entry : holdings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares");
        }
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Stock> market = new HashMap<>();
        Portfolio portfolio = new Portfolio(10000.0);  // Initial balance

        // Sample stocks
        market.put("AAPL", new Stock("AAPL", 150.0));
        market.put("GOOGL", new Stock("GOOGL", 2800.0));
        market.put("TSLA", new Stock("TSLA", 700.0));

        while (true) {
            System.out.println("\n1. View Market  2. Buy Stock  3. Sell Stock  4. View Portfolio  5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nMarket Prices:");
                    for (Stock stock : market.values()) {
                        System.out.println(stock.name + " - $" + stock.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buyStock = scanner.next().toUpperCase();
                    if (!market.containsKey(buyStock)) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();
                    portfolio.buyStock(buyStock, buyQuantity, market.get(buyStock).price);
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellStock = scanner.next().toUpperCase();
                    if (!market.containsKey(sellStock)) {
                        System.out.println("Stock not found!");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();
                    portfolio.sellStock(sellStock, sellQuantity, market.get(sellStock).price);
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
