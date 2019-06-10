<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="account-show">
	<header class="header">
		<div class="header-frame-account">
			<div class="header-account-text">
				<p>アカウント</p>
			</div>
			<div class="cancel">
				<span></span>
			</div>
		</div>
	</header>
	<section id="main">
		<section class="content">
			<div class="account-content-box">
				<div class="account-text-box">
					<form action="OrderListServlet" method="POST">
						<input type="hidden" name="accountLink" value="ticket">
						<p>
							<input type="submit" name="submit" value="チケットを確認する">
						</p>
					</form>
					<form action="OrderListServlet" method="POST">
						<input type="hidden" name="accountLink" value="cancel">
						<p>
							<input type="submit" name="submit" value="予約をキャンセルする">
						</p>
					</form>
					<form action="OrderListServlet" method="POST">
						<input type="hidden" name="accountLink" value="change">
						<p>
							<input type="submit" name="submit" value="人数を変更する">
						</p>
					</form>
					<form action="LogoutServlet" method="GET">
						<p>
							<input type="submit" name="submit" value="ログアウトする">
						</p>
					</form>
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
	</body>
	</html>