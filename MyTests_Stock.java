public class MyTests_Stock {

    // hannah - so far idk abt the rest

    // template form trade order again
    // Shared test data 
    private static String symbol = "GGGL";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static int numShares = 123;
    private static int numToSubtract = 24;
    private static double price = 123.45;

    private static Stock to = new Stock(symbol, "um a name", price);
    private static Trader b = new Trader(null, "buyer", null);
    private static Trader s = new Trader(null, "seller", null);


    public static void test() {

        System.out.println("\n===== Stock Tests =====");
        testStockConstructor();
    }

    private static void testStockConstructor() {
        System.out.println("\nRunning testStockConstructor...");
        System.out.println("Expected: GGGL, 123.45");
        System.out.println("Actual: " + to.getStockSymbol() + ", " + to.getLastPrice());
    }
}