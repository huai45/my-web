(function() {
    var _loading = [];
    Vue.http.interceptors.push(function(request, next) {
        if(!request.hideLoading) {
            var tmp = new Vue();
            tmp["$Message"].config({ top: 350 });
            _loading.push(tmp["$Message"].loading('正在加载中...', 0));
        }
        request.url = request.url + (request.url.indexOf("?") == -1 ? "?" : "&") + "v=" + new Date().getTime();
        next(function(response) {
            response.data = typeof response.data == "string" ? response.data != "" ? jQuery.parseJSON(response.data) : '' : response.data;
            for(var i in _loading) {
                _loading[i]();
            }
            if(response.status == 500) {
                new Vue()["$Notice"].error({
                    title: '出了点小问题',
                    desc: "系统繁忙，请稍后再试"
                });
            } else if(response.status == 0) {
                new Vue()["$Notice"].error({
                    title: '出了点小问题',
                    desc: "您的网络连接失败"
                });
            }
        });
    });
})();
var leftMemu = new Vue({
    el: ".nav",
    ready: function() {
        //alert("leftMemu"+this.memu+","+window.location.pathname);
        for(var i in this.memu) {
            var row = this.memu[i];
            //alert("row.url = "+row.url);
            if(row.url == window.location.pathname) {
                // alert("row.url = window.location.pathname");
                this.activeIndex = i;
                this.first = i;
            }
        }

    },
    data: {
        first: "",
        activeIndex: 0,
        theme: 'light',
        memu: [{
            title: "溯源商品列表1",
            fid: 0,
            icon: "card",
            enabled:true,
            url: "/demo"

        }, {
            title: "检测报告管理",
            icon: "card",
            fid: 0,
            enabled:true,
            url: "/ts/report"

        }, {
            title: "溯源二维码管理",
            icon: "card",
            fid: 0,
            enabled:true,
            
            url: "/ts/qrcode"

        }, {
            title: "用户管理",
            icon: "card",
            fid: 0,
            enabled:true,
            
            url: "/ts/user/list"

        }]
    },
    methods: {
        goturl: function(key) {
        		window.location.href = this.memu[key].url + "?p=" + head.activeIndex;

        }
    }
});
var head = new Vue({
    el: "#head",
    ready: function() {
        this.initData();
        this.activeIndex = window.location.search.indexOf("&")!=-1?window.location.search.split("&")[1].split("=")[1]:window.location.search.split("=")[1];
        //alert("this.activeIndex="+this.activeIndex)
        if(this.activeIndex){

        }else{
            this.activeIndex = 0;
        }
        this.first = this.activeIndex;
        this.setLeftMemu(this.activeIndex);
    },
    data: {
        userPin:"",
        logoutUrl:"",
        first: "",
        activeIndex: 0,
        theme: 'primary',
        memu: [{
            title: "溯源商品管理123",

        }]
    },
    methods: {
        initData:function(){
            /*Vue.http.get("/comInfo").then(function(response) {
               this.userPin=response.data.pin;
               this.logoutUrl=response.data.logoutUrl;
                document.getElementById("head_userPin").innerHTML=response.data.pin;

            }, function(response) {

            });*/
        },
        logout: function(url) {
            window.location.href =url;
            /*Vue.http.get("/comInfo").then(function(response) {
                window.location.href =response.data.logoutUrl;
            }, function(response) {

            });*/

        },
        goturl: function(key) {
            // alert(" goturl key = "+key)
            this.setLeftMemu(key);
            for(var i = 0; i < leftMemu.memu.length; i++) {
                if(leftMemu.memu[i].enabled) {
                    window.location.href = leftMemu.memu[i].url + "?p=" + key;
                    leftMemu.activeIndex = i;
                    break;
                }
            }
        },
        setLeftMemu: function(key) {
            //alert(" setLeftMemu key = "+key)
            for(var i in leftMemu.memu) {
                var row = leftMemu.memu[i];
                if(row.fid == key) {
                    row.enabled = true;
                } else {
                    row.enabled = false;
                }
            }
        }
    }
});