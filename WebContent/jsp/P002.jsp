<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description"
	content="街中でテイクアウトしたケーキを食べられる場所が見つかる！気になるケーキをテイクアウトして、
おしゃれなカフェに持ち込もう！まずは、CAKEOUTにログインしましょう。">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, ログイン">
<title>ログイン</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/form.css" type="text/css"
	media="screen">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+JP"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/script.js"></script>
</head>
<body>
	<div class="main-show">
		<jsp:include page="/jsp/header-form.jsp" flush="true" />
		<section id="main">
			<section class="content">
				<div class="form-box">
					<form class="form" action="LoginServlet" method="POST">
						<header id="search-header">
							<h2>ログイン</h2>
						</header>
						<div class="labeled-form-group form-inline">
							<div class="labeled-form-group-labels">
								<span class="labeled-form-group-main-label">メールアドレス</span>
							</div>
							<div class="labeled-form-group-input">
								<input type="text" name="mailAdd" placeholder="メールアドレス">
							</div>
						</div>
						<div class="labeled-form-group form-inline">
							<div class="labeled-form-group-labels">
								<span class="labeled-form-group-main-label">パスワード</span>
							</div>
							<div class="labeled-form-group-input">
								<input type="password" name="password" placeholder="パスワード">
							</div>
						</div>
						<c:if test="${isUserNull==true}">
							<p>メールアドレスかパスワードが間違っています</p>
						</c:if>
						<div class="button-block form-inline">
							<input type="hidden" name="pageToken"
								value="<c:out value="${token}"></c:out>"> <input
								class="submit-button" type="submit" value="ログイン">
						</div>
						<hr>
						<div class="form-footer">
							<p>
								はじめてご利用する方は<a href="AccountRegistViewServlet">アカウント登録</a>
							</p>
						</div>
					</form>
				</div>
			</section>
		</section>
		<jsp:include page="/jsp/footer.jsp" flush="true" />
	</div>
</body>
</html>