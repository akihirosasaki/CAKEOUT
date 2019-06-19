<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="注文確認ページを表示します">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, 注文 確認">
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
										alt="ケーキ屋">
								</div>
								<div class="confirm-text-container">
									<h3>
										<img alt="ケーキ屋" src="images/cake-icon.gif">
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
							<img class="confirm-arrow-img" src="images/arrow.gif">
							<div class="confirm-container confirm-container-cafe">
								<div class="confirm-img-container">
									<img class="popular-suggest-img"
										src="images/<c:out value="${cafeStoreInfo.cafeStorePrimaryImg}" />"
										alt="カフェ">
								</div>
								<div class="confirm-text-container">
									<h3>
										<img alt="カフェ" src="images/cafe-icon.gif">
										<c:out value="${cafeStoreInfo.cafeStoreName}" />
									</h3>
									<form action="TicketServlet" method="POST"
										id="ticketCreateForm">
										<div class="cafe-order-number">
											<input type="hidden" name="cakeStoreName"
												value="<c:out value="${cakeStoreInfo.cakeStoreName}" />">
											<input type="hidden" name="cafeStoreName"
												value="<c:out value="${cafeStoreInfo.cafeStoreName}" />">
											<input type="hidden" name="pageToken"
												value="<c:out value="${token}"></c:out>"> <select
												name="orderNum" class="change-order-num-box" required>
												<option value='' hidden>人数</option>
												<option value="1">1人</option>
												<option value="2">2人</option>
												<option value="3">3人</option>
												<option value="4">4人</option>
												<option value="5">5人</option>
												<option value="6">6人</option>
												<option value="7">7人</option>
												<option value="8">8人</option>
												<option value="9">9人</option>
											</select>
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
							<c:if test="${isLogin==true}">
								<input class="submit-button" type="submit" value="注文を確定する"
									form="ticketCreateForm">
							</c:if>
							<c:if test="${isLogin==false}">
								<input class="submit-button" type="submit" value="ログインして注文する"
									form="ticketCreateForm">
							</c:if>
						</div>
					</div>
				</section>
			</section>
			<jsp:include page="/jsp/footer.jsp" flush="true" />
		</div>
	</div>
</body>
</html>