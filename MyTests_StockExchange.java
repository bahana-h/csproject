public class MyTests_StockExchange {

    // template copied over from tradeorder

    // Shared test data 
    private static StockExchange stck = new StockExchange();

    public static void test() { // hannah
        System.out.println("\n===== StockExchange Tests =====");
        testStockExchangeConstructor();
        testStockExchangeListStock();
        testStockExchangeToString();
        testStockExchangeGetQuote1();
        testStockExchangeGetQuote2();
        testStockExchangePlaceOrder1();
        testStockExchangePlaceOrder2();
    }

    // ----------------------------
    // StockExchange Tests
    // ----------------------------

    private static void testStockExchangeConstructor() {
        System.out.println("\nRunning testStockExchangeConstructor...");
        StockExchange to = new StockExchange();
        System.out.println(to.toString());
    }

    private static void testStockExchangeListStock() {
        System.out.println("\nRunning testStockExchangeListStock...");
        stck.listStock("abc", "company name", 0);
        System.out.println("Expected: contains abc");
        System.out.println("Actual: " + stck.getListedStocks().toString());
    }
        
    private static void testStockExchangeGetQuote1() { // when getquote not found
        System.out.println("\nRunning testStockExchangeGetQuote1...");
        StockExchange hehe = new StockExchange();
        hehe.listStock("ERG", null, 0);
        System.out.println("Expected: edf not found");
        System.out.println("Actual: " + hehe.getQuote("edf").toString());
    }

    private static void testStockExchangeGetQuote2() { // when getquote found
        System.out.println("\nRunning testStockExchangeGetQuote2...");
        StockExchange hehehe = new StockExchange();
        hehehe.listStock("ERG", "company nameyeyyeye", 1000.00);
        System.out.println("Expected: not error");
        System.out.println("Actual: " + hehehe.getQuote("ERG").toString());
    }

    private static void testStockExchangePlaceOrder1() { // when placeorder NOT found
        System.out.println("\nRunning testStockExchangePlaceOrder1...");
        StockExchange hehehehe = new StockExchange();
        TradeOrder test = new TradeOrder(null, "HEH", true, true, 5, 0);
        System.out.println("Expected: HEH not found");
        System.out.println("Actual: ");
        hehehehe.placeOrder(test);
    }

    private static void testStockExchangePlaceOrder2() { // when placeorder found
        System.out.println("\nRunning testStockExchangePlaceOrder2...");
        StockExchange hehehehehe = new StockExchange();
        hehehehehe.listStock("EHE", "whateverhehehehheheahea", 0);
        TradeOrder test = new TradeOrder(null, "EHE", true, true, 5, 0);
        System.out.println("Expected: no error - order placed");
        System.out.println("Actual: ");
        hehehehehe.placeOrder(test);
    }

    private static void testStockExchangeToString() {
        System.out.println("\nRunning testStockExchangeToString...");
        StockExchange bee = new StockExchange();
        System.out.println("toString() returned: " + bee.toString());
    }
}