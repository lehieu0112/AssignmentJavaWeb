
package business;

import java.io.Serializable;
import java.text.NumberFormat;

public class Products implements Serializable {

   private int productID;
   private String productName;
   private String descriptions;
   private double price;
   private int warranty;
   private String pictureLink;
   
   public Products(){
   }
   
   public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }
    public String getFormatPrice(){
        NumberFormat c = NumberFormat.getNumberInstance();
        return c.format(this.price);
    }
    
}
