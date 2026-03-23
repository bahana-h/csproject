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
        // if doesn't meet conditions
        //return -1;
        //return -2;
        //return -3;

        // if good
        return 0;
    }

    //Removes a specified trader 
    // from the set of logged-in traders. 
    // The trader may be assumed 
    // to logged in already

    public void logout(Trader trader)
    {

    }

    // does oppostie of logout
    public int login(Trader trader)
    {
        return 0;

    }

    public void getQuote(String symbol, Trader trader)
    {

    }

    public void placeOrder(TradeOrder order)
    {

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
