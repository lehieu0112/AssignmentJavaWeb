<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="header.jsp"/>
<main id="main_product">
    <h1>Giỏ hàng của bạn : </h1><br>
    
    <table>
            <tr>
                <th>Số lượng</th>
                <th>Tên sản phẩm</th>
                <th>Đơn giá</th>
                <th>Tổng tiền</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${cart.items}">
                <tr>
                    <td>
                        <form action="ShoppingCart" method="post">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="productID" 
                                   value="<c:out value='${item.product.productID}'/>">
                            <input type="text" name="quantity" 
                                   value="<c:out value='${item.quantity}'/>" id="quantity">
                            <input type="submit" value="Cập nhật">
                        </form>
                    </td>
                    <td><c:out value="${item.product.productName}" /></td>
                    <td>${item.product.formatPrice}đ</td>
                    <td id="total">${item.lineItemTotal}đ</td>
                    <td>
                        <form action="ShoppingCart" method="post">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="productID" 
                                   value="<c:out value='${item.product.productID}'/>">
                            <input type="hidden" name="quantity" value="0">
                            <input type="submit" value="Xóa">
                        </form>
                    </td>
                </tr>
            </c:forEach>
    </table><br>
        
    <p><b>Để thay đổi số lượng</b>,nhập số lượng và nhấn cập nhật</p><br>
            <form action="ProductList" method="post">
                <input type="hidden" name="action" value="shop">
                <input type="submit" value="Tiếp tục mua sắm">
            </form><br><br>
            <h3>Tổng số tiền phải thanh toán: ${invoiceTotal} VNĐ</h3><br>
            <p><b>Vui lòng điền thông tin để thanh toán: </b></p><br>
            <form id="checkout" action="ShoppingCart" method="post">
                <label>Họ và tên: </label>
                <input type="text" name="customerName" required autofocus><span class="required">*</span><br>
                <label>Email: </label>
                <input type="email" name="customerEmail" required><span class="required">*</span><br>
                <label>Địa chỉ nhận hàng: </label>
                <input type="text" name="customerAddress" required><span class="required">*</span><br>
                <label>Số điện thoại: </label>
                <input type="tel" name="customerPhoneNumber" required><span class="required">*</span><br>
                <label>Ghi chú: </label><br>
                <textarea name="customerNote" rows="10" cols="30"></textarea><br>
                <input type="hidden" name="action" value="checkout">
                <input type="submit" value="Thanh toán">
            </form>
</main>
 <c:import url="footer.jsp"/>