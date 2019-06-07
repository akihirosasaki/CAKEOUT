<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <title>注文内容変更確認</title>
        <link rel="stylesheet" href="css/normalize.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/info.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/confirm.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/account.css" type="text/css" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/script.js"></script>
    </head>
    <body>
        <div class="account-show">
            <header class="header">
                <div class="header-frame-account">
                    <div class="header-account-text">
                        <p>アカウント</p>
                    </div>
                    <div class="cancel"><span></span></div>
                </div>
            </header>
            <section id="main">
                <section class="content">
                    <div class="account-content-box">
                        <div class="account-text-box">
                            <p><a href="">チケットを確認する</a></p>
                            <p><a href="">予約をキャンセルする</a></p>
                            <p><a href="">内容を変更する</a></p>
                            <p><a href="">ログアウトする</a></p>
                        </div>
                    </div>
                </section>
            </section>
        </div>
        <div class="main-show">
            <header class="header">
                <div class="header-frame">
                    <div class="logo">
                        <a href="IndexServlet">CAKEOUT</a>
                    </div>
                    <div class="account-button-wrapper">
                        <img class="account-button" src="images/account.gif">
                    </div>
                </div>
            </header>
            <section id="main">
                <section class="content">
                    <div class="content-block">
                        <div class="confirm-box">
                            <h1>人数を変更しますか？</h1>
                            <hr>
                            <div class="confirm-container confirm-container-cakes">
                                <div class="confirm-img-container"><img class="popular-suggest-img" src="images/<c:out value="${orderList[0].cakeStorePrimaryImg}" />" alt=""></div>
                                <div class="confirm-text-container">
                                    <h3><c:out value="${orderList[0].cakeStoreName}" /></h3>
                                </div>
                            </div>
                            <hr>
                            <img class="confirm-arrow-img" src="images/arrow.gif">
                            <hr>
                            <div class="confirm-container confirm-container-cafe">
                                <div class="confirm-img-container"><img class="popular-suggest-img" src="images/<c:out value="${orderList[0].cafeStorePrimaryImg}" />" alt=""></div>
                                <div class="confirm-text-container">
                                    <h3><c:out value="${orderList[0].cafeStoreName}" /></h3>
                                    <form action="OrderChangeServlet" method="POST" id="orderChangeForm">
	                                    <div class="cafe-order-number">
	                                    	<input type="hidden" name="orderId" value="${orderList[0].orderId}">
	                                        <input class="change-order-num-box" type="text" placeholder="人数" name="orderNum" required>
	                                    </div>
                                    </form>
                                </div>
                            </div>
                            <hr>
                        </div>
                        <div class="button-block">
                            <input class="submit-button" type="submit" value="変更する" form="orderChangeForm">
                        </div>
                    </div>
                </section>
            </section>
            <footer class="footer">
                <div class="footer-logo-box">
                    <div class="logo-footer">
                        <a href="IndexServlet">CAKEOUT</a>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html>