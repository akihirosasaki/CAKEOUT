window.addEventListener('DOMContentLoaded', function() {
    var swiper = new Swiper('.swipe-img .swiper-container', {
      pagination: '.swiper-pagination',
      paginationClickable: true,
      nextButton: '.swiper-button-next',
      prevButton: '.swiper-button-prev',
      loop: true
    });
  }, false);