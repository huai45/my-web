// 1. 定义（路由）组件。
var Foo = {
    template: '<div>foo ,id={{ $route.params.id}} , name={{ $route.params.name}}.</div>',
    beforeRouteEnter : function  (to, from, next) {
        //alert('Foo,to='+to+',from='+from+',next='+next)
        next(function(vm){
            vm.loadData();
        })

    },
    beforeRouteLeave : function  (to, from, next) {
        //alert('Foo,to='+to+',from='+from+',next='+next)
        this.deleteData()
        next()
    },
    created : function  () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData()
    },
    watch: {
        // 如果路由有变化，会再次执行该方法 路由变化是指url变化了
        '$route':'fetchData'
    },
    methods: {
        loadData: function () {
            alert('loadData , route = '+this.$route.path)
            this.$http.get('/main/hello').then(function(response){

            }, function(response){

            })

        },
        fetchData: function () {
            //alert('fetchData')
            console.log( ' fetchData ' );
        },
        deleteData: function () {
            alert('deleteData , route = '+this.$route)
        }
    }
}
var Bar = {
    template: '<div>bar ,id={{ $route.query.id}} , name={{ $route.query.name}}.</div>',
    beforeRouteEnter : function  (to, from, next) {
        //alert('Foo,to='+to+',from='+from+',next='+next)
        next(function(vm){
            vm.loadData();
        })

    },
    beforeRouteLeave : function  (to, from, next) {
        //alert('Foo,to='+to+',from='+from+',next='+next)
        this.deleteData()
        next()
    },
    created : function  () {
        // 组件创建完后获取数据，
        // 此时 data 已经被 observed 了
        this.fetchData()
    },
    watch: {
        '$route':'fetchData'
    },
    methods: {
        loadData: function () {
            alert('loadData , route = '+this.$route.path)
        },
        fetchData: function () {
            //alert('fetchData')
            console.log( ' fetchData ' );
        },
        deleteData: function () {
            alert('deleteData , route = '+this.$route)
        }
    }
}

var routes = [
    {
        path: '/foo/:id/:name',
        name: 'getFoo',
        component: Foo,
        beforeEnter: function  (to, from, next) {
            //alert('beforeEnter,to='+to+',from='+from+',next='+next)
            next()
            //next(to)    //此写法会进入死循环，不断进入beforeEnter方法
        }
    },
    {
        path: '/bar',
        name: 'getBar',
        alias: '/barAlias',
        component: Bar
    },
    {
        path: '/change',
        redirect: '/bar'
    }
]

var router = new VueRouter({
    routes: routes
});

router.beforeEach( function(to, from, next) {
    //alert('beforeEach ,to='+to+',from='+from+',next='+next)
    next()
})

var app = new Vue({
    el:'#app',
    router:router
})
