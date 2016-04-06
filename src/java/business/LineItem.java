
package business;

import java.io.Serializable;
import java.text.NumberFormat;

public class LineItem implements Serializable{
    private int invoiceID;
    private Products product;
    private int quantity;

    public LineItem(){
    }
        
    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }
    
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double lineItemTotal(){
        return (product.getPrice() * quantity);
    }
    public String getLineItemTotal(){
        NumberFormat c = NumberFormat.getNumberInstance();
        return c.format(this.lineItemTotal());
    }
    
}
