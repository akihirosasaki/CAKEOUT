<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <title>ログアウト</title>
        <link rel="stylesheet" href="css/normalize.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/form.css" type="text/css" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/script.js"></script>
    </head>
    <body>
        <jsp:include page="/jsp/header-map.jsp" flush="true" />
        <section id="main">
            <section class="content">
                <div class="complete-box">
                    <div class="complete-container">
                        <h2>ログアウトしました</h2>
                        <div class="button-block">
                            <button  class="submit-button" onclick="location.href='./IndexServlet'">TOPへ戻る</button>
                        </div>
                    </div>
                </div>
            </section>
        </section>
        <jsp:include page="/jsp/footer.jsp" flush="true" />
    </body>
</html>