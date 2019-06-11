<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
        <meta name="description" content="カフェの詳細ページを表示します。">
        <meta name="keywords" content="ケーキ テイクアウト, ケーキ カフェ 持ち込み, カフェ 詳細">
        <title>カフェ詳細</title>
        <link rel="stylesheet" href="css/normalize.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/info.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/account.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/swiper.min.css" type="text/css" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/script.js"></script>
        <script src="js/info.js"></script>
        <script src="js/swiper.min.js"></script>
    </head>
    <body>
        <div class="main-show">
            <jsp:include page="/jsp/header-map.jsp" flush="true" />
            <section id="main">
                <section class="content">
                    <div class="c-container">
                        <div class="swipe-img">
                            <div class="swiper-container">
                                <div class="swiper-wrapper">
                                    <c:forEach var="cafeStoreImg" items="${cafeStoreImgList}" varStatus="status1">
										<div class="swiper-slide info-img">
											<img src="images/<c:out value="${cafeStoreImg}"/>" alt="カフェ">
										</div>
									</c:forEach>
                                </div>
                                <div class="swiper-pagination"></div>
                                <div class="swiper-button-prev"></div>
                                <div class="swiper-button-next"></div>
                            </div>
                        </div>
                    </div>
                    <div class="cakes-shop-name">
					<h1>
						<c:out value="${cafeStoreInfo.cafeStoreName}" />
					</h1>
					</div>
                    <div class="tab_wrap">
                            <input id="tab1" type="radio" name="tab_btn" checked>
                            <input id="tab2" type="radio" name="tab_btn">

                            <div class="tab_area">
                                <label class="tab1_label" for="tab1">概要</label>
                                <label class="tab2_label" for="tab2">メニュー</label>
                            </div>
                            <div class="panel_area">
                                <div id="panel1" class="tab_panel">
                                    <div class="tab-content" id="cakes-info-content">
                                        <div class="info-block">
                                            <img class="info-icon" src="images/location.gif">
                                            <div>
                                                <c:out value="${cafeStoreInfo.cafeStoreAddress}" />
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="info-block">
                                            <img class="info-icon" src="images/time.gif">
                                            <div>
                                                <p>営業開始時間<c:out value="${cafeStoreInfo.cafeStoreOpenTime}" /></p>
                                                <p>営業終了時間<c:out value="${cafeStoreInfo.cafeStoreCloseTime}" /></p>
                                            </div>
                                        </div>
                                        <hr>
                                        <div class="info-block">
                                            <img class="info-icon" src="images/tel.gif">
                                            <div>
                                                <p><c:out value="${cafeStoreInfo.cafeStorePhoneNum}" /></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div id="panel2" class="tab_panel">
                                    <div class="tab-content"  id="cakes-menu-content">
                                    	<c:forEach var="cafeStoreMenu" items="${cafeStoreMenuList}" varStatus="status2">
	                                        <div class="menu-block">
	                                            <img class="menu-img" src="images/<c:out value="${cafeStoreMenu.cafeMenuImgUrl}" />" alt="カフェメニュー">
	                                            <div class="cafe-menu-text-container">
	                                                <div class="menu-item-name"><h3><c:out value="${cafeStoreMenu.cafeMenuName}" /></h3></div>
	                                                <div class="menu-item-price"><p>¥<c:out value="${cafeStoreMenu.cafeMenuPrice}" /></p></div>
	                                            </div>
	                                        </div>
	                                        <hr>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <form action="CakeCafeConfirmServlet" method="POST">
						<div class="btn-fixed">
							<input type="hidden" name="selectedCafeStoreId" value="<c:out value="${cafeStoreInfo.cafeStoreId}"/>" >
							<input type="hidden" name="selectedCafeStoreName" value="<c:out value="${cafeStoreInfo.cafeStoreName}"/>" >
							<input type="submit" class="submit-button" value="決定する">
						</div>
					</form>
                </section>
            </section>
        <jsp:include page="/jsp/footer.jsp" flush="true" />
    </div>
    </body>
</html>