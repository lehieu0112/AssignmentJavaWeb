
package data;

import business.Invoices;
import business.LineItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDB {
    public boolean insertInvoice(Invoices invoice){
        boolean insert = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "insert into Invoices"
                + "(customerID,invoiceTotal) values(?,?)";
        PreparedStatement ps=null;
        Statement s;
        ResultSet rs=null;
        try{
            ps = con.prepareStatement(sqlString);
            ps.setInt(1, invoice.getCustomer().getCustomerID());
            ps.setDouble(2, invoice.InvoiceTotal());
            ps.executeUpdate();
            insert = true;
            
            String identityQuery = "select @@IDENTITY as invoiceID";
            s = con.createStatement();
            rs = s.executeQuery(identityQuery);
            rs.next();
            int invoiceID = rs.getInt("invoiceID");
            rs.close();
            s.close();
            for(LineItem item: invoice.getItems()){
                item.setInvoiceID(invoiceID);
                insertLineItem(item);
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
            
        }
        return insert;
    }
    public void insertLineItem(LineItem item){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "insert into LineItems"
                + "(invoiceID,productID,quantity,lineItemTotal) values(?,?,?,?)";
        PreparedStatement ps=null;
        try{
            ps = con.prepareStatement(sqlString);
            ps.setInt(1, item.getInvoiceID());
            ps.setInt(2, item.getProduct().getProductID());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.lineItemTotal());
            ps.executeUpdate();
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
    } 
   
}
