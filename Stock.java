import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

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

    public String getQuote(){
        String result = companyName + "(" + stockSymbol + ")\n" + "Price: " + lastPrice + " hi: " + hiPrice + " lo: " + loPrice + " vol: " + volume + "\n" + "Ask: "
        String so = "none";
        TradeOrder firstso = sellOrders.peek();
        if (firstso != null)
            result += firstso ;
        else{
            result+=so;
        }
        TradeOrder firstbo = buyOrders.peek();
        if (firstbo != null)
            result += firstbo ;
        else{
            result+=so;
        }
        return companyName + "(" + stockSymbol + ")\n" + "Price: " + lastPrice + " hi: " + hiPrice + " lo: " + loPrice + " vol: " + volume + "\n" + "Ask: ";
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
