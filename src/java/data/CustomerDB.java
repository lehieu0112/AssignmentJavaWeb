
package data;

import business.Customers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDB {
    public boolean insertCustomer(Customers customer){
        boolean insert = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "insert into Customers"
                + "(customerName,customerEmail,customerAddress,"
                + "customerPhoneNumber,customerNote)" 
                + " values(?,?,?,?,?)";
        PreparedStatement ps=null;
        Statement s;
        ResultSet rs=null;
        try{
            ps = con.prepareStatement(sqlString);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerEmail());
            ps.setString(3, customer.getCustomerAddress());
            ps.setString(4, customer.getCustomerPhoneNumber());
            ps.setString(5, customer.getCustomerNote());
            ps.executeUpdate();
            insert = true;
            
            String identityQuery = "select @@IDENTITY as customerID";
            s = con.createStatement();
            rs = s.executeQuery(identityQuery);
            rs.next();
            int customerID = rs.getInt("customerID");
            rs.close();
            s.close();
            customer.setCustomerID(customerID);
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return insert;
    }
    public boolean isExistsCustomer(String email){
        boolean exists = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "select * from Customer where customerEmail =?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = con.prepareStatement(sqlString);
            ps.setString(1,email);
            rs = ps.executeQuery();
            while(rs.next()){
                exists = true;
            }
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return exists;
    }
    public boolean updateCustomer(Customers customer){
        boolean update = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection con = pool.getConnection();
        String sqlString = "update Customers set "
                + "customerName=?,customerAddress=?,"
                + "customerPhoneNumber=?,customerNote=? "
                + "where customerEmail=?";
        PreparedStatement ps=null;
        Statement s;
        ResultSet rs;
        try{
            ps = con.prepareStatement(sqlString);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerAddress());
            ps.setString(3, customer.getCustomerPhoneNumber());
            ps.setString(4, customer.getCustomerNote());
            ps.setString(5, customer.getCustomerEmail());
            ps.executeUpdate();
            update = true; 
            
            String email = customer.getCustomerEmail();
            String identityQuery = "select customerID from Customers where customerEmail="+email;
            s = con.createStatement();
            rs = s.executeQuery(identityQuery);
            rs.next();
            int customerID = rs.getInt("customerID");
            rs.close();
            s.close();
            customer.setCustomerID(customerID);
            
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(con);
        }
        return update;
    }
}
