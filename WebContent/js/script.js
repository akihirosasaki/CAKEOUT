history.pushState(null, null, null);
window.addEventListener("popstate", function() {
	history.pushState(null, null, null);
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
