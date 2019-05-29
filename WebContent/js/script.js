$(function() {
    $(".account-show").css("display", "none");

    $(".account-button-wrapper").click(function(){
        $(".account-show").toggle();
        $(".main-show").css("display", "none")
    });
    $(".cancel").click(function(){
        $(".account-show").toggle();
        $(".main-show").css("display", "block")
    })
});


$(function() {
    $(".suggest-show").css("display", "none");

    $(".top-cakes-search").click(function(){
        $(".suggest-show").toggle();
//        $(".top-cakes-search").css("display", "none");
    })
})
