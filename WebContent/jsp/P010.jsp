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
<title>注文確認</title>
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
	<jsp:include page="/jsp/header-top.jsp" flush="true" />
	<div class="main-show">
		<section id="main">
			<section class="content">
				<div class="content-block">
					<div class="confirm-box">
						<h1>この内容でいいですか？</h1>
						<c:if test="${isOrderCheck==false}">
							<p>1日に同じ注文はできません</p>
						</c:if>
						<c:if test="${isNumCheck==false}">
							<p>人数は数字2桁で入力してください</p>
						</c:if>
						<hr>
						<div class="confirm-container confirm-container-cakes">
							<div class="confirm-img-container">
								<img class="popular-suggest-img"
									src="images/<c:out value="${cakeStoreInfo.cakeStorePrimaryImg}" />"
									alt="">
							</div>
							<div class="confirm-text-container">
								<h3>
									<c:out value="${cakeStoreInfo.cakeStoreName}" />
								</h3>
								<form action="CakeStoreMapViewServlet" method="POST">
									<div class="button-block">
										<input type="hidden" name="cakeStoreArea"
											value="<c:out value="${cakeStoreInfo.cakeStoreStation}" />">
										<input type="submit" value="変更する" class="change-button">
									</div>
								</form>
							</div>
						</div>
						<hr>
						<img class="confirm-arrow-img" src="images/arrow.gif">
						<hr>
						<div class="confirm-container confirm-container-cafe">
							<div class="confirm-img-container">
								<img class="popular-suggest-img"
									src="images/<c:out value="${cafeStoreInfo.cafeStorePrimaryImg}" />"
									alt="">
							</div>
							<div class="confirm-text-container">
								<h3>
									<c:out value="${cafeStoreInfo.cafeStoreName}" />
								</h3>
								<form action="TicketServlet" method="POST" id="ticketCreateForm">
									<div class="cafe-order-number">
										<input type="hidden" name="cakeStoreName"
											value="<c:out value="${cakeStoreInfo.cakeStoreName}" />">
										<input type="hidden" name="cafeStoreName"
											value="<c:out value="${cafeStoreInfo.cafeStoreName}" />">
										<input class="change-order-num-box" type="text"
											placeholder="人数" name="orderNum" required>
									</div>
								</form>
								<form action="CafeStoreMapViewServlet" method="POST">
									<div class="button-block">
										<input type="hidden" name="selectedCakeStoreArea"
											value="<c:out value="${cakeStoreInfo.cakeStoreStation}" />">
										<input type="submit" value="変更する" class="change-button">
									</div>
								</form>
							</div>
						</div>
						<hr>
					</div>
					<div class="button-block">
						<input class="submit-button" type="submit" value="確定する"
							form="ticketCreateForm">
					</div>
				</div>
			</section>
		</section>
		<jsp:include page="/jsp/footer.jsp" flush="true" />
	</div>
</body>
</html>