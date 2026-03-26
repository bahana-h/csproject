public class MyTests_PriceComparator {

    // template copied over from tradeorder

    // Shared test data 
    private static String symbol = "GGGL";
    private static boolean buyOrder = true;
    private static boolean marketOrder = true;
    private static boolean limitOrder = false;

    // making some traders here for easy accessilbity
    private static Trader uno = new Trader(null, "he", null);
    private static Trader dos = new Trader(null, "behe", null);

    public static void test() { // hannah

        System.out.println("\n===== PriceComparator Tests =====");
        testPriceComparatorConstructor();
        testPriceComparatorConstructor2();
        testPriceComparatorCompareMM();
        testPriceComparatorCompareML();
        testPriceComparatorCompareLLTrue();
        testPriceComparatorCompareLLFalse();
    }

    // ----------------------------
    // PriceComparator Tests
    // ----------------------------
    
    private static void testPriceComparatorConstructor() {
        System.out.println("\nRunning testPriceComparatorConstructor...");
        PriceComparator to = new PriceComparator();
        System.out.println(to.toString());
    }

    private static void testPriceComparatorConstructor2() {
        System.out.println("\nRunning testPriceComparatorConstructor2...");
        PriceComparator to = new PriceComparator(false);
        System.out.println(to.toString());
    }
    
    private static void testPriceComparatorCompareMM() {
        System.out.println("\nRunning testPriceComparatorCompareMM...");
        PriceComparator to = new PriceComparator();
        TradeOrder unos = new TradeOrder(dos, symbol, buyOrder, marketOrder, 20, 0);
        TradeOrder doss = new TradeOrder(dos, symbol, buyOrder, marketOrder, 1, 0);
        System.out.println("Expected: 0");
        System.out.println("Actual: " + to.compare(unos, doss));
    }

    private static void testPriceComparatorCompareML() {
        System.out.println("\nRunning testPriceComparatorCompareML...");
        PriceComparator to = new PriceComparator();
        TradeOrder m = new TradeOrder(dos, symbol, buyOrder, marketOrder, 20, 0);
        TradeOrder l = new TradeOrder(dos, symbol, buyOrder, limitOrder, 1, 0);
        System.out.println("Expected: -1");
        System.out.println("Actual: " + to.compare(m, l));
        System.out.println("Expected: 1");
        System.out.println("Actual: " + to.compare(l, m));
    }

    private static void testPriceComparatorCompareLLTrue() {
        System.out.println("\nRunning testPriceComparatorCompareLLTrue...");
        PriceComparator to = new PriceComparator(true);
        TradeOrder l1 = new TradeOrder(dos, symbol, buyOrder, limitOrder, 20, 1.00);
        TradeOrder l2 = new TradeOrder(dos, symbol, buyOrder, limitOrder, 1, 20.55);
        System.out.println("Expected: -1955"); // (1.00-20.55)*100 = -1955
        System.out.println("Actual: " + to.compare(l1, l2));
    }

    private static void testPriceComparatorCompareLLFalse() {
        System.out.println("\nRunning testPriceComparatorCompareLLFalse...");
        PriceComparator to = new PriceComparator(false); // i js forgot to change this to false
        TradeOrder l1 = new TradeOrder(dos, symbol, buyOrder, limitOrder, 20, 1.00);
        TradeOrder l2 = new TradeOrder(dos, symbol, buyOrder, limitOrder, 1, 20.00);
        System.out.println("Expected: 1900"); // (20.00-1.00)*100 = 1900
        System.out.println("Actual: " + to.compare(l1, l2));
    }
}