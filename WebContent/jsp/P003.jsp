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
        <title>アカウント登録</title>
        <link rel="stylesheet" href="css/normalize.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/style.css" type="text/css" media="screen">
        <link rel="stylesheet" href="css/form.css" type="text/css" media="screen">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="js/script.js"></script>
    </head>
    <body>
        <header class="header">
            <div class="header-frame-form">
                <div class="logo-form">
                    <a href="IndexServlet">CAKEOUT</a>
                </div>
            </div>
        </header>
        <section id="main">
            <section class="content">
                <div class="form-box">
                    <form class="form" action="AccountRegistServlet" method="POST">
                            <header id="search-header"><h2>アカウント登録</h2></header>
                            <div class="labeled-form-group form-inline">
                                    <div class="labeled-form-group-labels">
                                        <span class="labeled-form-group-main-label">ユーザーネーム</span>
                                    </div>
                                    <div class="labeled-form-group-input">
                                        <input type="text" name="userName" placeholder="user">
                                    </div>
                            </div>
                            <div class="labeled-form-group form-inline">
                                <div class="labeled-form-group-labels">
                                    <span class="labeled-form-group-main-label">メールアドレス</span>
                                </div>
                                <c:if test="${mailCheck==false}"><p>このメールアドレスはすでに使われています</p></c:if>
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
                        <div class="button-block form-inline">
                            <input class="submit-button" type="submit" value="アカウント登録">
                        </div>
                        <hr>
                        <div class="form-footer">
                            <p class="">すでにアカウントをお持ちの方は<a href="LoginViewServlet">ログイン</a></p>
                        </div>
                    </form>
                </div>
            </section>
        </section>
        <footer class="footer">
            <div class="footer-logo-box">
                <div class="logo-footer">
                    <a href="IndexServlet">CAKEOUT</a>
                </div>
            </div>
        </footer>
    </body>
</html>