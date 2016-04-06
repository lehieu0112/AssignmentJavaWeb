
package business;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
    private ArrayList<LineItem> items;
    
    public Cart(){
        items = new ArrayList<LineItem>();
    }
    
    public ArrayList<LineItem> getItems() {
        return items;
    }
    
    public int getCount(){
        return items.size();
    }
    
    public void addItem(LineItem item){
        int  productID = item.getProduct().getProductID();
        int quantity = item.getQuantity();
        for(int i=0;i<items.size();i++){
            LineItem lineItem = items.get(i);
            if(lineItem.getProduct().getProductID() == productID){
               int currentQuantity = lineItem.getQuantity();
               lineItem.setQuantity(quantity+currentQuantity);
               return;
            }
        }
        items.add(item);
    }
    public void updateItem(LineItem item){
        int productID = item.getProduct().getProductID();
        int quantity = item.getQuantity();
        for(int i=0;i<items.size();i++){
            LineItem lineItem = items.get(i);
            if(lineItem.getProduct().getProductID() == productID){            
               lineItem.setQuantity(quantity);
               return;
            }
        }
    }
    public void removeItem(LineItem item){
        int productID = item.getProduct().getProductID();
        for(int i=0;i<items.size();i++){
            LineItem lineItem = items.get(i);
            if(lineItem.getProduct().getProductID() == productID){
               items.remove(i);
               return;
            }
        }
    }
}
