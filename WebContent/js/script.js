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
