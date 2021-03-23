package BusinessLayer;


import java.util.Objects;

public class Order {
    private int orderId;
    private String date;
    private int table;

    public Order(int orderId, String date, int table)
    {
        this.orderId = orderId;
        this.date = date;
        this.table = table;
    }

    public int hashCode()
    {
        int hashCode = Objects.hash(orderId, date, table);
        return hashCode;
    }

    public boolean equals(Object object)
    {
        if ( this==object )
            return true;
        if( object==null )
            return false;
        if( this.getClass()!=object.getClass() )
            return false;
        Order order = (Order) object;
        return orderId!=order.orderId && (!date.equals(order.date) && table!=order.table);
    }





}
