var app = new Vue({
    el: '#app',
    data: {
        message: 'Hello World!'
    },
    methods: {
        updateMessage: function () {
            alert(this.message);
            this.message = this.message+",1"
            app_input.message = this.message
        }
    }
})

var app_input = new Vue({
    el: '#app_input',
    data: {
        message: 'Hello Vue.js!'
    }
})

var app_list = new Vue({
    el: '#app_list',
    data: {
        todos: [
            { text: 'Learn JavaScript' },
            { text: 'Learn Vue.js' },
            { text: 'Build Something Awesome' }
        ]
    }
})
var func1 = function(){
    alert("func1")
}
var func2 = function(){
    alert("func2")
}
new Vue({
    el: '#app_text',
    data: {
        message: 'Hello Vue.js!'
    },
    methods: {
        reverseMessage: function () {
            this.message = this.message.split('').reverse().join('');
            this.$http.get('/main/hello').then(function(data){
                alert(data.body)
            },func2);
            /*
            $.get('/main/hello',function(data){
                alert(data);
            })*/
            alert(this.message);
        }
    }
})


// 定义
var MyComponent = Vue.extend({
    template: '<div>A custom component!</div>'
})
// 注册
Vue.component('my-component', MyComponent)
// 创建根实例
new Vue({
    el: '#example'
})

var Home = Vue.extend({
    template: '<div><h1>Home</h1><p>{{msg}}</p></div>',
    data: function() {
        return {
            msg: 'Hello, vue router!'
        }
    }
})

var About = Vue.extend({
    template: '<div><h1>About</h1><p>This is the tutorial about vue-router.</p></div>'
})

var router = new VueRouter();
router.map({
    '/Home': {
        component: Home
    },
    '/About': {
        component: About
    }
});
var frameApp = Vue.extend({});
router.start(frameApp, '#frameapp');

alert("init end 11")

//alert($("#app").html())
