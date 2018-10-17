var login = new Vue({
    el:"#login-body",
    data:{},
    methods:{
        init:function () {
            this.initHeader();
        },
        initHeader : function () {
            var height, largeHeader;
            height = window.innerHeight;
            largeHeader = document.getElementById('large-header');
            largeHeader.style.height = height + 'px';
        }
    },
    mounted:function () {
        this.init();
    }
});