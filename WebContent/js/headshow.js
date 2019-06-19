$(function() {
	$(".level1").hover(function() {
		$(this).children(".le").show().prev("a");
	}, function() {
		$(this).children(".le").hide();
	});

	$(".per-infor").hover(function() {
		$(this).children(".per").show();
	}, function() {
		$(this).children(".per").hide();
	});

	window.console = window.console || {
		log : function() {
			return false;
		}
	};

});

function getJsonLength(json) {
	var jsonLength = 0;
	for ( var i in json) {
		jsonLength++;
	}
	return jsonLength;
}

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}