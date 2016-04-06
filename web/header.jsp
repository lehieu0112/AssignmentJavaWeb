
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
        <link rel="stylesheet" type="text/css" href="/FinalAssignmentJavaWeb/styles/main.css">
    </head>
<body>

    <header>
        <h1>Wellcome to shopping cart web appplication !</h1><br>
        <ul>
            <li><a href="ProductList"><strong>Trang chủ</strong></a></li>
            <li><a href="ProductList?action=search&searchValue=NOTEBOOK"><strong>Laptop</strong></a></li>
            <li><a href="ProductList?action=search&searchValue=MTB"><strong>Máy tính bảng</strong></a></li>
            <li><a href="ProductList?action=search&searchValue=MOBILE"><strong>Điện thoại</strong></a></li>
            <li><a href="ProductList?action=search&searchValue=PC"><strong>Máy tính</strong></a></li>
            <li><a href="cart.jsp"><strong>Giỏ hàng của bạn (${cart.count} items)</strong></a></li>
            <li id="li_form">
                <form action="ProductList" method="post">
                    <input type="hidden" name="action" value="search">
                    <input type="text" name="searchValue">
                    <input type="submit" name="submit" value="Tìm kiếm">
                </form>
            </li>
        </ul>
    </header>
