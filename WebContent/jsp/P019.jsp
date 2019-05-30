<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="">
<meta name="keywords" content="">
<title>CAKEOUT｜ケーキをテイクアウトしてカフェに持ち込むおしゃれ体験を提供します</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/info.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/confirm.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/account.css" type="text/css"
	media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/script.js"></script>
</head>
<body>
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
						<p>
							<a href="">チケットを確認する</a>
						</p>
						<p>
							<a href="">予約をキャンセルする</a>
						</p>
						<p>
							<a href="">内容を変更する</a>
						</p>
						<p>
							<a href="">ログアウトする</a>
						</p>
					</div>
				</div>
			</section>
		</section>
	</div>
	<div class="main-show">
		<header class="header">
			<div class="header-frame">
				<c:if test="${isLogin==false}">
					<div class="logo">
						<a href="index.html">CAKEOUT</a>
					</div>
				</c:if>
				<c:if test="${isLogin==true}">
					<div class="account-button-wrapper">
						<img class="account-button" src="images/account.gif">
					</div>
				</c:if>
			</div>
		</header>
		<section id="main">
			<section class="content">
				<div class="content-block">
					<div class="popular-suggest">
						<h1>
							「
							<c:out value="${cakeStoreNameInput}" />
							」の検索結果
						</h1>
						<hr>
						<c:choose>
							<c:when test="${ fn:length(cakeStoreList)>1}">
								<c:set var="count" value="0" scope="page" />
								<c:forEach var="cakeStore" items="${cakeStoreList}"
									varStatus="status">
									<form action="CakeStoreInfoServlet" method="POST"
										name="popularCakeStoreForm">
										<div class="popular-suggest-block">
											<input type="hidden" name="cakeStoreId"
												value="${cakeStore.cakeStoreId}"> <a
												href="javascript:popularCakeStoreForm[<c:out value="${count}" />].submit()"></a>
											<div class="popular-suggest-img-container">
												<img class="popular-suggest-img"
													src="images/<c:out value="${cakeStore.cakeStorePrimaryImg}" />"
													alt="">
											</div>
											<div class="popular-suggest-h3">
												<h3>
													<c:out value="${cakeStore.cakeStoreName}" />
												</h3>
												<p>
													<c:out value="${cakeStore.cakeStoreStation}" />
												</p>
											</div>
										</div>
										<hr>
									</form>
									<c:set var="count" value="${count+1}" scope="page" />
								</c:forEach>
							</c:when>
							<c:otherwise>
								<c:forEach var="cakeStore" items="${cakeStoreList}"
									varStatus="status">
									<form action="CakeStoreInfoServlet" method="POST"
										name="popularCakeStoreForm">
										<div class="popular-suggest-block">
											<input type="hidden" name="cakeStoreId"
												value="${cakeStore.cakeStoreId}"> <a
												href="javascript:popularCakeStoreForm.submit()"></a>
											<div class="popular-suggest-img-container">
												<img class="popular-suggest-img"
													src="images/<c:out value="${cakeStore.cakeStorePrimaryImg}" />"
													alt="">
											</div>
											<div class="popular-suggest-h3">
												<h3>
													<c:out value="${cakeStore.cakeStoreName}" />
												</h3>
												<p>
													<c:out value="${cakeStore.cakeStoreStation}" />
												</p>
											</div>
										</div>
										<hr>
									</form>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</section>
		</section>
		<footer class="footer">
			<div class="footer-logo-box">
				<div class="logo-footer">
					<a href="index.html">CAKEOUT</a>
				</div>
			</div>
		</footer>
	</div>
</body>
</html>