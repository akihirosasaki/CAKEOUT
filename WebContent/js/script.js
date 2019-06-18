window.onunload = function(){};
history.forward();

$(document).ready(function(){
	var hSize = $(window).height();
	  $('.main-img').height(hSize); // アドレスバーを除いたサイズを付与
	});
	$(window).resize(function(){ // ページをリサイズした時の処理
	var hSize = $(window).height();
	  $('.main-img').height(hSize); // アドレスバーを除いたサイズを付与
	});

$(function() {
	$(".account-show").css("display", "none");

	$(".account-button-wrapper").on("click", function() {
		$(".account-show").toggle();
		$(".main-show").css("display", "none")
	});
	$(".cancel").on("click", function() {
		$(".account-show").toggle();
		$(".main-show").css("display", "block")
	});
});

$(function() {
	$(".top-cakes-search input").on("click", function() {
		var p = document.getElementById("labeled-form-group-main-label");

	});
});

$("form").submit(function() {
	var self = this;
	$(":submit", self).prop("disabled", true);
	setTimeout(function() {
		$(":submit", self).prop("disabled", false);
	}, 10000);
});
