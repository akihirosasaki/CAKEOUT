<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="注文完了画面を表示します。">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, 注文 完了">
<title>注文完了</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/ticket.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/account.css" type="text/css"
	media="screen">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/script.js"></script>
</head>
<body>
	<jsp:include page="/jsp/account.jsp" flush="true" />
	<div class="main-show">
		<jsp:include page="/jsp/header-top.jsp" flush="true" />
		<div class="main-show">
			<section id="main">
				<section class="content">
					<div class="content-block">
						<div class="ticket-title">
							<h1>注文を完了しました</h1>
							<p>持ち込みチケットを発行しました。カフェ入店時にこの画面を店員さんに見せて、入店チェックを完了してください。</p>
						</div>
						<div class="ticket-box">
							<div class="ticket-order-box">
								<p>
									名前：
									<c:out value="${userName}" />
								</p>
								<p>
									人数：
									<c:out value="${orderNum}" />
									人
								</p>
								<p>
									日時：
									<c:out value="${date}" />
								</p>
							</div>
							<div class="ticket-shop-name">
								<div class="ticket-cakes-name">
									<p>
										<c:out value="${cakeStoreName}" />
									</p>
								</div>
								<img class="ticket-arrow-img" src="images/arrow.gif">
								<div class="ticket-cafe-name">
									<p>
										<c:out value="${cafeStoreName}" />
									</p>
								</div>
							</div>
							<form action="TicketCheckServlet" method="POST">
								<div class="button-block">
									<input type="hidden" name="cakeStoreId"
										value="<c:out value="${selectedCakeStoreId}" />"> <input
										type="hidden" name="cafeStoreId"
										value="<c:out value="${selectedCafeStoreId}" />"> <input
										class="submit-button" type="submit" value="入店しました">
								</div>
							</form>
						</div>
					</div>
					<div class="button-block">
						<button class="submit-button-white"
							onclick="location.href='IndexServlet'">TOPへ戻る</button>
					</div>
				</section>
			</section>
			<jsp:include page="/jsp/footer.jsp" flush="true" />
		</div>
	</div>
</body>
</html>