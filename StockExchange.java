import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a stock exchange. A <code>StockExchange</code> keeps a
 * <code>HashMap</code> of stocks, keyed by a stock symbol. It has methods to
 * list a new stock, request a quote for a given stock symbol, and to place a
 * specified trade order.
 */
public class StockExchange // hannah
{
    private Map<String, Stock> listedStocks;
    
    // constructors
    /* public StockExchange()
        Constructs a new stock exchange object. Initializes listed stocks to an empty map (a HashMap). */
    public StockExchange() {
        listedStocks = new HashMap<String, Stock>();
    }

    /* public void listStock(java.lang.String symbol,
                      java.lang.String name,
                      double price)
        Adds a new stock with given parameters to the listed stocks.

        Parameters:
            symbol - stock symbol.
            name - full company name.
            price - opening stock price.
     */
    public void listStock(String symbol, String name, double price) {
        // new stock to listed stocks
        Stock newer = new Stock(symbol, name, price);
        // actually puts it in
        listedStocks.put(symbol, newer);
    }

    /* public java.lang.String getQuote(java.lang.String symbol)
    Returns a quote for a given stock. If the symbol (ex. XYZ) is not found in the exchange's list of stocks, the string that is returned should be "XYZ not found".

    Parameters:
        symbol - stock symbol.
    Returns:
        a text message that contains the quote. */
    public String getQuote(String symbol) {
        if (!listedStocks.containsKey(symbol)) {
            // if does not contain the. symbol
            //return like symbol "not found"
            return symbol + " not found";
        } else {
            // if does contain: returns quote for given stock
            Stock found = listedStocks.get(symbol);
            // and then use that to give the quote??? - update: getQuote() is in stock.java
            return found.getQuote();
        }
    }

    /* placeOrder
    public void placeOrder(TradeOrder order)
        Places a trade order by calling stock.placeOrder for the stock specified by the stock symbol in the trade order. If the stock (ex. XYZ) is not found in the exchange's list of stocks, then the exchange sends a message to the trader with the message "XYZ not found".

    Parameters:
        order - a trading order to be placed with this stock exchange. */
    public void placeOrder(TradeOrder order) {
        // i think this is just hte same as getquote basically but u need to find the symbol first
        String symbol = order.getSymbol();
        if (!listedStocks.containsKey(symbol)) {
            // if does not contain the. symbol
            // sends a message to the trader with the message "XYZ not found"
            // im just printing it because i dont know how to send it to trader
            System.out.println(symbol + " not found");
            // receiveMessage(symbol + " not found") <- u gotta send it to a xertain trader though?
        } else {
            // if does contain: pplaces it for given stock
            Stock found = listedStocks.get(symbol);
            // and then use that to place order using stock,placeorder
            found.placeOrder(order);
        }
    }

    //
    // The following are for test purposes only
    //
    protected Map<String, Stock> getListedStocks()
    {
        return listedStocks;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this StockExchange.
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
