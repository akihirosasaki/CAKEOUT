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
<meta name="description"
	content="街中でテイクアウトしたケーキを食べられる場所が見つかる！気になるケーキをテイクアウトして、
おしゃれなカフェに持ち込もう！まずは、あなたが気になっているケーキ屋のお店付近の持ち込み可能なカフェを探してみましょう。">
<meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, 人気 ケーキ 探す">
<title>CAKEOUT｜ケーキをテイクアウトしてカフェに持ち込むおしゃれ体験</title>
<link rel="stylesheet" href="css/normalize.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/top.css" type="text/css"
	media="screen">
<link rel="stylesheet" href="css/style.css" type="text/css"
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
		<c:if test="${!empty searchArea}">
		<jsp:include page="/jsp/header-top.jsp" flush="true" />
		</c:if>
		<section id="main">
			<section class="content">
				<c:if test="${empty searchArea}">
					<div class="first-view">
                    <img class="logo-img" src="images/CAKEOUT_logo_white.png">
                    <div class="border-top-left"></div>
                    <div class="border-top-right"></div>
                    <div class="scroll-text"><p>Scroll</p></div>
                    <div class="border-top-scroll"></div>
                    <div class="slide-mask">
                        <div class="mask-top"></div>
                        <div class="border top" style="transform: matrix(1, 0, 0, 1, 0, 0);"></div>
                        <div class="border right" style="transform: matrix(1, 0, 0, 1, 0, 0);"></div>
                        <div class="border bottom" style="transform: matrix(1, 0, 0, 1, 0, 0);"></div>
                        <div class="border left" style="transform: matrix(1, 0, 0, 1, 0, 0);"></div>
                    </div>
                    <div class="main-img-box">
                        <div class="main-img" style="background-image:url(images/top1.jpeg)"></div>
                        <div class="main-img" style="background-image:url(images/top2.jpeg)"></div>
                        <div class="main-img" style="background-image:url(images/top3.jpeg)"></div>
                        <div class="main-img" style="background-image:url(images/top4.jpeg)"></div>
                        <div class="main-img" style="background-image:url(images/top5.jpeg)"></div>
                        <div class="main-img" style="background-image:url(images/top6.jpeg)"></div>
                    </div>
                </div>
				</c:if>
				<div class="top-search-box">
					<c:if test="${empty searchArea}">
						<header id="search-header">
							<h1>ケーキ屋さんを探す</h1>
						</header>
					</c:if>
					<div class="labeled-form-group">
						<div class="labeled-form-group-labels">
							<c:if test="${empty searchArea}">
								<span class="labeled-form-group-main-label"
									id="labeled-form-group-main-label">エリアで探す</span>
							</c:if>
							<c:if test="${!empty searchArea}">
								<span class="labeled-form-group-main-label"
									id="labeled-form-group-main-label">エリアを変える</span>
							</c:if>
						</div>
						<form action="PopularCakeStoreServlet" method="POST"
							class="PopularCakeStoreForm">
							<div class="labeled-form-group-pull-down">
								<select name="searchArea" required>
									<option value='' hidden>選択してください</option>
									<option value="渋谷">渋谷</option>
									<option value="代官山">代官山</option>
									<option value="原宿">原宿</option>
									<option value="恵比寿">恵比寿</option>
								</select>
							</div>
							<c:if test="${empty searchArea}">
								<div class="button-block top-cakes-search">
									<input class="submit-button" type="submit" value="エリアで探す">
								</div>
							</c:if>
							<c:if test="${!empty searchArea}">
								<div class="button-block top-cakes-search">
									<input class="submit-button" type="submit" value="エリアを変える">
								</div>
							</c:if>
						</form>
					</div>
					<c:if test="${!empty isPopularCakeStore}">
						<div class="suggest-show">
							<c:choose>
								<c:when test="${isPopularCakeStore==true}">
									<div class="popular-suggest" id="popular-suggest">
										<p>
											<c:out value="${searchArea}" />
											エリアで人気のお店
										</p>
										<!-- <hr> -->
										<c:choose>
											<c:when test="${fn:length(popularCakeStores)==1}">
												<c:forEach var="popularCakeStore"
													items="${popularCakeStores}" varStatus="status">
													<hr>
													<form action="CakeStoreInfoServlet" method="POST"
														name="popularCakeStoreForm">
														<div class="popular-suggest-block">
															<input type="hidden" name="cakeStoreId"
																value="${popularCakeStore.cakeStoreId}"> <a
																href="javascript:popularCakeStoreForm.submit()"></a>
															<div class="popular-suggest-img-container">
																<img class="popular-suggest-img"
																	src="images/<c:out value="${popularCakeStore.cakeStorePrimaryImg}" />"
																	alt="人気ケーキ屋">
															</div>
															<div class="popular-suggest-h3">
																<h3>
																	<c:out value="${popularCakeStore.cakeStoreName}" />
																</h3>
															</div>
														</div>
														<!-- <hr> -->
													</form>
												</c:forEach>
											</c:when>
											<c:when test="${fn:length(popularCakeStores)>1 }">
												<c:set var="count" value="0" scope="page" />
												<c:forEach var="popularCakeStore"
													items="${popularCakeStores}" varStatus="status">
													<hr>
													<form action="CakeStoreInfoServlet" method="POST"
														name="popularCakeStoreForm">
														<div class="popular-suggest-block">
															<input type="hidden" name="cakeStoreId"
																value="${popularCakeStore.cakeStoreId}"> <a
																href="javascript:popularCakeStoreForm[<c:out value="${count}" />].submit()"></a>
															<div class="popular-suggest-img-container">
																<img class="popular-suggest-img"
																	src="images/<c:out value="${popularCakeStore.cakeStorePrimaryImg}" />"
																	alt="人気ケーキ屋">
															</div>
															<div class="popular-suggest-h3">
																<h3>
																	<c:out value="${popularCakeStore.cakeStoreName}" />
																</h3>
															</div>
														</div>
														<!-- <hr> -->
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
									<input class="submit-button" type="submit" value="MAPで探す">
								</div>
							</form>
						</div>
					</c:if>
					<c:if test="${empty searchArea}">
						<hr>
						<form action="CakeStoreListServlet" method="POST"
							class="CakeStoreListForm">
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
					</c:if>
				</div>
			</section>
		</section>
		<jsp:include page="/jsp/footer.jsp" flush="true" />
	</div>
</body>
</html>