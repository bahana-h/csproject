/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{
    // i think its ascending flag
    private boolean ascendingFlag;
    /* Compares two TradeOrder objects by price. */
    /* public PriceComparator()
        Constructs a price comparator that compares two orders in ascending order. Sets the private boolean ascending flag to true. */
    public PriceComparator() {
        ascendingFlag = true;
    }

    /* public PriceComparator(boolean asc)
        Constructs a price comparator that compares two orders in ascending or descending order. The order of comparison depends on the value of a given parameter. Sets the private boolean ascending flag to asc.

        Parameters:
        asc - if true, make an ascending comparator; otherwise make a descending comparator. */
    public PriceComparator(boolean asc){
        ascendingFlag = asc;
    }

    /* public int compare(TradeOrder order1,
                   TradeOrder order2)
        Compares two trade orders.

        Specified by:
        compare in interface java.util.Comparator<TradeOrder>
        Parameters:
        order1 - the first order
        order2 - the second order
        Returns:
        0: if both orders are market orders.
        -1: if order1 is market and order2 is limit.
        1: if order1 is limit and order2 is market.
        The difference in prices in cents: if both order1 and order2 are limit orders. In this last case, the difference returned is cents1 - cents2 or cents2 - cents1, depending on whether this is an ascending or descending comparator (ascending is true or false). You will need to round your answer otherwise floating point conversion to integer can cause errors. Be careful when rounding positive and negative values... */
    public int compare(TradeOrder order1, TradeOrder order2) {
        // boolean ofr order1
        boolean o1market = order1.
        boolean o1limit = order1.
        // for order 2
        boolean o2market = order2.
        boolean o2limit = order2.
        // 0, -1, and 1
        if (o1market == true && o2market == true) {
            return 0;
        } else if (o1market == true && o2limit == true) {
            return -1;
        } else if (o1limit = true && o2market) {
            return 1;
        } else { // both are limit orders
            if (ascendingFlag == true) {
                // cents1-2
            } else { // descending
                // cents2-1
            }
        }
        return 0;
    }

}
