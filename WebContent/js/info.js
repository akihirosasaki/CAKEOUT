window.addEventListener('DOMContentLoaded', function() {
    var swiper = new Swiper('.sample01 .swiper-container', {
      pagination: '.swiper-pagination',
      paginationClickable: true,
      nextButton: '.swiper-button-next',
      prevButton: '.swiper-button-prev',
      loop: true
    });
  }, false);