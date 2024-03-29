<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="注文のキャンセル画面を表示します。">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, 注文 キャンセル">
<title>注文キャンセル確認</title>
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
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/script.js"></script>
<script src="js/notBrowserBack.js"></script>
</head>
<body>
	<jsp:include page="/jsp/account.jsp" flush="true" />
	<div class="main-show">
		<jsp:include page="/jsp/header-top.jsp" flush="true" />
		<section id="main">
			<section class="content">
				<div class="content-block">
					<div class="confirm-box">
						<h1>予約をキャンセルしますか？</h1>
						<div class="popular-suggest">
							<hr>
							<div class="popular-suggest-block">
								<div class="popular-suggest-img-container">
									<img class="popular-suggest-img"
										src="images/<c:out value="${orderItem.cakeStorePrimaryImg}" />"
										alt="ケーキ屋">
								</div>
								<div class="popular-suggest-h3">
									<h3>
										<c:out value="${orderItem.cakeStoreName}" />
									</h3>
								</div>
							</div>
							<hr>
							<img class="confirm-arrow-img" src="images/arrow.gif">
							<hr>
							<div class="popular-suggest-block">
								<div class="popular-suggest-img-container">
									<img class="popular-suggest-img"
										src="images/<c:out value="${orderItem.cafeStorePrimaryImg}" />"
										alt="カフェ">
								</div>
								<div class="popular-suggest-h3">
									<h3>
										<c:out value="${orderItem.cafeStoreName}" />
									</h3>
								</div>
							</div>
							<hr>
						</div>
						<form action="OrderCancelServlet" method="POST">
							<div class="button-block">
								<input type="hidden" name="isNullCheck" value="true">
								<input class="submit-button" type="submit" value="キャンセルする">
							</div>
						</form>
					</div>
				</div>
			</section>
		</section>
		<jsp:include page="/jsp/footer.jsp" flush="true" />
	</div>
</body>
</html>