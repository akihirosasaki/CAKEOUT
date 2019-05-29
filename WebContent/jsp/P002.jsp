<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <title>ログイン</title>
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
                    <a href="index.html">CAKEOUT</a>
                </div>
            </div>
        </header>
        <section id="main">
            <section class="content">
                <div class="form-box">
                    <form class="form" action="LoginServlet" method="POST">
                            <header id="search-header"><h2>ログイン</h2></header>
                        <div class="labeled-form-group form-inline">
                                <div class="labeled-form-group-labels">
                                    <span class="labeled-form-group-main-label">メールアドレス</span>
                                </div>
                                <div class="labeled-form-group-input">
                                    <input type="text" name="mailAdd" placeholder="メールアドレス" required>
                                </div>
                        </div>
                        <div class="labeled-form-group form-inline">
                                <div class="labeled-form-group-labels">
                                    <span class="labeled-form-group-main-label">パスワード</span>
                                </div>
                                <div class="labeled-form-group-input">
                                    <input type="password" name="password" placeholder="パスワード" required>
                                </div>
                        </div>
                        <div class="button-block form-inline">
                                <input class="submit-button" type="submit" value="ログイン">
                        </div>
                        <hr>
                        <div class="form-footer">
                            <p>はじめてご利用する方は<a href="AccountRegistViewServlet">アカウント登録</a></p>
                        </div>
                    </form>
                </div>
            </section>
        </section>
        <footer class="footer">
            <div class="footer-logo-box">
                <div class="logo-footer">
                    <a href="index.html">CAKEOUT</a>
                </div>
            </div>
        </footer>
    </body>
</html>