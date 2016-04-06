
package data;

import business.Products;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDB {
    public ArrayList<Products> getProductsList(){
        ArrayList<Products> productsList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "select * from Products order by price asc";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sqlString);
            rs = ps.executeQuery();
            Products product;
            while(rs.next()){
                product = new Products();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setDescriptions(rs.getString("descriptions"));
                product.setPrice(rs.getDouble("price"));
                product.setWarranty(rs.getInt("warranty"));
                product.setPictureLink(rs.getString("pictureLink"));
                
                productsList.add(product);
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(con);
        }  
        return productsList;
    }
    public Products getProduct(int productID){
        Products product = new Products();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "select * from Products where productID = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sqlString);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            while(rs.next()){
                product = new Products();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setDescriptions(rs.getString("descriptions"));
                product.setPrice(rs.getDouble("price"));
                product.setWarranty(rs.getInt("warranty"));
                product.setPictureLink(rs.getString("pictureLink"));            
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(con);
        }  
        return product;
    }
    public ArrayList<Products> searchProducts(String search){
        ArrayList<Products> productsList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "select * from Products where productName like '%"+search+"%'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sqlString);            
            rs = ps.executeQuery();
            Products product;
            while(rs.next()){
                product = new Products();
                product.setProductID(rs.getInt("productID"));
                product.setProductName(rs.getString("productName"));
                product.setDescriptions(rs.getString("descriptions"));
                product.setPrice(rs.getDouble("price"));
                product.setWarranty(rs.getInt("warranty"));
                product.setPictureLink(rs.getString("pictureLink"));
                
                productsList.add(product);
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(rs);
            pool.freeConnection(con);
        }  
        return productsList;
    }

}
