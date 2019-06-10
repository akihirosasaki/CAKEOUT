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
<title>注文リスト</title>
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
		<section id="main">
			<section class="content">
				<div class="content-block">
					<div class="confirm-box">
						<h1>オーダーリスト</h1>
						<hr>
						<c:set var="count" value="0" scope="page" />
						<c:forEach var="orderItem" items="${orderList}" varStatus="status">
							<form action="OrderSelectServlet" method="POST" name="OrderListForm">
								<input type="hidden" name="orderId" value="<c:out value="${orderItem.orderId}" />">
								<div class="order-list-box">
									<p class="order-list-date">日時：<c:out value="${orderItem.createdAt}" /></p>
									<p><c:out value="${orderItem.cakeStoreName}" /></p>
									<img src="images/arrow.gif">
									<p><c:out value="${orderItem.cafeStoreName}" /></p>
									<a href="javascript:OrderListForm[<c:out value="${count}" />].submit()"></a>
								</div>
							</form>
							<c:set var="count" value="${count+1}" scope="page" />
							<hr>
						</c:forEach>
					</div>
				</div>
			</section>
		</section>
		<jsp:include page="/jsp/footer.jsp" flush="true" />
	</div>
</body>
</html>