
package controller;

import business.Cart;
import business.Customers;
import business.Invoices;
import business.LineItem;
import business.Products;
import data.CustomerDB;
import data.OrderDB;
import data.ProductDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ShoppingCart", urlPatterns = {"/ShoppingCart"})
public class ShoppingCart extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductDB pDB = new ProductDB();
        Products product;
        String url = "/allproducts.jsp";
        if((action.equals("add"))||(action.equals("update"))
                ||(action.equals("remove"))){
            int productID = Integer.parseInt(request.getParameter("productID"));
            String quantityString = request.getParameter("quantity");
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("cart");
            if(cart == null){
                cart = new Cart();
            }
            int quantity;
            try{
                quantity = Integer.parseInt(quantityString);
                if(quantity<0){
                    quantity = 1;
                }                
            }catch(NumberFormatException e){
                quantity = 1;
            } 
            product = pDB.getProduct(productID);
            
            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);
            if((quantity>0)&&(action.equals("add"))){
                cart.addItem(lineItem);
            }else if((quantity>0)&&(action.equals("update"))){
                cart.updateItem(lineItem);
            }else if(quantity == 0){
                cart.removeItem(lineItem);
            }
            session.setAttribute("cart", cart);
            Invoices invoice = new Invoices();
            invoice.setItems(cart.getItems());
            String invoiceTotal = invoice.getInvoiceTotal();
            session.setAttribute("invoiceTotal", invoiceTotal);
            
            url = "/cart.jsp";
            
        }else if(action.equals("checkout")){
            HttpSession session = request.getSession();
            Cart cart = (Cart)session.getAttribute("cart");
            String customerName = request.getParameter("customerName");
            String customerEmail = request.getParameter("customerEmail");
            String customerPhoneNumber = request.getParameter("customerPhoneNumber");
            String customerAddress = request.getParameter("customerAddress");
            String customerNote = request.getParameter("customerNote");
            int quantity = 0;           
            CustomerDB cDB = new CustomerDB();
            OrderDB oDB = new OrderDB();
            Customers customer;
            Invoices invoice;
            url = "/thanks.jsp";
            String message = "Giỏ hàng của bạn trống !";
            if(cart !=null){
                for(LineItem item: cart.getItems()){
                    quantity += item.getQuantity();
                }
                if(quantity>0){
                    customer = new Customers();
                    customer.setCustomerName(customerName);
                    customer.setCustomerEmail(customerEmail);
                    customer.setCustomerAddress(customerAddress);
                    customer.setCustomerPhoneNumber(customerPhoneNumber);
                    customer.setCustomerNote(customerNote);
                    if(cDB.isExistsCustomer(customerEmail)){
                        cDB.updateCustomer(customer);
                    }else{
                        cDB.insertCustomer(customer);
                    }
                    
                    invoice = new Invoices();
                    invoice.setCustomer(customer);
                    invoice.setItems(cart.getItems());
                    
                    if(oDB.insertInvoice(invoice)){
                        message = "Đặt hàng thành công !";
                        Cart newCart = new Cart();
                        session.setAttribute("cart", newCart);
                        Invoices newInvoice = new Invoices();
                        invoice.setItems(newCart.getItems());
                        String invoiceTotal = invoice.getInvoiceTotal();
                        session.setAttribute("invoiceTotal", invoiceTotal);
                        
                    }else{
                        message = "Failed ! Lỗi hệ thống !";
                    }                     
                }
                
            }
            request.setAttribute("message", message);
        }
        
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
