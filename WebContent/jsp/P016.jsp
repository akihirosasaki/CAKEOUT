<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="注文の内容変更を完了します。">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, 注文 変更 完了">
<title>注文内容変更完了</title>
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
		<jsp:include page="/jsp/header-map.jsp" flush="true" />
		<section id="main">
			<section class="content">
				<div class="complete-box">
					<div class="complete-container">
						<h2>内容を変更しました</h2>
						<div class="button-block">
							<button class="submit-button"
								onclick="location.href='./IndexServlet'">TOPに戻る</button>
						</div>
					</div>
				</div>
			</section>
		</section>
		<jsp:include page="/jsp/footer.jsp" flush="true" />
	</div>
</body>
</html>