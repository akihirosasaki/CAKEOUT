function makeMap(lat, lng) {    
    var canvas = document.getElementById('map-canvas'); // 地図を表示する要素を取得

    var latlng = new google.maps.LatLng(lat, lng);  // 中心の位置（緯度、経度）

    var mapOptions = {  // マップのオプション
        zoom: 17,
        center: latlng,
    };
    var map = new google.maps.Map(canvas, mapOptions); //作成
	
	google.maps.event.addListener(map, 'click', function() {
		$(".shop-modal").slideDown();
	});

	return map;
}

function closeBox() {
    $("#nav-text").fadeOut('slow');
}

//ページのロードが完了したら地図を読み込む
window.onload = function(){
    makeMap(35.446806, 139.636163);
    $('#nav-text').fadeIn(700);
    setTimeout(closeBox, 3000);
};

$(function() {
    $('.open').on('click', function() {
      $('#overlay, #modal-win').fadeIn();
    });

    $('.close').on('click', function() {
      $('#overlay, #modal-win').fadeOut();
    });
    
    locateCenter();
    $(window).resize(locateCenter);
  
    function locateCenter() {
      let w = $(window).width();
      let h = $(window).height();
      
      let cw = $('#modal-win').outerWidth();
      let ch = $('#modal-win').outerHeight();
     
      $('#modal-win').css({
        'left': ((w - cw) / 2) + 'px',
        'top': ((h - ch) / 2) + 'px'
      });
    }
  });

  $(function() {
    $('#map-canvas').on('click', function(){
      $(".shop-modal").slideUp();
    })
  });