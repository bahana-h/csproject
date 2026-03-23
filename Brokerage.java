import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a brokerage.
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;
    private Set<Trader> loggedTraders;
    private StockExchange exchange;

    // constructor

    // Constructs new brokerage affiliated with a given stock exchange. 
    // Initializes the map of traders to an empty map (a TreeMap), 
    // keyed by trader's name; 
    // initializes the set of active (logged-in) traders to an empty set (a TreeSet).
    public Brokerage( StockExchange exchange)
    {
        // new borkerage with an exchange
        this.exchange = exchange;

        // intiliaze map of traders
        traders = new TreeMap<String, Trader>();

        // set of active traders
        loggedTraders = new TreeSet<Trader>();

    }

    // methods

    // Tries to register a new trader with a given screen name and password. 
    // If successful, creates a Trader object for this trader
    //  and adds this trader to the map of all traders (using the screen name as the key).

    //0 if successful, or an error code (a negative integer) if failed:
    //-1 -- invalid screen name (must be 4-10 chars)
    //-2 -- invalid password (must be 2-10 chars)
    //-3 -- the screen name is already taken.
    public int addUser(String name, String password)
    {
        // invalid screen name
        // not between 4 or 10 char

        if (name.length() < 4 || name.length() > 10)
        {
            return -1;
        }

        // invalid password
        // not between 2 or 10
        if (password.length() < 2 || password.length() > 10)
        {
            return -2;
        }

        // screen name already taken
        // so if the name can be found in the colleciton of traders
        // ooh fun contains key cuz it's a map
        if (traders.containsKey(name))
        {
            return -3;
        }


        // now to actualy adding the user 
        // just add the object to the map
        Trader trader = new Trader(this, name, password);
        traders.put(name, trader);

        // all good
        return 0;
    }

    //Removes a specified trader donedone
    // from the set of logged-in traders. 
    // The trader may be assumed 
    // to logged in already

    // logout just removes it from set
    public void logout(Trader trader)
    {
        loggedTraders.remove(trader);
    }

    // does oppostie of logout
    // requirements
    //Tries to login a trader with a given screen name and password. 
    // If no messages are waiting for the trader, sends a "Welcome to SafeTrade!" 
    //message to the trader. Adds the trader to the set of all logged-in traders.

    //retuns
    //0 if successful, or an error code (a negative integer) if failed:
    //-1 -- screen name not found
    //-2 -- invalid password
    //-3 -- user is already logged in.

    public int login(String name, String password)
    {
        // identify whoever's loging in and get their info
        Trader loggingIn = traders.get(name);

        // screen name not there
        if(loggingIn == null)
        {
            return -1;
        }
        
        // invalid password
        // highlighted yellow can't compare strings with ==
        if(loggingIn.getPassword().equals(password))
        {
            return -2;
        }

        // user already logged in
        // check if alr in logged in base
        if (loggedTraders.contains(loggingIn))
        {
            return -3;
        }

        // alr they passed the tests
        // jst log them in
        // by adding them to the logged base
        loggedTraders.add(loggingIn);

        // dispay all the messages
        // so if none are there just send them the welcome

        if(!loggingIn.hasMessages())
        {
            loggingIn.receiveMessage("Welcome to SafeTrade!");
        }



        // all good
        return 0;

    }

    //equests a quote for a given stock from the stock exachange 
    //and passes it along to the trader by calling trader's receiveMessage method.
    public void getQuote(String symbol, Trader trader)
    {
        String gottenQuote = exchange.getQuote(symbol);
        // send it to trader
        trader.receiveMessage(gottenQuote);
    }

    // jst place it on the exchange
    public void placeOrder(TradeOrder order)
    {
        exchange.placeOrder(order);
    }

    













    /////////////////////////////////////////////// tests
    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }

    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }

    protected StockExchange getExchange()
    {
        return exchange;
    }

    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
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
