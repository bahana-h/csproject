public class MyTests_Stock {

    // hannah - so far idk abt the rest

    // template form trade order again
    // Shared test data 
    private static String symbol = "GGGL";
    private static String compname = "Giggle.com";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static int numShares = 123;
    private static int numToSubtract = 24;
    private static double price = 10.00;

    private static Stock to = new Stock(symbol, compname, price);
    private static Trader b = new Trader(null, "buyer", null);
    private static Trader s = new Trader(null, "seller", null);


    public static void test() {

        System.out.println("\n===== Stock Tests =====");
        testStockConstructor();
        testStockGetQuote1();
        testPlaceOrder();
        testExecuteOrder();
    }

    private static void testStockConstructor() {
        System.out.println("\nRunning testStockConstructor...");
        System.out.println("Expected: GGGL, 10.00, 10.00, Giggle.com, 10.00, 10.00, 0, [], []");
        System.out.println("Actual: " + to.getStockSymbol() + ", " + to.getLastPrice() + ", "+ to.getCompanyName()+", "+ to.getLoPrice()+", "+to.getHiPrice()+", "+to.getVolume()+", "+to.getBuyOrders()+", "+to.getSellOrders());
    }
    private static void testPlaceOrder(){
        TradeOrder sell = new TradeOrder(b, "GGGL", false, false, 300, 12.75);
        to.placeOrder(sell);
        System.out.println("\nRunning testPlaceOrder(sell)...");
        System.out.println("Expected: New order: Sell GGGL (Giggle.com)\n300 shares at 12.75");
        System.out.println("Actual:\n" + b.mailbox().peek());
        System.out.println("\nRunning testPlaceOrder(buy)...");
        TradeOrder buy = new TradeOrder(s, "GGGL", true, false, 400, 12.75);
        to.placeOrder(buy);
        System.out.println("Expected: New order: Buy GGGL (Giggle.com)\n400 shares at 12.75");
        System.out.println("Actual:\n" + s.mailbox().peek());
    }
    private static void testExecuteOrder(){
        TradeOrder buy2 = new TradeOrder(s, "GGGL", true, true, 100, 14.00);
        to.placeOrder(buy2);
        TradeOrder sell = new TradeOrder(b, "GGGL", false, false, 300, 12.75);
        to.placeOrder(sell);
        TradeOrder sell2 = new TradeOrder(b, "GGGL", false, true, 100, 14.75);
        to.placeOrder(sell2);
        TradeOrder buy = new TradeOrder(s, "GGGL", true, false, 400, 12.75);
        to.placeOrder(buy);
    }
    private static void testStockGetQuote1(){
        System.out.println("\nRunning testGetQuote...");
        TradeOrder sell = new TradeOrder(b, "GGGL", false, false, 300, 12.75);
        to.placeOrder(sell);
//     Giggle.com (GGGL)
//   Price: 10.00  hi: 10.00  lo: 10.00  vol: 0
//   Ask: 12.75 size: 300  Bid: 12.00 size: 500
        System.out.println("Expected: \nGiggle.com (GGGL)\nPrice: 10.00 hi:10.00 lo: 10.00 vol: 0\nAsk: 12.75 Bid: 12.00 size: 500");
        System.out.println("Actual:\n" + to.getQuote());
    }
}