<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cakeStoreArea;
	cakeStoreArea = (String) session.getAttribute("cakeStoreArea");
%>
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
<script type="text/javascript" charset="utf-8">
var cakeStoreArea = "<%=cakeStoreArea%>";
</script>
<script type="text/javascript" src="js/map.js" charset="utf-8"></script>

</head>
<body>
	<div class="main-show">
		<header class="header">
			<div class="header-frame-search">
				<div class="logo">
					<a href="index.html">CAKEOUT</a>
				</div>
			</div>
		</header>
		<section id="main">
			<section class="content">
				<div class="form-group-input">
					<div class="form-group-pull-down">
						<select name="area-pull-down">
							<option value='' hidden>選択してください</option>
							<option value="渋谷">渋谷</option>
							<option value="代官山">代官山</option>
							<option value="原宿">原宿</option>
						</select>
					</div>
				</div>
				<article id="map-canvas"></article>
				<div class="select-condition-box">
					<button class="select-condition-button open">条件追加</button>
					<div class="select-condition-detail-box">
						<!-- <button class="condition-status">営業中</button> -->
					</div>
					<div id="overlay"></div>
					<div id="modal-win">
						<form class="modal-form" action="#"
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
										id="open" name="open" value="open"> <label class="select-condition-label"
										for="open">営業中</label>
									<input type="checkbox" class="condition-status-check"
									id="test" name="test" value="test"> <label class="select-condition-label"
									for="test">テスト</label>
								</div>
								<input class="submit-button" type="button" value="絞り込む" id="select-button">
							</div>
						</form>
					</div>
				</div>
				<div id="nav-text" class="search-nav-text">
					<p>行きたいケーキ屋さんを選択してください</p>
				</div>
				<article class="shop-modal">
					<!-- <div class="shop-modal-img">
						<img src="images/shortcake.jpeg"> <img
							src="images/cheezecake.jpeg">
					</div> -->
					<form action="CakeStoreInfoServlet" method="POST"
						name="CafeStoreInfoForm">
						<p></p>
						<input type="hidden" name="cakeStoreId"> <a
							href="javascript:CafeStoreInfoForm.submit()"><span
							class="shop-modal-a"></span></a>
					</form>

					<!-- <form action="CafeStoreMapServlet" method="POST">
						<input class="btn-search" type="submit" value="決定">
					</form> -->
				</article>
			</section>
		</section>
	</div>
</body>
</html>