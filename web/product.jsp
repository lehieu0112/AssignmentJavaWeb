<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="header.jsp"/>
<main id="main_product">
    <img src="<c:url value="${product.pictureLink}"/>" width="360" height="360">
    <h3><strong>${product.productName}</strong></h3><br>
    <p><strong>Mã sản phẩm :</strong> ${product.productID}</p><br>
    <p><strong>Thông tin sản phẩm :</strong><br> ${product.descriptions}</p><br>
    <p><strong>Giá :</strong> ${product.formatPrice} VNĐ</p><br>
    <p><strong>Bảo hành :</strong> ${product.warranty} tháng</p><br>
    <form action="ShoppingCart" method="post">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="productID" value="${product.productID}">
        <input type="submit" name="submit" value="Chọn mua">
    </form>
</main>
 <c:import url="footer.jsp"/>