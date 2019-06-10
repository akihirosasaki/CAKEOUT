<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
<meta name="description" content="">
<meta name="keywords" content="">
<title>CAKEOUT｜ケーキをテイクアウトしてカフェに持ち込むおしゃれ体験を提供します</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
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
			<div class="eyecatch">
				<img src="images/eyeCatch.jpeg">
			</div>
			<div class="top-search-box">
				<header id="search-header">
					<h1>ケーキ屋さんを探す</h1>
				</header>
				<div class="labeled-form-group">
					<div class="labeled-form-group-labels">
						<span class="labeled-form-group-main-label">エリアで探す</span>
					</div>
					<form action="PopularCakeStoreServlet" method="POST">
						<div class="labeled-form-group-pull-down">
							<select name="searchArea" required>
								<option value='' hidden>選択してください</option>
								<option value="渋谷">渋谷</option>
								<option value="代官山">代官山</option>
								<option value="恵比寿">原宿</option>
							</select>
						</div>
						<div class="button-block top-cakes-search">
							<input class="submit-button" type="submit" value="探す">
						</div>
					</form>
				</div>
				<c:if test="${!empty isPopularCakeStore}">
					<div class="suggest-show">
						<c:choose>
							<c:when test="${isPopularCakeStore==true}">
								<div class="popular-suggest">
									<p>
										<c:out value="${searchErea}" />
										エリアで人気のお店
									</p>
									<hr>
									<c:choose>
										<c:when test="${fn:length(popularCakeStores)==1}">
											<c:forEach var="popularCakeStore"
												items="${popularCakeStores}" varStatus="status">
												<form action="CakeStoreInfoServlet" method="POST"
													name="popularCakeStoreForm">
													<div class="popular-suggest-block">
														<input type="hidden" name="cakeStoreId"
															value="${popularCakeStore.cakeStoreId}"> <a
															href="javascript:popularCakeStoreForm.submit()"></a>
														<div class="popular-suggest-img-container">
															<img class="popular-suggest-img"
																src="images/<c:out value="${popularCakeStore.cakeStorePrimaryImg}" />"
																alt="">
														</div>
														<div class="popular-suggest-h3">
															<h3>
																<c:out value="${popularCakeStore.cakeStoreName}" />
															</h3>
														</div>
													</div>
													<hr>
												</form>
											</c:forEach>
										</c:when>
										<c:when test="${fn:length(popularCakeStores)>1 }">
											<c:set var="count" value="0" scope="page" />
											<c:forEach var="popularCakeStore"
												items="${popularCakeStores}" varStatus="status">
												<form action="CakeStoreInfoServlet" method="POST"
													name="popularCakeStoreForm">
													<div class="popular-suggest-block">
														<input type="hidden" name="cakeStoreId"
															value="${popularCakeStore.cakeStoreId}"> <a
															href="javascript:popularCakeStoreForm[<c:out value="${count}" />].submit()"></a>
														<div class="popular-suggest-img-container">
															<img class="popular-suggest-img"
																src="images/<c:out value="${popularCakeStore.cakeStorePrimaryImg}" />"
																alt="">
														</div>
														<div class="popular-suggest-h3">
															<h3>
																<c:out value="${popularCakeStore.cakeStoreName}" />
															</h3>
														</div>
													</div>
													<hr>
												</form>
												<c:set var="count" value="${count+1}" scope="page" />
											</c:forEach>
										</c:when>
									</c:choose>
								</div>
							</c:when>
							<c:when test="${isPopularCakeStore==false}">
								<p>現在このエリアでの人気のお店はありません</p>
							</c:when>
						</c:choose>
						<form action="CakeStoreMapViewServlet" method="POST">
							<div class="button-block">
								<input type="hidden" name="cakeStoreArea" value="${searchArea}">
								<input class="submit-button" type="submit" value="その他の店を探す">
							</div>
						</form>
						<form action="CakeStoreListServlet" method="POST">
							<div class="labeled-form-group">
								<div class="labeled-form-group-labels">
									<span class="labeled-form-group-main-label">店名で探す</span>
								</div>
								<c:if test="${isInputCheck==false}">
									<p>店名が入力されていません</p>
								</c:if>
								<div class="labeled-form-group-input">
									<input type="text" name="cakeStoreName"
										placeholder="ピエール・エルメ・パリ　青山">
								</div>
							</div>
							<div class="button-block">
								<input class="submit-button" type="submit" value="店名で探す">
							</div>
						</form>
					</div>
				</c:if>
			</div>
		</section>
	</section>
	<jsp:include page="/jsp/footer.jsp" flush="true" />
</body>
</html>