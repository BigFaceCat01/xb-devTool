var login = new Vue({
    el:"#login-body",
    data:{},
    methods:{
        init:function () {
            this.initHeader();
            this.bgSlider();
        },
        initHeader : function () {
            var height, largeHeader;
            height = window.innerHeight;
            largeHeader = document.getElementById('large-header');
            largeHeader.style.height = height + 'px';
        },
        bgSlider:function () {
            var count = 1;
            //图片轮换
            setInterval(function () {
                var imgUrlArray = ["/img/login/bg/1Q01Q34515-1.jpg","/img/login/bg/1Q01Q34515-2.jpg","/img/login/bg/1Q01Q34515-3.jpg"
                ,"/img/login/bg/1Q01Q34515-4.jpg","/img/login/bg/1Q01Q34515-5.jpg","/img/login/bg/1Q01Q34515-6.jpg","/img/login/bg/1Q01Q34515-7.jpg"
                ,"/img/login/bg/1Q01Q34515-8.jpg","/img/login/bg/1Q01Q34515-9.jpg"];
                console.log(1);
                var newUrl = imgUrlArray[count];
                count++;
                if(count >= imgUrlArray.length){
                    count = 0;
                }
                $('.pagewrap').fadeOut(0);
                $('.pagewrap').css("background", "url("+newUrl+")");
                $('.pagewrap').fadeIn(100);
            }, 5000);
        }
    },
    mounted:function () {
        this.init();
    }
});