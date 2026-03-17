import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );//money.format(double) makes it 2 decimals

    private String stockSymbol;
    private String companyName;
    private double loPrice, hiPrice, lastPrice;
    private int volume;
    private PriorityQueue<TradeOrder> buyOrders, sellOrders;

    // TODO complete class
    public Stock(String symbol, String name, double price){
        stockSymbol=symbol;
        companyName=name;
        loPrice=price;
        hiPrice=price;
        lastPrice=price;
        volume=0;
        sellOrders = new PriorityQueue<>(new PriceComparator());//supposed to make an empty pq with a normal pC
        buyOrders = new PriorityQueue<>(new PriceComparator(false));//supposed to make an empty pq with pc(false)
        //dont think this is done
    }

    public String getQuote(){//IMPORTANT: check white space between words again
        String result = companyName + "(" + stockSymbol + ")\n" + "Price: " + lastPrice + " hi: " + hiPrice + " lo: " + loPrice + " vol: " + volume + "\n" + "Ask: ";
        String so = "none";
        TradeOrder firstso = sellOrders.peek();
        if (firstso != null){
            result += money.format(firstso.getPrice()) ;
        result+= " size: " + sellOrders.size();}
        else{
            result+=so;//ignore size
        }
result += " Bid: ";
        TradeOrder firstbo = buyOrders.peek();
        if (firstbo != null){
            result += money.format(firstbo.getPrice()) ;
        result+= " size: " + buyOrders.size();}
        else{
            result+=so;//just the word none
        }
        return result;

//example:
//   Giggle.com (GGGL)
//   Price: 10.00  hi: 10.00  lo: 10.00  vol: 0
//   Ask: 12.75 size: 300  Bid: 12.00 size: 500
// Or:(if no sell/buy orders, no size)
//   Giggle.com (GGGL)
//   Price: 12.00  hi: 14.50  lo: 9.00  vol: 500
//   Ask: none  Bid: 12.50 size: 200
    }

    public void placeOrder(TradeOrder order){//CHECK WHITE SPACE THINGS
        if (order.isBuy()){
            buyOrders.add(order);}
        else
            sellOrders.add(order);
//   New order:  Buy GGGL (Giggle.com)
//   200 shares at $38.00
// Or, for market orders:
//   New order:  Sell GGGL (Giggle.com)
//   150 shares at market
        String msg = "New order:  ";
        if(order.isBuy()){
            msg += "Buy " + stockSymbol + " (" + companyName + ")\n";
            

        }
        order.getTrader().mailbox().add(msg);


        executeOrders();
    }

    protected void executeOrders(){//what is protected??? thats what the doc said to use...

    }
    //
    // The following are for test purposes only
    //
    
    protected String getStockSymbol()
    {
        return stockSymbol;
    }
    
    protected String getCompanyName()
    {
        return companyName;
    }
    
    protected double getLoPrice()
    {
        return loPrice;
    }
    
    protected double getHiPrice()
    {
        return hiPrice;
    }

    protected double getLastPrice()
    {
        return lastPrice;
    }
    
    protected int getVolume()
    {
        return volume;
    }

    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }
    
    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
