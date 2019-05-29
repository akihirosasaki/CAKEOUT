<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, viewport-fit=cover, user-scalable=yes">
        <meta name="description" content="">
        <meta name="keywords" content="">
        <title>アカウント登録完了</title>
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
                <div class="complete-box">
                    <div class="complete-container">
                        <h2>アカウント登録を完了しました</h2>
                        <div class="button-block">
                            <button  class="submit-button" onclick="location.href='./IndexServlet'">ケーキ屋さんを探す</button>
                        </div>
                    </div>
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