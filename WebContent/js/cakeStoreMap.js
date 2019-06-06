var map;
var marker = [];
var infoWindow = [];
var statusList =[];

$(document).ready(function(){
	$('#select-button').click(function(){
		statusList = [];
		$(".select-condition-detail-box").empty();
		if($("#open").prop("checked")==true){
			statusList.push("open");
			$(".select-condition-detail-box").append('<button class="condition-status">営業中</button>');
		}
		if($("#test").prop("checked")==true){
			statusList.push("test");
			$(".select-condition-detail-box").append('<button class="condition-status">テスト</button>');
		}
		console.log(statusList);
		ajaxCenter(cakeStoreArea).done(function(result){
			//地図作成
			makeMap(result[0], result[1], cakeStoreArea);
			$('#overlay, #modal-win').fadeOut();
		}).fail(function(result){
			console.log("error");
		});
	});
});

// ページのロードが完了したら地図を読み込む
window.onload = function() {
	//中心点を取得
	ajaxCenter(cakeStoreArea).done(function(result){
		//地図作成
		makeMap(result[0], result[1], cakeStoreArea);
	}).fail(function(result){
		console.log("error");
	});
	$('#nav-text').fadeIn(700);
	setTimeout(closeBox, 3000);
};

function ajaxCenter(place) {
	var request = {
			cakeStoreArea : place
	};

	return $.ajax({
		type : "POST",
		url : "/AkihiroApp/GetCenterPositionServlet", // 送信先のServlet URL
		data : request, // リクエストJSON
		async : true, // true:非同期(デフォルト), false:同期
		dataType : "json"
	});
}


function makeMap(lat, lng, cakeStoreArea) {

	var canvas = document.getElementById('map-canvas'); // 地図を表示する要素を取得

	var latlng = new google.maps.LatLng(lat, lng); // 中心の位置（緯度、経度）

	var mapOptions = { // マップのオプション
		zoom : 15,
		center : latlng,
	};

	map = new google.maps.Map(canvas, mapOptions); // 作成

	//検索情報をもとに、店舗データのリストを取得
	ajaxStore(cakeStoreArea, statusList).done(function(result){
		console.log(result);
		//ピン作成
		add_marker(result, map);

		return map;
	}).fail(function(result){
		console.log("error");
	});
}


function ajaxStore(place, statusList) {
	var request = {
		cakeStoreArea : place,
		statusList : statusList
	};

	return $.ajax({
		type : "GET",
		url : "/AkihiroApp/CakeStoreMapServlet", // 送信先のServlet URL
		data : request, // リクエストJSON
		async : true, // true:非同期(デフォルト), false:同期
		dataType : "json"
	})
}

function add_marker(mapData, map) {
	for (var i=0; i< mapData.length; i++) {
		var item = mapData[i];
		marker[i] = new google.maps.Marker({
			position:new google.maps.LatLng(item["cakeStoreLat"], item["cakeStoreLon"]),
			map: map
		});

		attachMessage(marker[i], item);
	}
}

function attachMessage(marker, item) {
	google.maps.event.addListener(marker, 'click', function(){
		$(".shop-modal p").empty();
		$(".shop-modal input").remove('value');
		$(".shop-modal").slideDown();
		$(".shop-modal p").append(item["cakeStoreName"]);
		$(".shop-modal input").attr({
			'value': item["cakeStoreId"]
		});
	});
}

function closeBox() {
	$("#nav-text").fadeOut('slow');
}


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
			'left' : ((w - cw) / 2) + 'px',
			'top' : ((h - ch) / 2) + 'px'
		});
	}
});

$(function(){
	$('select').change(function(){
		cakeStoreArea = $(this).val();
		console.log(cakeStoreArea);
		ajaxCenter(cakeStoreArea).done(function(result){
			//地図作成
			makeMap(result[0], result[1], cakeStoreArea);
		}).fail(function(result){
			console.log("error");
		});
	});
});

$(function() {
	  $('.shop-modal').on('touchstart', onTouchStart);
	  $('.shop-modal').on('touchmove', onTouchMove);
	  $('.shop-modal').on('touchend', onTouchEnd);
	  var direction, position;

	  function onTouchStart(event) {
	    position = getPosition(event);
	    direction = '';
	  }

	  function onTouchMove(event) {
	    if(position - getPosition(event) < -70){
	      direction = 'down';
	    }
	  }

	  function onTouchEnd(event) {
	    if (direction == 'down'){
	    	$(".shop-modal").slideUp();
	    }
	  }

	  function getPosition(event) {
	    return event.originalEvent.touches[0].pageY;
	  }
});