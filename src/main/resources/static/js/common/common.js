Date.prototype.convert = function(format) {
    var args = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
        "S": this.getMilliseconds()
    };
    if(/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for(var i in args) {
        var n = args[i];
        if(new RegExp("(" + i + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? n : ("00" + n).substr(("" + n).length));
    }
    return format;
};

Date.prototype.convertToDate = function(dateTime) {
    var dateTmp = this;
    dateTmp = new Date(dateTmp.setYear(typeof dateTime == "string" ? dateTime.split("-")[0] : 0));
    dateTmp = new Date(dateTmp.setMonth(typeof dateTime == "string" ? dateTime.split("-")[1] : 0));
    dateTmp = new Date(dateTmp.setDate(typeof dateTime == "string" ? dateTime.split("-")[2] : 0));
    dateTmp = new Date(dateTmp.setHours(typeof dateTime == "string" ? dateTime.split(":")[0] : 0));
    dateTmp = new Date(dateTmp.setMinutes(typeof dateTime == "string" ? dateTime.split(":")[1] : 0));
    dateTmp = new Date(dateTmp.setSeconds(typeof dateTime == "string" ? dateTime.split(":")[2] : 0));
    return dateTmp;
};
function getUrlParam(url) {
    url = url || window.location.href;
    var objRequest = new Object();
    if(url.indexOf("?") != -1) {
        url = url.split("?")[1];
        var strArr = url.split("&");
        for(var i = 0; i < strArr.length; i++) {
            objRequest[strArr[i].split("=")[0]] = decodeURI((strArr[i].split("=")[1]));
        }
    }
    return objRequest;
}
Vue.mixin({
	methods: {
		_gallery: function(thumb, index, first) {
			var ary = [];
			var pswpElement = document.querySelectorAll('.pswp')[0];
			var loaded = false;
			thumb.each(function(i) {
				ary.push({
					//last: i == thumb.length - 1 ? true : false,
					el: first || this,
					msrc: $(this).attr("src"),
					src: $(this).data("src") || $(this).attr("src"),
					w: $(this).data("size") ? $(this).data("size").split("*")[0] : ($(this)[0].naturalWidth || 0),
					h: $(this).data("size") ? $(this).data("size").split("*")[1] : ($(this)[0].naturalHeight || 0)
				});
			});
			var options = {
				getThumbBoundsFn: function(index) {
					var thumbnail = ary[index].el;
					var pageYScroll = window.pageYOffset || document.documentElement.scrollTop;
					var rect = thumbnail.getBoundingClientRect();
					return {
						x: rect.left,
						y: rect.top + pageYScroll,
						w: rect.width
					};
				},
				mainClass: 'pswp--minimal--dark',
				barsSize: {
					top: 0,
					bottom: 0
				},
				history: false,
				shareEl: false,
				zoomEl: true,
				index: index,
				fullscreenEl: false,
				bgOpacity: 0.85,
				tapToToggleControls: false
			};
			var gallery = new PhotoSwipe(pswpElement, PhotoSwipeUI_Default, ary, options);
			gallery.init();

		}
	}

});
