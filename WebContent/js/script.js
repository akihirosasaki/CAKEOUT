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

$(function(){
	$(".top-cakes-search input").on("click", function(){
		var p = document.getElementById("labeled-form-group-main-label");

	});
});
