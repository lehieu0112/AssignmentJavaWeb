<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:import url="header.jsp"/>
<main>
            
        <c:forEach var="product" items="${productsList}">
            <div class="floating-box">
                <a href="<c:url value="/ProductList?action=get&productID=${product.productID}"/>">
                <img src="<c:url value="${product.pictureLink}"/>"
                     width="120" height="120"></a>  
                <h5>${product.productName}<br>
                     Mã sản phẩm: ${product.productID}
                     <br>${product.formatPrice} VNĐ</h5>
            </div>
        </c:forEach>
        
</main>
<c:import url="footer.jsp"/>
    
