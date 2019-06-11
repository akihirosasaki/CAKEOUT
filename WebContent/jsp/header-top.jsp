<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header">
	<div class="header-frame">
		<div class="logo">
			<a href="IndexServlet">CAKEOUT</a>
		</div>
		<c:if test="${isLogin==false}">
			<div class="login-button-wrapper">
				<a class="login-button" href="LoginViewServlet">ログイン</a>
			</div>
		</c:if>
		<c:if test="${isLogin==true}">
			<div class="account-button-wrapper">
				<img class="account-button" src="images/account.gif">
			</div>
		</c:if>
	</div>
</header>