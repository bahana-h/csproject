public class MyTests_Brokerage {


    // fields
        static StockExchange theExchange = new StockExchange();
        static Brokerage plzWork = new Brokerage(theExchange);
        static Trader theTrader = new Trader(plzWork, "name", "password");

        // public TradeOrder(Trader traDer, String symBol, boolean buYorder, boolean markeTorder, int numsHares, double prIce)
        static TradeOrder theTradeOrder = new TradeOrder(theTrader, "GGGL", true, true, 54, 150.00);



    public static void test() 
    {

        System.out.println("\n===== Brokerage Tests =====");
        // calling all of them
        brokerageConstructorTest();
        addUserTest();
        logoutTest();
        loginTest();
        getQuoteTest();
        placeOrderTest();

        
        //getTradersTest();
        //getLoggedTradersTest();
        //getExchangeTest();
    

        // TO SRING TESTTTUHH
        //toStringTest();
    }


        // test for constructor
        private static void brokerageConstructorTest()
        {
            Brokerage testBrokerage = new Brokerage(null);
        }



        private static void addUserTest()
        {
            // method tests

            // add user tests
            // make sure to test every failure

            //0 if successful, or an error code (a negative integer) if failed:
            plzWork.addUser("testName", "testPass");

            //-1 -- invalid screen name (must be 4-10 chars)
            plzWork.addUser("k", "somepassword");
            
            //-2 -- invalid password (must be 2-10 chars)
            plzWork.addUser("someName", "justAReallyLongStringThatsMoreThanTen");

            //-3 -- the screen name is already taken.
            plzWork.addUser("testName", "whatever");
        }

        public static void logoutTest()
        {
            plzWork.logout(theTrader);
        }

        public static void loginTest()
        {
            // don't know if i should put the first login first or last...
            //0 if successful, or an error code (a negative integer) if failed:
            plzWork.login("name", "password");
            //-1 -- screen name not found
            plzWork.login("whoever", "password");
            //-2 -- invalid password
            plzWork.login("name", "notPass");
            //-3 -- user is already logged in.
            plzWork.login("name", "password");

            
        }

        public static void getQuoteTest()
        {
            plzWork.getQuote("GGGL", theTrader);
        }



        public static void placeOrderTest()
        {
            plzWork.placeOrder(theTradeOrder);
        }



        public static void getTradersTest()
        {
            plzWork.getTraders();
        }

        public static void getLoggedTradersTest()
        {
            plzWork.getLoggedTraders();
        }

        public static void getExchangeTest()
        {
            plzWork.getExchange();
        }
}