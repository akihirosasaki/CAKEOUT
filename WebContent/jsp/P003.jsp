<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="街中でテイクアウトしたケーキを食べられる場所が見つかる！気になるケーキをテイクアウトして、
おしゃれなカフェに持ち込もう！まずは、アカウント登録してCAKEOUTで気になるケーキ屋の最寄りのカフェを探しましょう。">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, アカウント 登録 入力">
<title>アカウント登録</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/form.css" type="text/css"
	media="screen">
<link rel="stylesheet" type="text/css"
	href="css/validationEngine.jquery.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine.js"></script>
<script type="text/javascript" src="js/jquery.validationEngine-ja.js"></script>
<script src="js/script.js"></script>
</head>
<body>
	<div class="main-show">
		<jsp:include page="/jsp/header-form.jsp" flush="true" />
		<section id="main">
			<section class="content">
				<div class="form-box">
					<form class="form" action="AccountRegistServlet" method="POST">
						<header id="search-header">
							<h2>アカウント登録</h2>
						</header>
						<div class="labeled-form-group form-inline">
							<div class="labeled-form-group-labels">
								<span class="labeled-form-group-main-label">ユーザーネーム</span>
							</div>
							<c:if test="${userLengthCheck==false}">
								<p>ユーザー名は16文字以内にしてください</p>
							</c:if>
							<div class="labeled-form-group-input">
								<input type="text" name="userName" placeholder="user"
									class="validate[required,maxSize[16]] text-input" required>
							</div>
						</div>
						<div class="labeled-form-group form-inline">
							<div class="labeled-form-group-labels">
								<span class="labeled-form-group-main-label">メールアドレス</span>
							</div>
							<c:if test="${mailDuplicateCheck==false}">
								<p>このメールアドレスはすでに使われています</p>
							</c:if>
							<c:if test="${mailFormatCheck==false}">
								<p>メールアドレスの表記が不正です</p>
							</c:if>
							<div class="labeled-form-group-input">
								<input type="text" name="mailAdd" placeholder="メールアドレス"
									class="validate[required,maxSize[16]] text-input" required>
							</div>
						</div>
						<div class="labeled-form-group form-inline">
							<div class="labeled-form-group-labels">
								<span class="labeled-form-group-main-label">パスワード</span>
							</div>
							<c:if test="${passLengthCheck==false}">
								<p>パスワードは16文字以内にしてください</p>
							</c:if>
							<div class="labeled-form-group-input">
								<input type="password" name="password" required
									pattern="^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[!\x22\#$%&@'()*+,-./_])[\w!\x22\#$%&@'()*+,-./]{8,}$"
									placeholder="パスワード"
									class="validate[required,maxSize[16]] text-input" required>
							</div>
						</div>
						<div class="button-block form-inline">
							<input class="submit-button" type="submit" value="アカウント登録">
						</div>
						<hr>
						<div class="form-footer">
							<p class="">
								すでにアカウントをお持ちの方は<a href="LoginViewServlet">ログイン</a>
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