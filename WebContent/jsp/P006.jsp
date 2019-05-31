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
<title>ケーキ屋検索</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/search.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/account.css" type="text/css"
	media="screen">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAkVaV9oexHW-Mg302lv4S7AUIa_icr554"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/map.js"></script>
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
			<div class="header-frame-search">
				<div class="logo">
					<a href="index.html">CAKEOUT</a>
				</div>
				<c:if test="${isLogin==false}">
					<div class="login-button-wrapper">
						<a class="login-button" href="login.html">ログイン</a>
					</div>
				</c:if>
				<c:if test="${isLogin==true}">
					<div class="account-button-wrapper">
						<img class="account-button" src="../images/account.gif">
					</div>
				</c:if>
			</div>
		</header>
		<section id="main">
			<section class="content">
				<div class="form-group-input">
					<input id="pac-input" class="controls" type="text"
						name="store-name-input" placeholder="Search Box">
				</div>
				<article id="map-canvas"></article>
				<div class="select-condition-box">
					<button class="select-condition-button open">条件追加</button>
					<div class="select-condition-detail-box">
						<button class="condition-status">条件1</button>
						<button class="condition-status">条件2</button>
					</div>
					<div id="overlay"></div>
					<div id="modal-win">
						<form class="modal-form" action="CakeStoreMapServlet"
							method="POST">
							<div class="modal-header">
								<div class="modal-header-text">
									<p>条件を指定してください</p>
								</div>
								<div class="close">
									<span></span>
								</div>
							</div>
							<div class="modal-contents-container">
								<div class="modal-contents-status-container">
									<input type="checkbox" class="condition-status-check"
										id="status1"> <label class="select-condition-label"
										for="status1">条件１</label> <input type="checkbox"
										class="condition-status-check" id="status2"> <label
										class="select-condition-label" for="status2">条件２</label> <input
										type="checkbox" class="condition-status-check" id="status3">
									<label class="select-condition-label" for="status3">条件３</label>
								</div>
								<input class="submit-button" type="submit" value="絞り込む">
							</div>
						</form>
					</div>
				</div>
				<div id="nav-text" class="search-nav-text">
					<p>行きたいケーキ屋さんを選択してください</p>
				</div>
				<article class="shop-modal">
					<div class="shop-modal-img">
						<img src="images/shortcake.jpeg"> <img
							src="images/cheezecake.jpeg">
					</div>
					<p>ピエール・エルメ・パリ 青山</p>
					<a href="CakeStoreInfoServlet"><span class="shop-modal-a"></span></a>
					<form action="CafeStoreMapServlet" method="POST">
						<input class="btn-search" type="submit" value="決定">
					</form>
				</article>
			</section>
		</section>
	</div>
</body>
</html>