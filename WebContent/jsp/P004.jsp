<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <title>アカウント登録確認</title>
        <link rel="stylesheet" href="css/normalize.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/form.css" type="text/css" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/script.js"></script>
    </head>
    <body>
        <jsp:include page="/jsp/header-form.jsp" flush="true" />
        <section id="main">
            <section class="content">
                <div class="account-regist-box">
                    <header id="search-header"><h2>この内容でいいですか？</h2></header>
                    <div class="account-regist-content">
                        <p>ユーザーネーム：<c:out value="${userName}"/></p>
                        <p>メールアドレス：<c:out value="${mailAdd}"/></p>
                        <p>パスワード：<c:out value="${password}"/></p>
                    </div>
                    <form action="AccountRegistCompleteServlet" method="POST">
                        <div class="button-block form-inline">
                            <input class="submit-button" type="submit" value="確定する">
                        </div>
                    </form>
                </div>
            </section>
        </section>
        <jsp:include page="/jsp/footer.jsp" flush="true" />
    </body>
</html>