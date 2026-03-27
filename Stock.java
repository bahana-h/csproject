import java.lang.reflect.*;
import java.text.DecimalFormat;
import java.util.*;

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
        String result = companyName + " (" + stockSymbol + ")\n" + "Price: " + money.format(lastPrice) + " hi: " + money.format(hiPrice) + " lo: " + money.format(loPrice) + " vol: " + volume + "\n" + "Ask: ";
        String so = "none";
        TradeOrder firstso = sellOrders.peek();
        if (firstso != null){
            if(firstso.isMarket())
                result+= "market";
            else
                result += money.format(firstso.getPrice()) ;
        result+= " size: " + sellOrders.size();}
        else{
            result+=so;//ignore size
        }
result += " Bid: ";
        TradeOrder firstbo = buyOrders.peek();
        if (firstbo != null){
            if(firstbo.isMarket())
                result+= "market";
            else
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


// allright allright
// *alright

// last correctness test that came back as an error is here...
//this town's too small for the two of us buddy

// good bad and ugly theme plays
// wah wah wah


    public void placeOrder(TradeOrder order){//CHECK WHITE SPACE THINGS
        // hnnah comments: omgs hopefully i didnt break this T_T
        /*
        if (order.isBuy()){
            buyOrders.add(order);}
        else
            sellOrders.add(order);
        */
//   New order:  Buy GGGL (Giggle.com)
//   200 shares at $38.00
// Or, for market orders:
//   New order:  Sell GGGL (Giggle.com)
//   150 shares at market

        // u only use this as part of the end message i think
        String msg = "New order:  ";


    // now we seperate them by buy or sell

    // FORMAT ISSUE
    // on the safetrade documentation
    // you have to put "at market" it it's at market
    // neha i think you brought this up in class

        if(order.isBuy())
        {
            msg += "Buy " + stockSymbol + " (" + companyName + ")\n";
            msg+= order.getShares() + " shares at ";
            
            if (order.isMarket())
            {
                msg = msg + "market";

            }

            else
            {
                msg = msg + "$" + money.format(order.getPrice());
            }

            // add it 
            buyOrders.add(order);
        }

        // now for the sell

        else
        {
            msg += "Sell " + stockSymbol + " (" + companyName + ")\n";
            msg+= order.getShares() + " shares at ";

            if(order.isMarket())
            {
                msg+="market";
            }
            else
            {
                msg+= "$" + money.format(order.getPrice());
            }


            sellOrders.add(order);
        }



        order.getTrader().receiveMessage(msg);


        // at the end this thing's right
        executeOrders();
    }

    protected void executeOrders(){//what is protected??? thats what the doc said to use...
        TradeOrder ts = sellOrders.peek();//top
        TradeOrder tb = buyOrders.peek();//top

        // hannah edits - because stockexchagne isnt working properly
        // omgs its still not workwoenkaflndklfasdm
        if (ts == null || tb == null) {
            return;
        }
        // hannah edits - added hte null stuff -> hopefully it helps??????
        //while((ts!=null && tb !=null) && !((ts.isLimit()&&tb.isLimit()&&tb.getPrice()>=ts.getPrice())
        //)){
    // ||((ts.isLimit()&&tb.isMarket())||(ts.isMarket()&&tb.isLimit())
    // )||(ts.isMarket()&&tb.isMarket())))
        //if(ts.isLimit()&&tb.isLimit()&&tb.getPrice()>=ts.getPrice()){
            //step 2 at sell order price


            // sashark edits
            // before,
            // a valid sale condition didn't even exist lmao
            // because the loop ran when the orders DID NOT match, cuz they were both the same
            // so changed
            while (ts != null && tb != null && 
                (ts.isMarket() || tb.isMarket() || tb.getPrice() >= ts.getPrice()))
                // now market orders match
                // and the limit orders match
            {

            // alright this part is good
            int ss = 0; 
            if(ts.getShares()<tb.getShares()){
                ss = ts.getShares();
                tb.subtractShares(ts.getShares());
                ts.subtractShares(ts.getShares());
            }
            else{
                ss = tb.getShares();//need to store this for msg to buyer/seller later
                ts.subtractShares(tb.getShares());
                tb.subtractShares(tb.getShares());
                     
            }

        // alrgith the behemoth below i just scavenged teh stuff that worked
        // for future generations

        
/** 
 * 
            //update prices here
            if(ts.getPrice()< loPrice)
                loPrice = ts.getPrice();//new low price
            if(ts.getPrice()> hiPrice)
                hiPrice = ts.getPrice();//new high price
            lastPrice = ts.getPrice();
            volume += ss;//check...sasha said its right

            String msgtoBuyer = "You bought: "+ ss +" "+ stockSymbol + " at " + money.format(ts.getPrice()) + " amt" + money.format(ss*ts.getPrice());
            tb.getTrader().receiveMessage(msgtoBuyer);
            String msgtoSeller = "You sold: "+ ss+" "+ stockSymbol + " at " + money.format(ts.getPrice()) + " amt" + money.format(ss*ts.getPrice());
            ts.getTrader().receiveMessage(msgtoSeller);
            
            if(ts.getShares()==0){
                sellOrders.remove();
            }
            if(tb.getShares()==0){
                buyOrders.remove();
            }
            ts = sellOrders.peek();
            tb = buyOrders.peek();


         // hannah edits - idk stockexchange is having null pointer issues so im js adding this everywhere
        if(ts!=null && tb!=null && ((ts.isLimit()&&tb.isMarket())||(ts.isMarket()&&tb.isLimit()))){
            //step 2 at lim order price
            
            if(ts.getShares()<tb.getShares()){
                tb.subtractShares(ts.getShares());
                ss = ts.getShares();
                ts.subtractShares(ts.getShares());
            }
            else{
                ts.subtractShares(tb.getShares());
                ss = tb.getShares();
                tb.subtractShares(tb.getShares());
                
                 
            }//update prices here
            double limorderpr = 0;
            if (tb.isLimit()){
                limorderpr=tb.getPrice();
            }
            else{
                limorderpr=ts.getPrice();
            }
            if(limorderpr< loPrice)
                loPrice = limorderpr;
            if(limorderpr> hiPrice)
                hiPrice = limorderpr;
            lastPrice = limorderpr;
            volume += ss;//check...

            String msg2toBuyer = "You bought: "+ ss +" "+ stockSymbol + " at " + money.format(limorderpr) + " amt " + money.format(ss*limorderpr);
            tb.getTrader().receiveMessage(msg2toBuyer);
            String msg2toSeller = "You sold: "+ ss+" "+ stockSymbol + " at " + money.format(limorderpr) + " amt " + money.format(ss*limorderpr);
            ts.getTrader().receiveMessage(msg2toSeller);
            
            if(ts.getShares()==0){
                sellOrders.remove();
            }
            if(tb.getShares()==0){
                buyOrders.remove();
            }
        ts = sellOrders.peek();
        tb = buyOrders.peek();
        }
        if(ts!=null && tb!=null && ts.isMarket()&&tb.isMarket()){
            //step 2 at last sale price
            //int ss = 0;
            if(ts.getShares()<tb.getShares()){
                tb.subtractShares(ts.getShares());
                ss = ts.getShares();
                ts.subtractShares(ts.getShares());
            }
            else{
                ts.subtractShares(tb.getShares());
                ss = tb.getShares();
                tb.subtractShares(tb.getShares());
                
                 
            }
            
            //update prices here
            if(lastPrice< loPrice)
                loPrice = lastPrice;
            if(lastPrice> hiPrice)
                hiPrice = lastPrice;
            volume += ss;//check...
            String msg3toBuyer = "You bought: "+ ss +" "+ stockSymbol + " at " + money.format(lastPrice) + " amt " + money.format(ss*lastPrice);
            tb.getTrader().receiveMessage(msg3toBuyer);
            String msg3toSeller = "You sold: "+ ss+" "+ stockSymbol + " at " + money.format(lastPrice) + " amt " + money.format(ss*lastPrice);
            ts.getTrader().receiveMessage(msg3toSeller);
            
            if(ts.getShares()==0){
                sellOrders.remove();
            }
            if(tb.getShares()==0){
                buyOrders.remove();
            }
            
        }
            ts = sellOrders.peek();
            tb = buyOrders.peek();

            */
        // alright so basically
        // i just pushed that part under the rug
        // compacted it in there
        // let's get the right ideas down and then we can implement them

        // we have to determine trade price
        double tradePrice = 0;
        // possibilities

        // both trade market
        // sell at last market price
        if(ts.isMarket() && tb.isMarket())
        {
            tradePrice = lastPrice;
        }

        // both limit orders
        // trade at the set price
        else if(ts.isLimit() && tb.isLimit())
        {
            tradePrice = ts.getPrice();
        }

        // ok now we gotta deal with differnces
        // just logic

        // if buy is market and sell is limit
        // use sell limit
        else if (tb.isMarket())
        {
            tradePrice = ts.getPrice();
        }

        // if sell is market then buy limit
        else if (ts.isMarket())
        {
            tradePrice = tb.getPrice();
        }

        //update prices here
        // this part is correct too i think
            if(ts.getPrice()< loPrice)
                loPrice = ts.getPrice();//new low price
            if(ts.getPrice()> hiPrice)
                hiPrice = ts.getPrice();//new high price
            lastPrice = ts.getPrice();
            volume += ss;//check...sasha said its right


        // the message part has some problems

        // BUYER
        // use trade price instead of ts.getprice basically
        // annhilates mistakes
            String msgtoBuyer = "You bought: "+ ss +" "+ stockSymbol + " at " + money.format(tradePrice) + " amt" + money.format(ss*tradePrice);
            tb.getTrader().receiveMessage(msgtoBuyer);

        // SELLER
            String msgtoSeller = "You sold: "+ ss+" "+ stockSymbol + " at " + money.format(tradePrice) + " amt" + money.format(ss*tradePrice);
            ts.getTrader().receiveMessage(msgtoSeller);

        // this part's right too
        // remove all the zeroed ones
        if(ts.getShares()==0)
            {
                sellOrders.remove();
            }

        if(tb.getShares()==0)
            {
                buyOrders.remove();
            }

        
        // reset everything back to what it needs to be
        // and that's it for every while loop?
        ts = sellOrders.peek();
        tb = buyOrders.peek();






        
        }
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
