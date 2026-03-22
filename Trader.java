import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;
    private String screenName, password;
    private TraderView myView;
    private Queue<String> mailbox;

    //     Stores:
    // Trader name
    // Reference to Brokerage
    // Mailbox (Queue<String>)

    // Responsibilities:
    // Request quotes
    // Place orders
    // Receive messages
    // Display stored messages when logging in

    // constructor
    // sets all the fields
    public Trader(Brokerage b, String n, String p)
    {
        brokerage = b;
        screenName = n;
        password = p;
    }

    // methods

    //Compares this trader to another 
    // by comparing their screen names case blind.
    public int compareTo(Trader other)
    {
        return screenName.compareToIgnoreCase(other.screenName);
        
    }

    //Indicates whether some other trader is "equal to" this one, 
    // based on comparing their screen names case blind. 
    // This method will throw a ClassCastException 
    // if other is not an instance of Trader.
    public boolean equals(Object other)
    {
        // check if other is not a trader
        return true; // TODO fix
        

    }

    public String getName()
    {
        return screenName;
    }

    public String getPassword()
    {
        return password;
    }

    public void getQuote(String symbol)
    {
        // call brokerage's get quote
        return;
    }




    public boolean hasMessages()
    {
        //return WHATEVER MESSAGE CONTAINER.isEmpty();
        return true;
    }

    // Sets a new TraderView for this trader and saves a reference to it in myView. 
    // Removes and displays all the messages, if any, from this trader's mailbox 
    // by calling myView.showMessage(msg) for each message.
    public void setView(TraderView view)
    {

    }


    // Places a given order with the brokerage by calling brokerage's placeOrder.
    public void placeOrder(TradeOrder other)
    {
       // placeOrder(other);
    }


    // Logs out this trader. Calls brokerage's logout for this trader. 
    // Sets myView to null (this method is called from a 
    // TraderWindow's window listener when the "close window" button is clicked)
    public void quit()
    {
        // not made yet
        // logout(this);
    }


    // Adds msg to this trader's mailbox and displays all messages. 
    // If this trader is logged in (myView is not null) 
    // removes and shows all the messages in the mailbox 
    // by calling myView.showMessage(msg) for each msg in the mailbox.
    public void receiveMessage(String msg)
    {

    }





















    ////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // The following are for test purposes only
    //
    protected Queue<String> mailbox()
    {
        return mailbox;
    }
    
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Trader.
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
                if ( field.getType().getName().equals( "Brokerage" ) )
                    str += separator + field.getType().getName() + " "
                        + field.getName();
                else
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
