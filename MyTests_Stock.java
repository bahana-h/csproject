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
        testGetQuoteNum1();
    }

    private static void testStockConstructor() {
        System.out.println("\nRunning testStockConstructor...");
        System.out.println("Expected: GGGL, 10.00");
        System.out.println("Actual: " + to.getStockSymbol() + ", " + to.getLastPrice());
    }
    private static void testGetQuoteNum1(){
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