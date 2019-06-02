<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="">
<meta name="keywords" content="">
<title>ケーキ屋詳細</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/info.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/account.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/swiper.min.css" type="text/css"
	media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/script.js"></script>
<script src="js/info.js"></script>
<script src="js/swiper.min.js"></script>
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
				<div class="logo">
					<a href="index.html">CAKEOUT</a>
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
		<section id="main">
			<section class="content">
				<div class="c-container">
					<div class="sample sample01">
						<div class="swiper-container">
							<div class="swiper-wrapper">
								<c:forEach var="cakeStoreImg" items="${cakeStoreImgList}" varStatus="status1">
									<div class="swiper-slide info-img">
										<img src="images/<c:out value="${cakeStoreImg}"/>">
									</div>
								</c:forEach>
							</div>
							<div class="swiper-pagination"></div>
							<div class="swiper-button-prev"></div>
							<div class="swiper-button-next"></div>
						</div>
					</div>
				</div>
				<div class="cakes-shop-name">
					<h1>
						<c:out value="${cakeStoreInfo.cakeStoreName}" />
					</h1>
				</div>
				<div class="tab_wrap">
					<input id="tab1" type="radio" name="tab_btn" checked> <input
						id="tab2" type="radio" name="tab_btn">

					<div class="tab_area">
						<label class="tab1_label" for="tab1">概要</label> <label
							class="tab2_label" for="tab2">メニュー</label>
					</div>
					<div class="panel_area">
						<div id="panel1" class="tab_panel">
							<div class="tab-content" id="cakes-info-content">
								<div class="info-block">
									<img class="info-icon" src="images/location.gif">
									<div>
										<p>
											<c:out value="${cakeStoreInfo.cakeStoreAddress}" />
										</p>
									</div>
								</div>
								<hr>
								<div class="info-block">
									<img class="info-icon" src="images/time.gif">
									<div>
										<p>
											営業開始時間
											<c:out value="${cakeStoreInfo.cakeStoreOpenTime}" />
										</p>
										<p>
											営業終了時間
											<c:out value="${cakeStoreInfo.cakeStoreCloseTime}" />
										</p>
									</div>
								</div>
								<hr>
								<div class="info-block">
									<img class="info-icon" src="images/tel.gif">
									<div>
										<p>
											<c:out value="${cakeStoreInfo.cakeStorePhoneNum}" />
										</p>
									</div>
								</div>
							</div>
						</div>
						<div id="panel2" class="tab_panel">
							<div class="tab-content" id="cakes-menu-content">
								<c:forEach var="cakeStoreMenu" items="${cakeStoreMenuList}" varStatus="status2">
									<div class="menu-block">
										<img class="menu-img"
											src="images/<c:out value="${cakeStoreMenu.cakeMenuImgUrl}" />" alt="">
										<div class="menu-text-container">
											<div class="menu-item-name">
												<h3><c:out value="${cakeStoreMenu.cakeMenuName}" /></h3>
											</div>
											<div class="menu-item-stock">
												<p>在庫</p>
												<c:if test="${cakeStoreMenu.cakeStock > 10}">
													<img class="stock-icon" src="images/stock-many.gif">
												</c:if>
												<c:if test="${cakeStoreMenu.cakeStock <= 10 and cakeStoreMenu.cakeStock > 0}">
													<img class="stock-icon" src="images/stock-little.gif">
												</c:if>
												<c:if test="${cakeStoreMenu.cakeStock == 0}">
													<img class="stock-icon" src="images/stock-zero.gif">
												</c:if>
											</div>
										</div>
									</div>
								<hr>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="btn-fixed">
					<button class="submit-button" onclick="location.href='CafeStoreMapServlet'">決定する</button>
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