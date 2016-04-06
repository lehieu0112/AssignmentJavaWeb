
package business;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Invoices implements Serializable{
    private int invoiceID;
    private Customers customer;
    private ArrayList<LineItem> items;
    
    public Invoices(){
    }
    
    public double InvoiceTotal(){
        double total=0;
        for(LineItem item: this.items){
            total += item.lineItemTotal();
        }
        return total;
    }
    
    public String getInvoiceTotal(){
        NumberFormat c = NumberFormat.getNumberInstance();
        return c.format(this.InvoiceTotal());
    }
    
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<LineItem> items) {
        this.items = items;
    }

}
